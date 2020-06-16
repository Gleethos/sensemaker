package sensemaker.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sensemaker.gui.presentation.EXIFPresentation;

public class EXIFView extends AbstractView<EXIFPresentation>
{
    //____________________
    // FX-VIEW-ELEMENTS :

    @FXML
    private TextField shotTextField;
    @FXML
    private TextField orientationTextField;

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

    @Override
    protected void _bind(EXIFPresentation presentation)
    {
        _bidi(presentation.shotProperty(), shotTextField);
        _bidi(presentation.orientationProperty(), orientationTextField);
    }

    @Override
    protected EXIFPresentation getPresentation()
    {
        return _presentation;
    }

    //______________
    // VIEW LOGIC :

}
