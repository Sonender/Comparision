package pages;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Helper;

public class IbiboTest extends Helper{

	public RemoteWebDriver driver;
	public Helper helper;
	
	public void runTest()
	{
		super.getDate();
	    String day1= super.day1;
	    String day2= super.day2;
		Map<Object, Object> allList= new LinkedHashMap<Object, Object>();
		Map<Object,Object> reading;
		System.out.println("Test Executed");
		WebElement element = helper.locateByClass("icon-hotels");
		element.click();
	}
	
		
		
			
	}


