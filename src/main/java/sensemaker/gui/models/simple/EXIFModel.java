package sensemaker.gui.models.simple;

import sensemaker.gui.models.AbstractModel;

import java.sql.Date;
import java.util.Map;

public class EXIFModel extends AbstractModel<EXIFModel> {

    private Date _shot;
    private String _orientation;

    public EXIFModel()
    {
        super(null, null, null);
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
        _orientation = orientation;
        return this;
    }

    // SQL :

    @Override
    public <T> Map<String,T> generatePreparedSQLKeyValues(Class<T> type) {
        Map<String,T> list = _defaultPreparedKeyValues(type);
        if(type.isInstance(_orientation)) list.put(getAsTableName()+".orientation = ?",(T) _orientation);
        if(type.isInstance(_shot)) list.put(getAsTableName()+".shot = ?",(T)_shot);
        return list;
    }

    @Override
    public String getAsTableName(){
        return "EXIFs";
    }

}
