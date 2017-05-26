package ui.control;


import javafx.scene.Cursor;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class CPTextField extends CPDecoratedTextField {
    public final static String STYLE_CLASS = "cp-text-field";
    public final static String CLEAR_DECORATION_STYLE_CLASS = "cp-clear-decoration";
    public final static String DECORATION_REGION_STYLE_CLASS = "cp-decoration-region";

    public CPTextField() {
        super();

        setupClearContentDecoration();
        getStyleClass().add(STYLE_CLASS);
    }

    public void setupClearContentDecoration() {
        Region clearDecoration = new Region();
        clearDecoration.getStyleClass().add(DECORATION_REGION_STYLE_CLASS);

        StackPane clearDecorationStackPane = new StackPane(clearDecoration);
        clearDecorationStackPane.getStyleClass().addAll(CLEAR_DECORATION_STYLE_CLASS);
        clearDecorationStackPane.setCursor(Cursor.DEFAULT);
        clearDecorationStackPane.setVisible(false);
        rightDecoration.set(clearDecorationStackPane);

        clearDecoration.setOnMouseReleased(e -> this.clear());

        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && !clearDecorationStackPane.isVisible()) {
                clearDecorationStackPane.setVisible(true);
            } else if ((newValue.isEmpty() || newValue == null) && clearDecorationStackPane.isVisible()) {
                clearDecorationStackPane.setVisible(false);
            }
        });
    }
}
