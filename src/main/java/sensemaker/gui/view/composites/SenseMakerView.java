package sensemaker.gui.view.composites;

import sensemaker.gui.presentation.composites.SenseMakerPresentation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import sensemaker.gui.view.AbstractView;
import sensemaker.gui.view.lists.PictureListView;

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

    /**
     * This method is used used for binding and initializing the view!
     * It is called by the 'initialize' method in the super class which is itself
     * called by the FXML-Loader after it instantiated this very view!
     *
     * Said 'initialize' method in the super class call an
     * expected implementation of a getter method
     * returning the corresponding presentation instance!
     * This instance must set in the constructor of this class! (see above)
     *
     * @param presentation The corresponding presentation type to this view type!
     */
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
