package ui;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import ui.control.CPButton;

public class Controller {

    @FXML
    private CheckBox loadingCheckBox;

    @FXML
    private CPButton loadingCPButton;

    @FXML
    public void initialize() {
        loadingCPButton.loadingProperty().bind(loadingCheckBox.selectedProperty());
    }
}
