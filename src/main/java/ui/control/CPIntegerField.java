package ui.control;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextFormatter;

public class CPIntegerField extends CPTextField {
    public final static String STYLE_CLASS = "cp-number-field";

    private IntegerStringFilteredConverter converter = new IntegerStringFilteredConverter();
    private final TextFormatter<Number> formatter = new TextFormatter<>(converter,
            0, converter.getFilter());

    public IntegerProperty value;

    public CPIntegerField() {
        this(0);
    }

    public CPIntegerField(Integer value) {
        super();

        this.value = new SimpleIntegerProperty(value);

        setTextFormatter(formatter);
        textProperty().bindBidirectional(this.value, converter);

        getStyleClass().add(STYLE_CLASS);
    }


    public int getInteger() {
        return value.get();
    }

    public IntegerProperty integerProperty() {
        return value;
    }

    public void setInteger(int value) {
        this.value.set(value);
    }
}
