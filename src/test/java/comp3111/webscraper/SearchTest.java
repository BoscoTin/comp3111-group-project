package comp3111.webscraper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

public class SearchTest {
	
	@Test
	public void testSetCount() {
		Search s = new Search();
		for(int i = 0; i < 7; i++) {
			assertEquals(s.setCount(2, i), true);
			assertEquals(s.getCount(0), 2);
		}
		assertEquals(s.setCount(2, -1), false);
		assertEquals(s.setCount(2, 7), false);
	}
	
	@Test
	public void testSetXPoints() {
		Search s = new Search();
		for(int i = 0; i < 7; i++) {
			assertEquals(s.setXPoint("String", i), true);
			assertEquals(s.getXPoints(i), "String");
		}
		assertEquals(s.setXPoint("String", -1), false);
		assertEquals(s.setXPoint("String", 7), false);
	}
	
	@Test
	public void testSetYPoints() {
		Search s = new Search();
		for(int i = 0; i < 7; i++) {
			assertEquals(s.setYPoint(999.0, i), true);
			assertEquals(s.getYPoints(i) - 999.0 < 0.001, true);
		}
		assertEquals(s.setYPoint(999.0, -1), false);
		assertEquals(s.setYPoint(999.0, 7), false);
	}
	
	@Test
	public void testSetKeyword() {
		Search s = new Search();
		s.setKeyword("Noooo, I am so chur now");
		assertEquals(s.getKeyword(), "Noooo, I am so chur now");
	}
	
	@Test
	public void testSetItemList() {
		Search s = new Search();
		Vector<Item> result = new Vector<Item>();
		Item a = new Item();
		a.setPostDate("2018-11-29");
		Item b = new Item();
		b.setPostDate("2018-11-30");
		result.add(a);
		result.add(b);
		
		ItemList itemList = new ItemList(result);
		s.setItemList(itemList);
		
		assertEquals(s.getItemList().getItem(0).getPostDate(), "11/29/2018");
		assertEquals(s.getItemList().getItem(1).getPostDate(), "11/30/2018");
	}
	
	@Test
	public void testDayConsole() {
		Search s = new Search();
		Vector<Item> result = new Vector<Item>();
		Item a = new Item();
		a.setTitle("");
		a.setUrl("");
		a.setPostDate("2018-11-29");
		a.setPrice(0.0);
		Item b = new Item();
		b.setPostDate("2018-11-30");
		b.setUrl("");
		b.setTitle("");
		b.setPrice(0.0);
		
		ItemList itemList = new ItemList(result);
		s.setItemList(itemList);
		
		assertEquals( s.particularDayConsoleContent("11/30/2018"), a.getTitle() + "\t" + a.getPrice() + "\t" + a.getUrl() + "\t" + a.getPostDate() + "\n");
	}
}
