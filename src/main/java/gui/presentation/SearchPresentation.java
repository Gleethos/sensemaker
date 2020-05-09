package gui.presentation;

import gui.models.PictureModel;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchPresentation extends AbstractPresentation{

    private StringBinding displayNameProperty;

    PictureModel pictureModel;


    public SearchPresentation(){
        PictureModel model = new PictureModel();
        //model.getFirstNameProperty().addListener((s, o, n) -> displayNameProperty.invalidate());
        //model.getLastNameProperty().addListener((s, o, n) -> displayNameProperty.invalidate());
        refresh(model);
        displayNameProperty = new StringBinding() {
            @Override
            protected String computeValue() {
                return String.format("%s %s", getFirstNameProperty(), getLastNameProperty()).trim();
            }
        };



    }


    public void refresh(PictureModel model) {
        pictureModel = model;
        pictureModel.setFirstName(model.getFirstName());
        pictureModel.setLastName(model.getLastName());
    }

    public void applyChanges(PictureModel model) {
        model.setFirstName(pictureModel.getFirstName());
        model.setLastName(pictureModel.getLastName());
    }

    public String getFirstNameProperty() {
        return pictureModel.getFirstName() != null ? pictureModel.getFirstName() : "";
    }

    public StringProperty firstNamePropertyProperty() {
        return pictureModel.getFirstNameProperty();
    }

    public void setFirstNameProperty(String firstNameProperty) {
        this.pictureModel.setFirstName(firstNameProperty);
    }

    public String getLastNameProperty() {
        return pictureModel.getLastName() != null ? pictureModel.getLastName() : "";
    }

    public StringProperty lastNamePropertyProperty() {
        return pictureModel.getLastName();
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
