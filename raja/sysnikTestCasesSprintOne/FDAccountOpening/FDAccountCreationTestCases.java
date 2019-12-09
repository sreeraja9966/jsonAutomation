package FDAccountOpening;

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
import helper.WaitingHelper;
import helper.WebTableHelper;
import testBase.TestBase;

public class FDAccountCreationTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();

	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("FD Account Creation Verification");
		
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
/*@Parameters({"sbAccountOpeningLabelVerification"})
@Test(priority=0)
public void sbAccountOpeningLabelVerification(String sbAccountOpeningLabelVerification) {
	
	reportHelper.ChildTest("sbAccountOpeningLabelVerification");
	seleniumHelper.searchMenu("DD account opening");
	seleniumHelper.clickElement("//a[contains(text(),'DD Account Opening')]");
	
	autoPicking.loadPropertyFile(sbAccountOpeningLabelVerification);
		
}*/
@Parameters({"verifyMaturityDate"})
@Test(priority=1)
public void verifyMaturityDate(String verifyMaturityDate) {
	reportHelper.ChildTest("verifyMaturityDate");
	seleniumHelper.searchMenu("RD Account Opening");
	seleniumHelper.clickElement("//a[contains(text(),'RD Account Opening')]");
	 seleniumHelper.searchMenu("FD Account Opening");
	seleniumHelper.clickElement("//a[contains(text(),'FD Account Opening')]");
	autoPicking.loadPropertyFile(verifyMaturityDate);
		
}

@Parameters({"maturityAmountVerification"})
@Test(priority=2)
public void maturityAmountVerification(String maturityAmountVerification) {
	reportHelper.ChildTest("maturityAmountVerification");
	
	autoPicking.loadPropertyFile(maturityAmountVerification);
		
}
@Parameters({"verifyCustCategoryInFDOpening"})
@Test(priority=3)
public void verifyCustCategoryInFDOpening(String verifyCustCategoryInFDOpening) {
	reportHelper.ChildTest("verifyCustCategoryInFDOpening");
	
	autoPicking.loadPropertyFile(verifyCustCategoryInFDOpening);
		
}
@Parameters({"verifyModelResponse"})
@Test(priority=3)
public void verifyModelResponse(String verifyModelResponse) {
	reportHelper.ChildTest("verifyModelResponse");
	
	autoPicking.loadPropertyFile(verifyModelResponse);
		
}
@Parameters({"verifytablesInEntry"})
@Test(priority=3)
public void verifytablesInEntry(String verifytablesInEntry) {
	reportHelper.ChildTest("verifytablesInEntry");
	
	autoPicking.loadPropertyFile(verifytablesInEntry);
		
}

}
