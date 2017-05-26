package ui.control;


import javafx.scene.control.TextFormatter;

public class CPDecimalField extends CPTextField {
    public final static String STYLE_CLASS = "cp-decimal-field";

    private DecimalStringFilteredConverter converter = new DecimalStringFilteredConverter();
    private final TextFormatter<Number> formatter = new TextFormatter<>(converter,
            0, converter.getFilter());

    public CPDecimalField() {
        super();

        setTextFormatter(formatter);
        getStyleClass().add(STYLE_CLASS);
    }
}
