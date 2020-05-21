package sensemaker.gui.models;

import sensemaker.gui.IOFrame;

public class SenseMakerModel
{
    private static SenseMakerModel _instance = new SenseMakerModel();

    private SenseMakerModel()
    {

    }

    public static SenseMakerModel instance() {
        return _instance;
    }

}
