package sensemaker.gui.models.simple;

import sensemaker.gui.models.AbstractModel;

import java.util.Map;

public class IPTCModel extends AbstractModel<IPTCModel> {

    private String _title;
    private String _description;
    private String _copyright;
    private String _keywords;

    public IPTCModel()
    {
        super(null, null, null);
    }

    public String getTitle() {
        return _title;
    }

    public IPTCModel setTitle(String title) {
        this._title = title;
        return this;
    }

    public String getDescription() {
        return _description;
    }

    public IPTCModel setDescription(String description) {
        this._description = description;
        return this;
    }

    public String getCopyright() {
        return _copyright;
    }

    public IPTCModel setCopyright(String copyright) {
        this._copyright = copyright;
        return this;
    }

    public String getKeywords() {
        return _keywords;
    }

    public IPTCModel setKeywords(String keywords) {
        this._keywords = keywords;
        return this;
    }

    // SQL :

    @Override
    public <T> Map<String,T> generatePreparedSQLKeyValues(Class<T> type) {
        Map<String,T> map = _defaultPreparedKeyValues(type);
        if(type.isInstance(_copyright)) map.put(getAsTableName()+".copyright = ?",(T) _copyright);
        if(type.isInstance(_description)) map.put(getAsTableName()+".description = ?",(T) _description);
        if(type.isInstance(_keywords)) map.put(getAsTableName()+".keywords = ?",(T)_keywords);
        if(type.isInstance(_title)) map.put(getAsTableName()+".title = ?",(T)_title);
        return map;
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
    public IPTCModel completeRandomly(String seed) {
        int index = (getId()==null)?0:getId();
        if(seed!=null && !seed.equals("")) index = (index+1) * seed.hashCode();
        if (getTitle() == null) {
            String[] titles = new String[]{
                    "Beyond Resolution!", "I ROCK", "Look at Me",
                    "Go Fast!", "Blue Soul", "Me and My Friends",
                    "Banana", "Beany Title", "I am a Title", "I am an awesome Title! :)"
            };
            setTitle(titles[Math.abs(index) % titles.length]);
            index = index + index * index;
        }
        if(getCopyright()==null) {
            String[] surnames = new String[]{
                    "MIT", "Apache", "GNU", "Royalty Free"
            };
            setCopyright(surnames[Math.abs(index) % surnames.length]);
            index = index + index * index;
        }
        if(getDescription()==null) {
            String[] discriptions = new String[]{
                    "A beautiful picture", "...interesting...", "...what a day...", "That was fun!"
            };
            setDescription(discriptions[Math.abs(index) % discriptions.length]);
            index = index + index * index;
        }
        if(getKeywords()==null) {
            String[] keywords = new String[]{
                    "#Awesome", "#WhatIsGoingOn", "#SUCCESS", "#Sunny", "#SimplyMe", "#Rollin"
            };
            setKeywords(keywords[Math.abs(index) % keywords.length]);
        }
        return this;
    }

    @Override
    public String getAsTableName() {
        return "IPTCs";
    }
}
