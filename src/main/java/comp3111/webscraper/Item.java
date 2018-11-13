package comp3111.webscraper;



public class Item {
	private String title ; 
	private double price ;
	private String url ;
	
	// we need PostDate
	private String postDate;
	
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
	
	// add by bosco
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	
	// make equals() to facilitate the work
	public boolean equals(Item item) {
		if( title == item.title && price == item.price && url == item.url)
			return true;
		else return false;
	}

}
