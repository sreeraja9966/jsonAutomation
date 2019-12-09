package setUp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import helper.DbHelper;
import helper.EmailHelper;
import helper.FilloExcelDataGetter;
import helper.ReportHelper;
import helper.SeleniumHelper;
import testBase.TestBase;

public class SuiteSetUp extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	EmailHelper emailHelper = new EmailHelper();
	DbHelper  dbHelper = new DbHelper ();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	FilloExcelDataGetter filloExcelDataGetter = new FilloExcelDataGetter();
@BeforeSuite
public void setReport() {
	reportHelper.setReportName("Automation Report");
	strartBrowser("chrome");
	d.navigate().to("http://172.16.0.39:81/dynamic-form");
	seleniumHelper.enterText("//input[@id='BankCode']", "101");
	seleniumHelper.enterText("//input[@id='UserID']", "1");
	seleniumHelper.clickElement("//input[@name='login']");
	
	dbHelper.connectionEstablishmentWithDb();
	/*d.navigate().refresh();
	d.navigate().refresh();*/
	//filloExcelDataGetter.connectedToExcelDataFile();
	/*d.manage().window().setSize(new Dimension(1440,900));
	d.manage().window().fullscreen();
	try {
	    String line;
	    Process p = Runtime.getRuntime().exec
	    	    (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
	    BufferedReader input =
	            new BufferedReader(new InputStreamReader(p.getInputStream()));
	    while ((line = input.readLine()) != null) {
	        System.out.println(line); //<-- Parse data here.
	    }
	    input.close();
	} catch (Exception err) {
	    err.printStackTrace();
	}
	 log.info(d.getTitle()); 
    try {
		Robot robo=new Robot();
		robo.keyPress(KeyEvent.VK_ALT);
		robo.keyPress(KeyEvent.VK_TAB);
		robo.keyRelease(KeyEvent.VK_ALT);
		robo.keyRelease(KeyEvent.VK_TAB);
	
    
     
     while(!"CBS [ V3 ]".trim().equalsIgnoreCase(d.getTitle())) {
    	 log.info("WHILE LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOP");
    	
    	 robo.keyPress(KeyEvent.VK_ALT);
 		robo.keyPress(KeyEvent.VK_TAB);
 		robo.keyRelease(KeyEvent.VK_ALT);
 		robo.keyRelease(KeyEvent.VK_TAB);
     }
     log.info(d.getTitle()+"erfdahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh456"
     		+ "64"); 
    } catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
*/
}

@AfterSuite
public void tearDownThings() {
	
	filloExcelDataGetter.closeExcelConnection();
	dbHelper.closeDbConnection();
	//emailHelper.sendReport("sriraja.garlapati@sysnik.com", "Sprint 3 TestCases", "Sprint 3 Automation Report");
}
}
