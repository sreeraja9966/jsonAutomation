package drawingPower;

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
import setUp.SuiteSetUp;
import testBase.TestBase;

public class DrawingPowerEntryAuthorzationTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();

	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Drawing Power Entry Authorization Verification");
		
	}

@AfterMethod
public void closingChildBranch() {
	reportHelper.endChild();
	reportHelper.appendChild();
	d.close();
	
}
@BeforeMethod
public void clickMenu() {
	strartBrowser("chrome");
	d.navigate().to("http://172.16.0.39:81/dynamic-form");
	seleniumHelper.enterText("//input[@id='BankCode']", "101");
	seleniumHelper.enterText("//input[@id='UserID']", "1");
	seleniumHelper.clickElement("//input[@name='login']");
	
	}
@AfterClass
public void tearUp() {
	reportHelper.endParent();
	reportHelper.writeLogToReport();
	
}	

@Parameters({"DrawingPowerEntryAuthLabelVerification"})
@Test(priority=0)
public void DrawingPowerEntryAuthLabelVerification(String DrawingPowerEntryAuthLabelVerification) {
	reportHelper.ChildTest("DrawingPowerEntryAuthLabelVerification");
	seleniumHelper.searchMenu("Drawing Power Statement Submission Authorization");
	seleniumHelper.clickElement("//a[contains(text(),'Drawing Power Statement Submission Authorization')]");
	autoPicking.loadPropertyFile(DrawingPowerEntryAuthLabelVerification);
			
}

@Parameters({"DrawingPowerEntryAuthorizationE2e"})
@Test(priority=1)
public void DrawingPowerEntryAuthorizationE2e(String DrawingPowerEntryAuthorizationE2e) {
	reportHelper.ChildTest("DrawingPowerEntryAuthorizationE2e");
	seleniumHelper.searchMenu("Drawing Power Statement Submission Authorization");
	seleniumHelper.clickElement("//a[contains(text(),'Drawing Power Statement Submission Authorization')]");
	autoPicking.loadPropertyFile(DrawingPowerEntryAuthorizationE2e);
			
}

@Parameters({"DPEntryAuthRejection"})
@Test(priority=2)
public void DPEntryAuthRejection(String DPEntryAuthRejection) {
	reportHelper.ChildTest("DPEntryAuthRejection");
	seleniumHelper.searchMenu("Drawing Power Statement Submission");
	seleniumHelper.clickElement("//a[contains(text(),'Drawing Power Statement Submission')]");
	autoPicking.loadPropertyFile(DPEntryAuthRejection);
			
}




}
