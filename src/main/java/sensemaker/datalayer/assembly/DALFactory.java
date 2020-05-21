package sensemaker.datalayer.assembly;

import sensemaker.datalayer.API.DAL;
import sensemaker.datalayer.db.SQLiteDAL;
import sensemaker.datalayer.mock.MockDAL;

import java.util.logging.Logger;

public class DALFactory
{
    private static Logger _log = Logger.getLogger(DALFactory.class.getName());
    private boolean _doMocking = false;

    private Class _dalclass;

    public DALFactory() {
        _dalclass = SQLiteDAL.class;//Setting default DAL class
    }

    public DAL produce()
    {
        String dalname = (_doMocking)? MockDAL.class.getName() :_dalclass.getName();
        try {
            return (DAL)Class.forName(dalname).newInstance();
        } catch (InstantiationException e) {
            _log.warning(e.getMessage());
        } catch (IllegalAccessException e) {
            _log.warning(e.getMessage());
        } catch (ClassNotFoundException e) {
            _log.warning(e.getMessage());
        }
        return null;
    }

    public DALFactory setDoMocking(boolean doMocking){
        _doMocking = doMocking;
        return this;
    }


}
