package sensemaker.gui.models;

import java.sql.Date;

public class EXIFModel extends  AbstractModel{

    private int _id;
    private Date _shot;
    private String _orientation;
    private Date _created;
    private Date _deleted;

    EXIFModel(int id, Date shot, String orientation, Date created, Date deleted)
    {
        _id=id;
        _shot=shot;
        _orientation=orientation;
        _created=created;
        _deleted=deleted;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public Date getShot() {
        return _shot;
    }

    public String getOrientation() {
        return _orientation;
    }

    public void setOrientation(String orientation) {
        this._orientation = orientation;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        this._created = created;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        this._deleted = deleted;
    }
}
