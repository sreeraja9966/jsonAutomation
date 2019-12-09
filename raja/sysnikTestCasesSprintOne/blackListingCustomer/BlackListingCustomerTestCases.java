package blackListingCustomer;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import denominationVerification.DenominationVerificationMethods;
import helper.DbHelper;
import helper.ExecutionOfQuiresBeforeAnyTestCase;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import helper.WebTableHelper;
import testBase.TestBase;

public class BlackListingCustomerTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();
ExecutionOfQuiresBeforeAnyTestCase  executionOfQuiresBeforeAnyTestCase  = new ExecutionOfQuiresBeforeAnyTestCase ();
	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("BlackListing Verification");
		executionOfQuiresBeforeAnyTestCase.executeQuriesFromExcel("select * from BlackListingCustomer where Scenario='positive'", "ExecuteQuires");
	}

@AfterMethod
public void closingChildBranch() {
	reportHelper.endChild();
	reportHelper.appendChild();
}
@BeforeMethod
public void clickMenu() {

	}
@AfterClass
public void tearUp() {
	reportHelper.endParent();
	reportHelper.writeLogToReport();
	
}	

@Parameters({"blackListingE2EFlow"})
@Test(priority=0)
public void blackListingE2EFlow(String blackListingE2EFlow) {
	reportHelper.ChildTest("blackListingE2EFlow");

	 seleniumHelper.searchMenu("Blacklisted Customer Marking");
	seleniumHelper.clickElement("//a[contains(text(),'Blacklisted Customer Marking')]");
	autoPicking.loadPropertyFile(blackListingE2EFlow);
			
}























}
