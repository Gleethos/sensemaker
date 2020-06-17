package sensemaker.gui.view.lists;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sensemaker.gui.presentation.lists.AbstractListPresentation;
import sensemaker.gui.view.AbstractView;

public abstract class AbstractListView<ModelType> extends AbstractView<AbstractListPresentation<ModelType>>
{
    //____________________
    // FX-VIEW ELEMENTS :

    @FXML
    protected VBox _tableBox;

    @FXML
    protected TextField _searchField;

    @FXML
    protected Button _searchButton;

    @FXML
    protected Button _reloadButton;

    //_______________________
    // DEFAULT VIEW METHOD :

    @Override
    protected void _bind(AbstractListPresentation<ModelType> presentation) {
        _tableBox.getChildren().add(presentation.getTable());
        presentation.restore();
        presentation.applyFromModel();
        assert _tableBox != null;
        assert _searchField != null;
        assert _searchButton != null;
        assert _reloadButton != null;
        _bidi(presentation.searchWordProperty(), _searchField);
        _reloadButton.setOnAction(event -> {
            presentation.restore();
            presentation.applyFromModel();
        });
        _secondaryBind(presentation);
    }

    protected abstract void _secondaryBind(AbstractListPresentation<ModelType> presentation);

}
