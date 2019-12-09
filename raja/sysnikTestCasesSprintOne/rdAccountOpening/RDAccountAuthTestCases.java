package rdAccountOpening;

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

public class RDAccountAuthTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();

	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("RD Account Authorization Verification");
		
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
@Parameters({"RDAuthScreenVerification"})
@Test(priority=1)
public void RdAuthScreenVerification(String RDAuthScreenVerification) {
	reportHelper.ChildTest("RDAuthScreenVerification");
	 seleniumHelper.searchMenu("RD Authorization");
	seleniumHelper.clickElement("//a[contains(text(),'RD Authorization')]");
	autoPicking.loadPropertyFile(RDAuthScreenVerification);
		
}
}