package comp3111.webscraper;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Vector;


public class ItemListTest {
	private ItemList setItemList() {
		Vector<Item> result = new Vector<Item>();
		Item a = new Item();
		a.setPostDate("2018-11-29");
		Item b = new Item();
		b.setPostDate("2018-11-30");
		
		ItemList itemList = new ItemList(result);
		return itemList;
	}
	
	@Test
	public void testAddItem() {
		ItemList itemList = setItemList();
		Item a = new Item();
		a.setPostDate("2018-11-30");
		itemList.addItem(a);
		
		assertEquals(itemList.getItem(2).getPostDate(), "11/30/2018");
	}
	
	@Test
	public void testgetQuantity() {
		ItemList itemList = setItemList();
		assertEquals(itemList.getQuantity(), 2);
		ItemList itemList2 = new ItemList(null);
		assertEquals(itemList.getQuantity(), 0);
	}
}
