package sensemaker.gui.models;

import java.sql.Date;

public abstract class AbstractModel
{
    private Integer _id = null;
    private Date _created = null;
    private Date _deleted = null;

    public Integer id(){
        return _id;
    }
    public void setId(Integer id){
        _id = id;
    }

    public Date created(){
        return _created;
    }
    public void setCreated(Date created){
        _created = created;
    }

    public Date deleted(){
        return  _deleted;
    }
    public void setDeleted(Date deleted){
        _deleted = deleted;
    }

}
