package pages;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import utils.BrowserFactory;
import utils.GoogleSheetReader;
import utils.Helper;
import utils.Selection;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class MakeMyTripPage extends Helper {
	Selection select = new Selection();
	Map<Object, Object> allList= new LinkedHashMap<Object, Object>();
	Map<Object,Object> reading;

	//	public RemoteWebDriver driver;
	public Map<String, Map<String, List<Integer>>> forMMT()  throws InterruptedException
	{
		super.getDate();
		String day1= super.day1;
		String dateToSue = day1.substring(1,2);//http://172.17.34.6:4444/grid/register/;
		System.out.println("dateToSue"+dateToSue);
		String day2= super.day2;
		String dateToSue1=day2.substring(1,2);
		Map<String, Map<String, List<String>>> totalList =new HashMap<String, Map<String, List<String>>>();
		Map<Object, Object> allList= new LinkedHashMap<Object, Object>();
		Map<Object,Object> reading;
		System.out.println(dateToSue+"and "+dateToSue1);

		//  RemoteWebDriver driver = super.driver;
		// this.InitializeDriverMM();
		String goaVal="";
		String values="";

		List<String> interim= new ArrayList<String>();
		Map<Object, Object> list= new HashMap<Object, Object>();
		List<String> actual = new ArrayList<String>();
		Map<String,Integer> allData = new HashMap<String,Integer>();
		Map<String,Integer> allData1 = new HashMap<String,Integer>();

		WebDriverWait wait=super.waiting(20);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'hdr_item_link') and text()='hotels']")));
		WebElement element = super.locateByXpath("//a[contains(@class, 'hdr_item_link') and text()='hotels']");
		element.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("international")));
		super.maximize();
		//driver.manage().window().maximize();
		WebElement value = super.locateByid("hp-widget__sDest");
		value.clear();
		try {
			list= select.getValues("KeywordsSearch");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value.sendKeys("Goa, India");
		int counter=0;
		super.waitImplicit(2);
		List<WebElement> allLocation =super.locateByClasses("autoCompleteItem__label");
		//int count=0;
		for(WebElement sonu: allLocation){
			String text=sonu.getText().toLowerCase();
			String textComp= "Goa";
			if(text.startsWith(textComp.toLowerCase()))
			{	sonu.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("locationFilter")));
				break;}

		}
		
		 try{
			   // WebElement date1=driver.findElement(By.id("hp-widget__chkIn"));
				//date1.click();
			   int temp=0;
			 try{
				 
			 
			    List<WebElement> date2 =driver.findElements(By.linkText(dateToSue));
			    while(temp<date2.size())
			   
			    	{date2.get(temp).click();
			          break;}
			  	 }
			   catch(Exception e)
			   {
				   temp++;
			   }
			//  WebElement checkOut= super.locateByid("hp-widget__chkOut");
			 // checkOut.click();
			    
			 temp=0;
			  try
			   {
				  List<WebElement> date3 = driver.findElements(By.linkText(dateToSue1));
				 
				  while(temp<date3.size())
				  {
				  date3.get(temp).click();
				  break;
				  }
			   }
			   catch(Exception e)
			   {
				   temp++;
			   }
			     
			     driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	             WebElement close = driver.findElement(By.cssSelector("span.inputM__closeIcon.inputHlp__close.close_margin.closeFilter.o-i-cross-dark"));
	             close.click();
	             wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("dateFilterReturn hasDatepicker")));
			     
				  
		 } catch(Exception e)
			  {System.out.println(e);
			 // WebElement close1 = driver.findElement(By.cssSelector("div.dateFilterReturn.hasDatepicker span.o-i-cross-dark.close_modify_search"));
	            // close1.click();
			  }
      
		WebElement clickVal = super.locateByid("searchBtn");
		clickVal.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fix_header_mar_top")));
		System.out.println("This is the current list"+list);
		  
        Map<String, Map<String, List<Integer>>> totalVal=new HashMap<String, Map<String, List<Integer>>> ();
		for(Map.Entry<Object, Object> elements: list.entrySet())
		{
           try
           {
			List<Integer> valueString= new ArrayList<Integer>();
			List<Integer> valueStringNew =new ArrayList<Integer>();
			List<String>  valueStrings= new ArrayList<String>();
			String mapString=(String) elements.getKey();
			valueStrings =(List) elements.getValue();
			String[] valueofElement= new String[valueStrings.size()];
			for(String val:valueStrings)
			{
				valueofElement[valueStrings.indexOf(val)]=val.trim();

			}
			String valueCity = (String) elements.getKey();
			//  valueofElement = (List) elements.getValue();

			for(int i=0; i<valueofElement.length;i++)
			{
				String val= valueofElement[i];
				val=val+", "+valueCity;
				valueofElement[i]=val;
			}
			
			Map<String, List<Integer>> interVal = new HashMap<String, List<Integer>>();
			for(String valuesinlist: valueofElement )
			{
               List<Integer> mainlist =new ArrayList<Integer>();
				try
               {
			Thread.sleep(5000);
			WebElement myPageSearch = super.locateByid("hp-widget__sDest");
			myPageSearch.clear();
			myPageSearch.sendKeys(valuesinlist);
				System.out.println("valuesinlist"+valuesinlist);
			  // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				Thread.sleep(5000);
				List<WebElement> allLocation1 =driver.findElements(By.xpath("//li[@class='ui-menu-item']"));
			
 		     	for(WebElement elem:allLocation1){
					  System.out.println("elem values "+elem.getText().toString());
					  System.out.println(valuesinlist.split(" ")[0]);
					if((elem.getText().toLowerCase()).startsWith((valuesinlist.split(" ")[0]).toLowerCase()))
					{
						try
						{
							System.out.println("entered with"+elem.getText());
							mainlist =super.performOperation( elem, "searchBtn");
							interVal.put(valuesinlist,mainlist);
							totalVal.put(mapString,interVal);
							System.out.println("TOTAL MAP"+totalVal);
							break;
						}
						catch(StaleElementReferenceException elementHasDisappeared)
						{
							System.out.println("Exception occured"+elementHasDisappeared);
							}
					
					}
				}
			}
			catch(Exception e)
			{ System.out.println("brokehere "+e);}
		}
		   
        
	} catch(Exception e){}
	}
		return totalVal;
}
	}

//		//Element1.click();
//
//		try{
//		//	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fix_header_mar_top")));
//			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@mt-id='hotel_splashed_price']")));
//			List<WebElement> allVals = driver.findElements(By.xpath("//span[@mt-id='hotel_splashed_price']"));
//
//			for(WebElement values1:allVals)
//			{   
//				values=values1.getText().replace(",", "");
//				int val = Integer.parseInt(values);
//				valueString.add(val);
//
//
//			}
//			Collections.sort(valueString);
//
//			for(int val:valueString)
//			{
//				if(valueString.indexOf(val)<3)
//				{
//					valueStringNew.add(val);
//				}
//			}
//
//		}catch(Exception e)
//		{
//			System.out.println("No booking available or element not found" +e);
//			//String [] arr= {null, null, null};
//			valueStringNew.add(null);
//			valueStringNew.add(null);
//			valueStringNew.add(null);
//			
//		}
//		allList.put(elements.trim(), valueStringNew);
//	}
//	
//	
//
//	System.out.println(allList);
//
//	try {
//		 reading=read.getSheetMap("1DEXQ3ogh7f6xWbk1gwWbU18J9xafJ3r9PoqL_2OrvHw", "Sheet4");
//		 Map<Object, Object> usermap= reading;
//		if(reading.size()==0)
//		{
//		read.writeToSheet("1DEXQ3ogh7f6xWbk1gwWbU18J9xafJ3r9PoqL_2OrvHw", "Sheet4", allList);
//		}
//		else
//		{
//				Set<String> uniqueVals = new HashSet<String>(list);
//				List<String> interList = new ArrayList<String>();
//				if(reading.containsKey("MMTBookings"))
//					
//				{	
//					String[] list1=reading.get("MMTBookings").toString().split(",");
//					if(list1.length<=3)
//					{
//						System.out.println("Entering If");
//					interList.add(day1);
//					interList.add("");
//				    interList.add("");
//				    interList.add("");
//			       interList.add("MMTBookings");
//				    interList.add(day1);
//				    }
//					else
//						if(list1.length>3)
//						{
//							System.out.println("Entering Else");
//							interList.addAll((List<String>)reading.get("MMTBookings"));
//							interList.add("");
//							interList.add("");
//						    interList.add("MMTBookings");
//						    interList.add(day1);
//						}
//						
//					
//				reading.put("MMTBookings", interList);
//				}
//			
//			
//				for(String val:uniqueVals)
//				{
//					if(reading.containsKey(val) && allList.containsKey(val))
//					{ 
//						List<String> interimList=new ArrayList<String>();
//					 try{  
//						 List<String> imm= (List<String>) usermap.get(val);
//					    System.out.println("Reading"+imm);
//						interimList.addAll(imm);
////						String[] immVal=usermap.get(val).toString().split(",");
////						if(immVal.length>4)
//						interimList.add(" ");
//						interimList.addAll((List<String>)allList.get(val));
//						reading.put(val, interimList);
//					 }catch(Exception e )
//					 {System.out.println("Go  to next");}
//					}
//					
//			}
//		read.writeToSheet("1DEXQ3ogh7f6xWbk1gwWbU18J9xafJ3r9PoqL_2OrvHw", "Sheet4", reading);

//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}







//
//import java.util.List;
//public class MakeMyTripPage {
//	String url ="https://www.makemytrip.com/hotels";
//	String id= "hp-widget__sDest";
//
//	public void getWebSite()
//	{
//		driver.get(url);
//		this.waitTill(20,locateById(id));
//		
//	}
//	
//	public void sendData(String Data) throws InterruptedException
//	{
//		WebElement element = driver.findElement(By.id(id));
//		element.sendKeys(Data);
//		this.waitTill(20, locateByPartialLinkText("Hotels in"));
//		
//	}
//	
//	public String getElementsinList()
//
//	{
//		 List<WebElement> vals =  driver.findElements(By.className("row"));
//		 for(WebElement values: vals)
//		 {
//			WebElement elemen1 = driver.findElement(By.id("card-1"));
//			
//			 
//		 }
//		
//	}
//	
//	public void waitTill(int timeUnit, By locator)
//	{
//		WebDriverWait wait = new WebDriverWait(driver, timeUnit);
//		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//		
//	}
//	
//	public By locateById(String element) {
//		return By.id(element);
//	}
//	
//	public By locateByClass(String element) {
//		return By.className(element);
//	}
//	
//	public By locateByName(String element) {
//		return By.name(element);
//	}
//	
//	public By locateByPartialLinkText(String element) {
//		return By.partialLinkText(element);
//	}
//	
//
//	public void getCityData()
//	{
//		
//		driver.findElement(By.id("hp-widget__sDest"));
//		
//	}
//	
//}
