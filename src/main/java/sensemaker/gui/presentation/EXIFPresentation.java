package sensemaker.gui.presentation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sensemaker.gui.models.base.EXIFModel;

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

    @Override
    public void applyFromModel() {
            _shot.setValue(_model.getShot().toString());
            _orientation.setValue(_model.getOrientation());
    }

    @Override
    public void applyIntoModel() {
            _model.setShot(Date.valueOf(_shot.get()));
            _model.setOrientation(_orientation.getValue());
    }

    @Override
    public void persist() {
            _business().saveEXIF(_model);
    }

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
