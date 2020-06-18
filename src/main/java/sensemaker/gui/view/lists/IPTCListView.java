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

    /**
     * Every list view instance is expected to implement this method for binding.
     * It is called by the '_bin' method which is itself
     * called by the 'initialize' methed which
     * is ultimately called by the FXML-Loader after it instantiated this very view!
     *
     * @param presentation The corresponding presentation type to this view type!
     */
    @Override
    protected void _secondaryBind(AbstractListPresentation<IPTCModel> presentation)
    {
        assert _iptcController != null;
        //presentation.setTemplateModel(_iptcController.getPresentation().getModel());
        _searchButton.setOnAction( event -> {
            presentation.restore();
            if(!presentation.getModel().isEmpty()){
                _iptcController.getPresentation().getModel().setId(presentation.getModel().get(0).getId());
                _iptcController.getPresentation().restore();
                _iptcController.getPresentation().applyFromModel();
            }
            _iptcController.getPresentation().applyFromModel();
            presentation.applyFromModel();
        });
    }
}
