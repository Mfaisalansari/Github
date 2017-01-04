package wtTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import wtDriverManager.GetDriver;
import wtFunctionLibrary.GetTestData;
import wtFunctionLibrary.UtilityFunctions;
import wtObjectRepository.Login;

public class WT_Login{
	GetDriver dm;
	//RemoteWebDriver driver;
	String browser;
	
	
	GetTestData dp = null;
	HashMap<String, String> data = new HashMap<String, String>();
	public static HashMap<String, String> loginData = new HashMap<String, String>();
	String sheetName="Login";
	String testSuiteSheetName="TestSuite";

// ***********************************************************************
@Parameters({ "browser","fileName"})
@BeforeTest(groups="Login")
public void init(String browser,String fileName) {
	//	GetDriver dm = new GetDriver();
	
	 
		sheetName="Login";
		
		
		//Logs
		createLogs(); //only one time operation
		
		if (dp==null)
		{
		dp = new GetTestData(fileName, sheetName);
		try {
			data = dp.getData();
			loginData=data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new code
		dp=null;
		dp = new GetTestData(fileName, testSuiteSheetName);
		try {
			GetDriver.testSuite = dp.getData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		this.browser=browser;
			
		
}
// ***********************************************************************
//@AfterMethod
@AfterMethod
@AfterTest(groups="Login")
public void tearDown() 
{
	//	driver.quit();
	//	driver = null;
	
//		dp = null;
//		data = null;
//		ul = null;
	}

public void createLogs()
{
	File txtLog = new File(UtilityFunctions.txtLog);
	File statusLog = new File(UtilityFunctions.statusLog);
	//Handle Text Log
	if (txtLog.exists())
		txtLog.delete();
	else
	 {
		try {
			txtLog.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if (statusLog.exists())
		statusLog.delete();
	else
	 {
		try {
			statusLog.createNewFile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

 


// ***********************************************************************
@Test(groups="Login")
public void TC_2305(Method method) 
{	
	UtilityFunctions td=new UtilityFunctions();
	td.testDecision(method.getName());
	td=null;
	UtilityFunctions ul=null;
	GetDriver dm = new GetDriver();
	ul = new UtilityFunctions(dm.returnDriver(browser));
    ul.LaunchWTApp(data.get("loginurl"), "Launch WT URL");
    ul.EnterTestData(Login.loginUserName, data.get("username"), true, "Enter UserName");
    ul.EnterTestData(Login.loginUserName, data.get("username"), true, "Enter UserName");
    ul.EnterTestData(Login.loginUserName, data.get("username"), true, "Enter UserName");
    ul.EnterTestData(Login.loginUserName, data.get("username"), true, "Enter UserName");
    ul.EnterTestData(Login.loginUserName, data.get("username"), true, "Enter UserName");
    ul.EnterTestData(Login.loginUserName, data.get("username"), true, "Enter UserName");
   /* ul.ClickButton(Login.loginNext, "Next");
    ul.EnterTestData(Login.loginPassword, data.get("password"), false, "Enter Password");
    ul.ClickButton(Login.loginButton,"Login" );
    */
    ul.update_Logs(UtilityFunctions.statusLog,method.getName()+","+UtilityFunctions.currentTestStatus);
}	

@Test(groups="Login")
public void TC_2306(Method method) 
{	
	UtilityFunctions td=new UtilityFunctions();
	td.testDecision(method.getName());
	td=null;
	UtilityFunctions ul=null;
	GetDriver dm = new GetDriver();
	ul = new UtilityFunctions(dm.returnDriver(browser));
    ul.LaunchWTApp(data.get("loginurl"), "Launch WT URL");
    ul.EnterTestData(Login.loginUserName, data.get("username"), true, "Enter UserName");
   /* ul.ClickButton(Login.loginNext, "Next");
    ul.EnterTestData(Login.loginPassword, data.get("password"), false, "Enter Password");
    ul.ClickButton(Login.loginButton,"Login" );
    */
    ul.update_Logs(UtilityFunctions.statusLog,method.getName()+","+UtilityFunctions.currentTestStatus);
}	

@Test(groups="Login")
public void TC_2307(Method method) 
{	
	UtilityFunctions td=new UtilityFunctions();
	td.testDecision(method.getName());
	td=null;
	UtilityFunctions ul=null;
	GetDriver dm = new GetDriver();
	ul = new UtilityFunctions(dm.returnDriver(browser));
    ul.LaunchWTApp(data.get("loginurl"), "Launch WT URL");
    ul.EnterTestData(Login.loginUserName, data.get("username"), true, "Enter UserName");
    /*ul.ClickButton(Login.loginNext, "Next");
    ul.EnterTestData(Login.loginPassword, data.get("password"), false, "Enter Password");
    ul.ClickButton(Login.loginButton,"Login" );
    */
    ul.update_Logs(UtilityFunctions.statusLog,method.getName()+","+UtilityFunctions.currentTestStatus);
}	

}
