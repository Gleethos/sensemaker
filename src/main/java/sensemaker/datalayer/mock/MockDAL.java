package sensemaker.datalayer.mock;

import sensemaker.datalayer.API.Access;
import sensemaker.datalayer.API.DAL;
import sensemaker.gui.models.EXIFModel;
import sensemaker.gui.models.IPTCModel;
import sensemaker.gui.models.PhotographerModel;
import sensemaker.gui.models.PictureModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDAL implements DAL
{
    private Map<Class, Access> _accessors = new HashMap<>();

    public MockDAL(){
        initialize();
    }

    @Override
    public void initialize() {
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
        _accessors.put(
                EXIFModel.class,
                new Access<EXIFModel>(){
                    @Override public void initialize() {

                    }
                    @Override public List<EXIFModel> getAll() {

                        return null;
                    }
                    @Override public List<EXIFModel> findBy(EXIFModel M) {
                        return null;
                    }
                    @Override public void save(EXIFModel M) {

                    }

                    @Override public void delete(EXIFModel M) {

                    }
                });
        _accessors.put(
                IPTCModel.class, new Access<IPTCModel>(){

            @Override
            public void initialize() {

            }

            @Override
            public List<IPTCModel> getAll() {
                return null;
            }

            @Override
            public List<IPTCModel> findBy(IPTCModel M) {
                return null;
            }

            @Override
            public void save(IPTCModel M) {

            }

            @Override
            public void delete(IPTCModel M) {

            }
        });
        _accessors.put(PhotographerModel.class, new Access<PhotographerModel>(){
            @Override
            public void initialize() {

            }

            @Override
            public List<PhotographerModel> getAll() {
                return null;
            }

            @Override
            public List<PhotographerModel> findBy(PhotographerModel M) {
                return null;
            }

            @Override
            public void save(PhotographerModel M) {

            }

            @Override
            public void delete(PhotographerModel M) {

            }
        });
    }

    @Override
    public <T extends Access> T access(Class<T> type) {
        return (T) _accessors.get(type);
    }
}
