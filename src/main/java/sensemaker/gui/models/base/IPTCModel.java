package sensemaker.gui.models.base;

import sensemaker.gui.models.AbstractModel;

import java.sql.Date;
import java.util.Map;

public class IPTCModel extends AbstractModel<IPTCModel> {

    private String _title;
    private String _description;
    private String _copyright;
    private String _keywords;

    public IPTCModel()
    {
        super(null, null, null);
    }

    public String getTitle() {
        return _title;
    }

    public IPTCModel setTitle(String title) {
        this._title = title;
        return this;
    }

    public String getDescription() {
        return _description;
    }

    public IPTCModel setDescription(String description) {
        this._description = description;
        return this;
    }

    public String getCopyright() {
        return _copyright;
    }

    public IPTCModel setCopyright(String copyright) {
        this._copyright = copyright;
        return this;
    }

    public String getKeywords() {
        return _keywords;
    }

    public IPTCModel setKeywords(String keywords) {
        this._keywords = keywords;
        return this;
    }

    // SQL :

    @Override
    public <T> Map<String,T> generatePreparedSQLKeyValues(Class<T> type) {
        Map<String,T> map = _defaultPreparedKeyValues(type);
        if(type.isInstance(_copyright)) map.put(getAsTableName()+".copyright = ?",(T) _copyright);
        if(type.isInstance(_description)) map.put(getAsTableName()+".description = ?",(T) _description);
        if(type.isInstance(_keywords)) map.put(getAsTableName()+".keywords = ?",(T)_keywords);
        if(type.isInstance(_title)) map.put(getAsTableName()+".title = ?",(T)_title);
        return map;
    }

    @Override
    public String getAsTableName() {
        return "IPTCs";
    }
}
