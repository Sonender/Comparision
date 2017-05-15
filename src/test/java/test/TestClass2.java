package test;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.OneMore;
import pages.ForDelhi;

import pages.For_Goa;
import pages.IbiboTest;
import utils.BrowserFactory;
import utils.Helper;
public class TestClass2 {




	public class TestClass3 extends BrowserFactory{
		
		RemoteWebDriver driver;
		For_Goa page = new For_Goa();
       
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
		  public void runTest1() 
		  {
			
			try{
			page.InitializeBookingGoa(driver);
			page.runTest();
			}
			catch(Exception e)
			{System.out.println("Error"+e);}
		  }
	}

}
