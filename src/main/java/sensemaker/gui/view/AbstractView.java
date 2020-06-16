package sensemaker.gui.view;

import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.apache.log4j.Logger;
import sensemaker.gui.presentation.AbstractPresentation;

import java.net.URL;
import java.util.ResourceBundle;


public abstract class AbstractView<PresentationType> implements Initializable
{
    /**
     * Used for logging in the view layer...
     */
    protected static Logger _log = org.apache.log4j.Logger.getLogger(AbstractView.class);

    //________________________________
    // PRESENTATION GETTER EXPECTED :

    /**
     * It is expected that a view always returns a valid presentation!
     * The return value ought not to be null!!!
     *
     * @return The corresponding presentation type of this view!
     */
    protected abstract PresentationType getPresentation();

    //__________________________
    // DEFAULT INITIALIZATION :

    /**
     * This method calls the _bind method.
     * It expects there to be a getPresentation() implementation
     * which does not return null...
     *
     * @param location Current location!
     * @param resources Attached resources...
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _bind(getPresentation());
    }

    //__________________________________________________
    // EXPECTED BIND AND INITIALIZATION IMPLEMENTATION:

    /**
     * Every view instance is expected to implement this method for binding.
     * It is called by the 'initialize' method which is itself
     * called by the FXML-Loader after it instantiated this very view!
     *
     * @param presentation The corresponding presentation type to this view type!
     */
    protected abstract void _bind(PresentationType presentation);


    //___________________
    // BINFING UTILITY :

    /**
     * This Method analyzes two objects and
     * finds a way to bind them!
     *
     * @param first Anything that can be bound!
     * @param second Anything that can be bound!
     */
    protected void _bidi(Object first, Object second) {
        StringProperty sp1 = _findStringProperty(first);
        StringProperty sp2 = _findStringProperty(second);
        if (sp1!=null && sp2!=null) {
           sp1.bindBidirectional(sp2);
        }
    }

    /**
     * This is a private helper method for the _bidi method
     * used to bind Property instances more easily in
     * classes inheriting this one...
     *
     * @param o An object fromm which a Property object ought to be found!
     * @return A found Property instance from the given Object...
     */
    private StringProperty _findStringProperty(Object o)
    {
        if(o instanceof StringProperty) return (StringProperty)o;
        if(o instanceof TextField) return ((TextField)o).textProperty();
        else if(o instanceof Text) return ((Text)o).textProperty();
        return null;
    }

}
