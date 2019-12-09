package sysnik;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.apache.xpath.functions.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import helper.AssertionHelper;
import helper.DateHelper;
import helper.DropDownHelper;
import helper.FindElement;
import helper.ReportHelper;
import helper.RobotHelper;
import helper.SeleniumHelper;
import helper.SequencedProperties;
import testBase.TestBase;
	public class Dream extends TestBase {
AssertionHelper assertionHelper = new AssertionHelper();		
	SeleniumHelper seleniumHelper = new SeleniumHelper();	
	DropDownHelper dropDownHelper = new DropDownHelper();
	FindElement findElement = new FindElement();
DateHelper dateHelper = new DateHelper();
	RobotHelper robotHelper = new RobotHelper();
ReportHelper reportHelper = new ReportHelper();
		@BeforeTest
		public void test() {
			strartBrowser("chrome");
			d.manage().window().maximize();
			d.get("http://172.16.0.150:4200/dashboard/withdrawal");
			reportHelper.setReportName("Deposit screen Testing");
			reportHelper.startTest("Deposit Screen Verification");
			reportHelper.ChildTest("Auto Upload debugging");
			reportHelper.appendChild();
		}
		File file;
		File file2;
		
		Properties table=null;
		Properties table2=null;
		 String value=null;
		
		

		@Test
		public void loadPFile() throws IOException {
			String[] key=null;
			String key1;
			String key2;
			FileInputStream fi=null;
			FileInputStream fi2=null;
		File	 directory = new File("C:\\Users\\sriraja.garlapati\\eclipse-workspace\\sysnik\\datamaintenance\\DepositScreen\\scenarioOne");
			
			int fileCount=directory.list().length;
			System.out.println(fileCount+"---->fileCount");
			 Map<String,String>map = new LinkedHashMap<String,String>();
			 for(int i=0;i<=fileCount-2;i++) {
				 file = new File("C:\\Users\\sriraja.garlapati\\eclipse-workspace\\sysnik\\datamaintenance\\DepositScreen\\scenarioOne\\locator.properties");
				 file2 = new File("C:\\Users\\sriraja.garlapati\\eclipse-workspace\\sysnik\\datamaintenance\\DepositScreen\\scenarioOne\\data"+i+".properties");	
					
					 fi=new FileInputStream(file);
					 fi2=new FileInputStream(file2);
					 table=new SequencedProperties();
						 table2=new SequencedProperties();
					table.load(fi);	 
			 
					 
					table2.load(fi2);
			 for (final Entry<Object, Object> entry : table.entrySet()) {
		            map.put((String) entry.getKey(), (String) entry.getValue());
		        }
			for (String name: map.keySet()){

	         key =name.toString().split("_");
	        key1=key[0];
	    
			key2=key[1];
	
	             value = map.get(key1+"_"+key2).toString();  
	           
	    
	           operation(value, key2,key1);

	             if("submit".equalsIgnoreCase(key1)&& i==fileCount-2) { 
		        	   seleniumHelper.clickElement(table.getProperty(key1+"_"+key2));
		        	 
		           }   
	              
			}
			
			
			
		}}
		
		
		
		public void operation(String ele,String method,String labelName)  {
						

			if(method.equalsIgnoreCase("number")||method.equalsIgnoreCase("text")) {
			
			seleniumHelper.enterText(ele,table2.getProperty(labelName+"_"+method));
			reportHelper.writeLogInfoInChildTest(table2.getProperty(labelName+"_"+method)+" Was entered in "+labelName+" textbox");
			
			}
			else if ((method.equalsIgnoreCase("click")||method.equalsIgnoreCase("button")||method.equalsIgnoreCase("radio"))&&(!labelName.equalsIgnoreCase("submit"))) {

				seleniumHelper.clickElement(ele);
				reportHelper.writeLogInfoInChildTest(labelName+" was clicked");
			}
			else if (method.equalsIgnoreCase("dropdown")) {
          dropDownHelper.SelectUsingVisibleText(ele, table2.getProperty(labelName+"_"+method));
  		reportHelper.writeLogInfoInChildTest(table2.getProperty(labelName+"_"+method)+" Was selected from "+labelName+" "+method);
			}
			else if (method.equalsIgnoreCase("Calendar")) {

				dateHelper.enterStaticDateForCBSCalender(ele);	
				reportHelper.writeLogInfoInChildTest("date entered in"+labelName+" "+method);
				}
		else if (method.equalsIgnoreCase("file")) {
				seleniumHelper.clickElement(ele);
				robotHelper.fileUploadUsingRobot(table2.getProperty(labelName+"_"+method));
				reportHelper.writeLogInfoInChildTest("File uploading is done in"+labelName);
				
			}
			else if (method.equalsIgnoreCase("label")) {
				assertionHelper.verifyTextEquals(ele,table2.getProperty(labelName+"_"+method) );
			}
			else {
				log.info("method not found");
			}
		}
		
		
		@AfterTest
		public void tearDown() {

			reportHelper.endChild();
			reportHelper.endParent();
			reportHelper.writeLogToReport();
		}
		
	}


