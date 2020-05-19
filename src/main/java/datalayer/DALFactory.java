package datalayer;

import datalayer.db.SQLiteDAL;
import datalayer.mock.MockDAL;

import java.util.logging.Logger;

public class DALFactory
{
    private static Logger _log = Logger.getLogger(DALFactory.class.getName());
    private boolean _doMocking = false;

    private Class _dalclass;

    public DALFactory(){
        //Setting default DAL class
        _dalclass = SQLiteDAL.class;
    }

    public DAL produce(){
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

    public void setDoMocking(boolean doMocking){
        _doMocking = doMocking;
    }


}
