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

    @Override
    protected void _secondaryBind(AbstractListPresentation<PhotographerModel> presentation)
    {
        assert _photographerController != null;



    }
}
