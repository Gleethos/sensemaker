package sensemaker.gui.view.lists;

import sensemaker.gui.models.simple.EXIFModel;
import sensemaker.gui.presentation.lists.AbstractListPresentation;
import sensemaker.gui.presentation.lists.EXIFListPresentation;

public class EXIFListView extends AbstractListView<EXIFModel>
{
    //________________
    // PRESENTATION :

    private EXIFListPresentation _presentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public EXIFListView()
    {
        _presentation = new EXIFListPresentation();
    }

    //________________________
    // DEFAULT VIEW METHODS :


    @Override
    protected AbstractListPresentation<EXIFModel> getPresentation() {
        return _presentation;
    }
}
