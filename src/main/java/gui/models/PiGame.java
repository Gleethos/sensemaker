package gui.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import plugins.interfaces.IGame;

/**
 *
 */
public class PiGame {

    private StringProperty firstNameProperty = new SimpleStringProperty();
    private StringProperty lastNameProperty = new SimpleStringProperty();

    public PiGame(IGame game) {

    }

    public PiGame() {

    }

    public PiGame(String firstName, String lastName) {
        this.firstNameProperty = new SimpleStringProperty(firstName);// = firstName;
        this.lastNameProperty = new SimpleStringProperty(lastName);
    }

    public StringProperty getFirstNameProperty() {
        return firstNameProperty;
    }

    public void setFirstNameProperty(String firstName) {
        this.firstNameProperty.setValue(firstName);
    }

    public StringProperty getLastNameProperty() {
        return lastNameProperty;
    }

    public void setLastNameProperty(String lastName) {
        this.lastNameProperty.setValue(lastName);
    }



}
