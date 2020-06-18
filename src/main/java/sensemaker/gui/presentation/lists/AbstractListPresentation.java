package sensemaker.gui.presentation.lists;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sensemaker.gui.models.Model;
import sensemaker.gui.presentation.AbstractPresentation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListPresentation<ModelType> extends AbstractPresentation<List<ModelType>>
{
    //______________
    // PROPERTIES :

    private final TableView _table = new TableView();

    private final StringProperty _searchWord = new SimpleStringProperty();

    //_________
    // MODEL :

    private final List<ModelType> _models;
    private ModelType _templateModel;

    public AbstractListPresentation(ModelType templateModel)
    {
        _templateModel = templateModel;
        _models = new ArrayList<>();

        TableColumn[] columns = _columns();
        for(TableColumn column : columns){
            _table.getColumns().add(column);
        }
        restore();
        applyFromModel();
    }

    //_____________________________________________________________________________
    // EXPECTED IMPLEMENTATION FOR PROPER INSTANTIATION (in constructor above ^) :

    protected abstract TableColumn<String, ModelType>[] _columns();

    //________________
    // MODEL GETTER :

    @Override
    public List<ModelType> getModel()
    {
        return _models;
    }

    public ModelType getTemplateModel(){
        return _templateModel;
    }

    public void setTemplateModel(ModelType model){
        _templateModel = model;
    }

    //_____________________________
    // MODEL-VIEW SYNCHRONIZATION :


    /**
     * This method takes the contents of the stored
     * model instance and copies their values
     * into the individual 'Property' instances!
     */
    @Override
    public void applyFromModel(){
        _table.getItems().removeAll(_table.getItems());
        _table.getItems().addAll(_models);
    }

    /**
     * This method takes the values of the
     * stored 'Property' instances and copies their
     * values into the model instance of
     * this presentation.
     */
    @Override
    public void applyIntoModel(){
        //This should already happen automatically! (in table view items...)
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
        _models.forEach( m -> _business().save((Model) m) );
    }

    @Override
    public void restore() {
        _models.removeAll(_models);
        _models.addAll(_business().searchFor(_templateModel));
    }

    //______________________________
    // PROPERTY GETTER AND SETTER :

    public TableView getTable()
    {
        return _table;
    }

    public String getSearchWord(){
        return _searchWord.getValue();
    }

    public StringProperty searchWordProperty(){
        return _searchWord;
    }

}
