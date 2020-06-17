package sensemaker.gui.view.lists;

import javafx.fxml.FXML;
import sensemaker.gui.models.simple.IPTCModel;
import sensemaker.gui.presentation.lists.AbstractListPresentation;
import sensemaker.gui.presentation.lists.IPTCListPresentation;
import sensemaker.gui.view.simple.IPTCView;

public class IPTCListView extends AbstractListView<IPTCModel>
{
    //________________
    // PRESENTATION : 

    private IPTCListPresentation _presentation;

    //______________________________
    // CHILD VIEWS / (CONTROLLER) :

    @FXML
    private IPTCView _iptcController; // Used as search result view !

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public IPTCListView()
    {
        _presentation = new IPTCListPresentation();
    }

    //________________________
    // DEFAULT VIEW METHODS :


    @Override
    public AbstractListPresentation<IPTCModel> getPresentation() {
        return _presentation;
    }

    @Override
    protected void _secondaryBind(AbstractListPresentation<IPTCModel> presentation)
    {
        assert _iptcController != null;
    }
}
