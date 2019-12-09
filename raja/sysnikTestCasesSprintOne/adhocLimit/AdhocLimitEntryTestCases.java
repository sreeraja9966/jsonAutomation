package adhocLimit;

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

public class AdhocLimitEntryTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();
ExecutionOfQuiresBeforeAnyTestCase executionOfQuiresBeforeAnyTestCase = new ExecutionOfQuiresBeforeAnyTestCase();
	@BeforeTest
	public void setUp() {
		executionOfQuiresBeforeAnyTestCase.executeQuriesFromExcel("select * from Adhoc_limit where scenario='positive'", "ExecuteQuires");
		reportHelper.appendToExstingReport("Adhoc Limit Entry Verification");
		
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
	/*d.close();
	d.quit();*/
}	

@Parameters({"ValidateCustInfo"})
@Test(priority=0)
public void ValidateCustInfo(String ValidateCustInfo) {
	reportHelper.ChildTest("Adhoc Limit Entry||ValidateCustInfo");
	seleniumHelper.searchMenu("Adhoc Limit Entry");
	seleniumHelper.clickElement("//a[contains(text(),'Adhoc Limit Entry')]");
	autoPicking.loadPropertyFile(ValidateCustInfo);
			
}

@Parameters({"ValidateExpiryDate"})
@Test(priority=1)
public void ValidateExpiryDate(String ValidateExpiryDate) {
	reportHelper.ChildTest("Adhoc Limit Entry||ValidateExpiryDate");
	

	autoPicking.loadPropertyFile(ValidateExpiryDate);
			
}


@Parameters({"adhocSubmitMessageVerification"})
@Test(priority=2)
public void adhocSubmitMessageVerification(String adhocSubmitMessageVerification) {
	reportHelper.ChildTest("Adhoc Limit Entry||adhocSubmitMessageVerification");
	
	autoPicking.loadPropertyFile(adhocSubmitMessageVerification);
			
}


@Parameters({"adhocSubmitTableVerification"})
@Test(priority=2)
public void adhocSubmitTableVerification(String adhocSubmitTableVerification) {
	reportHelper.ChildTest("Adhoc Limit Entry||adhocSubmitTableVerification");
	
	autoPicking.loadPropertyFile(adhocSubmitTableVerification);
			
}



@Parameters({"duplicateEntry"})
@Test(priority=3)
public void duplicateEntry(String duplicateEntry) {
	reportHelper.ChildTest("Adhoc Limit Entry||duplicateEntry");
	
	autoPicking.loadPropertyFile(duplicateEntry);
			
}















}
