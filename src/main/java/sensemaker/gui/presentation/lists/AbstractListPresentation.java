package sensemaker.gui.presentation.lists;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sensemaker.gui.presentation.AbstractPresentation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListPresentation<ModelType> extends AbstractPresentation<List<ModelType>>
{
    //______________
    // PROPERTIES :

    private TableView _table = new TableView();

    //_________
    // MODEL :

    private final List<ModelType> _models;

    public AbstractListPresentation()
    {
        _models = new ArrayList<>();

        TableColumn<String, ModelType> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<String, ModelType> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        _table.getColumns().add(column1);
        _table.getColumns().add(column2);
        //tableView.getItems().add(new ModelType("John", "Doe"));
        //tableView.getItems().add(new ModelType("Jane", "Deer"));
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

    }

    /**
     * This method takes the values of the
     * stored 'Property' instances and copies their
     * values into the model instance of
     * this presentation.
     */
    @Override
    public void applyIntoModel() {

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

    }

    @Override
    public void restore() {

    }

    //______________________________
    // PROPERTY GETTER AND SETTER :

    public TableView getTable()
    {
        return _table;
    }


}
