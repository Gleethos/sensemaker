package sensemaker.gui.models.simple;

import sensemaker.gui.models.AbstractModel;

import java.util.Map;

/**
 *
 */
public class PictureModel extends AbstractModel<PictureModel>
{
    private String _path;
    private Integer _EXIF_id;
    private Integer _IPTC_id;
    private Integer _photographer_id;

    public PictureModel()
    {
        super(null, null, null);
    }

    public String getPath() {
        return _path;
    }

    public PictureModel setPath(String path) {
        this._path = path;
        return this;
    }

    public Integer getEXIFId() {
        return _EXIF_id;
    }

    public PictureModel setEXIFId(Integer EXIFId) {
        this._EXIF_id = EXIFId;
        return this;
    }

    public Integer getIPTCId() {
        return _IPTC_id;
    }

    public PictureModel setIPTCId(Integer IPTCId) {
        this._IPTC_id = IPTCId;
        return this;
    }

    public Integer getPhotographerId() {
        return _photographer_id;
    }

    public PictureModel setPhotographerId(int photographerId) {
        this._photographer_id = photographerId;
        return this;
    }

    // SQL :

    @Override
    public <T> Map<String, T> generatePreparedSQLKeyValues(Class<T> type) {
        Map<String, T> map = _defaultPreparedKeyValues(type);
        if(type.isInstance(_path)) map.put(getAsTableName()+".path = ?", (T)_path);
        if(type.isInstance(_EXIF_id)) map.put(getAsTableName()+".EXIF_id = ?",(T) _EXIF_id);
        if(type.isInstance(_IPTC_id)) map.put(getAsTableName()+".IPTC_id = ?",(T) _IPTC_id);
        if(type.isInstance(_photographer_id)) map.put(getAsTableName()+".photographer_id = ?",(T)_photographer_id);
        return map;
    }


    @Override
    public String getAsTableName() {
        return "pictures";
    }
}
