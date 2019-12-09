package ODAccountOpening;

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

public class ODAccountOpeningTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();

	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("OD Account Opening Verification");
		
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
/*@Parameters({"LabelVerification"})
@Test(priority=0)
public void LabelVerification(String LabelVerification) {
	
	reportHelper.ChildTest("LabelVerification");
	seleniumHelper.searchMenu("OD Account Opening");
	seleniumHelper.clickElement("//a[contains(text(),'OD Account Opening')]");
	
	autoPicking.loadPropertyFile(LabelVerification);
		
}*/
@Parameters({"E2E"})
@Test(priority=1)
public void E2E(String E2E) {
	
	reportHelper.ChildTest("E2E");
	
	seleniumHelper.searchMenu("Customer Authorization");
	seleniumHelper.clickElement("//a[contains(text(),'Customer Authorization')]");
	seleniumHelper.searchMenu("OD Account Opening");
	seleniumHelper.clickElement("//a[contains(text(),'OD Account Opening')]");
	
	autoPicking.loadPropertyFile(E2E);
		
}
}
