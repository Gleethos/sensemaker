package sensemaker.gui.models;

import java.sql.Date;

public class IPTCModel extends  AbstractModel{

    private int _id;
    private String _title;
    private String _description;
    private String _copyright;
    private String _keywords;
    private Date _created;
    private Date _deleted;

    IPTCModel(int id, String title, String description, String copyright, String keyword, Date created, Date deleted)
    {
        _id=id;
        _title=title;
        _description=description;
        _copyright=copyright;
        _keywords=keyword;
        _created=created;
        _deleted=deleted;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public String getCopyright() {
        return _copyright;
    }

    public void setCopyright(String copyright) {
        this._copyright = copyright;
    }

    public String getKeywords() {
        return _keywords;
    }

    public void setKeywords(String keywords) {
        this._keywords = keywords;
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
