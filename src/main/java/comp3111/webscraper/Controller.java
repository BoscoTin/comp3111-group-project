/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;
//task5&6
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
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
    
    @FXML
//    task6
    private MenuItem MenuItemLastSearch;
    
    @FXML
//    task5
    private Button ButtonRefine;
    
//    task5
    private String last;
    
    private WebScraper scraper;
    
    /**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();
    }

    /**
     * Default initializer. It is empty.
     * set every thing empty
     */
    @FXML
    private void initialize() {
    	labelCount.setText("");
    	labelPrice.setText("");
    	labelMin.setText("");
    	labelLatest.setText("");
    	textAreaConsole.setText("");
    	textFieldKeyword.setText("");
//    	task6
    	last="";
    	MenuItemLastSearch.setDisable(true);
//    	task5
    	ButtonRefine.setDisable(true);
    }
    
    /**
     * Called when the search button is pressed.
     */
    @FXML
    private void actionSearch() {
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText());
    	String output = "";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
    	}
    	textAreaConsole.setText(output);
//    	task6
    	MenuItemLastSearch.setDisable(false);
    	last = textFieldKeyword.getText();
//    	task5
    	ButtonRefine.setDisable(false);

    	
//    	labelCount.setText("Hi"); //lab5
//    	
//    	labelPrice.setText("HiHi"); //bosco lab5
    }
    /**task6
     * Called when the Refine button is pressed.
     */
    @FXML
    private void actionRefine() {
    	System.out.println("actionRefine: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText()+" "+last);
    	String output = "";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
    	}
    	textAreaConsole.setText(output);
    	ButtonRefine.setDisable(true);
    }
    
    /**task6
     * Called when the Last Search button is pressed. Very dummy action - print something in the command prompt.
     */
    @FXML
    private void actionLast() {
    	System.out.println("actionLast");
    	List<Item> result = scraper.scrape(last);
    	textFieldKeyword.setText(last);
    	String output = "";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
    	}
    	textAreaConsole.setText(output);
    	last="";
    	MenuItemLastSearch.setDisable(true);
    }
    
    /**task6
     * Called when the button is pressed. refer to ui.fxml MenuBar
     */
    @FXML
    private void actionClose() {
    	System.out.println("actionClose");
    	initialize();
    }
    @FXML
    private void actionQuit() {
    	System.out.println("actionQuit");
    	System.exit(0);     
    }
    @FXML
    private void actionAbout() {
    	System.out.println("actionAbout");
    	Alert dg =new Alert(Alert.AlertType.INFORMATION);
    	dg.setTitle("About My Team");
    	dg.setContentText("Bosco, Felix, Cheung\n"
    			+ "wttang, cfyauab, tkcheungad\n"
    			+ "BoscoTin, ycfelix, LLLLinda\n");
    	dg.setHeaderText("Up to down: Team members Name, Itsc and Github");
    	dg.show();
    	
    }
}

