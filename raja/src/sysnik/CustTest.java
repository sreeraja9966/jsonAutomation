package sysnik;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import helper.AlertHelper;
import helper.AssertionHelper;
import helper.BrowserHelper;
import helper.DataProviderHelper;
import helper.DropDownHelper;
import helper.FindElement;
import helper.JavaScriptHelper;
import helper.ReportHelper;
import helper.SeleniumHelper;
import helper.WaitingHelper;
import testBase.TestBase;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import autoPick.AutoPicking;
public class CustTest extends TestBase {
	BrowserHelper browserHelper=new BrowserHelper();
	ReportHelper reportHelper=new ReportHelper();
	AssertionHelper assertionHelper=new AssertionHelper();
	WaitingHelper waitingHelper=new WaitingHelper();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	FindElement name = new FindElement();
	DataProviderHelper dataProviderHelper = new DataProviderHelper();
	DropDownHelper dropDownHelper = new DropDownHelper();
	AutoPicking autoPicking = new AutoPicking();
	JavaScriptHelper javaScriptHelper = new JavaScriptHelper();
	AlertHelper alertHelper = new AlertHelper();
	FindElement findElement = new FindElement();
	@BeforeClass
	public void startReport() {
		reportHelper.setReportName("cust enroll verification");
		reportHelper.startTest("normalTest");
		strartBrowser("chrome");
		d.get("http://172.16.0.148:4200/dynamic-form");
		
		//seleniumHelper.clickElement("//a[contains(text(),'Document Capture entry')]");
	}
	
	@AfterClass
	public void tearUp() {
		reportHelper.endParent();
		reportHelper.writeLogToReport();
		
		
	}
	
	
	/*@Parameters({"locatorFile"})
	@Test
	public void custCreation(String locatorFile) throws InterruptedException {
		
		reportHelper.ChildTest("Table Title Verification");
		//autoPicking.loadPropertyFile(locatorFile);
		seleniumHelper.clickElement("//label[@id='custAccC']");
		
		seleniumHelper.enterText("//input[@id='custAccNo']", "2025");
		dropDownHelper.SelectUsingIndex("//select[@id='description']", 1);
		seleniumHelper.enterText("//input[@id='docNumber']", "2025");
		javaScriptHelper.scrollDownVertically();
		seleniumHelper.clickElement("//button[contains(text(),' Add Document')]");
		seleniumHelper.clickElement("//button[contains(text(),'Submit')]");
		autoPicking.loadPropertyFile(locatorFile);
		System.out.println(waitingHelper.isAngularDone());
		System.out.println(waitingHelper.isJQueryDone());
		//Thread.sleep(3000);
		MultiWindow.switchToModalDialog(d.getWindowHandle());
        System.out.println(seleniumHelper.getTextFromAnElement("//h6[@id='modale']"));   
		//seleniumHelper.clickElement("//button[contains(text(),'OK')]");
		reportHelper.endChild();
		reportHelper.appendChild();
	}*/
	
	@Test
	public void deathMarkCustomer() {
		reportHelper.ChildTest("deathMarkCustomer Verification");
		//javaScriptHelper.scrollDownVertically();
		seleniumHelper.clickElement("//a[contains(text(),' Document Capture Auth ')]");
		
		seleniumHelper.clickElement("//span[contains(text(),'Document Type')]");
		javaScriptHelper.scrollToElemet(By.xpath("//div[@class='dropdown-list']//ul[2]//li[1]//div"));
		seleniumHelper.clickElement("//div[@class='dropdown-list']//ul[2]//li[1]//div");
		seleniumHelper.clickElement("//div[@class='multiselect-dropdown']");
		javaScriptHelper.scrollUpVertically();
		
		
		
		
		
	}
}
