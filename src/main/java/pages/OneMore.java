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

public class OneMore extends Helper {
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
		String sr_header = "";
		int textSize = Integer
			.parseInt(driver.findElement(By.xpath("//div[@class='sr_header ']/h1")).getText().substring(9,12));
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
				// for (WebElement elem1 : elementlistStar) {
				// System.out.println(elem1.getAttribute("className"));
				// System.out.println(elem1.getText());
				//
				// }
				for (WebElement elem : element) {
					List<String> strm = new ArrayList<String>();

					text = elementlist.get(count).getText();
					// strm.add(text);

					URL = elementlistLink.get(count).getAttribute("href");
					strm.add(URL);
					System.out.println("HotelName" + text);
					System.out.println("HotelUrl" + URL);

					//try{
				//	String attrb = element.get(count).getAttribute("data-hotelid");
					//System.out.println(attrb);
					//WebElement piceElem=driver.findElement(By.cssSelector("div.sr_item.sr_item_new.sr_item_default.sr_property_block.sr_flex_layout.sr_ph_full.compset_loaded[data-hotelid=\""+attrb+"\"] div.sr_item_content.sr_item_content_slider_wrapper strong.price.scarcity_coloranim_rack_rate.b"));
					//String price=piceElem.getText();
					//System.out.println("price"+price);
					//} catch(Exception e){System.out.println("attrib has broken"+e);}
					try {
						String star = elementlistStar.get(count).getText();
						if (star.length() < 15)
							strm.add(star);
						else
							strm.add("Star Rating not available");

						System.out.println("HotelStars" + star);
					} catch (Exception e) {
						System.out.println(e+"Break2");
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

								if (texting != null && (texting.contains("Hotel Rooms :"))) {
									int indexOfHotel = texting.indexOf("Hotel Rooms :");
									String ind = texting.substring(indexOfHotel, indexOfHotel + 17);
									System.out.println(ind);
									strm.add(ind);
									System.out.println("not reaching here");
								} else
									strm.add("Hotel count is not there");
							} catch (Exception e) {
								System.out.println("Break7"+e);
								strm.add("Exception occured");
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
							
//                           
//							if (driver.getWindowHandles().size() > 1) {
//								Set<String> lists = driver.getWindowHandles();
//								for (String vkls : lists) {
//									try {
//
//										if (vkls.equals(winHandle))
//											continue;
//										else
//											driver.switchTo().window(vkls);
//										driver.close();
//									} catch (Exception e) {
//										System.out.println("Break9"+e);
//									}
//								}
//								driver.switchTo().window(winHandle);
//								try{
//									boolean popo_up = driver.findElement(By.id("notification_lightbox")).isDisplayed();
//									if (popo_up) {
//										System.out.println("Pop-up opened");
//										WebElement closeBtn = driver.findElement(By.className("modal-mask-closeBtn"));
//										closeBtn.click();
//									}
//									
//								}catch(Exception ec){
//									System.out.println("Break10"+ec);
//								}
//							}
							//else
							//continue;
						} catch (Exception e) {
							System.out.println("Break15"+e);
//							if ((driver.getWindowHandles().size() > 1)) {
//
//								Set<String> lists = driver.getWindowHandles();
//
//								for (String vkls : lists) {
//									if (vkls.equals(winHandle))
//										continue;
//									else
//										driver.switchTo().window(vkls);
//									driver.close();
//
//								}
//							} else
//								driver.switchTo().window(winHandle);
//						try{
//							boolean popo_up = driver.findElement(By.id("notification_lightbox")).isDisplayed();
//							if (popo_up) {
//								System.out.println("Pop-up opened");
//								WebElement closeBtn = driver.findElement(By.className("modal-mask-closeBtn"));
//								closeBtn.click();
//							}
//							
//						}catch(Exception ec){
//							System.out.println("Break11"+ec);
//						}
//						}
					}

					String Tprice = "";
					WebElement elementlistPrice1 = null;
					WebElement elementlistPrice = null;
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("window.scrollBy(0,250)", "");
					// try{
					//
					// elementlistPrice=
					// driver.findElement(By.cssSelector("div.sr_item
					// sr_item_new.sr_item_default.sr_property_block.sr_flex_layout[data-hotelid='"+attrb+"']
					// div:nth-of-type(2) div.sr_rooms_table_block.clearfix
					// div.room_details td.roomPrice.sr_discount
					// div.js_rackrate_animation_anchor.smart_price_style.b_bigger_tag
					// strong.price.availprice.no_rack_rate > b"));
					// //elementlistPrice=
					// driver.findElement(By.xpath("//div[@data-hotelid='"+attrb+"']//strong[@class='no_rack_rate']"));
					// }catch(Exception e){System.out.println(e);}
					// try{
					// elementlistPrice1=
					// driver.findElement(By.cssSelector("div.sr_item
					// sr_item_new.sr_item_default.sr_property_block.sr_flex_layout[data-hotelid='"+attrb+"']
					// div:nth-of-type(2) div.sr_rooms_table_block.clearfix
					// div.room_details td.roomPrice.sr_discount
					// div.js_rackrate_animation_anchor.smart_price_style.b_bigger_tag.animated
					// strong.price.scarcity_color.anim_rack_rate > b"));
					// // elementlistPrice1=
					// driver.findElement(By.xpath("//div[@data-hotelid='"+attrb+"']//strong[@class='anim_rack_rate']"));
					// }catch(Exception e){System.out.println(e);}

					// WebDriverWait wait = new WebDriverWait(driver, 20);
					// driver.manage().timeouts().implicitlyWait(3,
					// TimeUnit.SECONDS);
					// // System.out.println("price "+elementlistPrice.size();
					//
					// //System.out.println("price1 "+elementlistPrice1.size());
					// try{
					// boolean bool =elementlistPrice.isDisplayed();
					// boolean bool1 =elementlistPrice1.isDisplayed();
					// if(bool){
					// Tprice =elementlistPrice.getText();}
					// else
					// if(bool1)
					// {
					// Tprice=elementlistPrice1.getText();
					// }
					// System.out.println(Tprice);
					// if(Tprice!=null)
					// {
					//
					// strm.add(Tprice);
					// }
					//
					//
					// } catch(Exception e) {System.out.println(e);}
					count++;
					System.out.println("hello" + count);
					//if(count==2)
						//break;
					// try {
					// boolean element1 =
					// driver.findElement(By.className("sr_pagination_link")).isDisplayed();
					//
					// while (element1 == false) {
					// JavascriptExecutor jse1 = (JavascriptExecutor) driver;
					// jse1.executeScript("window.scrollBy(0,250)", "");
					// element1 =
					// driver.findElement(By.className("sr_pagination_link")).isDisplayed();
					// }
					// } catch (Exception e) {
					// System.out.println(e);
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
					selectSheet.writeToSheet("11LGluUkKjp8dNBhCRDlYGOs8ZpEK-Iz69yvaQupcX40", "Gurgaon", allValues);
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
				selectSheet.writeToSheet("11LGluUkKjp8dNBhCRDlYGOs8ZpEK-Iz69yvaQupcX40", "AllData", allValues);
				boolean popo_up = driver.findElement(By.id("notification_lightbox")).isDisplayed();
					if (popo_up) {
						System.out.println("Pop-up opened");
						WebElement closeBtn = driver.findElement(By.className("modal-mask-closeBtn"));
						closeBtn.click();
						
					}
					}
		
				}
		
		// allValues.put("Delhi", totalValues);
		selectSheet.writeToSheet("11LGluUkKjp8dNBhCRDlYGOs8ZpEK-Iz69yvaQupcX40", "AllData", allValues);
		// System.out.println(allValues);
	}

}
