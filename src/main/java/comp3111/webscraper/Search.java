package comp3111.webscraper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Search {
	private String keyword;
	private ItemList itemList;
	private String[] graphXPoints;
	private double[] graphYPoints;
	private int[] count;
	
	// getter
	public ItemList getItemList() { return itemList; }
	public String getKeyword() { return keyword; }
	public String getXPoints(int i) { return graphXPoints[i]; }
	public double getYPoints(int i) { return graphYPoints[i]; }
	public int getCount(int i) { return count[i];}
	
	/**
	 * The method of setting the console tab string output.
	 * 
	 * @param
	 * @return A String that contains all the items inside this particular search.
	 */
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
		this.graphXPoints = new String[7];
		this.graphYPoints = new double[7];
		this.count = new int[7];
	}
	
	// setter
	public void setKeyword(String search) { keyword = search; }
	public void setItemList(ItemList list) { itemList = list; }
	public void refineItemList(String keyword) { itemList.refineSearch(keyword); }
	
	// for testing purpose
	public boolean setXPoint(String category, int i) { 
		if( i < 0 || i > 6) return false; 
		graphXPoints[i] = category;
		return true;
	}
	public boolean setYPoint(Double price, int i) { 
		if( i < 0 || i > 6) return false; 
		graphYPoints[i] = price;
		return true;
	}
	public boolean setCount(int value, int i) { 
		if( i < 0 || i > 6) return false; 
		count[i] = value;
		return true;
	}
	
	/** The method of setting the XY coordinates of AreaChart inside trend tab
	 * 
	 * for advanced 3
	 */
	public void setAreaChart() {
		
		int size = itemList.getQuantity();
		
		for(int i = 0; i < 7; i++) {
			String dateNow = getTime(i);
			Double sum = 0.0;
			int count = 0;
			
			for(int point = 0; point < size; point++) {
				String date = itemList.getItem(point).getPostDate();
				if(date != null) {
					if( date.equals(dateNow) ) {
						sum += itemList.getItem(point).getPrice();
						count++;
					}
				}
			}
			
			graphXPoints[6 - i] = dateNow;
			graphYPoints[6 - i] = count == 0 ? 0.0 : sum/count;
			this.count[6-i] = count;
		}
		
	}
	
	/**
	 * Helper function of setAreaChart
	 * 
	 * @author wttang
	 * @param i: set the days to minus
	 * @return String: time to compare
	 */
	public String getTime(int i) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		date.setDate(date.getDate() - i);
		return sdf.format(date);
	}
	
	/**
	 * Function to collect items in particular day and return the whole string
	 * 
	 * @author wttang
	 * @param day - denote the day searching in the item list
	 * @return String - the whole string containing the information of all items of that day
	 */
	public String particularDayConsoleContent(String day) {
		String output = "";
		
		int quantity = itemList.getQuantity();
    	for (int i = 0; i < quantity; i++) {
    		Item item = itemList.getItem(i);
    		if( item.getPostDate().equals(day) )
    			output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\t" + item.getPostDate() + "\n";
    	}
    	
    	return output;
	}
}