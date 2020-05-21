package sensemaker.datalayer.db;

import sensemaker.datalayer.API.Access;
import sensemaker.datalayer.API.DAL;
import sensemaker.gui.models.PictureModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLiteDAL extends AbstractDatabaseConnection implements DAL {
    
    private Map<Class, Access> _accessors = new HashMap<>();

    private static SQLiteDAL _instance;

    static{
        //Close DB-Connection upon System-Shutdown!
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            _instance._close();
            _instance = null;
        }));
    }

    public SQLiteDAL(){
        initialize();
    }

    public static SQLiteDAL instance(){
        if(_instance==null) _instance = new SQLiteDAL();
        return _instance;
    }

    @Override
    public void initialize() {
        _construct("jdbc:sqlite:C:/sqlite/db/TempDB", "", "");
        _executeFile("bootstrap.sql");
        _query("SELECT * FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';");
        _accessors.put(
                PictureModel.class,
                new Access<PictureModel>(){
                    @Override public void initialize() {
                        
                    }
                    @Override public List<PictureModel> getAll() {

                        return null;
                    }
                    @Override public List<PictureModel> findBy(PictureModel M) {
                        return null;
                    }
                    @Override public void save(PictureModel M) {

                    }

                    @Override public void delete(PictureModel M) {

                    }
                });
    }

    @Override
    public <T extends Access> T access(Class<T> type) {
        return null;
    }
}
