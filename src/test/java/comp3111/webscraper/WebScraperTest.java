package comp3111.webscraper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Vector;

public class WebScraperTest {
	
	@Test
	public void testScrape() {
		WebScraper w = new WebScraper();
		
		
		assertEquals( w.scrape("iPhone 6s white") != null, true);
	}
}
