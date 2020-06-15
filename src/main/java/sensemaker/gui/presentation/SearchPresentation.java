package sensemaker.gui.presentation;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import sensemaker.gui.models.base.EXIFModel;
import sensemaker.gui.models.base.IPTCModel;
import sensemaker.gui.models.base.PhotographerModel;
import sensemaker.gui.models.base.PictureModel;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchPresentation extends AbstractPresentation<PictureModel>
{



    private StringBinding displayNameProperty;

    private IntegerProperty id;
    private StringProperty title;

    private PictureModel _pictureModel;
    private EXIFModel _EXIFModel;
    private IPTCModel _IPTCModel;
    private PhotographerModel _photographerModel;

    public SearchPresentation(){
        PictureModel model = new PictureModel();
        _EXIFModel = new EXIFModel();
        _IPTCModel = new IPTCModel();

        title = new SimpleStringProperty("test 1");
        id = new SimpleIntegerProperty(-1);
        refresh(model);
    }

    public void search(){
        _getDAL().access(PictureModel.class).findBy(_pictureModel);
    }

    @Override
    public void refresh(PictureModel model) {
        _pictureModel = model;
        title.setValue(model.getPath());
        id.setValue(model.getId());
        displayNameProperty = new StringBinding() {
            @Override
            protected String computeValue() {
                return String.format("%s %s", getPath(), getId()).trim();
            }
        };
    }

    @Override
    public void applyChanges(PictureModel model) {
        model.setId(_pictureModel.getId());
        model.setPath(_pictureModel.getPath());
    }

    public String getPath() {
        return _pictureModel.getPath() != null ? _pictureModel.getPath() : "";
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        _IPTCModel.setTitle(title);
    }

    public int getId() {
        return _pictureModel.getId();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this._pictureModel.setId(id);
    }

    public String getDisplayNameProperty() {
        return displayNameProperty.get();
    }

    public StringBinding displayNamePropertyProperty() {
        return displayNameProperty;
    }


}
