package sensemaker.gui.view.lists;


import sensemaker.gui.models.simple.PhotographerModel;
import sensemaker.gui.presentation.lists.AbstractListPresentation;
import sensemaker.gui.presentation.lists.PhotographerListPresentation;

public class PhotographerListView extends AbstractListView<PhotographerModel>
{
    //________________
    // PRESENTATION : 

    private PhotographerListPresentation _presentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public PhotographerListView()
    {
        _presentation = new PhotographerListPresentation();
    }

    //________________________
    // DEFAULT VIEW METHODS :


    @Override
    protected AbstractListPresentation<PhotographerModel> getPresentation() {
        return _presentation;
    }
}
