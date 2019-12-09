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
import testBase.TestBase;

public class DrawingPowerCancelEntryTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();

	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Drawing Power Cancel Entry Verification");
		strartBrowser("chrome");
		d.navigate().to("http://172.16.0.39:81/dynamic-form");
		seleniumHelper.enterText("//input[@id='BankCode']", "101");
		seleniumHelper.enterText("//input[@id='UserID']", "1");
		seleniumHelper.clickElement("//input[@name='login']");
	
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


@Parameters({"verificationOfStockDetailsinDPCancel"})
@Test(priority=0)
public void verificationOfStockDetailsinDPCancel(String verificationOfStockDetailsinDPCancel) {
	reportHelper.ChildTest("DrawingPowerEntryAuthLabelVerification||verificationOfStockDetailsinDPCancel");
	seleniumHelper.searchMenu("Drawing Power Statement Submission Cancel");
	seleniumHelper.clickElement("//a[contains(text(),'Drawing Power Statement Submission Cancel')]");
	autoPicking.loadPropertyFile(verificationOfStockDetailsinDPCancel);
			
}

@Parameters({"verifyDPCancelOnSubmitMessages"})
@Test(priority=1)
public void verifyDPCancelOnSubmitMessages(String verifyDPCancelOnSubmitMessages) {
	reportHelper.ChildTest("DrawingPowerEntryAuthLabelVerification||verifyDPCancelOnSubmitMessages");
	autoPicking.loadPropertyFile(verifyDPCancelOnSubmitMessages);
			
}
@Parameters({"tableVerifications"})
@Test(priority=2)
public void tableVerifications(String tableVerifications) {
	reportHelper.ChildTest("DrawingPowerEntryAuthLabelVerification||tableVerifications");
	autoPicking.loadPropertyFile(tableVerifications);
			
}
}
