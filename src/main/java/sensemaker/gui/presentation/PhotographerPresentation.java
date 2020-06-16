package sensemaker.gui.presentation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sensemaker.gui.models.base.PhotographerModel;

public class PhotographerPresentation extends AbstractPresentation<PhotographerModel>
{
    //______________
    // PROPERTIES :

    private StringProperty _forename = new SimpleStringProperty();
    private StringProperty _surname = new SimpleStringProperty();

    //_________
    // MODEL :
    private PhotographerModel _model;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public PhotographerPresentation()
    {
        _model = new PhotographerModel();
    }

    //________________
    // MODEL GETTER :

    @Override
    public PhotographerModel getModel()
    {
        return _model;
    }

    //___________________________________
    // MODEL-VIEW DATA SYNCHRONIZATION :

    @Override
    public void applyFromModel() {

    }

    @Override
    public void applyIntoModel() {

    }

    @Override
    public void persist() {

    }

    @Override
    public void restore() {

    }

    //______________________________
    // PROPERTY GETTER AND SETTER :


    public String getForename() {
        return _forename.get();
    }

    public StringProperty forenameProperty() {
        return _forename;
    }

    public void setForename(String forename) {
        this._forename.set(forename);
    }

    public String getSurname() {
        return _surname.get();
    }

    public StringProperty surnameProperty() {
        return _surname;
    }

    public void setSurname(String surname) {
        this._surname.set(surname);
    }
}
