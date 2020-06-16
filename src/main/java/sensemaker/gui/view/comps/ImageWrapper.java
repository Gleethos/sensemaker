package sensemaker.gui.view.comps;

import javafx.scene.image.Image;
import sensemaker.gui.models.base.PictureModel;
import sensemaker.gui.models.composits.DetailedPictureModel;

public class ImageWrapper extends Image
{
    private PictureModel _model;

    public ImageWrapper(String url) {
        super(url);
    }
    
    public PictureModel getPictureModel(){
        return _model;
    }

    public ImageWrapper setPictureModel(PictureModel model){
        _model = model;
        return this;
    }

}
