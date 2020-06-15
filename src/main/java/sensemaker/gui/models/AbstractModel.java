package sensemaker.gui.models;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractModel<FinalType> extends AbstractSearchableModel<FinalType>
{
    private Integer _id = null;
    private Date _created = null;
    private Date _deleted = null;

    public AbstractModel(Integer id, Date created, Date deleted)
    {
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

    // SQL:

    protected <T> Map<String,T> _defaultPreparedKeyValues(Class<T> type) {
        Map<String, T> map = new HashMap<>();
        if(type.isInstance(_id)) map.put(getAsTableName()+".id = ?",(T) _id);
        if(type.isInstance(_created)) map.put("DATE("+getAsTableName()+".created) = ?",(T)_created);
        if(type.isInstance(_deleted)) map.put("DATE("+getAsTableName()+".deleted) = ?",(T)_deleted);
        return map;
    }

    public abstract String getAsTableName();

    @Override
    public <T> Map<String, T> defaultInsertKeyValues(Class<T> type) {
        Map<String, T> map = generatePreparedSQLKeyValues(type);
        return map.entrySet().stream().map(
                    e -> new AbstractMap.SimpleEntry<>(
                        e.getKey().trim()
                                .replace(getAsTableName()+".", "")
                                .replace(" = ?","")
                                .replace("DATE(","").replace(")",""),
                            (T)e.getValue().toString()
                    )
                ).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    @Override
    public <T> Map<String, T> generateSoftPreparedSQLKeyValues(Class<T> type){
        Map<String, T> map = generatePreparedSQLKeyValues(type);
        return map.entrySet().stream().map(
                e ->
                    (e.getValue() instanceof String) ?
                    new AbstractMap.SimpleEntry<>(
                        e.getKey().trim().replace(" = ?"," LIKE ?"),
                        (T)("%"+((String)e.getValue())+"%")
                    )
                            :
                    (e.getValue() instanceof Date) ?
                            new AbstractMap.SimpleEntry<>(
                                    (!e.getKey().contains("DATE"))?"DATE("+e.getKey().trim().replace(" = ?","")+") = ?":e.getKey(),
                                    (T)(((Date)e.getValue()).toString())
                            )
                            :
                            e
        ).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
        ));
    }

}
