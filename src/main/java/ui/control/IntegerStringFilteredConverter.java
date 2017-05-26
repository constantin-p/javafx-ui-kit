package ui.control;


import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

public class IntegerStringFilteredConverter extends NumberStringConverter {

    public IntegerStringFilteredConverter() {
        super(NumberFormat.getIntegerInstance());
    }

    public UnaryOperator<TextFormatter.Change> getFilter() {
        return change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = getNumberFormat().parse( newText, parsePosition );
            if ( object == null || parsePosition.getIndex() < newText.length()) {
                return null;
            } else {
                return change;
            }
        };
    }
}
