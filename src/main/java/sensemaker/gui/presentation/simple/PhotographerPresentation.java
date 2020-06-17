package sensemaker.gui.presentation.simple;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sensemaker.gui.models.simple.PhotographerModel;
import sensemaker.gui.presentation.AbstractPresentation;

public class PhotographerPresentation extends AbstractPresentation<PhotographerModel>
{
    //______________
    // PROPERTIES :

    private final StringProperty _forename = new SimpleStringProperty();
    private final StringProperty _surname = new SimpleStringProperty();

    //_________
    // MODEL :
    private final PhotographerModel _model;

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
    /**
     * This method takes the contents of the stored
     * model instance and copies their values
     * into the individual 'Property' instances!
     */
    @Override
    public void applyFromModel() {
        _forename.setValue(_model.getForename());
        _surname.setValue(_model.getSurname());
    }

    /**
     * This method takes the values of the
     * stored 'Property' instances and copies their
     * values into the model instance of
     * this presentation.
     */
    @Override
    public void applyIntoModel() {
        _model.setSurname(_surname.getValue());
        _model.setForename(_forename.getValue());
    }

    /**
     * This method means business!
     * It saves / updates the stored model through the business layer.
     * If the model in question has no id (==null),
     * then the model is expected to be stored as a new
     * entry on the DAL layer.
     * Otherwise it is expected that the model is
     * simply updated...
     */
    @Override
    public void persist() {
        _business().save(_model);
    }

    /**
     * This method means business!
     * It simply tries to restore the model in question
     * via the business layer.
     * The model is expected to be filled by
     * the corresponding DAL entries with the same id...
     * WARNING:
     * If the id of the given model is not set (==null),
     * then the model will simply be filled by the
     * best matching entry the DAL can find! (:= search call with row-number == 1)
     */
    @Override
    public void restore() {
        _business().restore(_model);
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
