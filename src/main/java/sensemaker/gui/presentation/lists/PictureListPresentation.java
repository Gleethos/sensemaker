package sensemaker.gui.presentation.lists;

import javafx.scene.image.ImageView;
import sensemaker.gui.models.simple.PictureModel;
import sensemaker.gui.presentation.AbstractPresentation;
import sensemaker.gui.view.nodes.ImageWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *  This class is generalized by
 *  the 'AbstractPresentation' class!
 */
public class PictureListPresentation extends AbstractPresentation<List<PictureModel>>
{
    //______________
    // PROPERTIES :

    private final List<ImageView> _images; // The fx element is used as/like a property!

    //_________
    // MODEL :

    private final List<PictureModel> _models;


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public PictureListPresentation(List<ImageView> images)
    {
        _images = images;
        _models = new ArrayList<>();
    }

    //________________
    // MODEL GETTER :

    @Override
    public List<PictureModel> getModel()
    {
        return _models;
    }

    //_____________________________
    // MODEL-VIEW SYNCHRONIZATION :

    /**
     * This method takes the contents of the stored
     * model instance and copies their values
     * into the individual 'Property' instances!
     */
    @Override
    public void applyFromModel()
    {
        for(int i=0; i<_images.size(); i++) {
            _images.get(i).setImage(null); // := Removing all images...
        }
        for(int i=0; i<_images.size() && i<_models.size(); i++){
            try {
                _images.get(i).setImage(
                        new ImageWrapper(
                                new File(_models.get(i).getPath()).toURI().toURL().toExternalForm()
                        ).setPictureModel(_models.get(i))
                );
                _images.get(i).setFitHeight(128);
                _images.get(i).setPreserveRatio(true);
            } catch (Exception e) {
                _log.error(e.toString());
            }
        }
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
