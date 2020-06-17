package sensemaker.businesslayer;

import sensemaker.datalayer.API.DAL;
import sensemaker.gui.models.AbstractModel;
import sensemaker.gui.models.Model;
import sensemaker.gui.models.simple.EXIFModel;
import sensemaker.gui.models.simple.IPTCModel;
import sensemaker.gui.models.simple.PhotographerModel;
import sensemaker.gui.models.simple.PictureModel;
import sensemaker.gui.models.composites.DetailedPictureModel;
import sensemaker.gui.view.subview.DetailedPictureView;

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
        int[] index = {1};
        try (Stream<Path> walk = Files.walk(Paths.get(folderPath))) {

            List<String> result = walk
                    .map(Path::toString)
                    .filter(f -> f.endsWith(".png"))
                    .collect(Collectors.toList()
                    );

            result.forEach( p ->
            {
                String seed = (relativePath.equals("test_images"))?"":relativePath+index[0];
                File f = new File(p);
                EXIFModel exif = new EXIFModel().completeRandomly(seed);
                IPTCModel iptc = new IPTCModel().setTitle(f.getName()).completeRandomly(seed);
                PhotographerModel pg = new PhotographerModel().completeRandomly(seed);

                index[0]++;

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

    /**
     * This method tries to restore the given model contents from
     * the DAL!
     * It uses the contents of the provided model to find
     * the most similar data entry and uses it to fill the up the
     * the model...
     *
     * @param model The model which ought to be restored.
     */
    public void restorePicture(PictureModel model)
    {
        List<PictureModel> found =
                _dal.access(
                        PictureModel.class
                ).findBy(
                        (model.getId()!=null)?model:new PictureModel().setId(model.getId())
                );
        if(!found.isEmpty()){
            PictureModel first = found.get(0);
            model.setId(first.getId());
            model.setCreated(first.getCreated());
            model.setDeleted(first.getDeleted());
            model.setPath(first.getPath());
        }
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
    public List<IPTCModel> searchForIPTCs(IPTCModel model)
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

    /**
     * This method tries to restore the given model contents from
     * the DAL!
     * It uses the contents of the provided model to find
     * the most similar data entry and uses it to fill the up the
     * the model...
     *
     * @param model The model which ought to be restored.
     */
    public void restoreIPTC(IPTCModel model)
    {
        List<IPTCModel> found =
                _dal.access(
                        IPTCModel.class
                ).findBy(
                        (model.getId()!=null)?model:new IPTCModel().setId(model.getId())
                );
        if(!found.isEmpty()){
            IPTCModel first = found.get(0);
            model.setId(first.getId());
            model.setCreated(first.getCreated());
            model.setDeleted(first.getDeleted());
            model.setCopyright(first.getCopyright());
            model.setDescription(first.getDescription());
            model.setKeywords(first.getKeywords());
            model.setTitle(first.getTitle());
        }
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
    public List<EXIFModel> searchForEXIFs(EXIFModel model)
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

    /**
     * This method tries to restore the given model contents from
     * the DAL!
     * It uses the contents of the provided model to find
     * the most similar data entry and uses it to fill the up the
     * the model...
     *
     * @param model The model which ought to be restored.
     */
    public void restoreEXIF(EXIFModel model)
    {
        List<EXIFModel> found =
                _dal.access(
                        EXIFModel.class
                ).findBy(
                        (model.getId()!=null)?model:new EXIFModel().setId(model.getId())
                );
        if(!found.isEmpty()){
            EXIFModel first = found.get(0);
            model.setId(first.getId());
            model.setCreated(first.getCreated());
            model.setDeleted(first.getDeleted());
            model.setOrientation(first.getOrientation());
            model.setShot(first.getShot());
        }
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
    public List<PhotographerModel> searchForPhotographers(PhotographerModel model)
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

    /**
     * This method tries to restore the given model contents from
     * the DAL!
     * It uses the contents of the provided model to find
     * the most similar data entry and uses it to fill the up the
     * the model...
     *
     * @param model The model which ought to be restored.
     */
    public void restorePhotographer(PhotographerModel model)
    {
        List<PhotographerModel> found =
        _dal.access(
                PhotographerModel.class
        ).findBy(
                (model.getId()!=null)?model:new PhotographerModel().setId(model.getId())
        );
        if(!found.isEmpty()){
            PhotographerModel first = found.get(0);
            model.setId(first.getId());
            model.setCreated(first.getCreated());
            model.setDeleted(first.getDeleted());
            model.setForename(first.getForename());
            model.setSurname(first.getSurname());
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //__________________________________
    // GENERIC MODEL :

    /**
     * This given model is passed to the DAL layer where the contents
     * of the model are expected to be used as search parameters. (query building...)
     *
     * @param model The model containing the parameters used to perform the search operation!
     * @return A list of found model instances of the given type. (:= rows of the sql query)
     */
    public <T> List<T> searchFor(T model)
    {
        if(model instanceof PhotographerModel) return (List<T>) searchForPhotographers((PhotographerModel) model);
        if(model instanceof EXIFModel) return (List<T>) searchForEXIFs((EXIFModel) model);
        if(model instanceof IPTCModel) return (List<T>) searchForIPTCs((IPTCModel)model);
        if(model instanceof PictureModel) return (List<T>) searchForPictures((PictureModel)model);
        if(model instanceof DetailedPictureModel) return (List<T>) searchForDetailedPictures((DetailedPictureModel)model);
        return null;
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
    public void save(Model model)
    {
        if(model instanceof PhotographerModel) savePhotographer((PhotographerModel) model);
        if(model instanceof EXIFModel) saveEXIF((EXIFModel) model);
        if(model instanceof IPTCModel) saveIPTC((IPTCModel)model);
        if(model instanceof PictureModel) savePicture((PictureModel)model);
        if(model instanceof DetailedPictureModel) saveDetailedPicture((DetailedPictureModel)model);
    }

    /**
     * This method tries to restore the given model contents from
     * the DAL!
     * It uses the contents of the provided model to find
     * the most similar data entry and uses it to fill the up the
     * the model...
     *
     * @param model The model which ought to be restored.
     */
    public <T> void restore(T model)
    {
        if(model instanceof PhotographerModel) restorePhotographer((PhotographerModel)model);
        if(model instanceof EXIFModel) restoreEXIF((EXIFModel) model);
        if(model instanceof IPTCModel) restoreIPTC((IPTCModel)model);
        if(model instanceof PictureModel) restorePicture((PictureModel)model);
        //if(model instanceof DetailedPictureModel) restoreDetailedPicture((DetailedPictureModel)model);
    }

}
