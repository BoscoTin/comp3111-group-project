package comp3111.webscraper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.text.ParseException;
import java.util.ArrayList;

public class ItemList {
	
	// data member, follow the UML diagram
	private List<Item> list;
	private float totalPrice;
	private boolean isRefined;
	
	// constructor
	
    /**
     * constructor to initialize data member
     * @param the list of item object retrive
     */
	public ItemList(List<Item> list) {
		this.list = list;
	}
	
	// getters
    /**
     * default getter method to get length of the list
     * @param void
     * @return length in int
     */
	public int getQuantity() {return list==null?0:list.size();}
	
    /**
     * default getter method to get total price of the list
     * @param void
     * @return total price in float
     */
	public float getTotalPrice() {return totalPrice;}
	
    /**
     * checker function to checker the list has been refined or not
     * @param void
     * @return true if refined, false otherwise
     */
	public boolean isRefined() {return isRefined;}
	
	
    /**
     * checker function to checker the list has been refined or not
     * @param index to get from the list
     * @return the item with corresponding index
     */
	public Item getItem(int index) {return list.get(index);}
	
	/**
	 * Function merged 2 lists together and then do sorting
	 * 
	 * @author cfyauab
	 * @param anotherList - another list scrapped from another website that needs to be merged
	 * @return void
	 */
	public void mergeList(ItemList anotherList) {
		List<Item> newList = new ArrayList<Item>();
		newList.addAll(list);
		
		for(int i=0;i<anotherList.getQuantity();i++) 
		{
			newList.add(anotherList.getItem(i));
		}
		this.list=newList;
		
		Collections.sort(list, new Comparator<Item>() 
		{
			@Override
		    public int compare(Item o1, Item o2) {
		        // write comparison logic here like below , it's just a sample
		        if(o1.getPrice()>o2.getPrice()) 
		        {
		        	return 1;
		        }
		        if(o1.getPrice()<o2.getPrice()) 
		        {
		        	return -1;
		        }

		        if(o1.getPortal().equals("craigslist")&&!o2.getPortal().equals("craigslist"))
		        {return -1;}	
		        else if(!o1.getPortal().equals("craigslist")&&o2.getPortal().equals("craigslist"))
		        {return 1;}
		        return 0;
		      
		    }	
		});
		
	}
	public void refineSearch(String keyword) {
		
	}
	
	// functions, for editing the list array
	public void addItem(Item item) {
		list.add(item);
	}

}