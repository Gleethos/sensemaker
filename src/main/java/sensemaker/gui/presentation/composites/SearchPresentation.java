package sensemaker.gui.presentation.composites;

import sensemaker.gui.models.simple.EXIFModel;
import sensemaker.gui.models.simple.IPTCModel;
import sensemaker.gui.models.simple.PhotographerModel;
import sensemaker.gui.models.simple.PictureModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sensemaker.gui.models.composites.DetailedPictureModel;
import sensemaker.gui.presentation.AbstractPresentation;
import sensemaker.gui.presentation.lists.PictureListPresentation;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPresentation extends AbstractPresentation<DetailedPictureModel>
{
    //______________
    // PROPERTIES :

    private final StringProperty _display = new SimpleStringProperty();
    private final StringProperty _softSearch = new SimpleStringProperty();

    //_________
    // MODEL :

    DetailedPictureModel _model; // Dataholder for search queries!

    //___________
    // SIBLINGS:
    /**
     * The PictureListPresentation needs to be known in order
     * to pass the search results to it!
     */
    private PictureListPresentation _listPresentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public SearchPresentation()
    {
         _model = new DetailedPictureModel(
                 new PictureModel().setIsFoundSoftly(true),
                 new EXIFModel().setIsFoundSoftly(true),
                 new IPTCModel().setIsFoundSoftly(true),
                 new PhotographerModel().setIsFoundSoftly(true)
         ).setIsFoundSoftly(true); //:= Soft searching! :)
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
    public void applyFromModel()
    {
        _softSearch.setValue("Title: "+ _model.getIPTCModel().getTitle());
    }

    /**
     * This method takes the values of the
     * stored 'Property' instances and copies their
     * values into the model instance of
     * this presentation.
     */
    @Override
    public void applyIntoModel()
    {
        // The search data given by the user is too broad
        // in possible meaning.
        // It would not make sense to add the search key word
        // to any model field...
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
    public void persist()
    {
        _business().saveDetailedPicture(_model);
    }

    /**
     * Here the 'restore' method is being understood as a search operation.
     * Because that is what the restore functionality is ultimately doing anyways...
     *
     * It simply tries to restore the model fields in question via the business layer.
     * The model is expected to be filled by the corresponding DAL entries with the same id...
     * (WARNING):
     * If the id of the given model is not set (==null),
     * then the model will simply be filled by the
     * best matching entry the DAL can find! (:= search call with row-number == 1)
     *
     * ... for that reason the method is being understood as search call!
     */
    @Override
    public void restore()
    {
        String key = _softSearch.getValue();
        _model.getIPTCModel().setKeywords(key);
        _model.getIPTCModel().setTitle(key);
        _model.getIPTCModel().setCopyright(key);
        _model.getIPTCModel().setDescription(key);
        _model.getEXIFModel().setOrientation(key);
        _model.getPhotographerModel().setForename(key);
        _model.getPhotographerModel().setSurname(key);
        _model.getPictureModel().setPath(key);
        List<DetailedPictureModel> found = _business().searchForDetailedPictures(_model);
        _listPresentation.getModel().removeAll(_listPresentation.getModel());
        _listPresentation.getModel().addAll(
                found.stream().map(DetailedPictureModel::getPictureModel).collect(Collectors.toList())
        );
        _listPresentation.applyFromModel(); // Applies the newly modified model list!

    }

    //______________________________
    // PROPERTY GETTER AND SETTER :

    public String getDisplay() {
        return _display.get();
    }

    public StringProperty displayProperty() {
        return _display;
    }

    public String getSoftSearch() {
        return _softSearch.get();
    }

    public StringProperty softSearchProperty() {
        return _softSearch;
    }

    //______________________
    // PRESENTATION LOGIC :

    /**
     * This class needs to know about the PictureListPresentation
     * in order to pass the search result to it!
     * This method is called by the SearchView which
     * got the reference from the main controller/view :
     * Namels: SenseMakerView
     *
     * @param listPresentation The Presentation of the list of found pictures.
     */
    public void setPictureListPresentation(PictureListPresentation listPresentation){
        _listPresentation = listPresentation;
    }

}
