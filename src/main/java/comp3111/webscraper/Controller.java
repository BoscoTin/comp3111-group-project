/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;
import java.util.List;


/**
 * 
 * @author kevinw
 *
 *
 * Controller class that manage GUI interaction. Please see document about JavaFX for details.
 * 
 */
public class Controller {

    @FXML 
    private Label labelCount; 

    @FXML 
    private Label labelPrice; 

    @FXML 
    private Hyperlink labelMin; 

    @FXML 
    private Hyperlink labelLatest; 

    @FXML
    private TextField textFieldKeyword;
    
    @FXML
    private TextArea textAreaConsole;
    
    private WebScraper scraper;
    
    // Bosco add these attribute
    private Search[] search;
    private int searchNo;
    
    /**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();
    	
    	// for bosco advanced 3
    	search = new Search[5];
    	searchNo = 0;
    }

    /**
     * Default initializer. It is empty.
     */
    @FXML
    private void initialize() {
    	
    }
    
    /**
     * Called when the search button is pressed.
     */
    @FXML
    private void actionSearch() {
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	
    	// Bosco changed these part
    	Search s = new Search();
    	s.setKeyword(textFieldKeyword.getText());
    	ItemList itemList = new ItemList(scraper.scrape(textFieldKeyword.getText()) );
    	s.setItemList(itemList);
    	
    	search[searchNo] = s;
    	searchNo++;
    	
    	String output = "";
    	
    	
    	// Bosco changed the for loop
    	ItemList iList = search[searchNo - 1].getItemList();
    	for (int i = 0; i < iList.getQuantity(); i++) {
    		Item item = iList.getItem(i);
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
    	}
    	
    	textAreaConsole.setText(output);
    }
    
    /**
     * Called when the new button is pressed. Very dummy action - print something in the command prompt.
     */
    @FXML
    private void actionNew() {
    	System.out.println("actionNew");
    }
}

