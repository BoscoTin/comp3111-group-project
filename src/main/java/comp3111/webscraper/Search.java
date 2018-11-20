package comp3111.webscraper;


public class Search {
	private String keyword;
	private ItemList itemList;
	
	// getter
	public ItemList getItemList() { return itemList; }
	public String getKeyword() { return keyword; }
	
	// setter
	public void setKeyword(String search) { keyword = search; }
	public void setItemList(ItemList list) { itemList = list; }
}