package sensemaker.gui.presentation.lists;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sensemaker.gui.models.simple.EXIFModel;

public class EXIFListPresentation extends AbstractListPresentation<EXIFModel>
{
    public EXIFListPresentation() {
        super(new EXIFModel());
    }
    /**
     *  This class is fairly empty because the logic for displaying 
     *  the data as table has been generalized and implemented
     *  the 'AbstractListPresentation' class!
     */
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * This method is called by the super class of this concrete presentation class
     * during object construction!
     * The TableColumn array return by this method
     * is used by said super constructor in order to define
     * the TableView used to display the model data.
     *
     * @return An array of table columns used to define the table of the TableView in the super class.
     */
    @Override
    protected TableColumn<String, EXIFModel>[] _columns() {

        TableColumn<String, EXIFModel> column1 = new TableColumn<>("Shot");
        column1.setCellValueFactory(new PropertyValueFactory<>("shot"));

        TableColumn<String, EXIFModel> column2 = new TableColumn<>("Orientation");
        column2.setCellValueFactory(new PropertyValueFactory<>("orientation"));

        return new TableColumn[]{column1, column2};
    }
}
