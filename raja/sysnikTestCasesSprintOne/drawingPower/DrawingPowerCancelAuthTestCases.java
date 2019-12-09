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

public class DrawingPowerCancelAuthTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();

	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Drawing Power Cancel Authorization Verification");
		
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


@Parameters({"verificationOfStockDetailsinDPCancelAuth"})
@Test(priority=0)
public void verificationOfStockDetailsinDPCancel(String verificationOfStockDetailsinDPCancelAuth) {
	reportHelper.ChildTest("DrawingPowerEntryAuthLabelVerification||verificationOfStockDetailsinDPCancelAuth");
	seleniumHelper.searchMenu("Drawing Power Statement Submission Cancel Authorization");
	seleniumHelper.clickElement("//a[contains(text(),'Drawing Power Statement Submission Cancel Authorization')]");
	autoPicking.loadPropertyFile(verificationOfStockDetailsinDPCancelAuth);
			
}

@Parameters({"verifyDPCancelAuthOnSubmitMessages"})
@Test(priority=1)
public void verifyDPCancelOnSubmitMessages(String verifyDPCancelAuthOnSubmitMessages) {
	reportHelper.ChildTest("DrawingPowerEntryAuthLabelVerification||verifyDPCancelAuthOnSubmitMessages");
	autoPicking.loadPropertyFile(verifyDPCancelAuthOnSubmitMessages);
			
}/*
@Parameters({"tableVerifications"})
@Test(priority=2)
public void tableVerifications(String tableVerifications) {
	reportHelper.ChildTest("DrawingPowerEntryAuthLabelVerification||tableVerifications");
	autoPicking.loadPropertyFile(tableVerifications);
			
}*/
}
