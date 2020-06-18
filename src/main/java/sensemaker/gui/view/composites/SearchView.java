package sensemaker.gui.view.composites;

import javafx.scene.control.Button;
import sensemaker.gui.presentation.lists.PictureListPresentation;
import sensemaker.gui.presentation.composites.SearchPresentation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sensemaker.gui.view.AbstractView;

/**
 *
 */
public class SearchView extends AbstractView<SearchPresentation> implements Initializable
{
    //____________________
    // FX-VIEW-ELEMENTS :

    @FXML private TextField _softSearchTextField;
    @FXML private Text _displayText;
    @FXML private Button _searchButton;

    //________________
    // PRESENTATION :

    private final SearchPresentation _presentation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public SearchView()
    {
        super();
        _presentation = new SearchPresentation();
        _log.info("New SearchView instantiated!");
    }

    //________________________
    // DEFAULT VIEW METHODS :

    /**
     * This method is used used for binding and initializing the view!
     * It is called by the 'initialize' method in the super class which is itself
     * called by the FXML-Loader after it instantiated this very view!
     *
     * Said 'initialize' method in the super class call an
     * expected implementation of a getter method
     * returning the corresponding presentation instance!
     * This instance must set in the constructor of this class! (see above)
     *
     * @param presentation The corresponding presentation type to this view type!
     */
    @Override
    protected void _bind(SearchPresentation presentation)
    {
        _softSearchTextField.textProperty().addListener(s -> {
            //if(_presentation.)
            //_displayText.setText(String.valueOf(_softSearchTextField.textProperty().getValue()));
        });
        _bidi(presentation.displayProperty(), _displayText.textProperty());
        _bidi(presentation.softSearchProperty(), _softSearchTextField.textProperty());
        _searchButton.setOnAction(e -> presentation.restore() );
    }

    @Override
    public SearchPresentation getPresentation()
    {
        return _presentation;
    }

    //______________
    // VIEW-LOGIC :

    public void setPictureListPresentation(PictureListPresentation listPresentation)
    {
        _presentation.setPictureListPresentation(listPresentation);
    }

}
