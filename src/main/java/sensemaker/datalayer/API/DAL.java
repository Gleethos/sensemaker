package sensemaker.datalayer.API;

public interface DAL {

    void initialize();

    <T extends Access> T access(Class<T> type);






}
