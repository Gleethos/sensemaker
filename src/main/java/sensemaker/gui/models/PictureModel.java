package sensemaker.gui.models;

import java.sql.Date;

/**
 *
 */
public class PictureModel extends AbstractModel{

    private int _id;
    private String _path;
    private Date _created;
    private Date _deleted;
    private int _EXIF_id;
    private int _IPTC_id;
    private int _photographer_id;

    public PictureModel() {
        _id = -1;
        _path = "";
        _created = null;
        _deleted = null;
        _EXIF_id = -1;
        _IPTC_id = -1;
        _photographer_id = -1;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getPath() {
        return _path;
    }

    public void setPath(String _path) {
        this._path = _path;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date _created) {
        this._created = _created;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date _deleted) {
        this._deleted = _deleted;
    }

    public int getEXIFId() {
        return _EXIF_id;
    }

    public void setEXIFId(int EXIFId) {
        this._EXIF_id = EXIFId;
    }

    public int getIPTCId() {
        return _IPTC_id;
    }

    public void setIPTCId(int IPTCId) {
        this._IPTC_id = _IPTC_id;
    }

    public int getPhotographerId() {
        return _photographer_id;
    }

    public void setPhotographerId(int photographerId) {
        this._photographer_id = photographerId;
    }


}
