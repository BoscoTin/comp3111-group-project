package comp3111.webscraper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

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
		//TODO
	}
}
