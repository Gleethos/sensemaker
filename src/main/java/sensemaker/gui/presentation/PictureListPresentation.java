package sensemaker.gui.presentation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sensemaker.gui.models.base.PictureModel;
import sensemaker.gui.view.PictureListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PictureListPresentation extends AbstractPresentation<List<PictureModel>>
{
    private List<ImageView> _images = new ArrayList<>();

    public PictureListPresentation(List<ImageView> images){
        _images = images;
    }

    @Override
    public void refresh(List<PictureModel> models) {

        for(int i=0; i<_images.size() && i<models.size(); i++){
            try {
                _images.get(i).setImage(
                        new Image(
                                new File(models.get(i).getPath()).toURI().toURL().toExternalForm()
                        )
                );
                _images.get(i).setFitHeight(128);
                //_images.get(i).setFitWidth(64);
                _images.get(i).setPreserveRatio(true);
            } catch (Exception e) {
                _log.error(e.toString());
            }
        }

    }

    @Override
    public void applyChanges(List<PictureModel> model) {

    }
}
