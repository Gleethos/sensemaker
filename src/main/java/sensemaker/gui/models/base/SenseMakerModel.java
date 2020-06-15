package sensemaker.gui.models.base;

import sensemaker.gui.models.AbstractModel;

import java.sql.Date;
import java.util.Map;

public class SenseMakerModel extends AbstractModel<SenseMakerModel>
{
    public SenseMakerModel()
    {
        super(null, null, null);
    }


    @Override
    public String getAsTableName() {
        return null;
    }

    @Override
    public <T> Map<String, T> generatePreparedSQLKeyValues(Class<T> type) {
        return null;
    }
}
