package comp3111.webscraper;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
//task5&6
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Date;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.web.WebView;
import javafx.application.HostServices;
import javafx.scene.web.WebEngine;

public class ControllerTest {

	
	@Test
	public void TestGetItem() 
	{	
		Controller controller=new Controller();
		List<Item> list=new ArrayList<Item>();
		Item item=new Item();
		list.add(item);
		ItemList itemList=new ItemList(list);
		ObservableList<Item> observableList= controller.getItem(itemList);
		assertEquals(observableList.isEmpty(), false);
	}
}
