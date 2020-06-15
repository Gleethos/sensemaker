package sensemaker.gui.models;

public abstract class AbstractSearchableModel<FinalModel> implements Model<FinalModel>
{

    private boolean _softSearch = false;

    // SEARCH PROPERTIES:

    @Override
    public boolean isFoundSoftly(){
        return _softSearch;
    }

    @Override
    public FinalModel setIsFoundSoftly(boolean softly){
        _softSearch = softly;
        return (FinalModel)this;
    }

}
