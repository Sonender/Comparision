package test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.ForKerala;
import pages.ForNoida;
import pages.MakeMyTripPage;
import utils.BrowserFactory;
import utils.Helper;



public class TestClass {
	
ForKerala page = new ForKerala();
	RemoteWebDriver driver ;
	Map<String, Map<String, List<Integer>>> finalMap= new HashMap<String, Map<String, List<Integer>>>();
	
	@BeforeClass
	public  RemoteWebDriver  initilizeBrowser()
	{	
		driver=page.initializeBrowser("chrome");
		return driver;
	}
	
	@AfterClass
	public void closeBrowser()
	{
		page.closeBrowser();
	}
	
		@Test
	  public void runTest() 
	  {
		    
		try{
			page.InitializeBookingKerala(driver);
			page.runTest();
		}
		catch(Exception e)
		{System.out.println("Error"+e);}
	  }
	}
