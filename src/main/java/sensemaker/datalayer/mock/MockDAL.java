package sensemaker.datalayer.mock;

import sensemaker.datalayer.API.Access;
import sensemaker.datalayer.API.DAL;

public class MockDAL implements DAL {
    @Override
    public void initialize() {

    }

    @Override
    public <T extends Access> T access(Class<T> type) {
        return null;
    }
}
