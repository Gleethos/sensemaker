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

    @Override
    protected void _secondaryBind(AbstractListPresentation<EXIFModel> presentation)
    {
        assert _exifController != null;
    }
}
