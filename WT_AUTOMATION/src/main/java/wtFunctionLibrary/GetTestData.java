package wtFunctionLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetTestData {

	    
		int rowIndex = 0, columnIndex = 0;
		XSSFWorkbook  wb;
		Sheet ws;
		Row wr;
		String fileName="";
		String sheetName="";
		HashMap<String, String> data =null;	
		HashMap<String, String> ExecutionData =null;
		//String executionDataFileName="ExecutionData.xlsx";

//**********************************************************************************************
	   public GetTestData(String getFileName,String getSheetName)
	   {
		   fileName=getFileName;
		   sheetName=getSheetName;
	   }
    
	   
 //********************************************************************************************** 
	   
	   
		public 	HashMap<String, String>  getData() throws IOException  
		{	
			data =new HashMap<String, String>();	
			wb = new XSSFWorkbook(new FileInputStream(new File(fileName)));
			ws = wb.getSheet(sheetName);
			//wr = ws.getRow(0);	
			
			 
			for (rowIndex = 1; rowIndex <= ws.getLastRowNum(); rowIndex++) 
			{
				try
				{
				//System.out.println("Key="+ws.getRow(rowIndex).getCell(0).toString());
				//System.out.println("Value="+ws.getRow(rowIndex).getCell(1).toString());
				String key=	ws.getRow(rowIndex).getCell(0).toString().toLowerCase();
				String value=regEx(ws.getRow(rowIndex).getCell(1).toString());
				System.out.println("Key="+key+",Value="+value);
			//	ws.getRow(rowIndex).getCell(1).getCellType()
				data.put(key, value);  //this is where the data is copied into Hash
				}catch(Exception e)
				{
				data.put(String.valueOf(ws.getRow(rowIndex).getCell(0)), ""); //Put Empty String incase of no data from excel
				}
			}
	 
			return data;		
		}
//**********************************************************************************************		 

		public String regEx(String testString)
		{
			
			//String pattern = "(\\w)(\\s+)([\\.,])";
			//Pattern pattern = Pattern.compile("[0-9]+/.[0]+");
			//Matcher matcher = pattern.matcher(testString);
			String formattedString=testString.replaceAll("(?<=^\\d+)\\.0*$", "");
			return formattedString;
		}
	
}
