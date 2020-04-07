package db;

public class SQLiteSingleton extends AbstractDatabaseConnection
{

    private static SQLiteSingleton _instance;

    static{
        //Close DB-Connection upon System-Shutdown!
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            _instance._close();
            _instance = null;
        }));
    }

    private SQLiteSingleton(){
        super("jdbc:sqlite:C:/sqlite/db/TempDB", "", "");
    }

    public static SQLiteSingleton instance(){
        if(_instance==null) _instance = new SQLiteSingleton();
        return _instance;
    }

}



