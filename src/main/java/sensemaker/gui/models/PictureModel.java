package sensemaker.gui.models;

/**
 *
 */
public class PictureModel extends AbstractModel{

    private String _firstName;
    private String _lastName;

    public PictureModel() {

    }

    public PictureModel(String firstName, String lastName) {
        this._firstName = firstName;
        this._lastName = lastName;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        this._lastName = lastName;
    }



}
