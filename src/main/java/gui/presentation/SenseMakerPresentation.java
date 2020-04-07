package gui.presentation;

import gui.models.SenseMakerModel;

public class SenseMakerPresentation extends  AbstractPresentation {

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

}
