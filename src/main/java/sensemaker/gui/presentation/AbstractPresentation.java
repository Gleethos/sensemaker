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
    // LOGGING :

    protected static Logger _log = Logger.getLogger(AbstractPresentation.class);

    // BUSINESS ACCESS :

    private static Gatekeeper _business;
    static
    {
        _business = new Gatekeeper(new DALFactory().setDoMocking(false).produce());
        _business.syncImages("images");
    }

    protected Gatekeeper _business()
    {
        return _business;
    }

    // MODEL GETTER :

    public abstract ModelType getModel();

    // MODEL-VIEW DATA SYNCRONIZATION :

    public abstract void applyFromModel();

    public abstract void applyIntoModel();

    public abstract void persist();

    public abstract void restore();


}
