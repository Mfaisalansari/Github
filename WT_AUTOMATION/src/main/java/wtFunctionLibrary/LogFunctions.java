package wtFunctionLibrary;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

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

public class LogFunctions {

	protected  RemoteWebDriver driver;
	protected String imagePath = "test-output/Images/";
	protected int timeout=60;	
	public static String Report_Delimiter="::";
	public static String txtLog="stepLog.csv";
	public static String statusLog="statusLog.csv";
	public static int testCount=1;
	public static String currentTestName="";
	public static String currentTestStatus="PASS";
	
	public LogFunctions(RemoteWebDriver driver) {
		this.driver = driver;
	
	}
	
	// ************************************************************************************************************************
		public void logSteps(boolean status, String stepDetails, WebElement element) {
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ss_SSaa");
			Date date = new Date();

			String fileName = imagePath + dateFormat.format(date) + ".png";
			try {
				switch(currentTestName)
				{
				case "":
					currentTestName=Reporter.getCurrentTestResult().getName();
					update_Logs( txtLog,"TestName,FunctionName,StepDescription,TestData,Messages,ImagePath");
					//update_Logs( statusLog,"S.No,TestCaseName,Status");
					//update_Logs( excelLog,"#,TestName,TestStatus");
					break;
				default:
					if (!currentTestName.equalsIgnoreCase(Reporter.getCurrentTestResult().getName()))
					{
						update_Logs( txtLog,"\n");
						update_Logs( txtLog,"TestName,FunctionName,StepDescription,TestData,Messages,ImagePath");
				//		update_Logs( excelLog,testCount+","+currentTestName+","+currentTestStatus);
						//testCount++;
						currentTestName=Reporter.getCurrentTestResult().getName();
						currentTestStatus="PASS";
						
					}
					break;
				}
				
				
				if (status)
				{	
					Reporter.log(stepDetails +Report_Delimiter+ "<a href=" + getscreenshot(fileName, element) + ">Pass</a>");
					update_Logs(Reporter.getCurrentTestResult().getName(),txtLog,stepDetails +Report_Delimiter+"Pass,"+ "<a href=" + getscreenshot(fileName, element) + ">Pass</a>");
					Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
					//logStepCounter++;
				}
				else
				{
					Reporter.log(stepDetails + Report_Delimiter+"<a href=" + getscreenshot(fileName, element) + ">Fail</a>");
					Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
					update_Logs(Reporter.getCurrentTestResult().getName(),txtLog,stepDetails +Report_Delimiter+"Fail,"+ "<a href=" + getscreenshot(fileName, element) + ">Fail</a>");
					currentTestStatus="FAIL";
					//logStepCounter++;
				}
			} catch (Exception e) {

			}
			// imageCount++;
			System.out.println(stepDetails);

		}

	// ************************************************************************************************************************
		public String getscreenshot(String fileName, WebElement element) {
			// Thread.sleep(1000);

			try {
				if (element != null) {
					JavascriptExecutor javascript = (JavascriptExecutor) this.driver;
					javascript.executeScript("arguments[0].style.border='3px solid red'", element);
					// }Change - Moved Screenshot Command out of bracket
					FileUtils.copyFile(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE),
							new File(fileName));
					javascript.executeScript("arguments[0].style.border='3px solid black'", element);
				} else {
					JavascriptExecutor javascript = (JavascriptExecutor) this.driver;
					FileUtils.copyFile(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE),
							new File(fileName));

				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			return "./../" + fileName;

		}



	
	
	

	



		


	

//************************************************************************************************************************
public void update_Logs(String testName,String fileName,String logStatus)
{
	File file = new File(fileName);
	try {
			FileWriter fileWritter;
		  	fileWritter = new FileWriter(file.getName(),true);
		  	BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		  	bufferWritter.write("\n");
		    bufferWritter.write(testName+","+logStatus.replace(Report_Delimiter, ",").replace("\n", ""));
		    bufferWritter.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
	file=null;
}
 

public void update_Logs(String fileName,String logStatus)
{
	File file = new File(fileName);
	try {
         	FileWriter fileWritter;
		  	fileWritter = new FileWriter(file.getName(),true);
		  	BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		  	bufferWritter.write("\n");
		  	if(fileName.equalsIgnoreCase(statusLog))
		  	{
		  		bufferWritter.write(testCount+","+logStatus.replace(Report_Delimiter, ",").replace("\n", ""));
		  		testCount++;
		  	}
		  	else
		    bufferWritter.write(logStatus.replace(Report_Delimiter, ",").replace("\n", ""));
		    bufferWritter.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
	file=null;
}



//**************************************************************EXTENT-REPORT************************************************************************************************


public static void extentReportlogSteps(boolean status, String stepDetails, WebElement element){
	DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ss_SSaa");
	Date date = new Date();
	ExtentReports extentReports = new ExtentReports("test-output\\ExtentReport\\ExtentReport"+ dateFormat.format(date) +".html", true );
	extentReports.loadConfig(new File("src\\main\\resources\\extent-config.xml"));
	ExtentTest test = extentReports.startTest("Test Result", "Sample Automation Test Result");
	
	 String img = test.addScreenCapture("C:\\Users\\mohd.faisal\\workspace1\\Copy of SDample\\test-output\\Images\\17_Nov_2016__02_15_06_173PM.png");
     
	// log(LogStatus, details)
    test.log(LogStatus.INFO, "sample","This step shows usage of log(logStatus, details)"+"<img src='"+"C:\\Users\\mohd.faisal\\workspace1\\Copy of SDample\\test-output\\Images\\17_Nov_2016__02_15_06_173PM.png"+"'>");
	 
	 
     //test.log(LogStatus.INFO, "Image", "Image example: " + img);
	 extentReports.endTest(test);
	 extentReports.flush();

	
}


public static void extentReportlogSteps_Update(boolean status, String stepDetails, WebElement element){
	 
	ExtentReports extentReports = new ExtentReports("test-output\\ExtentReport\\ExtentReport" +".html", false );
	extentReports.loadConfig(new File("src\\main\\resources\\extent-config.xml"));
	ExtentTest test = extentReports.startTest("Test Result", "Sample Automation Test Result");
	
	 //String img = test.addScreenCapture("C:\\Users\\mohd.faisal\\workspace1\\Copy of SDample\\test-output\\Images\\17_Nov_2016__02_15_06_173PM.png");
     
	// log(LogStatus, details)
    //test.log(LogStatus.INFO, "sample","This step shows usage of log(logStatus, details)"+"<img src='"+"..\\..\\..\\17_Nov_2016__02_15_06_173PM.png"+"'>");
    
	 
	 
     //test.log(LogStatus.INFO, "Image", "Image example: " + img);
	 extentReports.endTest(test);
	 extentReports.flush();

	
}
 
}
 





 


 




