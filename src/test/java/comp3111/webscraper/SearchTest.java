package comp3111.webscraper;

import org.junit.Test;
import static org.junit.Assert.*;

public class SearchTest {
	@Test
	public void testSetItemList() {
		//TODO
	}
	
	public void testSetKeyword() {
		Search s = new Search();
		s.setKeyword("acde");
		assertEquals(s.getKeyword(), "acde");
	}
}
