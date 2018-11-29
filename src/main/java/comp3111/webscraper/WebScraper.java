package comp3111.webscraper;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.Vector;


/**
 * WebScraper provide a sample code that scrape web content. After it is constructed, you can call the method scrape with a keyword, 
 * the client will go to the default url and parse the page by looking at the HTML DOM.  
 * <br/>
 * In this particular sample code, it access to craigslist.org. You can directly search on an entry by typing the URL
 * <br/>
 * https://newyork.craigslist.org/search/sss?sort=rel&amp;query=KEYWORD
 *  <br/>
 * where KEYWORD is the keyword you want to search.
 * <br/>
 * Assume you are working on Chrome, paste the url into your browser and press F12 to load the source code of the HTML. You might be freak
 * out if you have never seen a HTML source code before. Keep calm and move on. Press Ctrl-Shift-C (or CMD-Shift-C if you got a mac) and move your
 * mouse cursor around, different part of the HTML code and the corresponding the HTML objects will be highlighted. Explore your HTML page from
 * body &rarr; section class="page-container" &rarr; form id="searchform" &rarr; div class="content" &rarr; ul class="rows" &rarr; any one of the multiple 
 * li class="result-row" &rarr; p class="result-info". You might see something like this:
 * <br/>
 * <pre>
 * {@code
 *    <p class="result-info">
 *        <span class="icon icon-star" role="button" title="save this post in your favorites list">
 *           <span class="screen-reader-text">favorite this post</span>
 *       </span>
 *       <time class="result-date" datetime="2018-06-21 01:58" title="Thu 21 Jun 01:58:44 AM">Jun 21</time>
 *       <a href="https://newyork.craigslist.org/que/clt/d/green-star-polyp-gsp-on-rock/6596253604.html" data-id="6596253604" class="result-title hdrlnk">Green Star Polyp GSP on a rock frag</a>
 *       <span class="result-meta">
 *               <span class="result-price">$15</span>
 *               <span class="result-tags">
 *                   pic
 *                   <span class="maptag" data-pid="6596253604">map</span>
 *               </span>
 *               <span class="banish icon icon-trash" role="button">
 *                   <span class="screen-reader-text">hide this posting</span>
 *               </span>
 *           <span class="unbanish icon icon-trash red" role="button" aria-hidden="true"></span>
 *           <a href="#" class="restore-link">
 *               <span class="restore-narrow-text">restore</span>
 *               <span class="restore-wide-text">restore this posting</span>
 *           </a>
 *       </span>
 *   </p>
 *}
 *</pre>
 * <br/>
 * The code 
 * <pre>
 * {@code
 * List<?> items = (List<?>) page.getByXPath("//li[@class='result-row']");
 * }
 * </pre>
 * extracts all result-row and stores the corresponding HTML elements to a list called items. Later in the loop it extracts the anchor tag 
 * &lsaquo; a &rsaquo; to retrieve the display text (by .asText()) and the link (by .getHrefAttribute()). It also extracts  
 * 
 *
 */
public class WebScraper {

	private static final String DEFAULT_URL = "https://newyork.craigslist.org/";
	private static final String CUSTOM_URL="https://www.preloved.co.uk/";
	
	private WebClient client;
	

	/**
	 * Default Constructor 
	 */
	public WebScraper() {
		client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
	}

	/**
	 * The only method implemented in this class, to scrape web content from the craigslist
	 * 
	 * @param keyword - the keyword you want to search
	 * @return A list of Item that has found. A zero size list is return if nothing is found. Null if any exception (e.g. no connectivity)
	 */
	public List<Item> scrape(String keyword) {

		try {
			//String searchUrl = DEFAULT_URL + "search/sss?sort=rel&query=" + URLEncoder.encode(keyword, "UTF-8");			
			String searchUrl = CUSTOM_URL+ "search?q=123";	
			
			
			Vector<Item> result = new Vector<Item>();
			
			boolean lastPage = false;
			int pageNum = 1;
			do {
				HtmlPage page = client.getPage(searchUrl);
				
				List<?> items = (List<?>) page.getByXPath("//li[@class='result-row']");
				
				// Pagination: get the total number of item first
				HtmlElement spanPageNum = ((HtmlElement) page.getFirstByXPath(".//span[@class='totalcount']"));
				HtmlElement spanPageRange = ((HtmlElement) page.getFirstByXPath(".//span[@class='range']"));
				
				// check if the total num in range
				String pageRange[] = spanPageRange.asText().split(" ");
				if( Integer.parseInt(pageRange[2]) == Integer.parseInt(spanPageNum.asText()) ) {
					lastPage = true;
				}
				// prevent the on9 case
				if( Integer.parseInt(pageRange[2]) > Integer.parseInt(spanPageNum.asText()) ) {
					break;
				}
				// Write in the console tab
				System.out.println("Checking page " + pageNum++ + " with item range " + spanPageRange.asText());
				
				// get each item and put it into the result list
				// no difference with skeleton code
				
				new Thread(()-> 
				{
					for (int i = 0; i < items.size(); i++) 
					{
						HtmlElement htmlItem = (HtmlElement) items.get(i);
						HtmlAnchor itemAnchor = ((HtmlAnchor) htmlItem.getFirstByXPath(".//p[@class='result-info']/a"));
						HtmlElement spanPrice = ((HtmlElement) htmlItem.getFirstByXPath(".//a/span[@class='result-price']"));				
					// add postDate
						HtmlElement timeClass = ((HtmlElement) htmlItem.getFirstByXPath(".//time[@class='result-date']"));
					// It is possible that an item doesn't have any price, we set the price to 0.0
					// in this case
						String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();
						Item item = new Item();
						item.setTitle(itemAnchor.asText());
						item.setUrl(DEFAULT_URL + itemAnchor.getHrefAttribute());
	
						item.setPrice(new Double(itemPrice.replace("$", "")));
						if(item.getUrl().contains("craigslist")) 
						{
							item.setPortal("craigslist");
						}
						// add postDate
						item.setPostDate(timeClass.asText());
					
						result.add(item);
					}
				}).start();
				
				
				
				// check if the pageg is the last page, if it is, change the url
				if( lastPage == false ) {
					HtmlAnchor itemAnchor = ((HtmlAnchor) page.getFirstByXPath(".//a[@class='button next']"));
					searchUrl = DEFAULT_URL + itemAnchor.getHrefAttribute();
				}
				
			} while(lastPage == false);
			
			
			client.close();
			
			Collections.sort(result, new Comparator<Item>() 
			
			{	@Override
				public int compare(Item o1, Item o2) 
				{
				
				if(o1.getPrice()==o2.getPrice()) 
				{
					if(o1.getPortal()!=null) {return 1;}
					if(o2.getPortal()!=null) {return -1;}
					return 0;
				}
					return o1.getPrice()>o2.getPrice()?1:-1;
				}
			});
			
//			for(Item item:result) 
//			{
//				System.out.println(item.getPrice());
//			}
			
			
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	
	
	
	public List<Item> scrapeSinglePage(String keyword) {

		try {
			
			String searchUrl = CUSTOM_URL + "search?keyword=123"+ URLEncoder.encode(keyword, "UTF-8");
			HtmlPage page = client.getPage(searchUrl);
			List<?> items = (List<?>) page.getByXPath("//li[@data-test-element='search-result']");
			Vector<Item> result = new Vector<Item>();

			for (int i = 0; i < items.size(); i++) {
				HtmlElement htmlItem = (HtmlElement) items.get(i);
				HtmlElement itemAnchor = htmlItem.getFirstByXPath(".//span[@itemprop='name']");
				HtmlElement spanPrice = htmlItem.getFirstByXPath(".//span[@itemprop='price']");
				
				
				// It is possible that an item doesn't have any price, we set the price to 0.0
				// in this case
				

				Item item = new Item();
				if(itemAnchor!=null)
				{
					System.out.println(itemAnchor.asText());
					item.setTitle(itemAnchor.asText());
				}
				
				if(spanPrice!=null)
				{
					String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();
					itemPrice=RemoveNonNumber(itemPrice);					
					item.setPrice(new Double(itemPrice.replace("?", "")));
				}
				
				item.setUrl(htmlItem.getAttribute("data-href"));

				result.add(item);
			}
			client.close();
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	private String RemoveNonNumber(String price) 
	{
		String result=new String();
		for(int i=0;i<price.length();i++) 
		{
			if(price.charAt(i)>='0'&&price.charAt(i)<='9'||price.charAt(i)=='.') 
			{
				result+=price.charAt(i);
			}
		}
		return result;
	}
	
	
	
}
