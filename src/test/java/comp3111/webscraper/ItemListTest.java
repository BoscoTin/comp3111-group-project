package comp3111.webscraper;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;


public class ItemListTest {
	private List<Item> setItemList() {
		Vector<Item> result = new Vector<Item>();
		Item a = new Item();
		a.setPostDate("2018-11-29");
		Item b = new Item();
		b.setPostDate("2018-11-30");
		
		return result;
	}
	
	@Test
	public void testAddItem() {
		ItemList itemList = new ItemList(setItemList());
		Item a = new Item();
		a.setPostDate("2018-11-30");
		itemList.addItem(a);
		
		assertEquals( itemList.getItem( itemList.getQuantity()-1 ).getPostDate(), "11/30/2018");
	}
	
	@Test
	public void testgetQuantity() {
		ItemList itemList = new ItemList(setItemList());
		Item a = new Item();
		itemList.addItem(a);
		assertEquals(itemList.getQuantity(), 1);
		ItemList itemList2 = new ItemList(null);
		assertEquals(itemList2.getQuantity(), 0);
	}
	
	@Test
	public void testMerge() {
		ItemList itemList = new ItemList(setItemList());
		
		ItemList itemList2 = new ItemList(null);
		itemList.mergeList(itemList2);
		assertEquals(itemList!=null, true);
	}
	
	
	
}
