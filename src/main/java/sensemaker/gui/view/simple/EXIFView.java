package sensemaker.gui.view.simple;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sensemaker.gui.presentation.simple.EXIFPresentation;
import sensemaker.gui.view.AbstractView;

public class EXIFView extends AbstractView<EXIFPresentation>
{
    //____________________
    // FX-VIEW-ELEMENTS :

    @FXML
    private TextField shotTextField;
    @FXML
    private TextField orientationTextField;

    @FXML
    private Button _saveButton;
    @FXML
    private Button _reloadButton;

    //________________
    // PRESENTATION :

    private EXIFPresentation _presentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public EXIFView()
    {
        _presentation = new EXIFPresentation();
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
    protected void _bind(EXIFPresentation presentation)
    {
        assert _saveButton != null;
        assert _reloadButton != null;
        _bidi(presentation.shotProperty(), shotTextField);
        _bidi(presentation.orientationProperty(), orientationTextField);
        _saveButton.setOnAction(event -> {
            _presentation.applyIntoModel();
            _presentation.persist();
        });
        _reloadButton.setOnAction(event -> {
            _presentation.restore();
            _presentation.applyFromModel();
        });
    }

    @Override
    public EXIFPresentation getPresentation()
    {
        return _presentation;
    }

    //______________
    // VIEW LOGIC :

}
