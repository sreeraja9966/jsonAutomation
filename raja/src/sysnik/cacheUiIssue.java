package sysnik;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import helper.AssertionHelper;
import helper.BrowserHelper;
import helper.DataProviderHelper;
import helper.DropDownHelper;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import helper.WaitingHelper;
import testBase.TestBase;

public class cacheUiIssue extends TestBase {
	BrowserHelper browserHelper=new BrowserHelper();
	ReportHelper reportHelper=new ReportHelper();
	AssertionHelper assertionHelper=new AssertionHelper();
	WaitingHelper waitingHelper=new WaitingHelper();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	FindElement name = new FindElement();
	DataProviderHelper dataProviderHelper = new DataProviderHelper();
	DropDownHelper dropDownHelper = new DropDownHelper();
	
	@BeforeClass
	public void startReport() {
		reportHelper.setReportName("ui issue verification");
		reportHelper.startTest("issue replication");
		strartBrowser("chrome");
		d.get("http://172.16.0.112:4200/dynamic-form");
	}
	
	@AfterClass
	public void tearUp() {
		reportHelper.endParent();
		reportHelper.writeLogToReport();
		
	}
	
	@Test(invocationCount = 10)
	public void mainTest() {
		reportHelper.ChildTest("UI Verification");
		
	
		name.searchClickableElement(By.xpath("//a[contains(text(),' Lien Revoke Authorization')]")).click();
		name.searchClickableElement(By.xpath("//a[contains(text(),' Customer Enrolment ')]")).click();
		dropDownHelper.SelectUsingIndex("//select[@id='maritalStatus']", 1);		
		String dropdownSelectedValue=dropDownHelper.getSelectedValue("//select[@id='maritalStatus']");
		assertionHelper.verifyTwoTexts(dropdownSelectedValue, "Married");
		
		dropDownHelper.SelectUsingIndex("//select[@id='bloodGroup']", 1);		
		String secondDropdownSelectedValue=dropDownHelper.getSelectedValue("//select[@id='bloodGroup']");
		assertionHelper.verifyTwoTexts(secondDropdownSelectedValue, "AB positive");
		
		reportHelper.endChild();
		reportHelper.appendChild();
	}
}
