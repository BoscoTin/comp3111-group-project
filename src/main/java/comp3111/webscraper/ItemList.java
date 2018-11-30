package comp3111.webscraper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class ItemList {
	
	// data member, follow the UML diagram
	private List<Item> list;
	private float totalPrice;
	private boolean isRefined;
	
	// constructor
	public ItemList(List<Item> list) {
		this.list = list;
	}
	
	// getters
	public int getQuantity() {return list==null?0:list.size();}
	public float getTotalPrice() {return totalPrice;}
	public boolean isRefined() {return isRefined;}
	public Item getItem(int index) {return list.get(index);}
	
	// functions, need to merge the ArrayList items and refine
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
	
	/*
	 * Sort the order of the Item in ascending order
	 * for basic 4, if you want to print in descending order, just print from end to start
	 * 
	 * @param operation - stating the factor that the list should be sort with
	 */
	public void sortItem(int operation) {
		// please use quicker sorting method :) from 3711 :)
		// because the time for processing the data is very slow
		// dont want demo last too long

		// return if the operation is not 1-4
		if( operation < 1 || operation > 4) return;
		// operation 1
		switch(operation){
		case 1:
			// Title
			
			break;
		case 2:
			// Price
			
			break;
		case 3:
			// URL
			break;
		case 4:
			// Post date
			break;
		}
	}
}