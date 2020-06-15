package sensemaker.gui.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import sensemaker.gui.presentation.PictureListPresentation;
import sensemaker.gui.view.comps.ImageWrapper;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class PictureListView extends AbstractView<PictureListPresentation> implements Initializable
{
    private PictureListPresentation _presentation;

    @FXML
    public FlowPane imageHolder;

    private List<ImageView> _images;

    public PictureListView(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _images = List.of(
              new ImageView(),
              new ImageView(),
              new ImageView(),
              new ImageView(),
              new ImageView(),
              new ImageView(),
              new ImageView(),
              new ImageView()
        );
        imageHolder.getChildren().addAll(_images);
        _presentation = new PictureListPresentation(_images);
        imageHolder.setPadding(new Insets(5, 0, 5, 0));
        imageHolder.setVgap(4);
        imageHolder.setHgap(4);
        imageHolder.setPrefWrapLength(170); // preferred width allows for two columns
        imageHolder.setStyle("-fx-background-color: DAE6F3;");
        for(ImageView image : _images){
            // Drag and Drop start: (Finished insider inspection!)
            image.setOnDragDetected(event -> {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                Dragboard db = image.startDragAndDrop(TransferMode.ANY);

                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putImage(image.getImage());
                db.setContent(content);

                event.consume();
            });
        }
    }

    @Override
    protected void _bind(PictureListPresentation presentation) {

    }

    @Override
    protected PictureListPresentation getPresentation() {
        return _presentation;
    }

}
