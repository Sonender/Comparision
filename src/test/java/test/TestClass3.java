



	
	package test;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import pages.MakeMyTripPage;
	import pages.OneMore;
import pages.ForBangalore;
import pages.ForKerala;
import pages.ForJaipur1;
import pages.IbiboTest;




	public class TestClass3 {
		
		RemoteWebDriver driver;
		ForBangalore page = new ForBangalore();
		
		
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
				page.InitializeBookingBangalore(driver);
			page.runTest();
			}
			catch(Exception e)
			{System.out.println("Error"+e);}
		  }
	}


