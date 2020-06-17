package sensemaker.gui.presentation;

import org.apache.log4j.Logger;
import sensemaker.businesslayer.Gatekeeper;
import sensemaker.datalayer.assembly.DALFactory;

/**
 *
 */
public abstract class AbstractPresentation<ModelType>
{
    //___________
    // LOGGING :

    protected static Logger _log = Logger.getLogger(AbstractPresentation.class);

    //___________________
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

    //________________
    // MODEL GETTER :

    public abstract ModelType getModel();

    //__________________________________
    // MODEL-VIEW DATA SYNCRONIZATION :

    /**
     * This method takes the contents of the stored
     * model instance and copies their values
     * into the individual 'Property' instances!
     */
    public abstract void applyFromModel();

    /**
     * This method takes the values of the
     * stored 'Property' instances and copies their
     * values into the model instance of
     * this presentation.
     */
    public abstract void applyIntoModel();

    /**
     * This method means business!
     * It saves / updates the stored model through the business layer.
     * If the model in question has no id (==null),
     * then the model is expected to be stored as a new
     * entry on the DAL layer.
     * Otherwise it is expected that the model is
     * simply updated...
     */
    public abstract void persist();

    /**
     * This method means business!
     * It simply tries to restore the model in question
     * via the business layer.
     * The model is expected to be filled by
     * the corresponding DAL entries with the same id...
     * WARNING:
     * If the id of the given model is not set (==null),
     * then the model will simply be filled by the
     * best matching entry the DAL can find! (:= search call with row-number == 1)
     */
    public abstract void restore();




}
