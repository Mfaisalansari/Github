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

public class ObjectFunctions extends LogFunctions {

	protected  RemoteWebDriver driver;
//	private String imagePath = "test-output/Images/";
//	private int timeout=60;	
//	public static String Report_Delimiter="::";
//	public static String txtLog="stepLog.csv";
//	public static String statusLog="statusLog.csv";
//	public static int testCount=1;
//	public static String currentTestName="";
//	public static String currentTestStatus="PASS";
	
	public ObjectFunctions(RemoteWebDriver driver) {
		super(driver);
		this.driver = driver;
		 //extentReportlogSteps(true, "", null);
	}
	// ************************************************************************************************************************

		public void SwitchWindow(String windowName) throws IOException, InterruptedException {

			//System.out.println("Entering ChidlWindow");
			try
			{
			wait(2);//Thread.sleep(2000);
			int i = 0;
	      
			String returnHandle = "";

			for (i = 0; i < 60; i++) {
				for (String Handle : this.driver.getWindowHandles()) {
					this.driver.switchTo().window(Handle);
					if (windowName.equals(driver.getTitle())) {
						// //System.out.println(driver.switchTo().window(Handle).getTitle());

						//System.out.println("current window Inside Childwindows" + driver.getTitle());
						//System.out.println("..............Window Found..................");
						returnHandle = Handle;
						break;
					}

				}
				wait(1);//Thread.sleep(1000);
				if (returnHandle != "")
					break;
			}

			//System.out.println("Exiting ChidlWindow");
			this.driver.switchTo().window(returnHandle);
			}catch(Exception e)
			{
				
				  logSteps(false, "Switch Window",null);
			}
		}

	
// ************************************************************************************************************************
// ************************************************************************************************************************
		public WebElement GetElement(By locator,int timeoutValue) {
			MouseMove();
			WebDriverWait wait = new WebDriverWait(this.driver, timeoutValue);
			WebElement element = null;
			
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				element=this.driver.findElement(locator);
			/*	JavascriptExecutor jse = (JavascriptExecutor)this.driver;
				jse.executeScript("arguments[0].scrollIntoView()", element); 
			*/	
			} catch(Exception e)
			{
				
			//	logSteps(false, "GetElement", "",element);
			}
	        wait=null;
			return element; // this.driver.findElement(locator);
		}
// ************************************************************************************************************************
		public void VerifyElementByText(By locator,String text, String logMessage) {	
			//logMessage format = FunctionName+stepDescription+TestData+Status+Message	
			//logMessage = "VerifyElementByText"+Report_Delimiter + logMessage+Report_Delimiter+text+Report_Delimiter;
			String functionName="VerifyElementByText";
			String stepDescription=logMessage;
			String testData=text;
			String messages="";
			logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
			
			WebElement element=null ;
			element = GetElement(locator,timeout); 
			
			if (element!=null && element.getText().contains(text))
			 logSteps(true, logMessage, element);
			else
			 logSteps(false, logMessage, element);	
			 
		}
// ************************************************************************************************************************		
	public void LaunchWTApp(String URL, String logMessage)  {
		//logMessage format = FunctionName+stepDescription+TestData+Status+Message
		//logMessage = "LaunchURL"+Report_Delimiter + logMessage+Report_Delimiter+URL+Report_Delimiter;
		WebDriverWait wait = new WebDriverWait(this.driver, timeout);
		String functionName="LaunchURL";
		String stepDescription=logMessage;
		String testData=URL;
		String messages="";
		logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
		
		try {
			this.driver.get(URL);
//			 wait.until(ExpectedConditions.visibilityOfElementLocated(Login.loginButton));
			 PageLoadVerify();
			 //extentReportlogSteps(true, "", null);
			 extentReportlogSteps_Update(true, "", null);
//			 logSteps(true, logMessage, null);
			
			//wait(5);
			} catch (TimeoutException e) {
				
			logSteps(false, logMessage, null);
		}
		wait=null;
	}
// ************************************************************************************************************************	
	// ************************************************************************************************************************	
		public void NavigateToHome(String URL, String logMessage)  {
			//logMessage format = FunctionName+stepDescription+TestData+Status+Message
			//logMessage = "LaunchURL"+Report_Delimiter + logMessage+Report_Delimiter+URL+Report_Delimiter;
			 
			String functionName="NavigateToHome";
			String stepDescription=logMessage;
			String testData=URL;
			String messages="";
			logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
		
//			
//			boolean present;
//			
//			try {
//			   this.driver.findElement(By.id("logoutLink"));
//			   present = true;
//			} catch (NoSuchElementException e) {
//			   present = false;
//			}
			
			
			
//			if (present)
//			{
//				this.driver.findElement(BagProcessing.onHandBag_home_button).click();
//			}
//			else {
			
		
	    
			
			  
			}
			

private void handle_Alert() {
	//Alert Handling put this in function
	Wait(2);
	Alert alert = this.driver.switchTo().alert();
	alert.dismiss();
}
	
	//	
	//****************************************************************************************************************
	 public void EnterTestData(By locator, String text, boolean isMandatory, String logMessage) {
         //logMessage format = FunctionName+stepDescription+TestData+Status+Message
  //     logMessage = "EnterTestData"+Report_Delimiter + logMessage+Report_Delimiter +text+Report_Delimiter;
         String functionName="EnterTestData";
         String stepDescription=logMessage;
         String testData=text;
         String messages="";
         logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
               
         WebElement element=null ;
         String element_ID="";
        
         PageLoadVerify(); //Verify Page Load
         element = GetElement(locator,timeout);
         if (element!=null)
         {
                element.clear();
                if (text!="")
                element.sendKeys(text.trim());   
                element.sendKeys("\t"); //Press TAB
                logSteps(true, logMessage, element);
         //     System.out.println("Attribute Value="+element.getAttribute("value").contentEquals(text));
                element_ID=element.getAttribute("id");
                if(isMandatory){
                      //MandatoryFieldCheck(locator);
                      MandatoryFieldCheck(element_ID);
                }
         }
         else
         logSteps(false, logMessage,element);
        

  }
	//****************************************************************************************************************
	 public void EnterTestDataWithoutTab(By locator, String text, boolean isMandatory, String logMessage) {
			//logMessage format = FunctionName+stepDescription+TestData+Status+Message
		//	logMessage = "EnterTestData"+Report_Delimiter + logMessage+Report_Delimiter +text+Report_Delimiter;
			String functionName="EnterTestData";
			String stepDescription=logMessage;
			String testData=text;
			String messages="";
			logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
				
			WebElement element=null ; 
			String element_ID="";
			
			PageLoadVerify(); //Verify Page Load
			element = GetElement(locator,timeout);
			if (element!=null)
			{
				element.clear();
				if (text!="")
				element.sendKeys(text);	
				//element.sendKeys("\t"); //Press TAB
				logSteps(element.getAttribute("value").equalsIgnoreCase(text.toLowerCase()), logMessage, element);
			//	System.out.println("Attribute Value="+element.getAttribute("value").contentEquals(text));
				element_ID=element.getAttribute("id");
				if(isMandatory){
					//MandatoryFieldCheck(locator);
					MandatoryFieldCheck(element_ID);
				}
			}
			else
			logSteps(false, logMessage,element);
			 

		}
	// ************************************************************************************************************************
		public void PressTABKey(By locator,String logMessage) {
			//logMessage format = FunctionName+stepDescription+TestData+Status+Message
		//	logMessage = "EnterTestData"+Report_Delimiter + logMessage+Report_Delimiter +text+Report_Delimiter;
			String functionName="EnterTestData";
			String stepDescription=logMessage;
			String testData="";
			String messages="";
			logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
			
			WebElement element=null ; 
			String element_ID="";
			element = GetElement(locator,timeout);
			if (element!=null)
			{
				element.sendKeys("\t");
				//logSteps(true, logMessage, element);
			}
			//else
			//logSteps(false, logMessage,element);
			 

		}
// ************************************************************************************************************************
	public void InvalidDataCheck(By locator, String logMessage) {
		WebElement element = null;
		String toolTip="";
		//logMessage format = FunctionName+stepDescription+TestData+Status+Message
		//logMessage = "InvalidDataCheck"+Report_Delimiter + logMessage;
		String functionName="InvalidDataCheck";
		String stepDescription="Verification: Invalid Data Test";
		String testData="";
		String messages="";
		logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData;
		
		try
		{
			element = GetElement(locator, timeout);
			//element.sendKeys(text);
			Wait(2);
			element.sendKeys("\t");
			toolTip= element.getAttribute("data-original-title");
			if (toolTip==null || toolTip.equalsIgnoreCase(""))
				toolTip=element.getAttribute("toolTip"); 
			
			logMessage=logMessage+Report_Delimiter+toolTip;
			
			System.out.println("*******Tool-Tip**********"+toolTip);
		if (!toolTip.equalsIgnoreCase(""))
			logSteps(true, logMessage, element);
		else
			logSteps(false, logMessage, element); 
    	
		}catch(Exception e)
		{
			logMessage=logMessage+Report_Delimiter+e;
			logSteps(false, logMessage, element); 
		}
		 
	 
	 
	}
	
// ************************************************************************************************************************
	
	public void ClearTextField(By locator,String logMessage) {
		//logMessage format = FunctionName+stepDescription+TestData+Status+Message
	//	String logMessage = "ClearTextField"+Report_Delimiter+"Clear Data"+Report_Delimiter+""+Report_Delimiter;
		String functionName="ClearTextField";
		String stepDescription="";
		String testData="";
		String messages="Clear Text";
		
		WebElement element=null ;
		
		element = GetElement(locator,timeout);
		try
		{
		if (element!=null )
		{
		logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
		element.clear();		
		logSteps(element.getAttribute("value").contentEquals(""), logMessage, element);
		}
		else
		{
			logSteps(false, logMessage, element);
		}
		}
		catch(Exception e)
		{
			messages="e";
			logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
			logSteps(false, logMessage, element);
		}

	}
// ************************************************************************************************************************

	public void ClickButton(By locator, String logMessage)  {		
		//logMessage format = FunctionName+stepDescription+TestData+Status+Message
		//logMessage = "Click"+Report_Delimiter + logMessage+Report_Delimiter+Report_Delimiter;
		WebDriverWait wait = new WebDriverWait(this.driver, timeout);
		String functionName="ClickButton";
		String stepDescription=logMessage;
		String testData="";
		String messages="";
		logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData;
		WebElement element=null;
		
		
		try
		{
		Wait(1);
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
	//	wait.until(ExpectedConditions.elementToBeClickable(locator));
		element = GetElement(locator,timeout);
		
	//	messages="Button Enabled Status="+
		if(element.isEnabled())
		{
		logMessage=logMessage+Report_Delimiter+"Enabled"; 
		element.click();
		PageLoadVerify();
		logSteps(true, logMessage, null);
		 
		}
		else
		{
			logMessage=logMessage+Report_Delimiter+"Not Enabled";
			logSteps(false, logMessage, null);
		}
		  
		}catch(Exception e)
		{
		//this.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		  logSteps(false, logMessage, null);
		
		}

	}
// ************************************************************************************************************************
	public void ActionClass_ClickButton(By locator, String logMessage)  {		
		//logMessage format = FunctionName+stepDescription+TestData+Status+Message
		//logMessage = "Click"+Report_Delimiter + logMessage+Report_Delimiter+Report_Delimiter;
		WebDriverWait wait = new WebDriverWait(this.driver, timeout);
		String functionName="ClickButton";
		String stepDescription=logMessage;
		String testData="";
		String messages="";
		logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData;
		WebElement element=null;
		Actions action = new Actions(this.driver);

		// To click on the element
		
		
		try
		{
		Wait(1);
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
	//	wait.until(ExpectedConditions.elementToBeClickable(locator));
		element = GetElement(locator,timeout);
	
	//	messages="Button Enabled Status="+
		if(element.isEnabled())
		{
		logMessage=logMessage+Report_Delimiter+"Enabled"; 
		//element.click();
		action.moveToElement(element).click().perform();
		PageLoadVerify();
		logSteps(true, logMessage, null);
		 
		}
		else
		{
			logMessage=logMessage+Report_Delimiter+"Not Enabled";
			logSteps(false, logMessage, null);
		}
		  
		}catch(Exception e)
		{
		//this.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		  logSteps(false, logMessage, null);
		}

	}	
	// ************************************************************************************************************************
		public String GetText(By locator,String logMessage)  {
			//logMessage format = FunctionName+stepDescription+TestData+Status+Message			
		    //logMessage = "GetText"+Report_Delimiter+logMessage+Report_Delimiter+"" ;
		    String functionName="GetText";
			String stepDescription="";
			String testData="";
			String messages="";
			logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData;
		    WebElement element=null;
			String returnText="";
				element = GetElement(locator,timeout); 
				//element.getAttribute("text");
				if(element!=null)	
				{
					returnText=element.getText();
					logMessage=logMessage+Report_Delimiter+returnText;
					logSteps(true, logMessage,element);
				}   
				else
					logSteps(false, logMessage, null);
				
            return returnText;
		}
// ************************************************************************************************************************
		// ************************************************************************************************************************

				public List  ReturnElements(By locator,String logMessage)  {
					//logMessage format = FunctionName+stepDescription+TestData+Status+Message			
				    //logMessage = "GetText"+Report_Delimiter+logMessage+Report_Delimiter+"" ;
				    String functionName="ReturnElements";
					String stepDescription="";
					String testData="";
					String messages="";
					logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData;
					 List<WebElement> allElements=null ;
					String returnText="";
						
						try {
							allElements = driver.findElements(locator);
						} catch(Exception e)
						{
							
						//	logSteps(false, "GetElement", "",element);
						}
				       
					
						 
						
		            return allElements;
				}
		// ************************************************************************************************************************		
		
		public List<String> getSelectOptions(By locator, String string) {
			String functionName="getSelectOptions";
			String stepDescription="Get options from dropdown";
			String testData="";
			String messages="";
			String logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData;
			WebElement element = null;
			List<String> optionsText= new ArrayList<>();

			try {
				element = GetElement(locator,timeout);
				Select selectElement = new Select(GetElement(locator,timeout));
				List<WebElement> options=selectElement.getOptions();
				for(WebElement e:options){
					optionsText.add(e.getText());
				}
				
			} catch (Exception e) {
				
				logSteps(false, logMessage,  element);
			}
			return optionsText;
		}
// ************************************************************************************************************************

		public List<String> getSelectedOptions(By locator, String string) {
			
			String functionName="getSelectOptions";
			String stepDescription="Get selected options from dropdown";
			String testData="";
			String messages="";
			String logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData;
			
			WebElement element = null;
			List<String> optionsText= new ArrayList<>();

			try {
				element = GetElement(locator,timeout);
				Select selectElement = new Select(GetElement(locator,timeout));
				List<WebElement> options=selectElement.getAllSelectedOptions();
				for(WebElement e:options){
					optionsText.add(e.getText());
				}
				
			} catch (Exception e) {
				
				logSteps(false, logMessage,  element);
			}
			return optionsText;
		}
		
// ************************************************************************************************************************
	public void SelectFromDropDown(By locator, String selectBy, String text,String logMessage)  {
			//logMessage format = FunctionName+stepDescription+TestData+Status+Message	
		//	String logMessage = "SelectFromDropDown"+Report_Delimiter + selectBy+Report_Delimiter+text+Report_Delimiter;
			String functionName="selectFromDropDown";
			String stepDescription=logMessage;
			String testData=text;
			String messages="";
			logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
			
			WebElement element = null;

			try {
				element = GetElement(locator,timeout);
				Select selectElement = new Select(element);
				switch (selectBy) {
				case "value":
					selectElement.selectByValue(text);
					logSteps(true, logMessage, element);
					break;

				case "visibletext":
					selectElement.selectByVisibleText(text);
					logSteps(true, logMessage,  element);
					break;

				case "index":
					selectElement.selectByIndex(Integer.valueOf(text));
					logSteps(true, logMessage,  element);
					break;
				}
			} catch (Exception e) {
				
				logSteps(false, logMessage, element);
			}
		}	

	// ************************************************************************************************************************
		public void MandatoryFieldCheck(String id)  {
			//logMessage format = FunctionName+stepDescription+TestData+Status+Message
			//String logMessage = "MandatoryFieldCheck"+Report_Delimiter+Report_Delimiter+Report_Delimiter ;	
			String functionName="MandatoryFieldCheck";
			String stepDescription="Verification: Mandatory Field Test for above Input";
			String testData="";
			String messages="";
			String logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
			WebElement element =null;
			
			String mandatoryCheckString=".input-group-addon.mandatory-bar+#"+id;
		    By mandatoryCheckCSS=By.cssSelector(mandatoryCheckString);
			 		    
			try
			{
				WebDriverWait wait = new WebDriverWait(this.driver, 1);
				wait.until(ExpectedConditions.visibilityOfElementLocated(mandatoryCheckCSS));
				wait=null;
				logSteps(true, logMessage, element);
			}catch(Exception e)
			{
					logSteps(false, logMessage, element);
			}
		    

		}
// ************************************************************************************************************************
	
				public void MandatoryFieldCheck(String id,String logMessage)  {
					//logMessage format = FunctionName+stepDescription+TestData+Status+Message
					//String logMessage = "MandatoryFieldCheck"+Report_Delimiter+Report_Delimiter+Report_Delimiter ;	
					String functionName="MandatoryFieldCheck";
					String stepDescription=logMessage;//"Verification: Mandatory Field Test";
					String testData="";
					String messages="";
					logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
					WebElement element =null;
					
					String mandatoryCheckString=".input-group-addon.mandatory-bar+#"+id;
				    By mandatoryCheckCSS=By.cssSelector(mandatoryCheckString);
					 		    
					try
					{
						WebDriverWait wait = new WebDriverWait(this.driver, 1);
						wait.until(ExpectedConditions.visibilityOfElementLocated(mandatoryCheckCSS));
						wait=null;
						logSteps(true, logMessage, element);
					}catch(Exception e)
					{
							logSteps(false, logMessage, element);
					}
				    

				}
		// ************************************************************************************************************************

	public void SwitchFrame(String frameName, String logMessage) {
		//logMessage format = FunctionName+stepDescription+TestData+Status+Message
		//logMessage = "SwitchFrameFunction"+Report_Delimiter + logMessage+Report_Delimiter;
		String functionName="frameName";
		String stepDescription="";
		String testData="";
		String messages="";
	    logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
	    
		try
		{
		this.driver.switchTo().frame(frameName);
		logSteps(true, logMessage,  null);
		}
		catch(Exception e)
		{
			
		  logSteps(false, logMessage, null);
		}

	}
// ************************************************************************************************************************

	public void QuitBrowser() throws IOException {
		//logMessage format = FunctionName+stepDescription+TestData+Status+Message
	//	String logMessage = "QuitBrowserFunction"+Report_Delimiter+"Close All Browsers"+Report_Delimiter;
		String functionName="QuitBrowser";
		String stepDescription="";
		String testData="";
		String messages="";
		String logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
		try
		{
		this.driver.quit();
		logSteps(true, logMessage,  null);
		}catch(Exception e)
		{
			
			  logSteps(false, logMessage, null);
			}

	}
// ************************************************************************************************************************

		public String ReturnAttribute(By locator, String attributeName)  {
			String functionName="ReturnAttribute";
			String stepDescription="";
			String testData=attributeName;
			String messages="";
			String logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
			WebElement element =null;
			
			element = GetElement(locator,timeout);
			String returnValue=element.getAttribute(attributeName).toString();
			 
			return returnValue;			 

		}
	
// ************************************************************************************************************************

	public void Wait(int time)  {
		//logMessage format = FunctionName+stepDescription+TestData+Status+Message
	//	String logMessage = "WaitFunction"+Report_Delimiter+"Wait"+Report_Delimiter;
		String functionName="Wait";
		String stepDescription="";
		String testData=String.valueOf(time)+" Sec";
		String messages="";
		String logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
		try
		{
		Thread.sleep(time*1000);	
		}catch(Exception e)
		{
	      // Add exception code if necessary
		}

	}

	// ************************************************************************************************************************
	// ************************************************************************************************************************
	public void VerifyCondition(By locator,String verificationType,String text,String logMessage)  {
					//logMessage format = FunctionName+stepDescription+TestData+Status+Message			
				    //logMessage = "GetText"+Report_Delimiter+logMessage+Report_Delimiter+"" ;
				    String functionName="VerifyCondition";
					String stepDescription=logMessage;
					String testData=text;
					String messages="";
					WebElement element=null;
					
					logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData;
					try
					{
					element =  GetElement(locator,3);
					switch(verificationType.toLowerCase())
					{
					
					case "emptystringcheck":
						  if (element.getText()!="")
						  {   messages=element.getText();
							  logMessage=logMessage+Report_Delimiter+messages;
							  logSteps(true, logMessage,  null);
						  } 
						  else
						  {
							  logMessage=logMessage+Report_Delimiter+messages;
							  logSteps(false, logMessage,  null);
						  } 
						 break;
						 
					case "notnull":
						  if (element!=null)
						  {
							  messages="";
							  logMessage=logMessage+Report_Delimiter+messages;
							  logSteps(true, logMessage,  null);
						  } 
						  else
						  {
							  messages="";
							  logMessage=logMessage+Report_Delimiter+messages;
							  logSteps(false, logMessage,  null);
						  }	  
						 break;
						 
					case "null":
						  if (element==null)
						  {
							  messages="";
							  logMessage=logMessage+Report_Delimiter+messages;
							  logSteps(true, logMessage,  null);
						  } 
						  else
						  {
							  messages="";
							  logMessage=logMessage+Report_Delimiter+messages;
							  logSteps(false, logMessage,  null);
						  }	  
						 break;	 
						 
					case "equals": //Case insensitive Comparison
						  if (element.getText().equalsIgnoreCase(text))
						  {
							  messages=element.getText();
							  logMessage=logMessage+Report_Delimiter+messages;
							  logSteps(true, logMessage,  null);
						  } 
						  else
						  {	
							  messages=element.getText();
							  logMessage=logMessage+Report_Delimiter+messages; 
							  logSteps(false, logMessage,  null);
						  }	  
						 break;	  
		 
					case "enabled":
					//	  if (element.getAttribute("disabled").equalsIgnoreCase(""))
						 if (element.isEnabled())
						  {
							  messages="Enabled";
							  logMessage=logMessage+Report_Delimiter+messages;
							  logSteps(true, logMessage,  null);
						  } 
						  else
						  {	
							  messages="Not Enabled";
							  logMessage=logMessage+Report_Delimiter+messages; 
							  logSteps(false, logMessage,  null);
						  }	  
						 break;	 		 
					
					case "disabled":
						//	  if (element.getAttribute("disabled").equalsIgnoreCase(""))
							 if (!element.isEnabled() || element.getAttribute("disabled").equalsIgnoreCase("true"))
							  {
								  messages="Disabled";
								  logMessage=logMessage+Report_Delimiter+messages;
								  logSteps(true, logMessage,  null);
							  } 
							  else
							  {	
								  messages="Enabled";
								  logMessage=logMessage+Report_Delimiter+messages; 
								  logSteps(false, logMessage,  null);
							  }	  
							 break;		 
						 
					case "contains":
						 //messages=element.getText();
						  if (element.getText().toLowerCase().contains(text.toLowerCase()) || element.getText().toLowerCase().replace(" ", "").trim().contains(text.toLowerCase()))
						  {
							  messages=element.getText();
							  logMessage=logMessage+Report_Delimiter+messages;
							  logSteps(true, logMessage,  null);
						  } 
						  else
						  {	
							  messages=element.getText();
							  logMessage=logMessage+Report_Delimiter+messages; 
							  logSteps(false, logMessage,  null);
						  }	  
						 break;	
						 
					case "equalsvalueattribute":
						
						if (element.getAttribute("value").toLowerCase().contains(text.toLowerCase()))
						{
							messages=element.getAttribute("value");
							logMessage=logMessage+Report_Delimiter+messages;
							logSteps(true, logMessage,  null);
						} 
						else
						{	
							messages="Match By Value Failed";
							logMessage=logMessage+Report_Delimiter+messages; 
							logSteps(false, logMessage,  null);
						}	  
						break;	 
						
					case "maxlength":
						
						if (element.getAttribute("maxlength").contentEquals(text))
						{
							messages=element.getAttribute("maxlength");
							logMessage=logMessage+Report_Delimiter+messages;
							logSteps(true, logMessage,  null);
						} 
						else
						{	
							messages="Max Length Test Failed";
							logMessage=logMessage+Report_Delimiter+messages; 
							logSteps(false, logMessage,  null);
						}	  
						break;	 	
						
						
					case "nonmandatorycheck": //need modification
						String id=element.getAttribute("id");
						String mandatoryCheckString=".input-group-addon.mandatory-bar+#"+id;
					    By mandatoryCheckCSS=By.cssSelector(mandatoryCheckString);
					     		    
						try
						{
							WebDriverWait wait = new WebDriverWait(this.driver, 1);
							wait.until(ExpectedConditions.visibilityOfElementLocated(mandatoryCheckCSS));
							wait=null;
							logMessage=logMessage+Report_Delimiter+"Verified: Field is  Mandatory";
							logSteps(false, logMessage, element);
						}catch(Exception e)
						{		logMessage=logMessage+Report_Delimiter+"Verified: Field is Not Mandatory";
								logSteps(true, logMessage, element);
						}
						 break;
					
                    case "reportsuccess":
                        messages="Verified";
                        logMessage=logMessage+Report_Delimiter+messages;
                        logSteps(true, logMessage,  null);
                        break;
                 
                    case "reportfailure":
                        messages="Failed Verification";
                        logMessage=logMessage+Report_Delimiter+messages;
                        logSteps(false, logMessage,  null);
                        break;
           
         					}
					}catch(Exception e)
					{
						logMessage=logMessage+Report_Delimiter+messages;
						 logSteps(false, logMessage,  null);
					}
					
					
	
			
	
}
	public void CheckEquality(String actual, String expected, String message) {
		String logMessage="check Equality"+Report_Delimiter+"Checking if two strings are equal"+Report_Delimiter+actual+Report_Delimiter+message;

		if (actual.equalsIgnoreCase(expected))
		{	
			logSteps(true, logMessage,  null);
		} 
		else
		{	
		
			logSteps(false, logMessage,  null);
		}	  
		
	}
	
	
	// ************************************************************************************************************************
public void PageLoadVerify()
{
	
	
	JavascriptExecutor js = (JavascriptExecutor)this.driver;
	
	 for (int i=0; i<30; i++){ 
		   try {
		    Thread.sleep(1000);
		    }catch (InterruptedException e) {} 
		   //To check page ready state.
		   if (js.executeScript("return document.readyState").toString().equals("complete"))
		   { 
	//		 // System.out.println("readyState="+js.executeScript("return document.readyState").toString()+" in Seconds="+i); 
		    break; 
		   }
	 }
	 
	 WebDriverWait wait = new WebDriverWait(this.driver, 60);
//	 wait.until(ExpectedConditions.invisibilityOfElementLocated(Login.pageLoadingImage));
	 
	 
	 Sikuli_Click();
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

public void MouseMove()
{
	Robot robot=null;
	try {
		robot = new Robot();
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	robot.mouseMove((int)(Math.random() * ((500) + 1)), (int)(Math.random() * ((100) + 1)));
	robot=null;
}
public void SwitchWindow(String windowName,String operation) throws IOException, InterruptedException {

    //System.out.println("Entering ChidlWindow");
    try
    {
    Wait(1);//Thread.sleep(2000);
    int i = 0;
    
    String returnHandle = "";

    for (i = 0; i < 60; i++) {
          for (String Handle : this.driver.getWindowHandles()) {
                 this.driver.switchTo().window(Handle);
                 if (windowName.equals(this.driver.getTitle())) {
                        // //System.out.println(driver.switchTo().window(Handle).getTitle());

                        //System.out.println("current window Inside Childwindows" + driver.getTitle());
                        //System.out.println("..............Window Found..................");
                        returnHandle = Handle;
                        break;
                 }

          }
          
          if (returnHandle != "")
                 break;
    }

    //System.out.println("Exiting ChidlWindow");
//     this.driver.switchTo().window(returnHandle);
  
    switch (operation)
    {
    case "closewindow":
           this.driver.close();
               break;

    case "printwindowcancel":
        //   this.driver.switchTo().window(returnHandle).getTitle();
         this.driver.findElement(By.className("cancel")).click();
                break;
               
    case "escapeKey":    
    	   	this.driver.switchTo().window(returnHandle);
    	    Actions action = new Actions(this.driver);
          //  action.contextClick().sendKeys(keysToSend).sendKeys(Keys.ESCAPE).build().perform();
             break;
             
             
    case "switch":
          this.driver.switchTo().window(returnHandle);
    }
    }catch(Exception e)
    {
          
            logSteps(false, "Switch Window",null);
    }
}



//************Umang
public List  ReturnElementsText(By locator,String logMessage)  {
     //logMessage format = FunctionName+stepDescription+TestData+Status+Message               
//logMessage = "GetText"+Report_Delimiter+logMessage+Report_Delimiter+"" ;
String functionName="ReturnElements";
     String stepDescription="";
     String testData="";
     String messages="";
     logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData;
     List<WebElement> allElements=null ;
     HashSet<String> allElementsText = new HashSet<String>();
//   String returnText="";
            
            try {
                   allElements = driver.findElements(locator);
                   
                   for(int i=0;i<allElements.size();i++)
                   {
                         String temp=allElements.get(i).getText();
                         allElementsText.add(temp);
                   }
                   
                   
                   
            } catch(Exception e)
            {
                   
            //     logSteps(false, "GetElement", "",element);
            }
            List<String> allElementsText_list = new ArrayList<String>(allElementsText);
return allElementsText_list;
}





public void EnterTestDataWithOutTab(By locator, String text, boolean isMandatory, String logMessage) {
     //logMessage format = FunctionName+stepDescription+TestData+Status+Message
//   logMessage = "EnterTestData"+Report_Delimiter + logMessage+Report_Delimiter +text+Report_Delimiter;
     String functionName="EnterTestData";
     String stepDescription=logMessage;
     String testData=text;
     String messages="";
     logMessage=functionName+Report_Delimiter+stepDescription+Report_Delimiter+testData+Report_Delimiter+messages;
            
     WebElement element=null ; 
     String element_ID="";
     
     PageLoadVerify(); //Verify Page Load
     element = GetElement(locator,timeout);
     if (element!=null)
     {
            element.clear();
            if (text!="")
            element.sendKeys(text);    
//          element.sendKeys("\t"); //Press TAB
            logSteps(element.getAttribute("value").equalsIgnoreCase(text.toLowerCase()), logMessage, element);
     //     System.out.println("Attribute Value="+element.getAttribute("value").contentEquals(text));
            element_ID=element.getAttribute("id");
            if(isMandatory){
                   //MandatoryFieldCheck(locator);
                   MandatoryFieldCheck(element_ID);
            }
     }
     else
     logSteps(false, logMessage,element);
     

}

public void Sikuli_Click()
{
	
	   try {
	        Alert alert = this.driver.switchTo().alert();
	        System.out.println("Alert Message"+  alert.getText());
	        alert.accept();
	   	} catch(Exception e)
	   {
	   		
	   }
    
   
  
	
}

public void scrollToElement(By locator) {
	JavascriptExecutor je= (JavascriptExecutor)this.driver;
	je.executeScript("arguments[0].scrollIntoView(true);", GetElement(locator, 1));
}
}

//************Umang





