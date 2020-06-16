package sensemaker.gui.view.lists;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import sensemaker.gui.presentation.lists.AbstractListPresentation;
import sensemaker.gui.presentation.lists.PictureListPresentation;
import sensemaker.gui.view.AbstractView;

public abstract class AbstractListView<ModelType> extends AbstractView<AbstractListPresentation<ModelType>>
{
    //____________________
    // FX-VIEW ELEMENTS :

    @FXML
    private VBox tableBox;

    //_______________________
    // DEFAULT VIEW METHOD :

    @Override
    protected void _bind(AbstractListPresentation<ModelType> presentation) {
        tableBox.getChildren().add(presentation.getTable());
    }


}
