package comp3111.webscraper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
	private String title ; 
	private double price ;
	private String url ;
	// for task 4 and adv task 3
	private Date postDate;
	
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
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPostDate() {
		if(postDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			return sdf.format(this.postDate);
		} else return "";
	}
	public void setPostDate(String postDate) {
		try {
			this.postDate = postDate == null ? new SimpleDateFormat("yyyy-MM-dd").parse(postDate) : null;
		} catch (ParseException e) {
			System.out.println(e);
		}
	}
	public boolean isDuplicated(Item item) {
		return (this.title == item.title)
				&& (this.price == item.price)
				&& (this.postDate == item.postDate);
	}
}
