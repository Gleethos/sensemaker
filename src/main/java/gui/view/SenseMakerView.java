package gui.view;

import gui.presentation.SenseMakerPresentation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SenseMakerView implements Initializable {

    @FXML
    public BorderPane root;

    private SenseMakerPresentation _presentation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        _presentation = SenseMakerPresentation.instance();

    }

}
