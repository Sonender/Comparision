package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import utils.Helper;

public class ForNoida extends Helper {
	int valInit = 1;

	// public RemoteWebDriver driver=super.driver;
	// Map<String, Map<String, List<String>>> allValues= new HashMap<String,
	// Map<String, List<String>>>();
	Map<Object, Object> allValues = new LinkedHashMap<Object, Object>();

	public void runTest() throws InterruptedException, IOException {
 //	try {
//			boolean element12 = driver.findElement(By.className("sr_pagination_link")).isDisplayed();
//
//			while (element12 == false) {
//				JavascriptExecutor jse = (JavascriptExecutor) driver;
//				jse.executeScript("window.scrollBy(0,250)", "");
//				element12 = driver.findElement(By.className("sr_pagination_link")).isDisplayed();
//			}
//			//selectSheet.writeToSheet("11LGluUkKjp8dNBhCRDlYGOs8ZpEK-Iz69yvaQupcX40", "Gurgaon"+valInit, allValues);
//			valInit++;
//			String val = Integer.toString(valInit);
//
//			WebElement elemhi = driver.findElement(By.linkText(val));
//			elemhi.click();
//			WebDriverWait wait = new WebDriverWait(driver, 20);
//			wait.until(ExpectedConditions.elementToBeClickable(By.className("switch-map-view")));
//		} catch(Exception e){System.out.println("failing here"+e);}
		int counter=1;
		String sr_header = "";
		int textSize = Integer
			.parseInt(driver.findElement(By.xpath("//div[@class='sr_header ']/h1")).getText().substring(7,9));
		int vals = Integer.parseInt(driver.findElement(By.className("sr_showed_amount_last")).getText());
		int totalVals = textSize / vals;
		System.out.println("values" + textSize + " " + vals + " " + totalVals);
		int count = 0;
		System.out.println("Test Executed");
   	WebDriverWait wait = new WebDriverWait(driver, 20);
//		try{
//			 wait = new WebDriverWait(driver, 20);
//			wait.until(new Predicate<WebDriver>() {
//				public boolean apply(WebDriver driver) {
//					return ((JavascriptExecutor) driver).executeScript("return document.readyState")
//							.equals("complete");
//				
//				}
//			});
//			}catch(Exception e){System.out.println("Break16"+e);}
		String text = "";
		String URL = "";
		List<String> ab = new ArrayList<String>();
		ab.add("URL");
		ab.add("Start Rating");
		ab.add("Location Rating");
		ab.add("Hotel Count");
		ab.add("Aminities");
		ab.add("Pricing");
		allValues.put("Hotel Name", ab);
		// Map<String, List<String>> totalValues=new
		// HashMap<String,List<String>>();
		for (int i = 1; i < totalVals; i++) 
		 {
			String city = "Gurgaon";

			try {
				String css = "";
				List<WebElement> element = driver.findElements(
						By.cssSelector("div.sr_item.sr_item_new.sr_item_default.sr_property_block.sr_flex_layout"));
				css = element.get(count).getCssValue("className");
				List<WebElement> elementlist = driver.findElements(By.cssSelector("" + css + "span.sr-hotel__name"));
				List<WebElement> elementlistLink = driver
						.findElements(By.cssSelector("" + css + "a.hotel_name_link.url"));

				List<WebElement> elementlistStar = null;
				try {
					elementlistStar = driver.findElements(By.cssSelector(
							"" + css + "h3.sr-hotel__title span[data-et-mouseenter^=OLZ] span.invisible_spoken"));
				} catch (Exception e) {
					System.out.println(e+"Break1");
				}
				
				for (WebElement elem : element) {
					List<String> strm = new ArrayList<String>();

					text = elementlist.get(count).getText();
					// strm.add(text);

					URL = elementlistLink.get(count).getAttribute("href");
					strm.add(URL);
					System.out.println("HotelName" + text);
					System.out.println("HotelUrl" + URL);

//					try{
//					String attrb = elem.getAttribute("data-hotelid");
//					} catch
					try {
						String star = elementlistStar.get(count).getText();
						if (star.length() < 15)
							strm.add(star);
						else
							strm.add("Star Rating not available");

						System.out.println("HotelStars" + star);
					} catch (Exception e) {
						System.out.println(e+"Break2");
						strm.add("Star Rating not available");
					}
					String winHandle = driver.getWindowHandle();
					elementlist.get(count).click();

					Set<String> winHandles = driver.getWindowHandles();
					System.out.println("count"+winHandles.size());

					for (String handles : winHandles) {

						if (handles.equals(winHandle))
							continue;
						else
							driver.switchTo().window(handles);
						//WebDriverWait wait=null;
						try {
							try{
							 wait = new WebDriverWait(driver, 20);
							wait.until(new Predicate<WebDriver>() {
								public boolean apply(WebDriver driver) {
									return ((JavascriptExecutor) driver).executeScript("return document.readyState")
											.equals("complete");
								
								}
							});
							}catch(Exception e){System.out.println("Break16"+e);}
							// WebElement hover=
							// driver.findElement(By.cssSelector("div#js--hp-gallery-scorecard
							// span.js--hp-scorecard-scoreword"));
							// Actions action = new Actions(driver);
							List<WebElement> location1 =null;
							try{
								wait.until(ExpectedConditions.presenceOfElementLocated(
							
									By.cssSelector(("span.review_list_score_breakdown_right p.review_score_value"))));
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							 location1 = driver
									.findElements(By.className("js--hp-scorecard-scoreword"));
							}catch(Exception e){System.out.println("Break 17"+e);
							driver.close();
							driver.switchTo().window(winHandle);
							}
							

							for (WebElement fir : location1) {
								try {
									fir.click();
									break;
								} catch (Exception e) {
								}
							}
							List<WebElement> location=null;
                            try
                            {
							 location = driver.findElements(By
									.cssSelector("li.clearfix.one_col[data-question$=location] p.review_score_value"));
                            }catch (Exception e){}
							String actual = null;
							for (WebElement ele : location) {
								try {
									System.out.println("ele" + ele.getText());

									String loc = ele.getText();
									if (loc.length() > 0) {
										actual = loc;
										break;
									} else
										continue;
								} catch (Exception e) {
									System.out.println(e+"Break3");
								}
							}

							System.out.println("loc" + actual);
							// System.out.println("Checkpoint1");
							if (actual != null){
								strm.add(actual);
							}
								// System.out.println("Checkpoint2");}
							
							else
								strm.add("Location not found");
							// System.out.println("Checkpoint3");
							List<WebElement> close=null;
							try {
								 close = driver.findElements(By.cssSelector("i.bicon-aclose"));
							} catch (Exception e) {
								System.out.println("Break4"+e);
							}
							// System.out.println("Checkpoint4");
								for(WebElement son : close){
								//	 System.out.println("Checkpoint5");
								try
								{
									boolean bol= son.isDisplayed();
								
									if(bol)
									{
									son.click();
									break;}
								}catch(Exception e){
									System.out.println("Break5"+e);
								}
								}
								

							JavascriptExecutor jse = (JavascriptExecutor) driver;
							jse.executeScript("window.scrollBy(0,250)", "");
							boolean valtrue = false;
							WebElement textElem = null;

							try{
							while (!valtrue) {
								try {
									textElem = driver.findElement(
											By.cssSelector("div.hotel_description_wrapper_exp.hp-description"));
									valtrue = textElem.isDisplayed();
									if (!valtrue) {
										jse.executeScript("window.scrollBy(0,250)", "");
									} else
										break;
								} catch (Exception e) {
									System.out.println("Break6"+e);
									driver.close();
									driver.switchTo().window(winHandle);
									jse.executeScript("window.scrollBy(0,250)", "");
								}
							}
							}catch(Exception e){ System.out.println("While Broken"+e);}
							try {
								String texting = textElem.getText();

								System.out.println("textElem" + texting);

								if (texting != null && (texting.contains("rooms"))) {
								//	int indexOfHotel = texting.indexOf("Hotel Rooms :");
								//	String ind = texting.substring(indexOfHotel, indexOfHotel + 17);
									System.out.println(texting);
									strm.add(texting);
								} else
									strm.add("Hotel count is not there");
							} catch (Exception e) {
								System.out.println("Break7"+e);
								strm.add("Hotel count is not there");
							}

							try {
								// System.out.println("Checkpoint6");
								List<WebElement> aminities = driver.findElements(By.cssSelector("div.hp_desc_important_facilities div.important_facility.hp-desc-facility"));
								String aminity = "";
								int len = 0;
								for (WebElement amn : aminities) {
									
									if (len == 0) {
										aminity = amn.getAttribute("data-name-en");

									} else
										aminity = aminity + ", " + amn.getAttribute("data-name-en");
									  len++;
								}
								System.out.println(aminity);
								if (aminity.length() > 1 && (aminity!= null))
									strm.add(aminity);
								else
									strm.add("No Aminity");

							} catch (Exception e) {
								System.out.println("Break8"+e);
							}
							//System.out.println("Checkpoint7");
                           String winHandleNow= driver.getWindowHandle();
                           int winCount=driver.getWindowHandles().size();
							if(!winHandleNow.equals(winHandle) && winCount>1)
							{	
								//System.out.println("Checkpoint8");
                               driver.close();
							   driver.switchTo().window(winHandle);
							
							}
							else
								driver.switchTo().window(winHandle);
														   
							try{
								boolean popo_up = driver.findElement(By.id("notification_lightbox")).isDisplayed();
								if (popo_up) {
									System.out.println("Pop-up opened");
									WebElement closeBtn = driver.findElement(By.className("modal-mask-closeBtn"));
									closeBtn.click();
								}
								
							}catch(Exception ec){
								//System.out.println("Break8"+ec);
							}
							

						} catch (Exception e) {
							System.out.println("Break15"+e);

					}

					String Tprice = "";
					WebElement elementlistPrice1 = null;
					WebElement elementlistPrice = null;
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,250)", "");
					
					count++;
					System.out.println("hello" + count);
					if(count==2)
						break;
					
					 }
					allValues.put(text, strm);
					System.out.println("enterimList"+allValues);
				}
				count = 0;
				try {
					boolean popo_up = driver.findElement(By.id("notification_lightbox")).isDisplayed();
					if (popo_up) {
						System.out.println("Pop-up opened");
						WebElement closeBtn = driver.findElement(By.className("modal-mask-closeBtn"));
						closeBtn.click();

					}

				} catch (Exception e1) {
					System.out.println("Break11"+e1);
				}
				try {
					boolean element12 = driver.findElement(By.className("sr_pagination_link")).isDisplayed();

					while (element12 == false) {
						JavascriptExecutor jse = (JavascriptExecutor) driver;
						jse.executeScript("window.scrollBy(0,250)", "");
						element12 = driver.findElement(By.className("sr_pagination_link")).isDisplayed();
					}
					
					
					selectSheet.writeToSheet("11LGluUkKjp8dNBhCRDlYGOs8ZpEK-Iz69yvaQupcX40", "Noida", allValues);
				
					
					valInit++; 
					String val = Integer.toString(valInit);

					WebElement elemhi = driver.findElement(By.linkText(val));
					elemhi.click();
					//WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.elementToBeClickable(By.className("switch-map-view")));
					// this.runTest();
					Thread.sleep(10000);
				} catch (Exception e) {

					System.out.println("Break12"+e);
					
					
				}
				// element.sendKeys("Delhi");
				Thread.sleep(2000);
				// WebElement elem =
				// driver.findElement(By.cssSelector("ul.c-autocomplete__list.sb-autocomplete__list.sb-autocomplete__list-two-lines
				// -visible li:nth-child(1)"));
				// elem.click();
				// WebElement searchBtn =
				// driver.findElement(By.className("sb-searchbox__button "));
				// searchBtn.click();
				
			} catch (Exception e) {
				System.out.println("Break13"+e);
				selectSheet.writeToSheet("11LGluUkKjp8dNBhCRDlYGOs8ZpEK-Iz69yvaQupcX40", "AllData1", allValues);
				boolean popo_up = driver.findElement(By.id("notification_lightbox")).isDisplayed();
					if (popo_up) {
						System.out.println("Pop-up opened");
						WebElement closeBtn = driver.findElement(By.className("modal-mask-closeBtn"));
						closeBtn.click();
						
					}
					}
		
				}
		
		// allValues.put("Delhi", totalValues);
		selectSheet.writeToSheet("11LGluUkKjp8dNBhCRDlYGOs8ZpEK-Iz69yvaQupcX40", "AllData1", allValues);
		// System.out.println(allValues);
	}

}
