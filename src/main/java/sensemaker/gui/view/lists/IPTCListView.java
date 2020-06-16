package sensemaker.gui.view.lists;

import sensemaker.gui.models.simple.IPTCModel;
import sensemaker.gui.presentation.lists.AbstractListPresentation;
import sensemaker.gui.presentation.lists.IPTCListPresentation;

public class IPTCListView extends AbstractListView<IPTCModel>
{
    //________________
    // PRESENTATION : 

    private IPTCListPresentation _presentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public IPTCListView()
    {
        _presentation = new IPTCListPresentation();
    }

    //________________________
    // DEFAULT VIEW METHODS :


    @Override
    protected AbstractListPresentation<IPTCModel> getPresentation() {
        return _presentation;
    }
}
