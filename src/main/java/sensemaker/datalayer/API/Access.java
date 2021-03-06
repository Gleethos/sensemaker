package sensemaker.datalayer.API;

import java.util.List;

public interface Access<ModelType> {

    List<ModelType> getAll();
    List<ModelType> findBy(ModelType M);
    void save(ModelType M);
    void delete(ModelType M);

}
