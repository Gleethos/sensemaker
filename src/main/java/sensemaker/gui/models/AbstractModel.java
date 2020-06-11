package sensemaker.gui.models;

import java.sql.Date;

public abstract class AbstractModel<FinalType>
{
    private Integer _id = null;
    private Date _created = null;
    private Date _deleted = null;

    AbstractModel(Integer id, Date created, Date deleted) {
        _id = id;
        _created = created;
        _deleted = deleted;
    }

    public Integer getId(){
        return _id;
    }
    public FinalType setId(Integer id){
        _id = id;
        return (FinalType) this;
    }

    public Date getCreated(){
        return _created;
    }
    public FinalType setCreated(Date created){
        _created = created;
        return (FinalType)this;
    }

    public Date getDeleted(){
        return  _deleted;
    }
    public FinalType setDeleted(Date deleted){
        _deleted = deleted;
        return (FinalType) this;
    }

}
