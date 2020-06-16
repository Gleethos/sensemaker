package sensemaker.gui.presentation.simple;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sensemaker.gui.models.simple.PictureModel;
import sensemaker.gui.presentation.AbstractPresentation;

public class PicturePresentation extends AbstractPresentation<PictureModel>
{

    //______________
    // PROPERTIES :

    private StringProperty _path = new SimpleStringProperty();

    //_________
    // MODEL :

    private PictureModel _model;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public PicturePresentation()
    {
        _model = new PictureModel();
    }

    //________________
    // MODEL GETTER :

    @Override
    public PictureModel getModel()
    {
        return _model;
    }

    //_____________________________
    // MODEL-VIEW SYNCHRONIZATION :
    /**
     * This method takes the contents of the stored
     * model instance and copies their values
     * into the individual 'Property' instances!
     */
    @Override
    public void applyFromModel()
    {
        _path.setValue(_model.getPath());
    }

    /**
     * This method takes the values of the
     * stored 'Property' instances and copies their
     * values into the model instance of
     * this presentation.
     */
    @Override
    public void applyIntoModel()
    {
        _model.setPath(_path.getValue());
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
        _business().savePicture(_model);
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
    public void restore()
    {

    }

    //______________________________
    // PROPERTY GETTER AND SETTER :

    public String getPath() {
        return _path.get();
    }

    public StringProperty pathProperty() {
        return _path;
    }

    public void setPath(String path) {
        this._path.set(path);
    }

    //______________________
    // PRESENTATION LOGIC :


}
