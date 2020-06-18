package sensemaker.gui.presentation.lists;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sensemaker.gui.models.simple.IPTCModel;
/**
 *  This class is fairly empty because the logic for displaying
 *  the data as table has been generalized and implemented
 *  the 'AbstractListPresentation' class!
 */
public class IPTCListPresentation extends AbstractListPresentation<IPTCModel>
{
    public IPTCListPresentation() {
        super(new IPTCModel());
    }

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
    protected TableColumn<String, IPTCModel>[] _columns()
    {
        TableColumn<String, IPTCModel> column1 = new TableColumn<>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<String, IPTCModel> column2 = new TableColumn<>("Description");
        column2.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<String, IPTCModel> column3 = new TableColumn<>("Copyright");
        column3.setCellValueFactory(new PropertyValueFactory<>("copyright"));

        TableColumn<String, IPTCModel> column4 = new TableColumn<>("Keywords");
        column4.setCellValueFactory(new PropertyValueFactory<>("keywords"));

        return new TableColumn[]{column1, column2, column3, column4};
    }
}
