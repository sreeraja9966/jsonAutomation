package lienNoting;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.BrowserHelper;
import helper.DbHelper;
import helper.ExecutionOfQuiresBeforeAnyTestCase;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import helper.WebTableHelper;
import testBase.TestBase;

public class LienNotingAuthorizationTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();
	BrowserHelper browserHelper = new BrowserHelper();
ExecutionOfQuiresBeforeAnyTestCase executionOfQuiresBeforeAnyTestCase = new ExecutionOfQuiresBeforeAnyTestCase();
	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Lien Noting Authorization Verification");
		//executionOfQuiresBeforeAnyTestCase.executeQuriesFromExcel("select * from LienNoting where scenario='positive'", "ExecuteQuires");
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
	
}	/*
@Parameters({"lienNotingLabelVerification"})
@Test(priority=0)
public void lienNotingLabelVerification(String lienNotingLabelVerification) {
	
	reportHelper.ChildTest("lienNotingLabelVerification");
	seleniumHelper.searchMenu("lien noting entry");
	seleniumHelper.clickElement("//a[contains(text(),'Lien Noting Entry')]");
	
	autoPicking.loadPropertyFile(lienNotingLabelVerification);
		
}*/
@Parameters({"lienNotingAuthScreenVerifications"})
@Test(priority=1)
public void lienNotingAuthScreenVerifications(String lienNotingAuthScreenVerifications) {
	
	reportHelper.ChildTest("lienNotingAuthScreenVerifications");
	seleniumHelper.searchMenu("lien noting Authorization");
	seleniumHelper.clickElement("//a[contains(text(),'Lien Noting Authorization')]");
	
	autoPicking.loadPropertyFile(lienNotingAuthScreenVerifications);
		
}
@Parameters({"onSubmitModelVerificationInLienAuth"})
@Test(priority=2)
public void onSubmitModelVerificationInLienAuth(String onSubmitModelVerificationInLienAuth) {
	
	reportHelper.ChildTest("onSubmitModelVerificationInLienAuth");
	
	
	autoPicking.loadPropertyFile(onSubmitModelVerificationInLienAuth);
		
}

@Parameters({"tableVerificationOnSunbitInLienAuth"})
@Test(priority=3)
public void tableVerificationOnSunbitInLienAuth(String tableVerificationOnSunbitInLienAuth) {
	
	reportHelper.ChildTest("tableVerificationOnSunbitInLienAuth");
	
	
	autoPicking.loadPropertyFile(tableVerificationOnSunbitInLienAuth);
		browserHelper.refresh();
}

@Parameters({"lienNotingRejection"})
@Test(priority=4)
public void lienNotingRejection(String lienNotingRejection) {
	
	
	reportHelper.ChildTest("lienNotingRejection");
	seleniumHelper.searchMenu("lien noting entry");
	seleniumHelper.clickElement("//a[contains(text(),'Lien Noting Entry')]");
	
	autoPicking.loadPropertyFile(lienNotingRejection);
		
}

@Parameters({"RejectionTableVerification"})
@Test(priority=5)
public void RejectionTableVerification(String RejectionTableVerification) {
	
	reportHelper.ChildTest("RejectionTableVerification");
		
	autoPicking.loadPropertyFile(RejectionTableVerification);
		
}
}
