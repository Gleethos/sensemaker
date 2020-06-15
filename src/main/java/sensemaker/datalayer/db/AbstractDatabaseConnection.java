package sensemaker.datalayer.db;

import sensemaker.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

public abstract class AbstractDatabaseConnection
{

    protected static Logger _log = Logger.getLogger(AbstractDatabaseConnection.class.getName());

    /**
     * Connection settings: URL, User, Password!
     */
    private String _url, _user, _pwd;

    private Connection _connection;

    private static Map<String, Connection> _$cache = new HashMap<>();

    protected void _construct(String url, String name, String password)
    {
        Connection conn = _$cache.get(url+"|"+name+"|"+password);
        _connection = conn;
        if(conn!=null) return;

        _url = url;
        _user = name;
        _pwd = password;

        if(_user.equals("")||_pwd.equals("")){
            try {
                conn = DriverManager.getConnection(_url);
            } catch (SQLException e) {
                _log.warning(e.getMessage());
            }
        } else {
            try {
                conn = DriverManager.getConnection(_url, _user, _pwd);
            } catch (SQLException e) {
                _log.warning(e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        _$cache.put(url+"|"+name+"|"+password, conn);
        _connection = conn;
    }
    
    /**
     * Closing Connection!
     */
    protected void _close(){
        try {
            _connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints all tables of a connection!
     */
    protected String[] _listOfAllTables(){
        String sql = "SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';";
        List<String> names = new ArrayList<>();
        _for(sql, null, rs -> {
            try {
                names.add(rs.getString("name"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return names.toArray(new String[0]);
    }

    public Map<String, String[]> tablesSpace(){
        String sql = "SELECT * FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';";
        Map<String, String[]> space = new HashMap<>();
        _for(sql, null, rs -> {
            try {
                String def = rs.getString("sql");
                def = def.split("\\(")[1];
                def = def.split("\\)")[0];
                String[] payload = def.split(",");
                for(int i=0; i<payload.length; i++) payload[i] = payload[i].trim();
                space.put(rs.getString("name"), payload);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        return space;
    }

    protected void _for(String sql, Consumer<ResultSet> start, Consumer<ResultSet> each){
        _for(sql, null, start, each);
    }

    protected void _for(String sql, List<Object> values, Consumer<ResultSet> start, Consumer<ResultSet> each){
        if (values!=null && !values.isEmpty()){
            try {
                PreparedStatement pstmt = _newPreparedStatement(sql, values);
                try {
                    ResultSet rs = pstmt.executeQuery();// loop through the result set
                    if(start!=null) start.accept(rs);
                    if(each!=null) do each.accept(rs); while(rs.next());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                _log.warning(e.getMessage());
            }
        } else {
            try {
                Statement stmt = _connection.createStatement();
                try {
                    ResultSet rs = stmt.executeQuery(sql);// loop through the result set
                    if(start!=null) start.accept(rs);
                    if(each!=null) do each.accept(rs); while(rs.next());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                _log.warning(e.getMessage());
            }
        }


    }

    protected Map<String, List<Object>> _query(String sql) {
        return _query(sql, null);
    }

    protected Map<String, List<Object>> _query(String sql, List<Object> values){
        System.out.println(sql);
        Map<String, List<Object>> result = new HashMap<>();
        _for(
                sql, values, // <=- Are used to build prepared statement when 'values' is not null!
                rs -> {
                    try {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();
                        for (int i = 1; i <= columnsNumber; i++) {
                            result.put(rsmd.getColumnName(i), new ArrayList<>());
                        }
                    } catch (Exception e){}
                },
                rs -> {
                    try {// loop through the result set
                        while (rs.next()) {
                            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                                String columnValue = rs.getString(i);
                                ResultSetMetaData rsmd = rs.getMetaData();
                                String column_name = rsmd.getColumnName(i);
                                if(rsmd.getColumnType(i)==java.sql.Types.ARRAY) {
                                    result.get(column_name).add(rs.getArray(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT) {
                                    result.get(column_name).add(rs.getInt(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN) {
                                    result.get(column_name).add(rs.getBoolean(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.BLOB) {
                                    result.get(column_name).add(rs.getBlob(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE) {
                                    result.get(column_name).add(rs.getDouble(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT) {
                                    result.get(column_name).add(rs.getFloat(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER) {
                                    result.get(column_name).add(rs.getInt(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR) {
                                    result.get(column_name).add(rs.getNString(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR) {
                                    result.get(column_name).add(rs.getString(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT) {
                                    result.get(column_name).add(rs.getInt(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT) {
                                    result.get(column_name).add(rs.getInt(column_name));
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.DATE) {
                                    String date = rs.getString(column_name);
                                    result.get(column_name).add((date==null)?null:Date.valueOf(date));
                                    //result.get(column_name).add(rs.getDate(column_name));
                                    //rs.getTimestamp(column_name);
                                }
                                else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
                                    result.get(column_name).add(rs.getTimestamp(column_name));
                                } else {
                                    result.get(column_name).add(rs.getObject(column_name));
                                }
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
        return result;
    }

    public int _lastInsertID(){
        return (Integer)_query("SELECT last_insert_rowid()").get("last_insert_rowid()").get(0);
    }

    /**
     * Defines and executes a SELECT * FROM on a connection.
     * @param tableName
     */
    protected void _selectAllFrom(String tableName){
        String sql = "SELECT * FROM "+tableName+";\n";
        _for(
                sql,
                rs -> {
                    try {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();
                        for (int i = 1; i <= columnsNumber; i++) {
                            System.out.print("| " + rsmd.getColumnName(i) + " |");
                        }
                    } catch (Exception e){}
                },
                rs -> {
                    try {// loop through the result set
                        while (rs.next()) {
                            System.out.print("\n");
                            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                                String columnValue = rs.getString(i);
                                System.out.print("| " + columnValue + " |");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }


    /**
     * SQL execution on connection!
     * @param sql
     */
    protected void _execute(String sql){
        Connection conn = _connection;
        try {
            Statement stmt = conn.createStatement();
            try {
                stmt.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void _executeFile(String name){
        Connection conn = _connection;
        String[] commands;
        String query = Environment.instance().readResource("sql/"+name);
        commands = query.split("--<#SPLIT#>--");
        for(String command : commands) _execute(command);
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static byte[] _readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];
        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }
        return fileData;
    }

    private PreparedStatement _newPreparedStatement(String sql, List<Object> values) throws SQLException {
        PreparedStatement pstmt = _connection.prepareStatement(sql);
        if(values!=null) {
            for(int i=0; i<values.size(); i++){
                pstmt.setObject(i+1, values.get(i));
            }
        }
        return pstmt;
    }

}
