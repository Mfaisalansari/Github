package wtFunctionLibrary;

import org.testng.annotations.Test;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import wtDriverManager.GetDriver;
import wtObjectRepository.Login;
import wtTests.WT_Login;

public class BusinessFunctions extends ObjectFunctions{

	protected  RemoteWebDriver driver;
//	private String imagePath = "test-output/Images/";
//	private int timeout=60;	
//	public static String Report_Delimiter="::";
//	public static String txtLog="stepLog.csv";
//	public static String statusLog="statusLog.csv";
//	public static int testCount=1;
//	public static String currentTestName="";
//	public static String currentTestStatus="PASS";
	
	public BusinessFunctions(RemoteWebDriver driver) {
		super(driver);
	    this.driver = driver;
	}
	 
	 
	 
 


//@Test
@Test
public void testDecision(String testName)
{
	 
	if (!GetDriver.testSuite.get(testName.toLowerCase()).toLowerCase().equalsIgnoreCase("y"))
	{
		update_Logs(txtLog,"\n");
		update_Logs(testName,txtLog,"Skipped");
		
		GetDriver.testSuite.put(testName.toLowerCase(),"Skipped");
		throw new SkipException("Skipping the test case");
	}
	
	 
}

}







