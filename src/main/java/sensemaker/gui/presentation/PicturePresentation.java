package sensemaker.gui.presentation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sensemaker.gui.models.base.PictureModel;

public class PicturePresentation extends AbstractPresentation<PictureModel>
{

    //______________
    // PROPERTIES :

    private StringProperty path = new SimpleStringProperty();

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

    @Override
    public void applyFromModel()
    {

    }

    @Override
    public void applyIntoModel()
    {

    }

    @Override
    public void persist() {
        _business().savePicture(_model);
    }

    @Override
    public void restore()
    {

    }

    //______________________________
    // PROPERTY GETTER AND SETTER :

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    //______________________
    // PRESENTATION LOGIC :


}
