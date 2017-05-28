package ui.control;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;

public class CPButton extends Button {
    public final static String STYLE_CLASS = "cp-button";
    public final static String DECORATION_REGION_STYLE_CLASS = "cp-decoration-region";

    public BooleanProperty loading;

    public CPButton() {
        this(true);
    }

    public CPButton(Boolean loading) {
        super();

        this.loading = new SimpleBooleanProperty(loading);

        ProgressIndicator progress = new ProgressIndicator(-1);
        progress.prefHeightProperty().bind(heightProperty());
        progress.prefWidthProperty().bind(progress.prefHeightProperty());

        loadingProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue) {
               setGraphic(progress);
               setDisable(true);
           } else {
               setGraphic(null);
               setDisable(false);
           }
        });

        progress.getStyleClass().add(DECORATION_REGION_STYLE_CLASS);
        getStyleClass().add(STYLE_CLASS);
    }

    public boolean isLoading() {
        return loading.get();
    }

    public BooleanProperty loadingProperty() {
        return loading;
    }
}
