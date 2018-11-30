package comp3111.webscraper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



/**
 * This class contains the item to be extracted from a website
 */
public class Item {
	private String title ; 
	private double price ;
	private String url ;
	private String Portal;
	// for task 4 and adv task 3
	private Date postDate;
	private Hyperlink hyperlink=null;
	
	
	
    /**
     * default getter method to get title
     * @param void
     * @return title in String
     * @exception NullPointerException if title doesnt exist.
     */
	public String getTitle() {
		return title;
	}
	
	
    /**
     * default setter method to set title
     * @param input desired title
     * @return void
     */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
    /**
     * default getter method to get the hyperlink
     * @param void
     * @return hyperlink in Hyperlink type
     * @exception NullPointerException if hyperlink doesnt exist.
     */
	public Hyperlink getHyperlink() {
    	
        return hyperlink;
        
    }
	
    /**
     * default getter method to get portal name
     * @param void
     * @return portal name in String
     * @exception NullPointerException if portal doesnt exist.
     */
	public String getPortal() {
		return Portal;
	}
	
	
    /**
     * default setter method to set portal name
     * @param desired input portal name
     * @return void
     */
	public void setPortal(String Portal) {
		this.Portal = Portal;
	}

	
    /**
     * default getter method to get price of an item
     * @param void
     * @return price in double
     * @exception NullPointerException if price doesnt exist.
     */
	public double getPrice() {
		return price;
	}
	
    /**
     * default setter method to set price of an item
     * @param desired price
     * @return void
     */
	public void setPrice(double price) {
		this.price = price;
	}
	
	
    /**
     * default getter method to get url of an item
     * @param void
     * @return url in String
     * @exception NullPointerException if url doesnt exist.
     */
	public String getUrl() {
		return url;
	}
	
	
    /**
     * default setter method to set url of an item
     * and also set the action of clicking the hyperlink
     * @param desired url
     * @return void
     */
	public void setUrl(String url) {
		this.hyperlink=new Hyperlink(url);
		hyperlink.setOnAction(e -> {
			WebScraperApplication.getHost().showDocument(url);
		});
		
		this.url = url;
	}
	
	
    /**
     * default getter method to get postdate of an item
     * @param void
     * @return postdate in String
     */
	public String getPostDate() {
		if(postDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			return sdf.format(this.postDate);
		} else return "";
	}
	
    /**
     * default getter method to get time of an item
     * @param void
     * @return time in long
     */
	public long getTime() {
		return postDate != null ? postDate.getTime() : 0;
	}
	
    /**
     * default setter method to set postdate of an item
     * @param desired postdate found on websites
     * @return void
     * @exception ParseException if datetime format error.
     */
	public void setPostDate(String postDate) {
		try {
			this.postDate = postDate != null ? new SimpleDateFormat("yyyy-MM-dd").parse(postDate) : null;
		} catch (ParseException e) {
			System.out.println(e);
		}
	}
	
    /**
     * checker function to look for duplicated item
     * @param another item
     * @return true if found, false if not found
     */
	public boolean isDuplicated(Item item) {
		return (this.title == item.title)
				&& (this.price == item.price)
				&& (this.postDate == item.postDate);
	}
}
