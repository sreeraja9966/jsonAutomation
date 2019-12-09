package drawingPower;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.DbHelper;
import helper.ExecutionOfQuiresBeforeAnyTestCase;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import helper.WebTableHelper;
import testBase.TestBase;

public class DrawingPowerEntryTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();
ExecutionOfQuiresBeforeAnyTestCase executionOfQuiresBeforeAnyTestCase = new ExecutionOfQuiresBeforeAnyTestCase();
	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Drawing Power Entry Verification");
		executionOfQuiresBeforeAnyTestCase.executeQuriesFromExcel("select * from DrawingPowerEntry where scenario='positive'", "ExecuteQuires");
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

@Parameters({"DrawingPowerLabelVerification"})
@Test(priority=0)
public void DrawingPowerLabelVerification(String DrawingPowerLabelVerification) {
	reportHelper.ChildTest("DrawingPowerLabelVerification");
	/*seleniumHelper.searchMenu("Customer Authorization");
	seleniumHelper.clickElement("//a[contains(text(),'Customer Authorization')]");*/
	seleniumHelper.searchMenu("Drawing Power Statement Submission");
	seleniumHelper.clickElement("//a[contains(text(),'Drawing Power Statement Submission')]");
	autoPicking.loadPropertyFile(DrawingPowerLabelVerification);
			
}
@Parameters({"CustomerDetailsVerification"})
@Test(priority=1)
public void CustomerDetailsVerification(String CustomerDetailsVerification) {
	reportHelper.ChildTest("CustomerDetailsVerification");
	seleniumHelper.searchMenu("Drawing Power Statement Submission");
	seleniumHelper.clickElement("//a[contains(text(),'Drawing Power Statement Submission')]");
	autoPicking.loadPropertyFile(CustomerDetailsVerification);
			
}
@Parameters({"ExpiryDateVerification"})
@Test(priority=2)
public void ExpiryDateVerification(String ExpiryDateVerification) {
	reportHelper.ChildTest("ExpiryDateVerification");
	autoPicking.loadPropertyFile(ExpiryDateVerification);
			
}


@Parameters({"MarginVerification"})
@Test(priority=3)
public void MarginVerification(String MarginVerification) {
	reportHelper.ChildTest("MarginVerification");
	autoPicking.loadPropertyFile(MarginVerification);
			
}

@Parameters({"ValueCalculationVerificationBasedOnMargin"})
@Test(priority=4)
public void ValueCalculationVerificationBasedOnMargin(String ValueCalculationVerificationBasedOnMargin) {
	reportHelper.ChildTest("ValueCalculationVerificationBasedOnMargin");
	autoPicking.loadPropertyFile(ValueCalculationVerificationBasedOnMargin);
			
}
@Parameters({"ValidateDPArrivedAndDpAllowed"})
@Test(priority=5)
public void ValidateDPArrivedAndDpAllowed(String ValidateDPArrivedAndDpAllowed) {
	reportHelper.ChildTest("ValidateDPArrivedAndDpAllowed");
	autoPicking.loadPropertyFile(ValidateDPArrivedAndDpAllowed);
			
}
@Parameters({"DrawingPowerEntryE2e"})
@Test(priority=6)
public void DrawingPowerEntryE2e(String DrawingPowerEntryE2e) {
	reportHelper.ChildTest("DrawingPowerEntryE2e");
	autoPicking.loadPropertyFile(DrawingPowerEntryE2e);
			
}

@Parameters({"DuplicateEntry"})
@Test(priority=7)
public void DuplicateEntry(String DuplicateEntry) {
	reportHelper.ChildTest("DuplicateEntry");
	autoPicking.loadPropertyFile(DuplicateEntry);
			
}
}
