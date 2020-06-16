package sensemaker.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sensemaker.gui.presentation.IPTCPresentation;

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

    @Override
    protected void _bind(IPTCPresentation presentation)
    {
        _bidi(presentation.titleProperty(), titleTextField);
        _bidi(presentation.descriptionProperty(), descriptionTextField);
        _bidi(presentation.copyrightProperty(), copyrightTextField);
        _bidi(presentation.keywordsProperty(), keywordsTextField);
    }

    @Override
    protected IPTCPresentation getPresentation()
    {
        return _presentation;
    }


    //______________
    // VIEW-LOGIC :

}
