/*
Copyright (c) <2013>, Intel Corporation All Rights Reserved.
 
The source code, information and material ("Material") contained herein is owned by Intel Corporation or its suppliers or licensors, and title to such Material remains with Intel Corporation
or its suppliers or licensors. The Material contains proprietary information of Intel or its suppliers and licensors. The Material is protected by worldwide copyright laws and treaty provisions. 
No part of the Material may be used, copied, reproduced, modified, published, uploaded, posted, transmitted, distributed or disclosed in any way without Intel's prior express written permission. 
No license under any patent, copyright or other intellectual property rights in the Material is granted to or conferred upon you, either expressly, by implication, inducement, estoppel or otherwise. 
Any license under such intellectual property rights must be express and approved by Intel in writing.
 
Unless otherwise agreed by Intel in writing, you may not remove or alter this notice or any other notice embedded in Materials by Intel or Intel’s suppliers or licensors in any way.
*/
package gui.view.subview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

import gui.view.interfaces.*;

public class PictureView extends Pane implements IChildItem{

    private IParentItem _parent;
    private boolean _moveInProgress = false;
    private int _touchPointId;
    private Point2D _prevPos;

    public PictureView(IParentItem parentContainer){
        super();
        _parent = parentContainer;
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("PictureGUI.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

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

    public void onTouchPressed(TouchEvent t) {
        if (!_moveInProgress) {
            if (_parent.getFocusedItem() != PictureView.this) {
                _parent.unfocusItem();
                _parent.focusItem(PictureView.this);
            }
            _moveInProgress = true;
            _touchPointId = t.getTouchPoint().getId();
            _prevPos = new Point2D(t.getTouchPoint().getSceneX(), t.getTouchPoint().getSceneY());
            System.out.println("TOUCH BEGIN " + t.toString());
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
            //JComponent content = this.RealmNode.getContent();
            _prevPos = currPos;
        }
        t.consume();
    }

    public void onTouchReleased(TouchEvent t) {
        if (t.getTouchPoint().getId() == _touchPointId) {
            _moveInProgress = false;
            System.err.println("TOUCH RELEASED " + t.toString());
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

    @Override
    public Node getNode() {
        return this;
    }

}