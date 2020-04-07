package gui.presentation;

import gui.models.PiGame;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchPresentation extends AbstractPresentation{

    private final StringProperty twoWayInput = new SimpleStringProperty("DEFAULT");

    private StringBinding displayNameProperty;

    PiGame piGame;


    public SearchPresentation(){
        PiGame model = new PiGame();
        model.getFirstNameProperty().addListener((s, o, n) -> displayNameProperty.invalidate());
        model.getLastNameProperty().addListener((s, o, n) -> displayNameProperty.invalidate());
        refresh(model);
        displayNameProperty = new StringBinding() {
            @Override
            protected String computeValue() {
                return String.format("%s %s", getFirstNameProperty(), getLastNameProperty()).trim();
            }
        };



    }


    public void refresh(PiGame model) {
        piGame = model;
        piGame.getFirstNameProperty().setValue(model.getFirstNameProperty().getValue());
        piGame.getLastNameProperty().setValue(model.getLastNameProperty().getValue());
    }

    public void applyChanges(PiGame model) {
        model.setFirstNameProperty(piGame.getFirstNameProperty().getValue());
        model.setLastNameProperty(piGame.getLastNameProperty().getValue());
    }

    public String getFirstNameProperty() {
        return piGame.getFirstNameProperty().get() != null ? piGame.getFirstNameProperty().get() : "";
    }

    public StringProperty firstNamePropertyProperty() {
        return piGame.getFirstNameProperty();
    }

    public void setFirstNameProperty(String firstNameProperty) {
        this.piGame.getFirstNameProperty().set(firstNameProperty);
    }

    public String getLastNameProperty() {
        return piGame.getLastNameProperty().get() != null ? piGame.getLastNameProperty().get() : "";
    }

    public StringProperty lastNamePropertyProperty() {
        return piGame.getLastNameProperty();
    }

    public void setLastNameProperty(String lastNameProperty) {
        this.piGame.getLastNameProperty().set(lastNameProperty);
    }

    public String getDisplayNameProperty() {
        return displayNameProperty.get();
    }

    public StringBinding displayNamePropertyProperty() {
        return displayNameProperty;
    }


}
