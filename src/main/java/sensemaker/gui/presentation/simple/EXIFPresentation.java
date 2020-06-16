package sensemaker.gui.presentation.simple;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sensemaker.gui.models.simple.EXIFModel;
import sensemaker.gui.presentation.AbstractPresentation;

import java.sql.Date;

public class EXIFPresentation extends AbstractPresentation<EXIFModel>
{
    //_________
    // MODEL :

    private final EXIFModel _model;

    //______________
    // PROPERTIES :

    private final StringProperty _shot = new SimpleStringProperty();
    private final StringProperty _orientation = new SimpleStringProperty();

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public EXIFPresentation(){
        _model = new EXIFModel();
    }

    //________________
    // MODEL GETTER :

    @Override
    public EXIFModel getModel()
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
            _shot.setValue(_model.getShot().toString());
            _orientation.setValue(_model.getOrientation());
    }

    /**
     * This method takes the values of the
     * stored 'Property' instances and copies their
     * values into the model instance of
     * this presentation.
     */
    @Override
    public void applyIntoModel() {
            _model.setShot(Date.valueOf(_shot.get()));
            _model.setOrientation(_orientation.getValue());
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
            _business().saveEXIF(_model);
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

    }

    //______________________________
    // PROPERTY GETTER AND SETTER :


    public String getShot() {
        return _shot.get();
    }

    public StringProperty shotProperty() {
        return _shot;
    }

    public void setShot(String shotProperty) {
        this._shot.set(shotProperty);
    }

    public String getOrientation() {
        return _orientation.get();
    }

    public StringProperty orientationProperty() {
        return _orientation;
    }

    public void setOrientation(String orientationProperty) {
        this._orientation.set(orientationProperty);
    }
}
