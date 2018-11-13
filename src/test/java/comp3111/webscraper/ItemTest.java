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
	public void testSetPrice() {
		Item i = new Item();
		i.setPrice(1.0);
		
		// set the epsilon as 0.001
		assertEquals(i.getPrice(), 1.0, 0.001);
	}
	
	@Test
	public void testSetUrl() {
		Item i = new Item();
		i.setUrl("https://www.google.com.hk/");
		assertEquals(i.getUrl(), "https://www.google.com.hk/");
	}
	
	@Test
	public void testNotEquals() {
		Item i = new Item();
		i.setTitle("Aiyoku no Eustia Angel's Blessing");
		i.setPrice(9800);
		i.setUrl("http://august-soft.com/eustia/index.html");
		
		Item j = new Item();
		i.setTitle("Bosco");
		i.setPrice(0);
		i.setUrl("http://bosco.nomoney.me/poor/beggingForFood");
		
		assertEquals(i.equals(j), false);
	}
	
	@Test
	public void testEquals() {
		Item i = new Item();
		i.setTitle("Aiyoku no Eustia Angel's Blessing");
		i.setPrice(9800);
		i.setUrl("http://august-soft.com/eustia/index.html");
		
		Item j = new Item();
		i.setTitle("Aiyoku no Eustia Angel's Blessing");
		i.setPrice(9800);
		i.setUrl("http://august-soft.com/eustia/index.html");
		
		assertEquals(i.equals(j), true);
	}
}
