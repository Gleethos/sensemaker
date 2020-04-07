package gui.models;

import gui.IOFrame;
import plugins.GameManager;
import plugins.interfaces.IGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SenseMakerModel
{

    private static SenseMakerModel _instance = new SenseMakerModel();

    private SenseMakerModel()
    {

    }

    public static SenseMakerModel instance(){
        return _instance;
    }


}
