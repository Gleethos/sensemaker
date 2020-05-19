package datalayer.db;

public class SQLiteBase extends AbstractDatabaseConnection
{

    private static SQLiteBase _instance;

    static{
        //Close DB-Connection upon System-Shutdown!
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            _instance._close();
            _instance = null;
        }));
    }

    private SQLiteBase(){
        super("jdbc:sqlite:C:/sqlite/db/TempDB", "", "");
    }

    public static SQLiteBase instance(){
        if(_instance==null) _instance = new SQLiteBase();
        return _instance;
    }

}



