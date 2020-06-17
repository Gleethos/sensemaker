package sensemaker.gui.models.simple;

import sensemaker.gui.models.AbstractModel;

import java.sql.Date;
import java.util.Map;

public class EXIFModel extends AbstractModel<EXIFModel> {

    private Date _shot;
    private String _orientation;

    public EXIFModel()
    {
        super(null, null, null);
    }

    public Date getShot() {
        return _shot;
    }

    public EXIFModel setShot(Date date){
        _shot = date;
        return this;
    }

    public String getOrientation() {
        return _orientation;
    }

    public EXIFModel setOrientation(String orientation) {
        _orientation = orientation;
        return this;
    }

    // SQL :

    @Override
    public <T> Map<String,T> generatePreparedSQLKeyValues(Class<T> type) {
        Map<String,T> list = _defaultPreparedKeyValues(type);
        if(type.isInstance(_orientation)) list.put(getAsTableName()+".orientation = ?",(T) _orientation);
        if(type.isInstance(_shot)) list.put(getAsTableName()+".shot = ?",(T)_shot);
        return list;
    }

    /**
     * This method should be handled with caution as it
     * generates random values for model fields that have not been set!
     * It uses a seed String as a source of pseudo randomness...
     *
     * @param seed A String from which its hash code is used as source of pseudo randomness
     * @return The instance itself. := Factory Pattern!
     */
    @Override
    public EXIFModel completeRandomly(String seed) {
        int index = (getId()==null)?0:getId();
        if(seed!=null && !seed.equals("")) index = (index+1) * seed.hashCode();
        if (getOrientation() == null) {
            String[] orientations = new String[]{
                    "Right", "Left", "Up", "Down", "Inverted", "Horizontal"
            };
            setOrientation(orientations[Math.abs(index) % orientations.length]);
            index = index + index * index;
        }
        if(getShot()==null) {
            String[] surnames = new String[]{
                    "2020-06-17", "2020-03-14", "2019-04-03", "1998-10-24", "2021-07-17", "2022-11-10"
            };
            setShot(Date.valueOf(surnames[Math.abs(index) % surnames.length]));
        }
        return this;
    }

    @Override
    public String getAsTableName(){
        return "EXIFs";
    }

}
