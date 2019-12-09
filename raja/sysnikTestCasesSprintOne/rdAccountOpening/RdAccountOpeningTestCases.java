package rdAccountOpening;

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

public class RdAccountOpeningTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();

	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("RD Account Opening Verification");
		
	}

@AfterMethod
public void closingChildBranch() {
	reportHelper.endChild();
	reportHelper.appendChild();
}
@BeforeMethod
public void clickMenu() {
	//d.navigate().refresh();
//	seleniumHelper.clickElement("//a[@id='account']");
	
	}
@AfterClass
public void tearUp() {
	reportHelper.endParent();
	reportHelper.writeLogToReport();
	
}	

@Parameters({"verifyMaturityDate"})
@Test(priority=1)
public void verifyMaturityDate(String verifyMaturityDate) {
	reportHelper.ChildTest("verifyMaturityDate");
	 seleniumHelper.searchMenu("RD Account Opening");
	seleniumHelper.clickElement("//a[contains(text(),'RD Account Opening')]");
	autoPicking.loadPropertyFile(verifyMaturityDate);
		
}
@Parameters({"verifyModelResponse"})
@Test(priority=1)
public void verifyModelResponse(String verifyModelResponse) {
	reportHelper.ChildTest("verifyModelResponse");

	autoPicking.loadPropertyFile(verifyModelResponse);
		
}
}
