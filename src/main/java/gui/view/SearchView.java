package gui.view;

import gui.presentation.SearchPresentation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    
    @FXML private TextField searchTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private Text displayText;

    private final SearchPresentation _presentation;

    public SearchView() {
        super();
        _presentation = new SearchPresentation();
        _log.info("New SearchView instantiated!");
    }

    @Override
    public void initialize (
            URL location,
            ResourceBundle resources
    ) {
        _bind(_presentation);
    }

    @Override
    protected void _bind(SearchPresentation presentation) {
        _bidi(lastNameTextField, displayText);
        //lastNameTextField.textProperty().bindBidirectional(displayText.textProperty());
        firstNameTextField.textProperty().addListener((s)->{
            _log.info("firstname changes! to : "+s);
        });
    }

    @Override
    protected SearchPresentation getPresentation() {
        return _presentation;
    }


}
