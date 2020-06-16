package sensemaker.gui.view.nodes;

import javafx.scene.image.Image;
import sensemaker.gui.models.simple.PictureModel;

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
