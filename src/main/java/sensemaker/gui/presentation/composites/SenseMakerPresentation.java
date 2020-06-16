package sensemaker.gui.presentation.composites;

import sensemaker.gui.models.simple.SenseMakerModel;
import sensemaker.gui.presentation.AbstractPresentation;

public class SenseMakerPresentation extends AbstractPresentation<SenseMakerModel>
{

    @Override
    public SenseMakerModel getModel()
    {
        return null;
    }

    //___________________________________
    // MODEL-VIEW DATA SYNCHRONIZATION :
    /**
     * This method takes the contents of the stored
     * model instance and copies their values
     * into the individual 'Property' instances!
     */
    @Override
    public void applyFromModel() {

    }

    /**
     * This method takes the values of the
     * stored 'Property' instances and copies their
     * values into the model instance of
     * this presentation.
     */
    @Override
    public void applyIntoModel() {

    }

    /**
     * This method means business!
     * It saves / updates the stored model through the business layer.
     * If the model in question has no id (==null),
     * then the model is expected to be stored as a new
     * entry on the DAL layer.
     * Otherwise it is expected that the model is
     * simply updated...
     */
    @Override
    public void persist() {

    }

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
    @Override
    public void restore() {

    }

}
