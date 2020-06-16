package sensemaker.businesslayer;

import sensemaker.datalayer.API.DAL;
import sensemaker.gui.models.base.EXIFModel;
import sensemaker.gui.models.base.IPTCModel;
import sensemaker.gui.models.base.PhotographerModel;
import sensemaker.gui.models.base.PictureModel;
import sensemaker.gui.models.composits.DetailedPictureModel;
import sensemaker.gui.presentation.subpresentation.DetailedPicturePresentation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Gatekeeper {

    private final DAL _dal;

    public Gatekeeper(DAL dal){
        _dal = dal;
    }

    public Date syncImages(String relativePath)
    {
        final String dir = System.getProperty("user.dir").replace("\\","/");
        String folderPath = dir+"/"+relativePath;
        System.out.println("current dir = " + folderPath);
        try (Stream<Path> walk = Files.walk(Paths.get(folderPath))) {

            List<String> result = walk
                    .map(Path::toString)
                    .filter(f -> f.endsWith(".png"))
                    .collect(Collectors.toList()
                    );

            result.forEach( p ->
            {
                File f = new File(p);
                EXIFModel exif = new EXIFModel();
                IPTCModel iptc = new IPTCModel().setTitle(f.getName());
                PhotographerModel pg = new PhotographerModel();

                _dal.access(EXIFModel.class).save(exif); // Creates new ids!
                _dal.access(IPTCModel.class).save(iptc);
                _dal.access(PhotographerModel.class).save(pg);

                assert exif.getId()!=null && iptc.getId()!=null && pg.getId()!=null;

                PictureModel pic = new PictureModel().setPath(p);
                pic.setEXIFId(exif.getId());
                pic.setIPTCId(iptc.getId());
                pic.setPhotographerId(pg.getId());

                _dal.access(PictureModel.class).save(pic);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //______________________________
    // MODEL : DetailedPictureModel

    /**
     * This Method accepts the "DetailedPictureModel" model.
     * The model is a composition of three sub-models, namely:
     * PictureModel,
     * IPTCModel,
     * EXIFModel,
     * PhotographerModel
     *
     * This given model is passed to the DAL layer where the contents
     * of the model are expected to be used as search parameters. (query building...)
     *
     * @param model The model containing the parameters used to perform the search operation!
     * @return A list of found "DetailedPictureModel" instances. (:= rows of the sql query)
     */
    public List<DetailedPictureModel> searchForDetailedPictures(DetailedPictureModel model)
    {
        return _dal.access(DetailedPictureModel.class).findBy(model);
    }

    /**
     * This method simply hands the given model
     * to the underlying DAL layer to perform a generic
     * "save" procedure.
     * If the model id is set then the model with
     * this id will simply be overwritten...
     * If however the getId method of the given model returns null,
     * then the DAL is expected to store the model as a new entry.
     * (It is also expected to set the id of the given model)
     *
     * @param model The model which ought to be saved.
     */
    public void saveDetailedPicture(DetailedPictureModel model)
    {
        _dal.access(DetailedPictureModel.class).save(model);
    }

    //______________________
    // MODEL : PictureModel

    /**
     * This given model is passed to the DAL layer where the contents
     * of the model are expected to be used as search parameters. (query building...)
     *
     * @param model The model containing the parameters used to perform the search operation!
     * @return A list of found model instances of the given type. (:= rows of the sql query)
     */
    public List<PictureModel> searchForPictures(PictureModel model)
    {
        return _dal.access(PictureModel.class).findBy(model);
    }

    /**
     * This method simply hands the given model
     * to the underlying DAL layer to perform a generic
     * "save" procedure.
     * If the model id is set then the model with
     * this id will simply be overwritten...
     * If however the getId method of the given model returns null,
     * then the DAL is expected to store the model as a new entry.
     * (It is also expected to set the id of the given model)
     *
     * @param model The model which ought to be saved.
     */
    public void savePicture(PictureModel model)
    {
        _dal.access(PictureModel.class).save(model);
    }

    //___________________
    // MODEL : IPTCModel

    /**
     * This given model is passed to the DAL layer where the contents
     * of the model are expected to be used as search parameters. (query building...)
     *
     * @param model The model containing the parameters used to perform the search operation!
     * @return A list of found model instances of the given type. (:= rows of the sql query)
     */
    public List<IPTCModel> searchForIPTC(IPTCModel model)
    {
        return _dal.access(IPTCModel.class).findBy(model);
    }

    /**
     * This method simply hands the given model
     * to the underlying DAL layer to perform a generic
     * "save" procedure.
     * If the model id is set then the model with
     * this id will simply be overwritten...
     * If however the getId method of the given model returns null,
     * then the DAL is expected to store the model as a new entry.
     * (It is also expected to set the id of the given model)
     *
     * @param model The model which ought to be saved.
     */
    public void saveIPTC(IPTCModel model)
    {
        _dal.access(IPTCModel.class).save(model);
    }

    //______________
    // MODEL : EXIF

    /**
     * This given model is passed to the DAL layer where the contents
     * of the model are expected to be used as search parameters. (query building...)
     *
     * @param model The model containing the parameters used to perform the search operation!
     * @return A list of found model instances of the given type. (:= rows of the sql query)
     */
    public List<EXIFModel> searchForPictures(EXIFModel model)
    {
        return _dal.access(EXIFModel.class).findBy(model);
    }

    /**
     * This method simply hands the given model
     * to the underlying DAL layer to perform a generic
     * "save" procedure.
     * If the model id is set then the model with
     * this id will simply be overwritten...
     * If however the getId method of the given model returns null,
     * then the DAL is expected to store the model as a new entry.
     * (It is also expected to set the id of the given model)
     *
     * @param model The model which ought to be saved.
     */
    public void saveEXIF(EXIFModel model)
    {
        _dal.access(EXIFModel.class).save(model);
    }

    //___________________________
    // MODEL : PhotographerModel

    /**
     * This given model is passed to the DAL layer where the contents
     * of the model are expected to be used as search parameters. (query building...)
     *
     * @param model The model containing the parameters used to perform the search operation!
     * @return A list of found model instances of the given type. (:= rows of the sql query)
     */
    public List<PhotographerModel> searchForPictures(PhotographerModel model)
    {
        return _dal.access(PhotographerModel.class).findBy(model);
    }

    /**
     * This method simply hands the given model
     * to the underlying DAL layer to perform a generic
     * "save" procedure.
     * If the model id is set then the model with
     * this id will simply be overwritten...
     * If however the getId method of the given model returns null,
     * then the DAL is expected to store the model as a new entry.
     * (It is also expected to set the id of the given model)
     *
     * @param model The model which ought to be saved.
     */
    public void savePhotographer(PhotographerModel model)
    {
        _dal.access(PhotographerModel.class).save(model);
    }

    public void restorePhotographer(PhotographerModel model)
    {
        List<PhotographerModel> found =
        _dal.access(
                PhotographerModel.class
        ).findBy(
                (model.getId()!=null)?model:new PhotographerModel().setId(model.getId())
        );
        if(!found.isEmpty()){

        }
    }

}
