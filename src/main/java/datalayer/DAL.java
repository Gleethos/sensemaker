package datalayer;

public interface DAL {

    void initialize();

    <T extends Access> T access(Class<T> type);






}
