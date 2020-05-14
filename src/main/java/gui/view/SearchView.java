package gui.view;

import gui.presentation.SearchPresentation;
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
    @FXML private TextField nameSearchTextField;
    @FXML private TextField dateSearchTextField;
    @FXML private TextField creatorSearchTextField;
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
        _bidi(creatorSearchTextField, displayText);
        dateSearchTextField.textProperty().addListener((s)->{
            _log.info("firstname changes! to : "+s);
        });
    }

    @Override
    protected SearchPresentation getPresentation() {
        return _presentation;
    }


}
