package ui.control;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextFormatter;

public class CPDecimalField extends CPTextField {
    public final static String STYLE_CLASS = "cp-decimal-field";

    private DecimalStringFilteredConverter converter = new DecimalStringFilteredConverter();
    private final TextFormatter<Number> formatter = new TextFormatter<>(converter,
            0, converter.getFilter());

    private DoubleProperty value;

    public CPDecimalField() {
        this(0.00);
    }

    public CPDecimalField(Double value) {
        super();

        this.value = new SimpleDoubleProperty(value);

        setTextFormatter(formatter);
        textProperty().bindBidirectional(this.value, converter);

        getStyleClass().add(STYLE_CLASS);
    }

    public double getValue() {
        return value.get();
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }
}
