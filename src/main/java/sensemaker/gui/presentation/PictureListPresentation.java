package sensemaker.gui.presentation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sensemaker.gui.models.base.PictureModel;
import sensemaker.gui.view.PictureListView;
import sensemaker.gui.view.comps.ImageWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PictureListPresentation extends AbstractPresentation<List<PictureModel>>
{
    //______________
    // PROPERTIES :

    private final List<ImageView> _images;

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

    @Override
    public void applyFromModel()
    {
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

    @Override
    public void applyIntoModel() {

    }

    @Override
    public void persist() {

    }

    @Override
    public void restore() {

    }


}
