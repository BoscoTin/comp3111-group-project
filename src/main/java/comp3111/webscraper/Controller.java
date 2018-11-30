/**
 * 
 */
package comp3111.webscraper;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
//task5&6
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.web.WebView;
import javafx.application.HostServices;
import javafx.scene.web.WebEngine;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


/**
 * This provides basic control to GUI components
 */
public class Controller {

	@FXML
	private TableView<Item> Table;

	@FXML
	private TableColumn<Item,String> Title;

	@FXML
	private TableColumn<Item,Double> Price;
	@FXML
	private TableColumn<Item,Hyperlink> URL;
	@FXML
	private TableColumn<Item, String> Posted_Date;


	
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
    
    // Bosco add these attribute
    private Search[] search;
    private int searchNo;
    
    @FXML
    private AreaChart trendChart;
    @FXML
    private ComboBox trendComboBox;
    @FXML
    private NumberAxis trendGraphYAxis;
    /**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();
    	
    	// for bosco advanced 3
    	search = new Search[5];
    	searchNo = -1;
    }
    
    

    /**
     * This class implement the callback to update the tablecell in gui
     */
    public class HyperlinkCell implements  Callback<TableColumn<Item, Hyperlink>, TableCell<Item, Hyperlink>> {
        
        @Override
        /**
         * This method updates a tablecolumn
         * @param arg The column to be updated.
         * @return TableCell<Item, Hyperlink> squared.
         */
        public TableCell<Item, Hyperlink> call(TableColumn<Item, Hyperlink> arg) {
            TableCell<Item, Hyperlink> cell = new TableCell<Item, Hyperlink>() {
                @Override               
                protected void updateItem(Hyperlink item, boolean empty) {
                    setGraphic(item);   
                }
            };
            return cell;
        }
    }

   
    
    /**
     * This method updates a tablecolumn
     * @param void
     * @return void
     * @exception NullPointerException On input error.
     */
    @FXML
    private void initialize() {
    	
		Title.setCellValueFactory(new PropertyValueFactory<Item,String>("title"));
		
		Price.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
		
		URL.setCellValueFactory(new PropertyValueFactory<Item,Hyperlink>("hyperlink"));
		URL.setCellFactory(new HyperlinkCell());
		
		Posted_Date.setCellValueFactory(new PropertyValueFactory<Item,String>("postDate"));
		

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
    //task 4
    
    /**
     * This method fill the table with updated information
     * @param The itemList Itemlist with product information
     * @return void
     * @exception NullPointerException On input error.
     */
    private void FillTable(ItemList itemList)
	{
    	
		Table.setItems(getItem(itemList));
	}

    //task 4
    
    /**
     * This method gets items for putting in the table
     * @param ItemList - list product information
     * @return ObservableList - containing the same product information
     * @exception NullPointerException On input error.
     */
	public ObservableList<Item> getItem(ItemList itemList)
	{
		ObservableList<Item> list= FXCollections.observableArrayList();
		for(int i=0;i<itemList.getQuantity();i++)
		{
			list.add(itemList.getItem(i));
			//System.out.println(itemList.getItem(i).getTitle());
		}
		
		return list;
	}


    /**
     * Called when the search button is pressed.
     */
    @FXML
    private void actionSearch() {
    	if(searchNo != 4)
    		searchNo++;
    	else {
    		for(int i = 0; i < 4; i++)
    			search[i] = search[i+1];
    	}
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	
    	// Bosco changed these part
    	Search s = new Search();
    	s.setKeyword(textFieldKeyword.getText());
    	ItemList itemList = new ItemList(scraper.scrape(textFieldKeyword.getText()) );
    	
    	//basic 2
    	ItemList SinglePage=new ItemList(scraper.scrapeSinglePage(textFieldKeyword.getText()));
    	itemList.mergeList(SinglePage);
    	//basic 2 end
    	
    	s.setItemList(itemList);
  
    	
    	search[searchNo] = s;
    	
    	String output = s.getConsoleContent();
    	textAreaConsole.setText(output);
    	
//    	task6
    	MenuItemLastSearch.setDisable(false);
    	last = textFieldKeyword.getText();
//    	task5
    	ButtonRefine.setDisable(false);

    	//advance 3
    	s.setAreaChart();
    	updateComboBox();
    	updateAreaChart(searchNo);
    	
    	//task 4
		FillTable(itemList);
    }
    
    /**task6
     * Called when the Refine button is pressed.
     */
    @FXML
    private void actionRefine() {
    	System.out.println("actionRefine: " + textFieldKeyword.getText());
    	/* Problem here for Linda (also for your actionLast())
    	 * Please use search[searchNo].refine() to filter the searched item list
    	 * 
    	 * To print out the data in console, use search[searchNo].getConsoleContent() to get the output string
    	 * As my trend tab need search class, so the interface become complicated, sorry for my bad
    	 */
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
    
    
    /**
     * Function to update the comboBox content in the trend tab.
     * 
     * for advanced 3
     */
    private void updateComboBox() {
    	trendComboBox.getItems().clear();
    	int i = 0;
    	for(Search s : search) {
    		if( s != null ) {
    			trendComboBox.getItems().add(i++, s.getKeyword());
    		}
    	}
    	
    	trendComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
                for(int i = 0; i <= searchNo; i++) {
                	if( search[i].getKeyword().equals(t1) ) {
                		updateAreaChart(i);
                		break;
                	}
                }              
            }    
        });
    }
    
    /**
     * Function to update the Chart with the assigned search
     * 
     * for advanced 3
     * @param searchNo - the search that linked to which search user want to show with the chart
     */
    private void updateAreaChart(int searchNo) {
    	if(searchNo < 0 || searchNo > 4) return;
    	else {
    		if(trendChart.getData() != null) {
    			trendChart.getData().clear();
    		}
    		
    		trendGraphYAxis.setLabel("The average selling price of the " + search[searchNo].getKeyword());
    		
    		XYChart.Series<String, Number> series= new XYChart.Series<String, Number>();
    		
    		for(int i = 0; i < 7; i++) {
    			if(search[searchNo].getCount(i) != 0) {
    				XYChart.Data<String, Number> data = new XYChart.Data<String, Number>
					( search[searchNo].getXPoints(i), search[searchNo].getYPoints(i));
    				series.getData().add(data);
    			}
    		}
    		trendChart.getData().add(series);
    		// add point listener
    		for( XYChart.Data<String, Number> point : series.getData() ){
    			point.getNode().setStyle("-fx-background-color: blue");
    			
    			point.getNode().setOnMouseClicked(event -> {
    	    		if(event.getClickCount() == 2) {
    	    			for( XYChart.Data<String, Number> node : series.getData() )
    	    				node.getNode().setStyle("-fx-background-color: blue");
    	    			
    	    			String day = point.getXValue();
    	    			textAreaConsole.setText( search[searchNo].particularDayConsoleContent(day) );
    	    			
    	    			point.getNode().setStyle("-fx-background-color: black;");
    	    		}
    	    		else return;
    			});	
    		}
    	}
    }
    
}

