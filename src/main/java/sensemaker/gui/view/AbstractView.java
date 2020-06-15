package sensemaker.gui.view;

import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.apache.log4j.Logger;
import sensemaker.gui.presentation.AbstractPresentation;


public abstract class AbstractView<PresentationType>{

    protected static Logger _log = org.apache.log4j.Logger.getLogger(AbstractView.class);

    protected abstract void _bind(PresentationType presentation);

    protected abstract PresentationType getPresentation();

    protected void _bidi(Object first, Object second) {
        StringProperty sp1 = _findStringProperty(first);
        StringProperty sp2 = _findStringProperty(second);
        if (sp1!=null && sp2!=null) {
           sp1.bindBidirectional(sp2);
        }
    }

    private StringProperty _findStringProperty(Object o)
    {
        if(o instanceof StringProperty) return (StringProperty)o;
        if(o instanceof TextField) return ((TextField)o).textProperty();
        else if(o instanceof Text) return ((Text)o).textProperty();
        return null;
    }

}
