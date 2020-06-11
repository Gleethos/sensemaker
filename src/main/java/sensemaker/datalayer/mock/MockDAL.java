package sensemaker.datalayer.mock;

import sensemaker.datalayer.API.Access;
import sensemaker.datalayer.API.DAL;
import sensemaker.gui.models.EXIFModel;
import sensemaker.gui.models.IPTCModel;
import sensemaker.gui.models.PhotographerModel;
import sensemaker.gui.models.PictureModel;

import java.util.ArrayList;
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
                new Access<PictureModel>()
                {
                    private int _pictureModelCounter = 1;
                    private List<PictureModel> _pictureModelList = new ArrayList<>();

                    @Override public void initialize() {

                    }

                    @Override public List<PictureModel> getAll() {
                        return _pictureModelList;
                    }

                    @Override public List<PictureModel> findBy(PictureModel M) {
                        return null;
                    }

                    @Override public void save(PictureModel M) {
                        if(M.getId()==null) {
                            M.setPhotographerId(_pictureModelCounter);
                            _pictureModelCounter ++;
                            _pictureModelList.add(M);
                        } else {
                            //...
                        }

                    }

                    @Override public void delete(PictureModel M) {

                    }
                });
        _accessors.put(
                EXIFModel.class,
                new Access<EXIFModel>()
                {
                    private int _EXIFModelCounter = 1;
                    private List<EXIFModel> _EXIFModelList = new ArrayList<>();

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
    public <T> Access<T> access(Class<T> type) {
        return  _accessors.get(type);
    }

    @Override
    public void reset(){
        initialize();
    }
}
