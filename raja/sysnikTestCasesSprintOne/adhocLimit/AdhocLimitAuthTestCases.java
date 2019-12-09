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
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import helper.WebTableHelper;
import testBase.TestBase;

public class AdhocLimitAuthTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();

	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Adhoc Limit Auth Verification");
		
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

@Parameters({"ValidateAdhocAuthScreenDetails"})
@Test(priority=0)
public void ValidateAdhocAuthScreenDetails(String ValidateAdhocAuthScreenDetails) {
	reportHelper.ChildTest("Adhoc Limit Authorization||Validate Adhoc Auth ScreenDetails");
	seleniumHelper.searchMenu("Adhoc Limit Authorization");
	seleniumHelper.clickElement("//a[contains(text(),'Adhoc Limit Authorization')]");
	autoPicking.loadPropertyFile(ValidateAdhocAuthScreenDetails);
			
}


@Parameters({"SubmitAdhocLimitAuth"})
@Test(priority=1)
public void SubmitAdhocLimitAuth(String SubmitAdhocLimitAuth) {
	reportHelper.ChildTest("Adhoc Limit Authorization||Submit Adhoc Limit Auth");
	autoPicking.loadPropertyFile(SubmitAdhocLimitAuth);
			
}

@Parameters({"ValidateAdhocAuthTableDetails"})
@Test(priority=2)
public void ValidateAdhocAuthTableDetails(String ValidateAdhocAuthTableDetails) {
	reportHelper.ChildTest("Adhoc Limit Authorization||Validate Adhoc Auth Table Details");
	autoPicking.loadPropertyFile(ValidateAdhocAuthTableDetails);
			
}




















}
