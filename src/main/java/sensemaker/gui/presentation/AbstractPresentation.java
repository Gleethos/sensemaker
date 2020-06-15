package sensemaker.gui.presentation;

import org.apache.log4j.Logger;
import sensemaker.businesslayer.Gatekeeper;
import sensemaker.datalayer.API.DAL;
import sensemaker.datalayer.assembly.DALFactory;
import sensemaker.gui.view.AbstractView;

/**
 *
 */
public abstract class AbstractPresentation<ModelType>
{
    protected static Logger _log = Logger.getLogger(AbstractPresentation.class);
    private static Gatekeeper _business;
    static
    {
        _business = new Gatekeeper(new DALFactory().setDoMocking(false).produce());
        _business.syncImages("images");
    }

    protected Gatekeeper _business(){
        return _business;
    }

    public abstract void refresh(ModelType model);

    public abstract void applyChanges(ModelType model);

}
