package sensemaker.gui.presentation.composites;

import javafx.scene.image.ImageView;
import sensemaker.gui.models.simple.EXIFModel;
import sensemaker.gui.models.simple.IPTCModel;
import sensemaker.gui.models.simple.PhotographerModel;
import sensemaker.gui.models.simple.PictureModel;
import sensemaker.gui.models.composites.DetailedPictureModel;
import sensemaker.gui.presentation.AbstractPresentation;
import sensemaker.gui.view.nodes.ImageWrapper;

import java.io.File;
import java.util.List;

public class DetailedPicturePresentation extends AbstractPresentation<DetailedPictureModel>
{
    //______________
    // PROPERTIES :

    private ImageView imageView;

    //_________
    // MODEL :

    private DetailedPictureModel _model;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public DetailedPicturePresentation(int id)
    {
        List<DetailedPictureModel> found  = _business().searchForDetailedPictures(
                new DetailedPictureModel(
                        new PictureModel().setId(id),
                        new EXIFModel(),
                        new IPTCModel(),
                        new PhotographerModel()
                )
        );
        if (found.isEmpty()){
            throw new IllegalStateException("DetailedPicturePresentation has not found the picture of the received id!");
        }
        _model = found.get(0);
    }

    //________________
    // MODEL GETTER :

    @Override
    public DetailedPictureModel getModel()
    {
        return _model;
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
        String path = _model.getPictureModel().getPath();
        ImageWrapper image;
        try {
            image = new ImageWrapper(new File(path).toURI().toURL().toExternalForm());
        } catch(Exception e) {
            _log.error(e);
            return;
        }
        imageView.setImage(image);
    }

    @Override
    public void applyIntoModel() {

    }

    @Override
    public void persist() {
        _business().saveDetailedPicture(_model);
    }

    @Override
    public void restore()
    {

    }

    //___________________
    // PROPERTY GETTER :


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }




}
