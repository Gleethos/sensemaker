package gui.presentation;

import gui.models.PictureModel;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sun.java2d.pipe.SpanShapeRenderer;

public class SearchPresentation extends AbstractPresentation<PictureModel>{

    private StringBinding displayNameProperty;
    private StringProperty firstNameProperty;
    private StringProperty lastNameProperty;


    PictureModel pictureModel;


    public SearchPresentation(){
        PictureModel model = new PictureModel();
        //firstNameProperty.addListener((s, o, n) -> displayNameProperty.invalidate());
        //lastNameProperty.addListener((s, o, n) -> displayNameProperty.invalidate());
        refresh(model);
        displayNameProperty = new StringBinding() {
            @Override
            protected String computeValue() {
                return String.format("%s %s", getFirstNameProperty(), getLastNameProperty()).trim();
            }
        };
        firstNameProperty = new SimpleStringProperty("test 1");
        lastNameProperty = new SimpleStringProperty("test 2");

    }

    @Override
    public void refresh(PictureModel model) {
        pictureModel = model;
        firstNameProperty.setValue(model.getFirstName());
        lastNameProperty.setValue(model.getLastName());
    }

    @Override
    public void applyChanges(PictureModel model) {
        model.setFirstName(pictureModel.getFirstName());
        model.setLastName(pictureModel.getLastName());
    }

    public String getFirstNameProperty() {
        return pictureModel.getFirstName() != null ? pictureModel.getFirstName() : "";
    }

    public StringProperty firstNamePropertyProperty() {
        return firstNameProperty;
    }

    public void setFirstNameProperty(String firstNameProperty) {
        this.pictureModel.setFirstName(firstNameProperty);
    }

    public String getLastNameProperty() {
        return pictureModel.getLastName() != null ? pictureModel.getLastName() : "";
    }

    public StringProperty lastNamePropertyProperty() {
        return lastNameProperty;
    }

    public void setLastNameProperty(String lastNameProperty) {
        this.pictureModel.setLastName(lastNameProperty);
    }

    public String getDisplayNameProperty() {
        return displayNameProperty.get();
    }

    public StringBinding displayNamePropertyProperty() {
        return displayNameProperty;
    }


}
