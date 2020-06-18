package sensemaker.gui.view.lists;


import javafx.fxml.FXML;
import sensemaker.gui.models.simple.PhotographerModel;
import sensemaker.gui.presentation.lists.AbstractListPresentation;
import sensemaker.gui.presentation.lists.PhotographerListPresentation;
import sensemaker.gui.view.simple.PhotographerView;

public class PhotographerListView extends AbstractListView<PhotographerModel>
{
    //________________
    // PRESENTATION : 

    private PhotographerListPresentation _presentation;

    //______________________________
    // CHILD VIEWS / (CONTROLLER) :

    @FXML
    private PhotographerView _photographerController; // Used as search result holder!

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public PhotographerListView()
    {
        _presentation = new PhotographerListPresentation();
    }

    //________________________
    // DEFAULT VIEW METHODS :


    @Override
    public AbstractListPresentation<PhotographerModel> getPresentation() {
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
    protected void _secondaryBind(AbstractListPresentation<PhotographerModel> presentation)
    {
        assert _photographerController != null;
        //presentation.setTemplateModel(_photographerController.getPresentation().getModel());
        _searchButton.setOnAction( event -> {
            presentation.restore();
            if(!presentation.getModel().isEmpty()){
                _photographerController.getPresentation().getModel().setId(presentation.getModel().get(0).getId());
                _photographerController.getPresentation().restore();
                _photographerController.getPresentation().applyFromModel();
            }
            _photographerController.getPresentation().applyFromModel();
            presentation.applyFromModel();
        });
    }
}
