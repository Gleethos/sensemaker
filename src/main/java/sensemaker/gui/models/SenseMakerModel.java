package sensemaker.gui.models;

import java.sql.Date;

public class SenseMakerModel extends AbstractModel<SenseMakerModel>
{
    private static SenseMakerModel _instance = new SenseMakerModel();

    private SenseMakerModel()
    {
        super(null, new Date(System.currentTimeMillis()), null);
    }

    public static SenseMakerModel instance() {
        return _instance;
    }

}
