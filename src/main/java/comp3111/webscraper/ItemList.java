package comp3111.webscraper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	public int getQuantity() {return list.size();}
	public float getTotalPrice() {return totalPrice;}
	public boolean isRefined() {return isRefined;}
	public Item getItem(int index) {return list.get(index);}
	
	// functions, need to merge the ArrayList items and refine
	public void mergeList(ItemList anotherList) {
		// dont use contains() in List, isDuplicated() is for your function
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