package sample;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.Person;
import presentationmodels.PersonPresentationModel;
import utils.Binding;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public BorderPane root;

    private Person model;
    private PersonPresentationModel presentationModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new Person();
        presentationModel = new PersonPresentationModel(model);
        Binding.applyBinding(root, presentationModel);
    }
}
