package sensemaker.gui.presentation;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import sensemaker.gui.models.PictureModel;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchPresentation extends AbstractPresentation<PictureModel>{

    private StringBinding displayNameProperty;
    private StringProperty path;
    private IntegerProperty id;


    PictureModel pictureModel;


    public SearchPresentation(){
        PictureModel model = new PictureModel();
        path = new SimpleStringProperty("test 1");
        id = new SimpleIntegerProperty(-1);
        refresh(model);
    }

    @Override
    public void refresh(PictureModel model) {
        pictureModel = model;
        path.setValue(model.getPath());
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
        model.setId(pictureModel.getId());
        model.setPath(pictureModel.getPath());
    }

    public String getPath() {
        return pictureModel.getPath() != null ? pictureModel.getPath() : "";
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.pictureModel.setPath(path);
    }

    public int getId() {
        return pictureModel.getId();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.pictureModel.setId(id);
    }

    public String getDisplayNameProperty() {
        return displayNameProperty.get();
    }

    public StringBinding displayNamePropertyProperty() {
        return displayNameProperty;
    }


}
