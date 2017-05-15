package utils;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.google.common.base.Predicate;

public abstract class Helper extends BrowserFactory {

	public String day1;
	public String day2;
	public RemoteWebDriver driver;
	String filePathScreenShot =System.getProperty("user.dir");

	public void getDate() {
		Calendar date1, date2;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		date1 = Calendar.getInstance();
		date1.setTime(new Date()); // Now use today date.
		date1.add(Calendar.DATE, 1); // Adding 1 days
		date2 = Calendar.getInstance();
		date2.setTime(new Date()); // Now use today date.
		date2.add(Calendar.DATE, 2); // Adding 2 days
		this.day1 = sdf.format(date1.getTime());
		this.day2 = sdf.format(date2.getTime());
	}

	public By locatorClass(String locator) {
		By by = new By.ByClassName(locator);
		return by;

	}

	public By locatorVal(String locator) {
		By by = new By.ById(locator);
		return by;

	}

	public void waitTypeLocated(int time, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public void waitTypeVisibility(int time, By locator) {
		String xyz = "presenceOfElementLocated";
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public WebElement locateByName(String name) {
		WebElement element = driver.findElement(By.name(name));
		return element;
	}

	public List<WebElement> locateByNames(String name) {
		List<WebElement> element = driver.findElements(By.name(name));
		return element;
	}

	public WebElement locateByClass(String className) {
		WebElement element = driver.findElement(By.className(className));
		return element;
	}

	public List<WebElement> locateByClasses(String className) {
		List<WebElement> element = driver.findElements(By.className(className));
		return element;
	}


	public List<Integer> performOperation(WebElement elem, String SearchCriteria) {
		//Map<String, List<String>> inList = new HashMap<String, List<String>>();
		List<Integer> vals = new ArrayList<Integer>();
		System.out.println("reached at this point");
		new Actions(driver).moveToElement(elem).click().build().perform();
		System.out.println("reached after this point");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement searchButton = driver.findElement(By.id(SearchCriteria));
		searchButton.click();
		WebDriverWait wait = this.waiting(30);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.className("c-dropdown-value")));
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@mt-id='hotel_splashed_price']")));

			List<WebElement> allVals = driver.findElements(By.className("card__BArea"));
			System.out.println("Size of list " + allVals.size());
			String driverWin = driver.getWindowHandle();

			for (WebElement elem1 : allVals) {
				
				try {
					System.out.println("getTheValue" + elem1.getText());

					elem1.click();
				} catch (Exception e) {
					System.out.println("Exception occured");
				}
				Set<String> handles = driver.getWindowHandles();
				for (String webElems : handles) {
					try {
						if ((webElems.equals(driverWin)))
							continue;
						else {
							driver.switchTo().window(webElems);
							// driver.manage().timeouts().implicitlyWait(10,
							// TimeUnit.SECONDS);

							List<WebElement> element = new ArrayList<WebElement>();
							//List<WebElement> elementSec = new ArrayList<WebElement>();
							try {	

								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@mt-id='filter_name' and contains(text(),'Including Meal(s)')]")));
								JavascriptExecutor js = (JavascriptExecutor) driver;
								js.executeScript("window.scrollBy(0,250)", "");
								WebElement meals=driver.findElement(By.xpath("//span[@mt-id='filter_name' and contains(text(),'Including Meal(s)')]"));
								meals.click();
							} catch (Exception e) {
								System.out.println("Entered the exception block" +e);
								driver.close();
								driver.switchTo().window(driverWin);
								break;
							}
							element = driver.findElements(By.id("details-select-book1"));
							System.out.println("Size of the array" + element.size());

							if (element.size()!= 0) {
								System.out.println("reached Before this"+element.get(0).getText());
								int i=0;
								while(i<element.size())
								{ 
									try
									{
										element.get(i).click();
										break;
									}catch(Exception e)
									{
										System.out.println(e);
										System.out.println("value of i" +i);
										i++;
									}
								}
								System.out.println("reached After this");
								wait.until( new Predicate<WebDriver>() {
						            public boolean apply(WebDriver driver) {
						                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
						            }
						        }
						    );
									
								
								try
								{
								wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.clearfix.append_bottom4.grand_total.hidden-xs #price_review  p:nth-child(2)")));
								} catch(Exception e) {}
								driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
								
								WebElement elementFin = driver.findElement(By.cssSelector("div.clearfix.append_bottom4.grand_total.hidden-xs #price_review  p:nth-child(2)"));

								String val=elementFin.getText();
								int value=0;
								if(val.length()>0){
									value=Integer.parseInt(val.substring(4).replace(",", ""));
									vals.add(value);
								}
								else
								{
									System.out.println("The size of the list is null"+value); 
								}	

								System.out.println("reached here after runnning the code"+value); 
								//	Thread.sleep(10000);
								driver.close();
								driver.switchTo().window(driverWin);
								break;
							}

							else {
								driver.close();
								driver.switchTo().window(driverWin);
								break;
							}
							//System.out.println(vals);
						}
						
					} catch (Exception e) {
						System.out.println("Exception" + e);
						driver.close();
						driver.switchTo().window(driverWin);
						break;
					}
				//	System.out.println(vals);

				}
			}
		} catch (Exception e) {

		}
		return vals;
	}

	public void maximize() {
		driver.manage().window().maximize();

	}

	public void waitImplicit(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public WebElement locateByid(String id) {
		WebElement element = driver.findElement(By.id(id));
		return element;
	}

	public List<WebElement> locateByids(String id) {
		List<WebElement> element = driver.findElements(By.id(id));
		return element;
	}

	public WebElement locateByXpath(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element;
	}

	public WebDriverWait waiting(int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait;
	}

	public List<WebElement> locateByXpaths(String xpath) {
		List<WebElement> element = driver.findElements(By.xpath(xpath));
		return element;
	}

	public RemoteWebDriver InitializeDriverMM(RemoteWebDriver driver) {

		driver.get("https://www.makemytrip.com");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait1 = new WebDriverWait(driver, 2);
		try {
			wait1.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[contains(@class, 'hdr_item_link') and text()='hotels']")));
			// WebElement element =
			// driver.findElement(By.xpath("//a[contains(@class,
			// 'hdr_item_link') and text()='hotels']"));

		} catch (Exception e) {
			System.out.println("unwanted window opens up closing now");
			driver.close();
			// driver.quit();
			InitializeDriverMM(driver);

		}
		return this.driver = driver;

	}

	public RemoteWebDriver InitializeSomeOtherWindow() {
		driver.get("https://www.goibibo.com/");
		System.out.println("Reached");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		return driver;
	}

	public RemoteWebDriver InitializeIbibo(RemoteWebDriver driver) {
		driver.get("https://www.goibibo.com/");
		System.out.println("Reached");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebDriverWait wait = this.waiting(20);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("icon-hotels")));
		return driver;
	}

	public RemoteWebDriver InitializeBooking(RemoteWebDriver driver) {
		driver.get("http://www.booking.com/searchresults.en-gb.html?label=gen173nr-1FCAEoggJCAlhYSDNiBW5vcmVmaGyIAQGYAS64AQfIAQzYAQHoAQH4AQuoAgM&sid=77c6026325a377612508237d425f855a&sb=1&src=searchresults&src_elem=sb&error_url=http%3A%2F%2Fwww.booking.com%2Fsearchresults.en-gb.html%3Flabel%3Dgen173nr-1FCAEoggJCAlhYSDNiBW5vcmVmaGyIAQGYAS64AQfIAQzYAQHoAQH4AQuoAgM%3Bsid%3D77c6026325a377612508237d425f855a%3Bcheckin_month%3D3%3Bcheckin_monthday%3D26%3Bcheckin_year%3D2017%3Bcheckout_month%3D3%3Bcheckout_monthday%3D27%3Bcheckout_year%3D2017%3Bcity%3D-2106102%3Bclass_interval%3D1%3Bdest_id%3D-2096897%3Bdest_type%3Dcity%3Bdtdisc%3D0%3Bgroup_adults%3D2%3Bgroup_children%3D0%3Binac%3D0%3Blabel_click%3Dundef%3Bmih%3D0%3Bno_rooms%3D1%3Boffset%3D0%3Bpostcard%3D0%3Braw_dest_type%3Dcity%3Broom1%3DA%252CA%3Bsb_price_type%3Dtotal%3Bsearch_selected%3D1%3Bsrc%3Dsearchresults%3Bsrc_elem%3Dsb%3Bss%3DGurgaon%252C%2520Delhi%2520NCR%252C%2520India%3Bss_all%3D0%3Bss_raw%3Dgurgaon%3Bssb%3Dempty%3Bsshis%3D0%3Bssne_untouched%3DNew%2520Delhi%26%3B&ss=Gurgaon&ssne=Gurgaon&ssne_untouched=Gurgaon&city=-2096897&checkin_monthday=28&checkin_month=3&checkin_year=2017&checkout_monthday=29&checkout_month=3&checkout_year=2017&room1=A%2CA&no_rooms=1&group_adults=2&group_children=0");
		System.out.println("Reached google");
		try{
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sr_header ']/h1")));
		}
		catch(Exception e){System.out.println(e);}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return this.driver=driver;
	}
	
	public RemoteWebDriver InitializeBooking1(RemoteWebDriver driver) {
		driver.get("http://www.booking.com/searchresults.en-gb.html?label=gen173nr-1FCAEoggJCAlhYSDNiBW5vcmVmaGyIAQGYAS64AQfIAQzYAQHoAQH4AQuoAgM&sid=77c6026325a377612508237d425f855a&sb=1&src=searchresults&src_elem=sb&error_url=http%3A%2F%2Fwww.booking.com%2Fsearchresults.en-gb.html%3Flabel%3Dgen173nr-1FCAEoggJCAlhYSDNiBW5vcmVmaGyIAQGYAS64AQfIAQzYAQHoAQH4AQuoAgM%3Bsid%3D77c6026325a377612508237d425f855a%3Bcheckin_month%3D3%3Bcheckin_monthday%3D29%3Bcheckin_year%3D2017%3Bcheckout_month%3D3%3Bcheckout_monthday%3D30%3Bcheckout_year%3D2017%3Bcity%3D-2098033%3Bclass_interval%3D1%3Bdtdisc%3D0%3Bgroup_adults%3D2%3Bgroup_children%3D0%3Binac%3D0%3Blabel_click%3Dundef%3Bmih%3D0%3Bno_rooms%3D1%3Boffset%3D0%3Bpostcard%3D0%3Broom1%3DA%252CA%3Bsb_price_type%3Dtotal%3Bsrc%3Dsearchresults%3Bsrc_elem%3Dsb%3Bss%3DJaipur%3Bss_all%3D0%3Bssb%3Dempty%3Bsshis%3D0%3Bssne%3DJaipur%3Bssne_untouched%3DJaipur%26%3B&ss=Noida%2C+Delhi+NCR%2C+India&ssne=Jaipur&ssne_untouched=Jaipur&city=-2098033&checkin_monthday=29&checkin_month=3&checkin_year=2017&checkout_monthday=30&checkout_month=3&checkout_year=2017&room1=A%2CA&no_rooms=1&group_adults=2&group_children=0&ss_raw=noida&ac_position=0&ac_langcode=en&dest_id=900048213&dest_type=city&search_pageview_id=1a9f7d868de90357&search_selected=true&dest_id=900048213&dest_type=city&search_pageview_id=1a9f7d868de90357&search_selected=true&search_pageview_id=1a9f7d868de90357&ac_suggestion_list_length=5&ac_suggestion_theme_list_length=0");
		System.out.println("Reached google");
		try{
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sr_header ']/h1")));
		}
		catch(Exception e){System.out.println(e);}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return this.driver=driver;
	}
	
	public RemoteWebDriver InitializeBookingNoida(RemoteWebDriver driver) {
		driver.get("http://www.booking.com/searchresults.en-gb.html?label=gen173nr-1FCAEoggJCAlhYSDNiBW5vcmVmaGyIAQGYAS64AQfIAQzYAQHoAQH4AQuoAgM&sid=77c6026325a377612508237d425f855a&sb=1&src=searchresults&src_elem=sb&error_url=http%3A%2F%2Fwww.booking.com%2Fsearchresults.en-gb.html%3Flabel%3Dgen173nr-1FCAEoggJCAlhYSDNiBW5vcmVmaGyIAQGYAS64AQfIAQzYAQHoAQH4AQuoAgM%3Bsid%3D77c6026325a377612508237d425f855a%3Bcheckin_month%3D3%3Bcheckin_monthday%3D28%3Bcheckin_year%3D2017%3Bcheckout_month%3D3%3Bcheckout_monthday%3D29%3Bcheckout_year%3D2017%3Bcity%3D-2096897%3Bclass_interval%3D1%3Bdtdisc%3D0%3Bgroup_adults%3D2%3Bgroup_children%3D0%3Binac%3D0%3Blabel_click%3Dundef%3Bmih%3D0%3Bno_rooms%3D1%3Boffset%3D0%3Bpostcard%3D0%3Broom1%3DA%252CA%3Bsb_price_type%3Dtotal%3Bsrc%3Dsearchresults%3Bsrc_elem%3Dsb%3Bss%3DGurgaon%3Bss_all%3D0%3Bssb%3Dempty%3Bsshis%3D0%3Bssne%3DGurgaon%3Bssne_untouched%3DGurgaon%26%3B&ss=New+Delhi%2C+Delhi+NCR%2C+India&ssne=Gurgaon&ssne_untouched=Gurgaon&city=-2096897&checkin_monthday=28&checkin_month=3&checkin_year=2017&checkout_monthday=29&checkout_month=3&checkout_year=2017&room1=A%2CA&no_rooms=1&group_adults=2&group_children=0&ss_raw=Delhi&ac_position=0&ac_langcode=en&dest_id=-2106102&dest_type=city&search_pageview_id=797d5bb7aa9b018b&search_selected=true&search_pageview_id=797d5bb7aa9b018b&ac_suggestion_list_length=5&ac_suggestion_theme_list_length=0");
		System.out.println("Reached google");
		try{
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sr_header ']/h1")));
		}
		catch(Exception e){System.out.println(e);}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return this.driver=driver;
	}
	
	
	public RemoteWebDriver InitializeBookingGurgaon(RemoteWebDriver driver) {
		driver.get("http://www.booking.com/searchresults.en-gb.html?aid=304142&label=gen173nr-1FCAEoggJCAlhYSDNiBW5vcmVmaGyIAQGYAS64AQfIAQzYAQHoAQH4AQuoAgM&sid=77c6026325a377612508237d425f855a&sb=1&src=searchresults&src_elem=sb&error_url=http%3A%2F%2Fwww.booking.com%2Fsearchresults.en-gb.html%3Faid%3D304142%3Blabel%3Dgen173nr-1FCAEoggJCAlhYSDNiBW5vcmVmaGyIAQGYAS64AQfIAQzYAQHoAQH4AQuoAgM%3Bsid%3D77c6026325a377612508237d425f855a%3Bcheckin_month%3D4%3Bcheckin_monthday%3D1%3Bcheckin_year%3D2017%3Bcheckout_month%3D4%3Bcheckout_monthday%3D2%3Bcheckout_year%3D2017%3Bcity%3D-74897%3Bclass_interval%3D1%3Bdest_id%3D-2096897%3Bdest_type%3Dcity%3Bdtdisc%3D0%3Bgroup_adults%3D2%3Bgroup_children%3D0%3Binac%3D0%3Blabel_click%3Dundef%3Bmih%3D0%3Bno_rooms%3D1%3Boffset%3D0%3Bpostcard%3D0%3Braw_dest_type%3Dcity%3Broom1%3DA%252CA%3Bsb_price_type%3Dtotal%3Bsb_travel_purpose%3Dleisure%3Bsearch_selected%3D1%3Bsrc%3Dsearchresults%3Bsrc_elem%3Dsb%3Bss%3DGurgaon%252C%2520Delhi%2520NCR%252C%2520India%3Bss_all%3D0%3Bss_raw%3Dgurgaon%3Bssb%3Dempty%3Bsshis%3D0%3Bssne_untouched%3DBelgrade%26%3B&ss=Gurgaon&ssne=Gurgaon&ssne_untouched=Gurgaon&city=-2096897&sb_travel_purpose=leisure&checkin_monthday=2&checkin_month=4&checkin_year=2017&checkout_monthday=3&checkout_month=4&checkout_year=2017&room1=A%2CA&no_rooms=1&group_adults=2&group_children=0");
		System.out.println("Reached google");
		try{
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sr_header ']/h1")));
		}
		catch(Exception e){System.out.println(e);}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return this.driver=driver;
	}
	public RemoteWebDriver InitializeBookingGoa(RemoteWebDriver driver) {
	driver.get("https://www.booking.com/searchresults.en-gb.html?label=gen173rf-1FCAEoggJCAlhYSDNYA2hsiAEBmAEuuAEHyAEM2AEB6AEB-AELkgIBeaICDG95b3Jvb21zLmNvbagCAw&sid=77c6026325a377612508237d425f855a&checkin_month=5&checkin_monthday=2&checkin_year=2017&checkout_month=5&checkout_monthday=3&checkout_year=2017&class_interval=1&dest_id=4127&dest_type=region&dtdisc=0&group_adults=2&group_children=0&inac=0&index_postcard=0&label_click=undef&map=1&mih=0&no_rooms=1&postcard=0&raw_dest_type=region&room1=A%2CA&sb_price_type=total&search_selected=1&src=index&ss=Goa%2C%20India&ss_all=0&ss_raw=goa&ssb=empty&sshis=0&nflt=class%3D3%3B&lsf=class%7C3%7C175&unchecked_filter=class");
	System.out.println("Reached google");
	try{
	WebDriverWait wait = new WebDriverWait(driver,20);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sr_header ']/h1")));
	}
	catch(Exception e){System.out.println(e);}
	//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	return this.driver=driver;
	}
	public RemoteWebDriver InitializeBookingDelhi(RemoteWebDriver driver) {
		driver.get("https://www.booking.com/searchresults.en-gb.html?label=gen173rf-1FCAEoggJCAlhYSDNYA2hsiAEBmAEuuAEHyAEM2AEB6AEB-AELkgIBeaICDG95b3Jvb21zLmNvbagCAw&sid=77c6026325a377612508237d425f855a&checkin_month=5&checkin_monthday=2&checkin_year=2017&checkout_month=5&checkout_monthday=3&checkout_year=2017&class_interval=1&dest_id=-2106102&dest_type=city&dtdisc=0&group_adults=2&group_children=0&inac=0&index_postcard=0&label_click=undef&mih=0&no_rooms=1&postcard=0&raw_dest_type=city&room1=A%2CA&sb_price_type=total&search_selected=1&src=index&ss=New%20Delhi%2C%20Delhi%20NCR%2C%20India&ss_all=0&ss_raw=delhi&ssb=empty&sshis=0&nflt=class%3D3%3B&lsf=class%7C3%7C438&unchecked_filter=class");
		System.out.println("Reached google");
		try{
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sr_header ']/h1")));
		}
		catch(Exception e){System.out.println(e);}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return this.driver=driver;
	}
	
	public void takeScreenShot(String testClassName, String testMethodName) {
		String newFolderName = filePathScreenShot + " " + build_number;
		System.out.println(driver.getCurrentUrl());	
		System.out.println();	
		File targetFolder = new File(newFolderName);
		if (!targetFolder.exists()) {
			// System.out.println("File created " + targetFolder);
			targetFolder.mkdir();
		}

		// taking screenshot
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// adding test class name before the test method while creating
		// screenshot file
		File targetFile = new File(newFolderName + testClassName + "_" + testMethodName + ".png");

		// The below method will save the screen shot with test class and method
		// name
		try {
			FileUtils.copyFile(scrFile, targetFile);
		} catch (IOException e) {
			 System.out.println(e+"");
			e.printStackTrace();
		}
	}

	

	public RemoteWebDriver InitializeBookingBangalore(RemoteWebDriver driver) {
		driver.get("https://www.booking.com/searchresults.en-gb.html?label=gen173rf-1FCAEoggJCAlhYSDNYA2hsiAEBmAEuuAEHyAEM2AEB6AEB-AELkgIBeaICDG95b3Jvb21zLmNvbagCAw&sid=77c6026325a377612508237d425f855a&checkin_month=5&checkin_monthday=2&checkin_year=2017&checkout_month=5&checkout_monthday=3&checkout_year=2017&city=-2106102&class_interval=1&dest_id=-2090174&dest_type=city&dtdisc=0&group_adults=2&group_children=0&inac=0&index_postcard=0&label_click=undef&mih=0&no_rooms=1&postcard=0&raw_dest_type=city&room1=A%2CA&sb_price_type=total&search_selected=1&src=searchresults&ss=Bangalore%2C%20Karnataka%2C%20India&ss_all=0&ss_raw=Bangalore&ssb=empty&sshis=0&ssne_untouched=New%20Delhi&nflt=class%3D3%3B&lsf=class%7C3%7C239&unchecked_filter=class");
		System.out.println("Reached google");
		try{
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sr_header ']/h1")));
		}
		catch(Exception e){System.out.println(e);}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return this.driver=driver;
	}
	
	public RemoteWebDriver InitializeBookingKerala(RemoteWebDriver driver) {
		driver.get("https://www.booking.com/searchresults.en-gb.html?label=gen173rf-1FCAEoggJCAlhYSDNYA2hsiAEBmAEuuAEHyAEM2AEB6AEB-AELkgIBeaICDG95b3Jvb21zLmNvbagCAw&sid=77c6026325a377612508237d425f855a&checkin_month=6&checkin_monthday=1&checkin_year=2017&checkout_month=6&checkout_monthday=2&checkout_year=2017&city=-2094056&class_interval=1&dest_id=3476&dest_type=region&dtdisc=0&group_adults=2&group_children=0&inac=0&index_postcard=0&label_click=undef&map=1&mih=0&no_rooms=1&postcard=0&raw_dest_type=region&room1=A%2CA&sb_price_type=total&search_selected=1&src=searchresults&ss=Kerala%2C%20India&ss_all=0&ss_raw=Kerela&ssb=empty&sshis=0&ssne_untouched=Darjeeling&nflt=class%3D3%3B&lsf=class%7C3%7C313&unchecked_filter=class#map_closed");
		System.out.println("Reached google");
		try{
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sr_header ']/h1")));
		}
		catch(Exception e){System.out.println(e);}
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return this.driver=driver;
	}
	
	
	
	public Map<String,Integer> getDateElement( int date)
	{
		Map<String, Integer> mapping = new HashMap<String, Integer>();
		int a=0;
		int b=0;
		if(1<=date && date<=5)	
		{
			a=1;
			b=2+date;			
		}
		else
		if(6<=date && date<=12)
		{
			a=2;
		    b=date%5;
		    if(date>9)
		    	b=date%5+5;
		}
		else
		 if(13<=date && date<=19)	
		 {
			 a=3;
			 b=date%12;
		 }
		 else
		  if(20<=date && date<=26)
		  {
			  a=4;
			  b=date%19;
		  }
		  else
		  {
			  a=5;
			  b=date%25;
		  }
		mapping.put("first",a);
		mapping.put("second",b);
		return mapping;
	}
	
}
