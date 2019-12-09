package sysnik;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.python.modules.thread.thread;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import helper.AssertionHelper;
import helper.BrowserHelper;
import helper.DateHelper;
import helper.FindElement;
import helper.JavaScriptHelper;
import helper.ReportHelper;
import helper.RepositoryHelper;
import helper.RobotHelper;
import helper.SeleniumHelper;
import helper.WaitingHelper;
import testBase.TestBase;

public class FileUpload extends TestBase{
	public  Robot robo;
	static FindElement FindElement=new FindElement();
	static SeleniumHelper seleniumHelper = new SeleniumHelper();
	static AssertionHelper assertionHelper = new AssertionHelper();
	static RepositoryHelper repositoryHelper = new  RepositoryHelper();
	BrowserHelper browserHelper = new BrowserHelper();
	static ReportHelper reportHelper = new ReportHelper();
	 DateHelper dateHelper = new DateHelper();
	@Test
	public  void test() throws AWTException, InterruptedException {
		strartBrowser(repositoryHelper.loadPropertyFile("GenricRepo.Properties","", "Browser"));
		reportHelper.setReportName("Sample");
		reportHelper.startTest("UseCase");
		reportHelper.ChildTest("testcase1");
		
	//	reportHelper.writeLogInCaseOfPassInChildTest(browserHelper.setBrowserVersion());
		reportHelper.writeLogInCaseOfPassInChildTest("stepDetails");
		reportHelper.endChild();
		reportHelper.appendChild();
		reportHelper.ChildTest("testcase2");
		reportHelper.writeLogInCaseOfPassInChildTest("stepDetails");
		reportHelper.writeLogInCaseOfFailInChildTest("stepDetails");
		reportHelper.writeLogInCaseOfPassInChildTest(browserHelper.getBrowserVersion());
		
		
		
		
		d.get("C:\\Users\\sriraja.garlapati\\Desktop\\calendar.html");
		d.manage().window().maximize();
		SoftAssert softAssert = new SoftAssert();
		
		
		dateHelper.enterSystemDate("//input[@name='lastname']", "mm//dd//yyyy");
		//assertionHelper.verifyTextEquals((FindElement.searchClickableElement(d.findElement(By.xpath("//h2[contains(text(),'Text field')]")))), "Tet field");
		//Assert.assertEquals("Lakshay Sharma", d.findElement(By.xpath("//h2[contains(text(),'Text field')]")).getText());
		//Assert.assertEquals("Text field", d.findElement(By.xpath("//h2[contains(text(),'Text field')]")).getText());
		softAssert.assertEquals("Lakshay Sharma", d.findElement(By.xpath("//h2[contains(text(),'Text field')]")).getText());
		softAssert.assertAll();
		softAssert.assertEquals(" Sharma", d.findElement(By.xpath("//h2[contains(text(),'Text field')]")).getText());
		softAssert.assertAll();
		System.out.println("assertion working");
		reportHelper.endChild();
		reportHelper.appendChild();
		reportHelper.endParent();
		reportHelper.writeLogToReport();
		
}
}
