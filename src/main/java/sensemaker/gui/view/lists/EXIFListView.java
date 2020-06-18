package sensemaker.gui.view.lists;

import javafx.fxml.FXML;
import sensemaker.gui.models.simple.EXIFModel;
import sensemaker.gui.presentation.lists.AbstractListPresentation;
import sensemaker.gui.presentation.lists.EXIFListPresentation;
import sensemaker.gui.view.simple.EXIFView;

public class EXIFListView extends AbstractListView<EXIFModel>
{
    //________________
    // PRESENTATION :

    private EXIFListPresentation _presentation;

    //______________________________
    // CHILD VIEWS / (CONTROLLER) :

    @FXML
    private EXIFView _exifController; // Used as search result view!

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public EXIFListView()
    {
        _presentation = new EXIFListPresentation();
    }

    //________________________
    // DEFAULT VIEW METHODS :


    @Override
    public AbstractListPresentation<EXIFModel> getPresentation() {
        return _presentation;
    }

    /**
     * Every list view instance is expected to implement this method for binding.
     * It is called by the '_bin' method which is itself
     * called by the 'initialize' methed which
     * is ultimately called by the FXML-Loader after it instantiated this very view!
     *
     * @param presentation The corresponding presentation type to this view type!
     */
    @Override
    protected void _secondaryBind(AbstractListPresentation<EXIFModel> presentation)
    {

        assert _exifController != null;
        //presentation.setTemplateModel(_exifController.getPresentation().getModel());
        _searchButton.setOnAction( event -> {
            presentation.restore();
            if(!presentation.getModel().isEmpty()){
                _exifController.getPresentation().getModel().setId(presentation.getModel().get(0).getId());
                _exifController.getPresentation().restore();
                _exifController.getPresentation().applyFromModel();
            }
            _exifController.getPresentation().applyFromModel();
            presentation.applyFromModel();
        });

    }
}
