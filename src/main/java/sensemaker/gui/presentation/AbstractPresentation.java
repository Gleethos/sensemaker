package sensemaker.gui.presentation;

/**
 *
 */
public abstract class AbstractPresentation<ModelType> {

    public abstract void refresh(ModelType model);

    public abstract void applyChanges(ModelType model);




}
