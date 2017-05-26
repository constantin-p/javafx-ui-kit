package ui.control;


import javafx.scene.Cursor;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class CPSearchField extends CPTextField {
    public final static String STYLE_CLASS = "cp-search-field";
    public final static String SEARCH_DECORATION_STYLE_CLASS = "cp-search-decoration";
    public final static String DECORATION_REGION_STYLE_CLASS = "cp-decoration-region";

    public CPSearchField() {
        super();

        setupSearchDecoration();
        getStyleClass().add(STYLE_CLASS);
    }

    public void setupSearchDecoration() {
        Region searchDecoration = new Region();
        searchDecoration.getStyleClass().add(DECORATION_REGION_STYLE_CLASS);

        StackPane searchDecorationStackPane = new StackPane(searchDecoration);
        searchDecorationStackPane.getStyleClass().addAll(SEARCH_DECORATION_STYLE_CLASS);
        searchDecorationStackPane.setCursor(Cursor.DEFAULT);
        leftDecoration.set(searchDecorationStackPane);
    }
}
