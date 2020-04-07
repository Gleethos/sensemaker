package gui.view;

public abstract class AbstractView<PresentationType> {

    protected abstract void _bind(PresentationType presentation);

    protected abstract PresentationType getPresentation();

}
