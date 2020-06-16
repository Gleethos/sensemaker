package sensemaker.gui.view;

import javafx.scene.control.Button;
import sensemaker.gui.presentation.PictureListPresentation;
import sensemaker.gui.presentation.SearchPresentation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class SearchView extends AbstractView<SearchPresentation> implements Initializable
{
    //____________________
    // FX-VIEW-ELEMENTS :

    @FXML private TextField softSearchTextField;
    @FXML private Text displayText;
    @FXML private Button searchButton;

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

    @Override
    protected void _bind(SearchPresentation presentation)
    {
        softSearchTextField.textProperty().addListener( s -> {
            //if(_presentation.)
            displayText.setText(String.valueOf(softSearchTextField.textProperty().getValue()));
        });
        _bidi(presentation.displayProperty(), displayText.textProperty());
        _bidi(presentation.softSearchProperty(), softSearchTextField.textProperty());
        searchButton.setOnAction( e -> presentation.restore() );
    }

    @Override
    protected SearchPresentation getPresentation()
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
