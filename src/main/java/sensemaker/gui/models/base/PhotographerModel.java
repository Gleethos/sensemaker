package sensemaker.gui.models.base;

import sensemaker.gui.models.AbstractModel;
import java.util.Map;

public class PhotographerModel extends AbstractModel<PhotographerModel> {

    private String _forename;
    private String _surname;

    public PhotographerModel()
    {
        super(null, null, null);
    }

    public PhotographerModel setForename(String forename) {
        _forename = forename;
        return this;
    }

    public String getForename() {
        return _forename;
    }

    public PhotographerModel setSurname(String surname) {
        _surname = surname;
        return this;
    }

    public String getSurname() {
        return _surname;
    }

    // SQL :

    @Override
    public <T> Map<String, T> generatePreparedSQLKeyValues(Class<T> type) {
        Map<String, T> map = _defaultPreparedKeyValues(type);
        if(type.isInstance(_forename)) map.put(getAsTableName()+".forename = ?", (T) _forename);
        if(type.isInstance(_surname)) map.put(getAsTableName()+".surname = ?", (T) _surname);
        return map;
    }

    @Override
    public String getAsTableName() {
        return "photographers";
    }
}
