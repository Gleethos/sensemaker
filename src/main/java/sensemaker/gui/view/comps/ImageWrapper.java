package sensemaker.gui.view.comps;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sensemaker.gui.models.composits.DetailedPictureModel;

public class ImageWrapper extends Image
{
    private DetailedPictureModel _model;

    public ImageWrapper(String url) {
        super(url);
    }
    
    public DetailedPictureModel getDetailedPictureModel(){
        return _model;
    }

    public void setDetailedPictureModel(DetailedPictureModel model){
        _model = model;
    }

}
