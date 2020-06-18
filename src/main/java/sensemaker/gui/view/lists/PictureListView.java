package sensemaker.gui.view.lists;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import sensemaker.gui.presentation.lists.PictureListPresentation;
import sensemaker.gui.view.AbstractView;
import sensemaker.gui.view.nodes.ImageWrapper;

import java.util.ArrayList;
import java.util.List;

public class PictureListView extends AbstractView<PictureListPresentation>
{
    //____________________
    // FX-VIEW-ELEMENTS :

    @FXML
    public FlowPane imageHolder;


    private final List<ImageView> _images; // Dynamically placed and removed...

    //________________
    // PRESENTATION :

    private PictureListPresentation _presentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public PictureListView()
    {
        _images = new ArrayList<>();
        _presentation = new PictureListPresentation(_images);
    }

    //________________________
    // DEFAULT VIEW METHODS :

    /**
     * This method is used used for binding and initializing the view!
     * It is called by the 'initialize' method in the super class which is itself
     * called by the FXML-Loader after it instantiated this very view!
     *
     * Said 'initialize' method in the super class call an
     * expected implementation of a getter method
     * returning the corresponding presentation instance!
     * This instance must set in the constructor of this class! (see above)
     *
     * @param presentation The corresponding presentation type to this view type!
     */
    @Override
    protected void _bind(PictureListPresentation presentation)
    {
        _images.addAll(List.of(
                new ImageView(), new ImageView(),
                new ImageView(), new ImageView(),
                new ImageView(), new ImageView(),
                new ImageView(), new ImageView(),
                new ImageView(), new ImageView(),
                new ImageView(), new ImageView()
        ));
        imageHolder.getChildren().addAll(_images);
        _presentation = new PictureListPresentation(_images);
        imageHolder.setPadding(new Insets(5, 0, 5, 0));
        imageHolder.setVgap(4);
        imageHolder.setHgap(4);
        imageHolder.setPrefWrapLength(170); // preferred width allows for two columns
        imageHolder.setStyle("-fx-background-color: DAE6F3;");
        for(ImageView image : _images){
            // Drag and Drop start: (Finished insider inspection!)
            image.setOnDragDetected( event -> {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                if(image.getImage() instanceof ImageWrapper &&
                        ((ImageWrapper)image.getImage()).getPictureModel()!=null
                ){
                    Dragboard db = image.startDragAndDrop(TransferMode.ANY);

                    /* Put a string on a dragboard */
                    ClipboardContent content = new ClipboardContent();

                    content.putString(
                            Integer.toString(
                                    ((ImageWrapper)image.getImage())
                                            .getPictureModel().getId()
                            )
                    );
                    db.setContent(content);
                    event.consume();
                }
            });
        }
    }

    @Override
    public PictureListPresentation getPresentation()
    {
        return _presentation;
    }

    //______________
    // VIEW-LOGIC :

}
