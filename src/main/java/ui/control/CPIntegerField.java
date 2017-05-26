package ui.control;


import javafx.scene.control.TextFormatter;

public class CPIntegerField extends CPTextField {
    public final static String STYLE_CLASS = "cp-number-field";

    private IntegerStringFilteredConverter converter = new IntegerStringFilteredConverter();
    private final TextFormatter<Number> formatter = new TextFormatter<>(converter,
            0, converter.getFilter());

    public CPIntegerField() {
        super();

        setTextFormatter(formatter);
        getStyleClass().add(STYLE_CLASS);
    }
}
