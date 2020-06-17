package sensemaker.gui.view.simple;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sensemaker.gui.presentation.simple.IPTCPresentation;
import sensemaker.gui.view.AbstractView;

public class IPTCView extends AbstractView<IPTCPresentation>
{
    //____________________
    // FX-VIEW-ELEMENTS :

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField copyrightTextField;
    @FXML
    private TextField keywordsTextField;

    @FXML
    private Button _saveButton;
    @FXML
    private Button _reloadButton;

    //________________
    // PRESENTATION :

    private IPTCPresentation _presentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public IPTCView()
    {
        _presentation = new IPTCPresentation();
    }

    //_______________________
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
    protected void _bind(IPTCPresentation presentation)
    {
        assert _saveButton != null;
        assert _reloadButton != null;
        _bidi(presentation.titleProperty(), titleTextField);
        _bidi(presentation.descriptionProperty(), descriptionTextField);
        _bidi(presentation.copyrightProperty(), copyrightTextField);
        _bidi(presentation.keywordsProperty(), keywordsTextField);
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
    public IPTCPresentation getPresentation()
    {
        return _presentation;
    }


    //______________
    // VIEW-LOGIC :

}
