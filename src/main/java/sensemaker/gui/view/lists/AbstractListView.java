package sensemaker.gui.view.lists;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import sensemaker.gui.presentation.lists.AbstractListPresentation;
import sensemaker.gui.view.AbstractView;

public abstract class AbstractListView<ModelType> extends AbstractView<AbstractListPresentation<ModelType>>
{
    //____________________
    // FX-VIEW ELEMENTS :

    @FXML
    private VBox _tableBox;

    //_______________________
    // DEFAULT VIEW METHOD :

    @Override
    protected void _bind(AbstractListPresentation<ModelType> presentation) {
        _tableBox.getChildren().add(presentation.getTable());
    }


}
