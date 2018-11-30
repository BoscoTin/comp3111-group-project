package comp3111.webscraper;


import org.junit.Test;
import static org.junit.Assert.*;


public class ItemTest {

	@Test
	public void testSetTitle() {
		Item i = new Item();
		i.setTitle("ABCDE");
		assertEquals(i.getTitle(), "ABCDE");
	}
	
	@Test
	public void testSetPostDate() {
		Item i = new Item();
		i.setPostDate("2018-11-30");
		assertEquals(i.getPostDate(), "11/30/2018");
		i.setPostDate(null);
		assertEquals(i.getPostDate(), "");
	}
	
	@Test
	public void testSetPrice() {
		Item i = new Item();
		i.setPrice(999.0);
		assertEquals( i.getPrice() - 999.0 < 0.001, true);
	}
	
	@Test
	public void testGetTime() {
		Item i = new Item();
		i.setPostDate("2018-11-30");
		assertEquals( i.getTime() > 0, true);
		
		i.setPostDate(null);
		assertEquals( i.getTime() == 0, true);
	}
}
