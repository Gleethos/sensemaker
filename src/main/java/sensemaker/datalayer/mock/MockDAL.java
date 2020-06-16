package sensemaker.datalayer.mock;

import sensemaker.datalayer.API.Access;
import sensemaker.datalayer.API.DAL;
import sensemaker.gui.models.simple.EXIFModel;
import sensemaker.gui.models.simple.IPTCModel;
import sensemaker.gui.models.simple.PhotographerModel;
import sensemaker.gui.models.simple.PictureModel;
import sensemaker.gui.models.composites.DetailedPictureModel;

import java.sql.Date;
import java.util.*;

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

                    @Override public List<PictureModel> getAll() {
                        return _pictureModelList;
                    }

                    @Override public List<PictureModel> findBy(PictureModel M) {
                        return null;
                    }

                    @Override public void save(PictureModel M) {
                        if(M.getId()==null) {
                            M.setId(_pictureModelCounter);
                            if(M.getCreated()==null) M.setCreated(Date.valueOf(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString()));
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

                    @Override public List<EXIFModel> getAll() {
                        return _EXIFModelList;
                    }
                    @Override public List<EXIFModel> findBy(EXIFModel M) {
                        return null;
                    }
                    @Override public void save(EXIFModel M) {
                        if(M.getId()==null) {
                            M.setId(_EXIFModelCounter);
                            if(M.getCreated()==null) M.setCreated(Date.valueOf(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString()));
                            _EXIFModelCounter++;
                            _EXIFModelList.add(M);
                        } else {

                        }
                    }
                    @Override public void delete(EXIFModel M) {

                    }
                });
        _accessors.put(
                IPTCModel.class,
                new Access<IPTCModel>()
                {
                    private int _IPTCModelCounter = 1;
                    private List<IPTCModel> _IPTCModelList = new ArrayList<>();

                    @Override
                    public List<IPTCModel> getAll() {
                        return _IPTCModelList;
                    }

                    @Override
                    public List<IPTCModel> findBy(IPTCModel M) {
                        return null;
                    }

                    @Override
                    public void save(IPTCModel M) {
                        if(M.getId()==null) {
                            M.setId(_IPTCModelCounter);
                            if(M.getCreated()==null) M.setCreated(Date.valueOf(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString()));
                            _IPTCModelCounter++;
                            _IPTCModelList.add(M);
                        } else {
                            //...
                        }
                    }

                    @Override
                    public void delete(IPTCModel M) {

                    }
                });
        _accessors.put(
                PhotographerModel.class,
                new Access<PhotographerModel>()
                {
                    private int _photographerModelCounter = 1;
                    List<PhotographerModel> _photographerModelList = new ArrayList<>();

                    @Override
                    public List<PhotographerModel> getAll() {
                        return _photographerModelList;
                    }

                    @Override
                    public List<PhotographerModel> findBy(PhotographerModel M) {
                        return null;
                    }

                    @Override
                    public void save(PhotographerModel M) {
                        if(M.getId()==null) {
                            M.setId(_photographerModelCounter);
                            if(M.getCreated()==null) M.setCreated(Date.valueOf(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString()));
                            _photographerModelCounter++;
                            _photographerModelList.add(M);
                        } else {

                        }
                    }

                    @Override
                    public void delete(PhotographerModel M) {

                    }
            });
        _accessors.put(DetailedPictureModel.class, new Access() {

            @Override
            public List getAll() {
                return null;
            }

            @Override
            public List findBy(Object M) {
                return null;
            }

            @Override
            public void save(Object M) {

            }

            @Override
            public void delete(Object M) {

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
