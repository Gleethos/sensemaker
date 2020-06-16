package sensemaker.gui.view.simple;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sensemaker.gui.presentation.simple.PhotographerPresentation;
import sensemaker.gui.view.AbstractView;

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
