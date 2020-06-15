package sensemaker.gui.models;

import java.util.List;
import java.util.Map;

public interface Model {

    public abstract <T> Map<String, T> generatePreparedSQLKeyValues(Class<T> type);

    public abstract <T> Map<String, T> defaultInsertKeyValues(Class<T> type);

    public abstract <T> Map<String, T> generateSoftPreparedSQLKeyValues(Class<T> type);

}
