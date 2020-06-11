package sensemaker.gui.models;

import java.sql.Date;

/**
 *
 */
public class PictureModel extends AbstractModel<PictureModel>
{

    private String _path;
    private int _EXIF_id;
    private int _IPTC_id;
    private int _photographer_id;

    public PictureModel()
    {
        super(null, new Date(System.currentTimeMillis()), null);
    }

    public String getPath() {
        return _path;
    }

    public PictureModel setPath(String _path) {
        this._path = _path;
        return this;
    }

    public int getEXIFId() {
        return _EXIF_id;
    }

    public PictureModel setEXIFId(int EXIFId) {
        this._EXIF_id = EXIFId;
        return this;
    }

    public int getIPTCId() {
        return _IPTC_id;
    }

    public PictureModel setIPTCId(int IPTCId) {
        this._IPTC_id = _IPTC_id;
        return this;
    }

    public int getPhotographerId() {
        return _photographer_id;
    }

    public PictureModel setPhotographerId(int photographerId) {
        this._photographer_id = photographerId;
        return this;
    }


}
