package sensemaker.gui.view;

import javafx.scene.layout.AnchorPane;
import sensemaker.gui.presentation.SenseMakerPresentation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SenseMakerView extends AbstractView<SenseMakerPresentation> implements Initializable {

    @FXML
    private PictureInspectionView _pictureInspectionController;

    @FXML
    private SearchView _searchController;

    @FXML
    public BorderPane root;

    private SenseMakerPresentation _presentation;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        _presentation = new SenseMakerPresentation();
        assert _pictureInspectionController != null;
        assert _searchController !=null;
    }

    @Override
    protected void _bind(SenseMakerPresentation presentation) {

    }

    @Override
    protected SenseMakerPresentation getPresentation() {
        return null;
    }
}
