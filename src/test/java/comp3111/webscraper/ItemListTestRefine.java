package comp3111.webscraper;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Vector;


public class ItemListTestRefine {
	private ItemList setItemList() {
		Vector<Item> result = new Vector<Item>();
		Item a = new Item();
		a.setTitle("linda");
		a.setPostDate("2018-11-29");
		Item b = new Item();
		b.setTitle("linda by by");
		b.setPostDate("2018-11-30");
		
		ItemList itemList = new ItemList(result);
		return itemList;
	}

	@Test
	public void testisRefine() {
		ItemList list=setItemList();
		boolean sth= list.isRefined();
		assertEquals(sth, false);
	}
	@Test
	public void testRefineSearch() {
		ItemList list=setItemList();

		assertEquals( list.isRefined(), false);
		list.refineSearch("by");
		assertEquals( list.isRefined(), true);
	}
}
