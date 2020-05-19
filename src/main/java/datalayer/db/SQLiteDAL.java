package datalayer.db;

import datalayer.Access;
import datalayer.DAL;

public class SQLiteDAL implements DAL {
    @Override
    public void initialize() {

    }

    @Override
    public <T extends Access> T access(Class<T> type) {
        return null;
    }
}
