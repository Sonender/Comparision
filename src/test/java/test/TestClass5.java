package test;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pages.ForTesting;

public class TestClass5 {
	
	RemoteWebDriver driver;
	@Test

	public void testOne()
	{
		  ForTesting test = new ForTesting();
		 int a= test.valueSeta(4);
	      int b= test.valueSetb(5);
			System.out.println("test1");
			Assert.assertEquals(a, b);
		//    ITestResult result 
		
	}
	
	
	@Test
	public void testSecond()
	
	{
		int a=1;
		int b=1;
		
		 Assert.assertEquals(a, b);
		 System.out.println("test4");
		
	}

}
