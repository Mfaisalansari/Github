package wtDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.*;

public class GetDriver {
	public RemoteWebDriver driver;
	public  RemoteWebDriver wtWebDriver;
	public static HashMap<String, String> testSuite = new HashMap<String, String>();
	
	
	
//************************************************************************************************************************
 
	public RemoteWebDriver returnDriver(String browserType)  
	{
		
		System.out.println("BrowserInfo="+browserType.toLowerCase());
		Dimension dimension = new Dimension(400, 400);
		
		switch(browserType.toLowerCase())
		   {
		
		   case "firefox":
			      DesiredCapabilities capabilities = new DesiredCapabilities();
			      capabilities.setBrowserName("firefox");
			      capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			      //Below code for Selenium Grid
			      //this.driver =  new WebDriver(capabilities);//new RemoteWebDriver(new URL("http://192.168.0.6:4444/wd/hub"),capabilities);
			      FirefoxProfile profile = new FirefoxProfile();
			      capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			      this.driver = new FirefoxDriver(capabilities);
			   //  this.driver.manage().window().setSize(dimension);
			     this.driver.manage().window().maximize();
			     this.driver.manage().deleteAllCookies();
			 //    this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			     
			     break;
			 	  
		   case "chrome":
			      DesiredCapabilities capabilities1 = new DesiredCapabilities();
			      System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
			   //   capabilities1.setBrowserName("chrome");
			     capabilities1.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			      //Below code for Selenium Grid
			    //  this.driver = new RemoteWebDriver(new URL("http://192.168.0.6:4444/wd/hub"),capabilities1);
			      this.driver = new ChromeDriver(capabilities1);; //new ChromeDriver(capabilities1); 
			      this.driver.manage().window().setSize(dimension);
			   //   this.driver.manage().window().maximize();
			      this.driver.manage().deleteAllCookies();
			      this.driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
			   //   this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			       
			   break;
			   
		   case "ie":
			      DesiredCapabilities capabilities2 = new DesiredCapabilities();
			      System.setProperty("webdriver.ie.driver", "lib\\IEDriverServer.exe");
			      capabilities2.setBrowserName("internet explorer");
			      capabilities2.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			      //Below code for Selenium Grid
			     // this.driver = new RemoteWebDriver(new URL("http://192.168.0.6:4444/wd/hub"),capabilities2);
			      this.driver = new InternetExplorerDriver(capabilities2);//new InternetExplorerDriver(); 
			      this.driver.manage().deleteAllCookies();
			      this.driver.manage().window().maximize();
			   //   this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			       
			   break;
		   }	
		
		wtWebDriver=this.driver;
		return  this.driver;
	}
	
	

}
