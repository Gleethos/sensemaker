package sensemaker.gui.presentation.simple;

import javafx.beans.property.SimpleStringProperty;
import sensemaker.gui.models.simple.IPTCModel;
import sensemaker.gui.presentation.AbstractPresentation;

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

    private final IPTCModel _model;

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
    /**
     * This method takes the contents of the stored
     * model instance and copies their values
     * into the individual 'Property' instances!
     */
    @Override
    public void applyFromModel()
    {
        _title.setValue(_model.getTitle());
        _description.setValue(_model.getDescription());
        _copyright.setValue(_model.getCopyright());
        _keywords.setValue(_model.getKeywords());
    }

    /**
     * This method takes the values of the
     * stored 'Property' instances and copies their
     * values into the model instance of
     * this presentation.
     */
    @Override
    public void applyIntoModel() {
        _model.setTitle(_title.getValue());
        _model.setDescription(_description.getValue());
        _model.setCopyright(_copyright.getValue());
        _model.setKeywords(_keywords.getValue());
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
        _business().saveIPTC(_model);
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
