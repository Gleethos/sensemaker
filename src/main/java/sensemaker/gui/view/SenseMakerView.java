package sensemaker.gui.view;

import sensemaker.gui.presentation.SenseMakerPresentation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class SenseMakerView extends AbstractView<SenseMakerPresentation> implements Initializable
{

    //____________________
    // FX-VIEW-ELEMENTS :

    @FXML
    private PictureInspectionView _pictureInspectionController;
    @FXML
    private SearchView _searchController;
    @FXML
    private PictureListView _pictureListController;

    // Fx-Root
    @FXML
    public BorderPane root;

    //________________
    // PRESENTATION :

    private SenseMakerPresentation _presentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public SenseMakerView()
    {
        _presentation = new SenseMakerPresentation();
    }

    //________________________
    // DEFAULT VIEW METHODS :

    @Override
    protected void _bind(SenseMakerPresentation presentation)
    {
        _presentation = new SenseMakerPresentation();
        assert _pictureInspectionController != null;
        assert _searchController != null;
        assert _pictureListController != null;
        // Wire search and picture list together:
        _searchController.setPictureListPresentation(
                _pictureListController.getPresentation()
        );
    }

    @Override
    protected SenseMakerPresentation getPresentation()
    {
        return _presentation;
    }

    //______________
    // VIEW-LOGIC :

}
