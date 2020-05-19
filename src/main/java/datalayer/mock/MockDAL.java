package datalayer.mock;

import datalayer.Access;
import datalayer.DAL;

public class MockDAL implements DAL {
    @Override
    public void initialize() {

    }

    @Override
    public <T extends Access> T access(Class<T> type) {
        return null;
    }
}
