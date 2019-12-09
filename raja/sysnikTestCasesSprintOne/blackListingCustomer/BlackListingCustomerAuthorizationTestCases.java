package blackListingCustomer;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.DbHelper;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import helper.WebTableHelper;
import testBase.TestBase;

public class BlackListingCustomerAuthorizationTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();

	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("BlackListing Autorization Verification");
		
	}

@AfterMethod
public void closingChildBranch() {
	reportHelper.endChild();
	reportHelper.appendChild();
}
@BeforeMethod
public void clickMenu() {
	
	reportHelper.appendToExstingReport("BlackListing Marking Auth verification");
	}
@AfterClass
public void tearUp() {
	reportHelper.endParent();
	reportHelper.writeLogToReport();
	
}	



@Parameters({"blackListingAutorizationE2EFlow"})
@Test(priority=0)
public void blackListingAutorizationE2EFlow(String blackListingAutorizationE2EFlow) {
	reportHelper.ChildTest("blackListingAutorizationE2EFlow");
	 seleniumHelper.searchMenu("Blacklisted Customer Marking Authorization");
	seleniumHelper.clickElement("//a[contains(text(),'Blacklisted Customer Marking Authorization')]");
	autoPicking.loadPropertyFile(blackListingAutorizationE2EFlow);
			
}
}
