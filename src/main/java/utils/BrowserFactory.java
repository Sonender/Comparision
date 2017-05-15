package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class  BrowserFactory {
	
    public  RemoteWebDriver driver;
  	public  ThreadLocal<RemoteWebDriver> threadDriver;
	public Selection selectSheet=new Selection();
	private String environment = selectSheet.setEnv();
	static String build_number= "0001";
    static int counter=0;
	
	
	public RemoteWebDriver initializeBrowser(@Optional("chrome") String Browser)
	{
		build_number = System.getProperty("buildnumber");
		if(environment.equalsIgnoreCase(":local")){
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			DesiredCapabilities caps = new DesiredCapabilities();
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			driver=new ChromeDriver(caps);
			driver.manage().window().maximize();			
			
		}
		else
			try {
				driver = this.setDriver(Browser);
			   
			}
			catch (Exception e) { 
			System.out.println("Error is :" + e.getMessage());}
		  
		return driver;
			}

    
	public void closeBrowser()
	{
		if(environment.equalsIgnoreCase("local"))
		{
			driver.close();
			driver.quit();			
		}
		else
			getDriver().quit();
	}
	
	
	private RemoteWebDriver setDriver(String Browser)
	{ 
		switch(Browser){
		case "chrome":
		   driver =this.initDriver("chrome");
		   break;
		case "firefox":
			 driver =this.initDriver("firefox");
			 break;
		case "ie":
			driver=this.initDriver("ie");
			break;
		default:
			 driver =this.initDriver("chrome");
			 break;
			
		}
		
		return this.driver;
			
		}
		private RemoteWebDriver initDriver( String driverName)
		
		{
			
			
			String nodeip="";
			String nodeUrl = "";			
			String url = "http://nodeip:4444/wd/hub";
			String publicipAddress;
		
			
			switch(environment)
			{
			case "local":
				publicipAddress="127.0.0.1"; 
				break;
			case "remote":
				publicipAddress = "10.10.64.130";
				break;
			default:
				publicipAddress="127.0.0.1";
				break;
				
			}
			String pingCmd = "ping -c 4 " + publicipAddress;
			System.out.println("pingAdderss"+publicipAddress);
			String pingResult = "";
			

			try {
				 threadDriver = new ThreadLocal<RemoteWebDriver>();
				// ChromeOptions options = new ChromeOptions();
				// options.addArguments("-incognito");
				DesiredCapabilities Dcp = new DesiredCapabilities();
				
					Dcp = DesiredCapabilities.chrome();
				//	Dcp.setCapability(ChromeOptions.CAPABILITY, options);
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.BROWSER, Level.ALL);
					Dcp.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
					Dcp.setBrowserName("chrome");
					
					
				
				Dcp.setPlatform(Platform.ANY);
				// Logging preferences
				LoggingPreferences browserLogPreference = new LoggingPreferences();
				browserLogPreference.enable(LogType.BROWSER, Level.ALL);
				Dcp.setCapability(CapabilityType.LOGGING_PREFS, browserLogPreference);
				Runtime r = Runtime.getRuntime();
				Process p = r.exec(pingCmd);
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					pingResult += inputLine;
					 }
				in.close();
					System.out.println("pingResult"+pingResult);
					if (pingResult.contains("bytes from")) {
						System.out.println("Entered if");
						nodeip = publicipAddress;
						nodeUrl=url.replace("nodeip", nodeip);
						//System.out.println(nodeUrl);
				  }
					try {
						
					
						System.out.println("counter"+counter);
				//		 if(counter==0){
						System.out.println("NodeUrl"+nodeUrl );
			//				counter++;
					  threadDriver.set(new RemoteWebDriver(new URL(nodeUrl), Dcp));//}
					//	else {
						
						//	nodeUrl=nodeUrl.replace(":4444",":4445");
			          //  System.out.println("NodeUrl"+nodeUrl );
			          //  threadDriver.set(new RemoteWebDriver(new URL(nodeUrl), Dcp));
						
					//	}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					
					  getDriver().manage().window().maximize();
				      getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                       driver.manage().window().maximize();
                       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				
				} catch (Exception ex) {
					System.out.println("OYOWeb: " + ex.getMessage());
								}
			
			return driver= getDriver();
		}
		public RemoteWebDriver getDriver() {
			return threadDriver.get();
		}
		
					}

