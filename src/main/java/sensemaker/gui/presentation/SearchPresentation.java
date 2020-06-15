package sensemaker.gui.presentation;

import sensemaker.gui.models.base.EXIFModel;
import sensemaker.gui.models.base.IPTCModel;
import sensemaker.gui.models.base.PhotographerModel;
import sensemaker.gui.models.base.PictureModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sensemaker.gui.models.composits.DetailedPictureModel;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPresentation extends AbstractPresentation<DetailedPictureModel>
{

    private StringProperty displayProperty;
    private StringProperty softSearchProperty;

    DetailedPictureModel _detailedModel; // Dataholder for search queries!

    private PictureListPresentation _listPresentation;

    public SearchPresentation()
    {
         _detailedModel = new DetailedPictureModel(
                 new PictureModel().setIsFoundSoftly(true),
                 new EXIFModel().setIsFoundSoftly(true),
                 new IPTCModel().setIsFoundSoftly(true),
                 new PhotographerModel().setIsFoundSoftly(true)
         ).setIsFoundSoftly(true); //:= Soft searching! :)

        softSearchProperty = new SimpleStringProperty("INI VALUE");
        displayProperty = new SimpleStringProperty("INI VALUE");

        refresh(_detailedModel);
    }

    public void setPictureListPresentation(PictureListPresentation listPresentation){
        _listPresentation = listPresentation;
    }

    public void search(){
        String key = softSearchProperty.getValue();
        _detailedModel.getIPTCModel().setKeywords(key);
        _detailedModel.getIPTCModel().setTitle(key);
        _detailedModel.getIPTCModel().setCopyright(key);
        _detailedModel.getIPTCModel().setDescription(key);
        _detailedModel.getEXIFModel().setOrientation(key);
        _detailedModel.getPhotographerModel().setForename(key);
        _detailedModel.getPhotographerModel().setSurname(key);
        _detailedModel.getPictureModel().setPath(key);
        List<DetailedPictureModel> found = _business().searchForPictures(_detailedModel);

        _listPresentation.refresh(
                found.stream().map(d->d.getPictureModel()).collect(Collectors.toList())
        );

    }

    @Override
    public void refresh(DetailedPictureModel model)
    {
        _detailedModel = model;
        softSearchProperty.setValue("Title: "+model.getIPTCModel().getTitle());
        //softSearchProperty.addListener(s -> {
        //    System.out.println("Searching");
        //    displayProperty.setValue(softSearchProperty.getValue());
        //});
    }

    @Override
    public void applyChanges(DetailedPictureModel model) {

    }

    // GETTER:


    public String getDisplayProperty() {
        return displayProperty.get();
    }

    public StringProperty displayProperty() {
        return displayProperty;
    }

    public String getSoftSearchProperty() {
        return softSearchProperty.get();
    }

    public StringProperty softSearchProperty() {
        return softSearchProperty;
    }

}
