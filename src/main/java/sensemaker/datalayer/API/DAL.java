package sensemaker.datalayer.API;

public interface DAL {

    void initialize();

    <T> Access<T> access(Class<T> type);

    void reset();

}
