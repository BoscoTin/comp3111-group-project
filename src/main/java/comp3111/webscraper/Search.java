package comp3111.webscraper;


public class Search {
	private String keyword;
	private ItemList itemList;
	private int[] graphXPoints;
	private int[] graphYPoints;
	
	// getter
	public ItemList getItemList() { return itemList; }
	public String getKeyword() { return keyword; }
	public int[] getXPoints() { return graphXPoints; }
	public int[] getYPoints() { return graphYPoints; }
	
	public String getConsoleContent() {
		String output = "";
		
		int quantity = itemList.getQuantity();
    	for (int i = 0; i < quantity; i++) {
    		Item item = itemList.getItem(i);
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\t" + item.getPostDate() + "\n";
    	}
    	
    	return output;
	}
	
	// constructor
	public Search() {
		this.graphXPoints = new int[7];
		this.graphYPoints = new int[7];
	}
	
	// setter
	public void setKeyword(String search) { keyword = search; }
	public void setItemList(ItemList list) { itemList = list; }
	public void refineItemList(String keyword) { itemList.refineSearch(keyword); }
}