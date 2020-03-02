package presentationmodels;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.Person;

/**
 *
 */
public class PersonPresentationModel extends PresentationModel {

    private StringProperty _firstName = new SimpleStringProperty();
    private StringProperty _lastName = new SimpleStringProperty();
    private StringBinding _displayName;

    public PersonPresentationModel() {
        super();
        _firstName.addListener((s, o, n) -> _displayName.invalidate());
        _lastName.addListener((s, o, n) -> _displayName.invalidate());

        _displayName = new StringBinding() {
            @Override
            protected String computeValue() {
                return String.format("%s %s", getFirstName(), getLastName()).trim();
            }
        };
    }

    public PersonPresentationModel(Person model) {
        this();
        refresh(model);
    }

    public void refresh(Person model) {
        _firstName.setValue(model.getFirstName());
        _lastName.setValue(model.getLastName());
    }

    public void applyChanges(Person model) {
        model.setFirstName(_firstName.getValue());
        model.setLastName(_lastName.getValue());
    }

    public String getFirstName() {
        return _firstName.get() != null ? _firstName.get() : "";
    }

    public StringProperty firstNameProperty() {
        return _firstName;
    }

    public void setFirstName(String firstName) {
        this._firstName.set(firstName);
    }

    public String getLastName() {
        return _lastName.get() != null ? _lastName.get() : "";
    }

    public StringProperty lastNameProperty() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        this._lastName.set(lastName);
    }

    public String getDisplayName() {
        return _displayName.get();
    }

    public StringBinding displayNameProperty() {
        return _displayName;
    }
}
