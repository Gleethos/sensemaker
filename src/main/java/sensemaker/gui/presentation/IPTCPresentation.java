package sensemaker.gui.presentation;

import javafx.beans.property.SimpleStringProperty;
import sensemaker.gui.models.base.IPTCModel;

public class IPTCPresentation extends AbstractPresentation<IPTCModel>
{
    //______________
    // PROPERTIES :

    private final SimpleStringProperty _title = new SimpleStringProperty();
    private final SimpleStringProperty _description = new SimpleStringProperty();
    private final SimpleStringProperty _copyright = new SimpleStringProperty();
    private final SimpleStringProperty _keywords = new SimpleStringProperty();

    //_________
    // MODEL :

    private IPTCModel _model;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public IPTCPresentation()
    {
        _model = new IPTCModel();
    }

    //________________
    // MODEL GETTER :

    @Override
    public IPTCModel getModel()
    {
        return _model;
    }

    //_____________________________
    // MODEL-VIEW DATA SYNCHRONIZATION :

    @Override
    public void applyFromModel()
    {
        _title.setValue(_model.getTitle());
        _description.setValue(_model.getDescription());
        _copyright.setValue(_model.getCopyright());
        _keywords.setValue(_model.getKeywords());
    }

    @Override
    public void applyIntoModel() {
        _model.setTitle(_title.getValue());
        _model.setDescription(_description.getValue());
        _model.setCopyright(_copyright.getValue());
        _model.setKeywords(_keywords.getValue());
    }

    @Override
    public void persist() {
        _business().saveIPTC(_model);
    }

    @Override
    public void restore() {

    }

    //______________________________
    // PROPERTY GETTERS AND SETTERS:


    public String getTitle() {
        return _title.get();
    }

    public SimpleStringProperty titleProperty() {
        return _title;
    }

    public void setTitle(String title) {
        this._title.set(title);
    }

    public String getDescription() {
        return _description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return _description;
    }

    public void setDescription(String description) {
        this._description.set(description);
    }

    public String getCopyright() {
        return _copyright.get();
    }

    public SimpleStringProperty copyrightProperty() {
        return _copyright;
    }

    public void setCopyright(String copyright) {
        this._copyright.set(copyright);
    }

    public String getKeywords() {
        return _keywords.get();
    }

    public SimpleStringProperty keywordsProperty() {
        return _keywords;
    }

    public void setKeywords(String keywords) {
        this._keywords.set(keywords);
    }


    //______________________
    // PRESENTATION LOGIC :

}
