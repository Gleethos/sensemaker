package sensemaker.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sensemaker.gui.presentation.PhotographerPresentation;

public class PhotographerView extends AbstractView<PhotographerPresentation>
{
    //____________________
    // FX-VIEW-ELEMENTS :

    @FXML
    private TextField forenameTextField;
    @FXML
    private TextField surnameTextField;

    //________________
    // PRESENTATION :

    private PhotographerPresentation _presentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public PhotographerView()
    {
        _presentation = new PhotographerPresentation();
    }

    //________________________
    // DEFAULT VIEW METHODS :

    @Override
    protected void _bind(PhotographerPresentation presentation)
    {
        _bidi(presentation.forenameProperty(), forenameTextField);
        _bidi(presentation.surnameProperty(), surnameTextField);
    }

    @Override
    protected PhotographerPresentation getPresentation() {
        return _presentation;
    }

    //______________
    // VIEW-LOGIC :

}
