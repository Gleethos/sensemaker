package sensemaker.gui.models;

import java.sql.Date;

public class IPTCModel extends  AbstractModel<IPTCModel>{

    private String _title;
    private String _description;
    private String _copyright;
    private String _keywords;

    public IPTCModel()
    {
        super(null, new Date(System.currentTimeMillis()), null);
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

}
