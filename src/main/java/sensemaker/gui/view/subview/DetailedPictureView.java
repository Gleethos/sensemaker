package sensemaker.gui.view.subview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import sensemaker.gui.presentation.composites.DetailedPicturePresentation;
import sensemaker.gui.view.interfaces.*;

public class DetailedPictureView extends Pane implements IChildItem, Initializable
{
    //__________________
    // TOUCH ELEMENTS :

    private IParentItem _parent;
    private boolean _moveInProgress = false;
    private int _touchPointId;
    private Point2D _prevPos;

    //____________________
    // FX-VIEW-ELEMENTS :

    @FXML
    Button closeBtn;

    //________________
    // PRESENTATION :

    private DetailedPicturePresentation _presentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public DetailedPictureView(IParentItem parentContainer, int id)
    {
        super();
        _presentation = new DetailedPicturePresentation(id);
        _parent = parentContainer;
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("composites/DetailedPictureGUI.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        closeBtn.setOnAction( e -> _parent.remove(this));
    }


    //______________
    // VIEW LOGIC :

    @Override
    public void onUnselected() {
        getStyleClass().clear();
        getStyleClass().add("mainFxmlClassUnselected");
    }

    @Override
    public void onSelected() {
        getStyleClass().clear();
        getStyleClass().add("mainFxmlClassSelected");
    }

    public void onTouchPressed(TouchEvent t)
    {
        if (!_moveInProgress) {
            if (_parent.getFocusedItem() != DetailedPictureView.this) {
                _parent.unfocusItem();
                _parent.focusItem(DetailedPictureView.this);
            }
            _moveInProgress = true;
            _touchPointId = t.getTouchPoint().getId();
            _prevPos = new Point2D(t.getTouchPoint().getSceneX(), t.getTouchPoint().getSceneY());
        }
        t.consume();
    }

    public void onTouchMoved(TouchEvent t) {
        if (_moveInProgress && t.getTouchPoint().getId() == _touchPointId) {
            //this part should be oprimized in a praoduction code but here in order to present the steps i took a more verbose approach
            Point2D currPos = new Point2D(t.getTouchPoint().getSceneX(), t.getTouchPoint().getSceneY());
            double[] translationVector = new double[2];
            translationVector[0] = currPos.getX() - _prevPos.getX();
            translationVector[1] = currPos.getY() - _prevPos.getY();
            //i used this instead of setTranslate* because we don't care about the original position of the object and aggregating _translation
            //will require having another variable
            setTranslateX(getTranslateX() + translationVector[0]);
            setTranslateY(getTranslateY() + translationVector[1]);
            _prevPos = currPos;
        }
        t.consume();
    }

    public void onTouchReleased(TouchEvent t) {
        if (t.getTouchPoint().getId() == _touchPointId) {
            _moveInProgress = false;
        }
        t.consume();
    }

    @FXML
    public void onScroll(ScrollEvent t) {
        t.consume();
    }

    @FXML
    public void onSwipe(SwipeEvent t) {
        t.consume();
    }

    @FXML
    public void onDrag(MouseEvent t) {
        //this part should be optimized in a production code but here in order to present the steps i took a more verbose approach
        Point2D currPos = new Point2D(t.getSceneX(), t.getSceneY());
        double[] translationVector = new double[2];
        translationVector[0] = (_prevPos!=null)?currPos.getX() - _prevPos.getX():0;
        translationVector[1] = (_prevPos!=null)?currPos.getY() - _prevPos.getY():0;
        //i used this instead of setTranslate* because we don't care about the original position of the object and aggregating _translation
        //will require having another variable
        setTranslateX(getTranslateX() + translationVector[0]);
        setTranslateY(getTranslateY() + translationVector[1]);
        _prevPos = currPos;
        t.consume();
    }

    @Override
    public Node getNode() {
        return this;
    }

}