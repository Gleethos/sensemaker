package sensemaker.gui.presentation.subpresentation;

import sensemaker.gui.models.base.EXIFModel;
import sensemaker.gui.models.base.IPTCModel;
import sensemaker.gui.models.base.PhotographerModel;
import sensemaker.gui.models.base.PictureModel;
import sensemaker.gui.models.composits.DetailedPictureModel;
import sensemaker.gui.presentation.AbstractPresentation;

import java.util.List;

public class DetailedPicturePresentation extends AbstractPresentation<DetailedPictureModel>
{
    //______________
    // PROPERTIES :

    // This is the representation of a composite...

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

    @Override
    public void applyFromModel() {

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






}
