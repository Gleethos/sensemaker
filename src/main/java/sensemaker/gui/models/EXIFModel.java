package sensemaker.gui.models;

import java.sql.Date;

public class EXIFModel extends AbstractModel{

    private Date _shot;
    private String _orientation;

    public EXIFModel()
    {
        super(null, new Date(System.currentTimeMillis()), null);
    }

    public Date getShot() {
        return _shot;
    }

    public EXIFModel setShot(Date date){
        _shot = date;
        return this;
    }

    public String getOrientation() {
        return _orientation;
    }

    public EXIFModel setOrientation(String orientation) {
        this._orientation = orientation;
        return this;
    }

}
