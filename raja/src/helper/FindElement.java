package helper;

import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.ExecuteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import testBase.TestBase;

public class FindElement extends TestBase{
	private static final Logger logger = Logger.getLogger(FindElement.class);
	
	WaitingHelper waitingHelper =new WaitingHelper();
	JsWaiter jsWaiter = new JsWaiter();
	public  WebElement searchClickableElement(By xpath){
		ReportHelper reportHelper = new ReportHelper();
		try {
			jsWaiter.setDriver(d);
			jsWaiter.waitAllRequest();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(isElementPresent(xpath)) {
				return d.findElement(xpath);
			}
			else {
				reportHelper.writeLogInCaseOfFailInChildTest("ELEMENT NOT FOUND"+xpath);
				reportHelper.addScreenShotInCaseOfFailInReportInChild("ELEMENT NOT FOUND"+xpath);
			}
		} catch (Exception e) {
			log.error(e);
			reportHelper.writeLogInCaseOfFailInChildTest("ELEMENT NOT FOUND"+xpath);
			reportHelper.addScreenShotInCaseOfFailInReportInChild("ELEMENT NOT FOUND"+xpath);
				}
		return null;
	}
	
	
	
	public boolean isElementPresent(By element) {
		
		try {
			return waitingHelper.waitForElementVisible(element, 3, 10);
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
			}
	
}
