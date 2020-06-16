package sensemaker.gui.models.composites;

import sensemaker.gui.models.AbstractSearchableModel;
import sensemaker.gui.models.simple.EXIFModel;
import sensemaker.gui.models.simple.IPTCModel;
import sensemaker.gui.models.simple.PhotographerModel;
import sensemaker.gui.models.simple.PictureModel;

import java.util.HashMap;
import java.util.Map;

public class DetailedPictureModel extends AbstractSearchableModel<DetailedPictureModel>
{
    private PictureModel _pictureModel;
    private EXIFModel _exifModel;
    private IPTCModel _iptcModel;
    private PhotographerModel _photographerModel;

    public DetailedPictureModel (
            PictureModel pictureModel,
            EXIFModel exifModel,
            IPTCModel iptcModel,
            PhotographerModel photographerModel
    ){
        assert pictureModel!=null && exifModel!=null && iptcModel!=null && photographerModel!=null;
        assert exifModel.getId()==pictureModel.getEXIFId();
        assert iptcModel.getId()==pictureModel.getIPTCId();
        assert photographerModel.getId() == pictureModel.getPhotographerId();

        _pictureModel = pictureModel;
        _exifModel = exifModel;
        _iptcModel = iptcModel;
        _photographerModel = photographerModel;
    }

    public PictureModel getPictureModel(){
        return _pictureModel;
    }

    public EXIFModel getEXIFModel() {
        return _exifModel;
    }

    public IPTCModel getIPTCModel(){
        return _iptcModel;
    }

    public PhotographerModel getPhotographerModel(){
        return _photographerModel;
    }

    // SQL :

    @Override
    public <T> Map<String, T> generatePreparedSQLKeyValues(Class<T> type) {
        Map<String, T> map = new HashMap<>();
        map.putAll(_exifModel.generatePreparedSQLKeyValues(type));
        map.putAll(_iptcModel.generatePreparedSQLKeyValues(type));
        map.putAll(_pictureModel.generatePreparedSQLKeyValues(type));
        return map;
    }

    @Override
    public <T> Map<String, T> defaultInsertKeyValues(Class<T> type) {
        Map<String, T> map = new HashMap<>();
        map.putAll(_exifModel.defaultInsertKeyValues(type));
        map.putAll(_iptcModel.defaultInsertKeyValues(type));
        map.putAll(_pictureModel.defaultInsertKeyValues(type));
        return map;
    }

    @Override
    public <T> Map<String, T> generateSoftPreparedSQLKeyValues(Class<T> type) {
        Map<String, T> map = new HashMap<>();
        map.putAll(_exifModel.generateSoftPreparedSQLKeyValues(type));
        map.putAll(_iptcModel.generateSoftPreparedSQLKeyValues(type));
        map.putAll(_pictureModel.generateSoftPreparedSQLKeyValues(type));
        return map;
    }

}
