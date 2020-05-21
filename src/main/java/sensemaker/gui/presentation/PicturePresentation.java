package sensemaker.gui.presentation;

import sensemaker.gui.models.PictureModel;

public class PicturePresentation  extends AbstractPresentation<PictureModel>{

    private PictureModel _model;

    PicturePresentation() { // Constructor receives search query

        _model = new PictureModel();// Constructor receives search query!

    }

    @Override
    public void refresh(PictureModel model) {

    }

    @Override
    public void applyChanges(PictureModel model) {

    }


}
