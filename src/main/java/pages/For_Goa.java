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

public class For_Goa extends Helper {
	int valInit = 1;
	

	// public RemoteWebDriver driver=super.driver;
	// Map<String, Map<String, List<String>>> allValues= new HashMap<String,
	// Map<String, List<String>>>();
	Map<Object, Object> allValues = new LinkedHashMap<Object, Object>();

	public void runTest() throws InterruptedException, IOException {
		
		int counter = 1;
		String sr_header = "";
		int textSize = Integer
				.parseInt(driver.findElement(By.xpath("//div[@class='sr_header ']/h1")).getText().substring(5,8).replaceAll(",", ""));
		int vals = Integer.parseInt(driver.findElement(By.className("sr_showed_amount_last")).getText());
		int totalVals = textSize /vals;
		System.out.println("values" + textSize + " " + vals + " " + totalVals);
		
		System.out.println("Test Executed");
		WebDriverWait wait = new WebDriverWait(driver, 10);
	
		
		List<String> ab = new ArrayList<String>();
		ab.add("URL");
		ab.add("Pricing");
		ab.add("Start Rating");
		ab.add("Location Rating");
		ab.add("Hotel Count");
		ab.add("Aminities");
		ab.add("Rooms Info");
		ab.add("Room_with_Price");

		allValues.put("Hotel Name", ab);
		// Map<String, List<String>> totalValues=new
		// HashMap<String,List<String>>();
		for (int i = 1; i < totalVals + 2; i++) {
			String city = "Gurgaon";

			try {
				try {
					boolean element12 = driver.findElement(By.cssSelector("a#newsletter_button_footer")).isDisplayed();
					System.out.println(element12);
					// System.out.println("element12"+element12);
					// int kbc = 0;
					while (!element12) {

						JavascriptExecutor jse = (JavascriptExecutor) driver;
						jse.executeScript("window.scrollBy(0,-250)", "");
						element12 = driver.findElement(By.cssSelector("a#newsletter_button_footer")).isDisplayed();
						System.out.println(element12);
						// System.out.println("KBC" + kbc);

					}
				} catch (Exception e) {

				}
				String css = "";
				List<WebElement> element = driver.findElements(
						By.cssSelector("div.sr_item.sr_item_new.sr_item_default.sr_property_block.sr_flex_layout"));
				System.out.println("element" + element.size());
				//css = element.get(count).getCssValue("className");
				List<WebElement> elementlist = driver.findElements(By.cssSelector("" + css + "span.sr-hotel__name"));
				System.out.println("elementlist" + elementlist.size());
				List<WebElement> allListfor = driver.findElements(By.cssSelector("span.sr-hotel__name"));
				System.out.println("allListfor" + allListfor.size());
				List<WebElement> elementlistLink = driver.findElements(By.cssSelector("a.hotel_name_link.url"));
				System.out.println("elementlistLink" + elementlistLink.size());
				List<WebElement> prcies = driver.findElements(By.cssSelector("div.room_details"));
				System.out.println("prcies" + prcies.size());
				Thread.sleep(2000);
				try {
					boolean registerBut = driver.findElement(By.cssSelector("a.show_map sr_header--map")).isDisplayed();
					System.out.println(registerBut);
					int kbc = 0;
					while (registerBut) {

						JavascriptExecutor jse = (JavascriptExecutor) driver;
						jse.executeScript("window.scrollBy(0,250)", "");
						registerBut = driver.findElement(By.cssSelector("a.show_map sr_header--map")).isDisplayed();

						System.out.println(registerBut);
					}
				} catch (Exception e) {
				}

				List<WebElement> elementlistStar = null;
				try {
					elementlistStar = driver.findElements(
							By.cssSelector("h3.sr-hotel__title span[data-et-mouseenter^=OLZ] span.invisible_spoken"));
					System.out.println("elementlistStar" + elementlistStar.size());
				} catch (Exception e) {
					System.out.println(e + "Break1");
				}
				 int allListVal=allListfor.size();
				 //int count=0;
				
				for (int count=0;count<allListVal;count++) {
					try{
					System.out.println("Count Value"+count);
					//if(count==allListfor.size())
						//break;
				    String text =null;
				    String URL=null;
					int closeWinIcon=0;
					List<String> strm = new ArrayList<String>();
					try {
						text = allListfor.get(count).getText();
						//counter++;
					} catch (Exception e) {
						text = "Hotel Naame not find";
						
					     }
						// strm.add(text);
						try {
							URL = elementlistLink.get(count).getAttribute("href");
						} catch (Exception e12) {
							URL = "URL not found";
						}
						strm.add(URL);
						WebElement price = null;
						
							// String var =
							// prcies.get(count).getAttribute("className");
							String countforPrice = null;
							

							String priceNow=null;
							try{ priceNow=element.get(count).getAttribute("data-hotelid");
							}catch(Exception eb){System.out.println("Locator not found");}
							
							System.out.println("Countee here"+ count);
							try{
								
								System.out.println("priceNow"+priceNow);
						
								price=driver.findElement(By.cssSelector(
										"div[data-hotelid= \""+ priceNow+"\"] div.sr_item_content.sr_item_content_slider_wrapper div.sr_rooms_table_block.clearfix div.room_details td.roomPrice.sr_discount b"));
								String pricings = price.getText();
								System.out.println("pricings" + pricings);
								strm.add(pricings);
							} catch (Exception eb) {
								System.out.println("price not available");
								strm.add("Sold Out Property");
							}

						System.out.println("HotelName" + text);
						System.out.println("HotelUrl" + URL);

						try {
							WebElement startElem = driver.findElement(By.cssSelector(
									"div[data-hotelid= \""+ priceNow+"\"] div.sr_item_content.sr_item_content_slider_wrapper h3.sr-hotel__title span[data-et-mouseenter^=OLZ] span.invisible_spoken"));
							String star = startElem.getText();

							//if (star.length() < 15)
								strm.add(star);
							
							System.out.println("HotelStars" + star);
						} catch (Exception e3) {
							strm.add("Star Rating not available");
							System.out.println("Break Here " + e3);
						}

						String winHandle = driver.getWindowHandle();
						try {
							allListfor.get(count).click();
						} catch (Exception e4) {
							System.out.println("Not able to click on allList");
							try {
								boolean popo_up = driver.findElement(By.id("notification_lightbox")).isDisplayed();
								if (popo_up) {
									System.out.println("Pop-up opened");
									WebElement closeBtn = driver.findElement(By.className("modal-mask-closeBtn"));
									closeBtn.click();

								}

							} catch (Exception e1) {
								System.out.println("Break11" + e1);
							}
							Thread.sleep(5000);
						}
						
					

						Set<String> winHandles = driver.getWindowHandles();
						int winHandleCount = winHandles.size();
						System.out.println("count" + winHandleCount);
						
						int trial_counter=0;
						if (winHandleCount == 1)
							try {
								
								allListfor.get(count).click();
								
								while(winHandleCount==2)
								{
									allListfor.get(count).click();
							       Thread.sleep(3000);
							       trial_counter++;
							       if(trial_counter==3)
							    	   break;
								}
								
								
							} catch (Exception eb) {
								System.out.println("got an exception again");
								//count++;
							}
//						if (winHandleCount > 2) {
//							for (String handle : winHandles) {
//								if (handle.equals(winHandle))
//									continue;
//								else
//								driver.switchTo().window(handle);
//								driver.close();
//								driver.switchTo().window(winHandle);
//								//break;
//
//							}

	//					}
						Set<String> winHandlesNew = driver.getWindowHandles();
                        if(winHandlesNew.size()>1)
                        {	
						for (String handles : winHandlesNew) {

							if (handles.equals(winHandle))
								continue;
							else
								driver.switchTo().window(handles);
							// WebDriverWait wait=null;
							try {
								try {
									wait = new WebDriverWait(driver, 20);
									wait.until(new Predicate<WebDriver>() {
										public boolean apply(WebDriver driver) {
											return ((JavascriptExecutor) driver)
													.executeScript("return document.readyState").equals("complete");

										}
									});
								} catch (Exception e3) {
									System.out.println("Break16" + e3);
								}
								// WebElement hover=
								// driver.findElement(By.cssSelector("div#js--hp-gallery-scorecard
								// span.js--hp-scorecard-scoreword"));
								// Actions action = new Actions(driver);
								List<WebElement> location1 = null;
								try {
									wait.until(ExpectedConditions.presenceOfElementLocated(

									By.cssSelector(("span.review_list_score_breakdown_right p.review_score_value"))));
									driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
									location1 = driver.findElements(By.className("js--hp-scorecard-scoreword"));
								} catch (Exception e4) {
									System.out.println("Break 17" + e4);
									closeWinIcon=1;
									// driver.close();
									// driver.switchTo().window(winHandle);
								}
								System.out.println("current count"+count);
                                try
                                   {
								for (WebElement fir : location1) {
									try {
										fir.click();
										break;
									} catch (Exception e5) {
									}
								}
							}catch(Exception ee){System.out.println( "no close button");}
								List<WebElement> location = null;
								try {
									location = driver.findElements(By.cssSelector(
											"li.clearfix.one_col[data-question$=location] p.review_score_value"));
								} catch (Exception e6) {
								}
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
									} catch (Exception e7) {
										System.out.println(e7 + "Break3");
									}
								}

								System.out.println("loc" + actual);
								// System.out.println("Checkpoint1");
								if (actual != null) {
									strm.add(actual);
								}
								// System.out.println("Checkpoint2");}

								else
									strm.add("Location not found");
								// System.out.println("Checkpoint3");
								List<WebElement> close = null;
								try {
									close = driver.findElements(By.cssSelector("i.bicon-aclose"));
								
								// System.out.println("Checkpoint4");
								for (WebElement son : close) {
									// System.out.println("Checkpoint5");
									try {
										boolean bol = son.isDisplayed();

										if (bol) {
											son.click();
											break;
										}
									} catch (Exception e9) {
										System.out.println("Break5" + e9);
									}
								}
								} catch (Exception e8) {
									System.out.println("Break4" + e8);
								}

								JavascriptExecutor jse = (JavascriptExecutor) driver;
								jse.executeScript("window.scrollBy(0,250)", "");
								boolean valtrue = false;
								WebElement textElem = null;

								try {
									while (!valtrue) {
										try {
											textElem = driver.findElement(
													By.cssSelector("div.hotel_description_wrapper_exp.hp-description"));
											valtrue = textElem.isDisplayed();
											if (!valtrue) {
												jse.executeScript("window.scrollBy(0,250)", "");
											} 
											else
												break;
										} catch (Exception e10) {
											System.out.println("Break6" + e10);
											// driver.close();
											// driver.switchTo().window(winHandle);
											jse.executeScript("window.scrollBy(0,250)", "");
										}
									}
								} catch (Exception e11) {
									System.out.println("While Broken" + e11);
								}
								try {
									String texting = textElem.getText();

									System.out.println("textElem" + texting);

									if (texting != null && (texting.contains("Hotel Rooms :"))) {
										int indexOfHotel = texting.indexOf("Hotel Rooms :");
										String ind=null;
										try{
										 ind = texting.substring(indexOfHotel, indexOfHotel + 17);
										}catch(Exception ei)
										{
											ind=texting.substring(indexOfHotel, indexOfHotel + 16);
										}
										System.out.println(ind);
										strm.add(ind);
									} else
										strm.add("Hotel count is not there");
								} catch (Exception e12) {
									System.out.println("Break7" + e12);
								}

								try {
									// System.out.println("Checkpoint6");
									List<WebElement> aminities = driver.findElements(By.cssSelector(
											"div.hp_desc_important_facilities div.important_facility.hp-desc-facility"));
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
									if (aminity.length() > 1 && (aminity != null))
										strm.add(aminity);
									else
										strm.add("No Aminity");

								} catch (Exception e13) {
									System.out.println("Break8" + e13);
								}
								
								try
								{
								 jse.executeScript("window.scrollBy(0,250)", "");
								//List<String> hotelVals =new ArrayList<String>();
								List<WebElement> hotels =driver.findElements(By.cssSelector("tbody#room_availability_container a.jqrt.togglelink.js-track-hp-rt-room-name"));	
								String hotelsVals="";
								 String values="";
								for(WebElement hotVals:hotels)
								{
									int length= hotVals.getAttribute("href").length();
									String id= hotVals.getAttribute("href").substring(length-8, length);
									System.out.println("Hotel Ka URL"+id);
									String hotel_type=hotVals.getAttribute("data-room-name-en");
									if(hotel_type==null)
										hotel_type=hotVals.getText();
									     System.out.println("Hotel_type"+hotel_type);
									     hotelsVals= hotelsVals+hotel_type+"\n ";
									    	 ;
									     Thread.sleep(2000);
									     try{
									     wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tr[id^=\""+id+"\"] strong")));
									     }catch(Exception e){
									    	 id=hotVals.getAttribute("href").substring(length-9, length);
									     }
									  List<WebElement> valVal= driver.findElements(By.cssSelector("tr[id^=\""+id+"\"] strong"));
									 
									  for(WebElement elem: valVal)
									   {
										   String valing= elem.getText();
										   System.out.println("valing"+valing);
										   if(valing!=null && valing.contains("Rs"))
										   {
											   values=values+valing+"\n ";
										   }
										 											  
									   }
								}
								if(hotelsVals!=null)
								strm.add(hotelsVals);
							   if(values!=null)
								strm.add(values);
								}
								catch(Exception e)
								{
									strm.add("Hotel Not Present");
									 strm.add("Price not Present");
								}
								
							   if(!(driver.getWindowHandle().equals(winHandle)))
	 							driver.close();
								
								driver.switchTo().window(winHandle);

								try {
									if (winHandleCount > 2) {
										for (String handle : winHandlesNew) {
											if (handle.equals(winHandle))
												continue;
											else
											driver.switchTo().window(handle);
											driver.close();
											driver.switchTo().window(winHandle);
											
                                        }   
										}
									 Set<String> getLatestCount=driver.getWindowHandles();
									  if(closeWinIcon==1&& getLatestCount.size()==2)
									  {
										  for (String handle : getLatestCount) 
										  {
										  if (handle.equals(winHandle))
												continue;
											else
											driver.switchTo().window(handle);
											driver.close();
											driver.switchTo().window(winHandle);
											
                                      }   
									  }
								
									 
									boolean popo_up = driver.findElement(By.id("notification_lightbox")).isDisplayed();
									if (popo_up) {
										System.out.println("Pop-up opened");
										WebElement closeBtn = driver.findElement(By.className("modal-mask-closeBtn"));
										closeBtn.click();
									}

								} catch (Exception ec) {
									// System.out.println("Break8"+ec);
								}

							
							} catch (Exception e14) {
								System.out.println("Break15" + e14);
							
							}

							String Tprice = "";
							WebElement elementlistPrice1 = null;
							WebElement elementlistPrice = null;
							
							JavascriptExecutor jse = (JavascriptExecutor) driver;
							jse.executeScript("window.scrollBy(0,250)", "");
							//count++;
							System.out.println("hello" + count);
							if(driver.getWindowHandle()!=winHandle)
								driver.switchTo().window(winHandle);
						
							System.out.println(winHandle+" "+driver.getWindowHandle());
						   
                        }
					
                        }
                        else
                        	continue;
						allValues.put(text, strm);
						System.out.println("enterimList" + allValues);
						
				
				}catch(Exception e){}
				}
				
			
					//count = 0;
					try {
						boolean popo_up = driver.findElement(By.id("notification_lightbox")).isDisplayed();
						if (popo_up) {
							System.out.println("Pop-up opened");
							WebElement closeBtn = driver.findElement(By.className("modal-mask-closeBtn"));
							closeBtn.click();

						}

					} catch (Exception e1) {
						System.out.println("Break11" + e1);
					}
					try {
						boolean element12 = driver.findElement(By.className("sr_pagination_link")).isDisplayed();

						while (element12 == false) {
							JavascriptExecutor jse = (JavascriptExecutor) driver;
							jse.executeScript("window.scrollBy(250,0)", "");
							element12 = driver.findElement(By.className("sr_pagination_link")).isDisplayed();
						}

						selectSheet.writeToSheet("11LGluUkKjp8dNBhCRDlYGOs8ZpEK-Iz69yvaQupcX40", "Goa", allValues);

						valInit++;
						String val = Integer.toString(valInit);

						WebElement elemhi = driver.findElement(By.linkText(val));
						elemhi.click();
						// WebDriverWait wait = new WebDriverWait(driver, 20);
						wait.until(ExpectedConditions.elementToBeClickable(By.className("switch-map-view")));
						// this.runTest();
						Thread.sleep(10000);
					} catch (Exception e) {

						System.out.println("Break12" + e);

					}
					// element.sendKeys("Delhi");
					Thread.sleep(2000);

				
			} catch (Exception e) {
				System.out.println("Break13" + e);
				selectSheet.writeToSheet("11LGluUkKjp8dNBhCRDlYGOs8ZpEK-Iz69yvaQupcX40", "GoaData", allValues);
				try {
					boolean popo_up = driver.findElement(By.id("notification_lightbox")).isDisplayed();
					if (popo_up) {
						System.out.println("Pop-up opened");
						WebElement closeBtn = driver.findElement(By.className("modal-mask-closeBtn"));
						closeBtn.click();
					}
				} catch (Exception e1) {
				}

			}

		}

		// allValues.put("Delhi", totalValues);
		selectSheet.writeToSheet("11LGluUkKjp8dNBhCRDlYGOs8ZpEK-Iz69yvaQupcX40", "GoaData", allValues);
		// System.out.println(allValues);
	}

}
