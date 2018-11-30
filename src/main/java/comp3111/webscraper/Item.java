package comp3111.webscraper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Item {
	private String title=null ; 
	private Double price=(double) 0 ;
	private String url=null ;
	private Hyperlink hyperlink=null;
	
	
	// for task 4 and adv task 3
	private String postDate=null;
	private String Portal=null;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public String getPortal() 
	{
		return this.Portal;
	}
    public Hyperlink getHyperlink() {
    	
        return hyperlink;
        
    }
	public void setPortal(String Portal) 
	{
		this.Portal=Portal;
		
	}
	
	public void setUrl(String url) {
		this.hyperlink=new Hyperlink(url);
		hyperlink.setOnAction(e -> {
			WebScraperApplication.getHost().showDocument(url);
		});
		
		
		this.url = url;
	}
	
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public boolean isDuplicated(Item item) {
		return (this.title == item.title)
				&& (this.price == item.price)
				&& (this.postDate == item.postDate);
	}
}
