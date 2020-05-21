package sensemaker.gui.presentation;

import sensemaker.gui.models.SenseMakerModel;

public class SenseMakerPresentation extends  AbstractPresentation<SenseMakerModel> {

    private static SenseMakerPresentation _instance = new SenseMakerPresentation();

    private SenseMakerModel _model;

    // Children:

    private SearchPresentation _search;


    private SenseMakerPresentation(){
        _model = SenseMakerModel.instance();
    }

    public static SenseMakerPresentation instance(){
        return _instance;
    }

    @Override
    public void refresh(SenseMakerModel model) {

    }

    @Override
    public void applyChanges(SenseMakerModel model) {

    }
}
