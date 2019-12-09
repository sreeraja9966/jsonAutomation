package lienNoting;

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

public class LienRevokeTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();
ExecutionOfQuiresBeforeAnyTestCase executionOfQuiresBeforeAnyTestCase = new ExecutionOfQuiresBeforeAnyTestCase();
	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Lien Revoke Verification");
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
@Parameters({"LienRevokeInfoVerification"})
@Test(priority=1)
public void LienRevokeInfoVerification(String LienRevokeInfoVerification) {
	
	reportHelper.ChildTest("LienRevokeInfoVerification");
	seleniumHelper.searchMenu("lien revoke entry");
	seleniumHelper.clickElement("//a[contains(text(),'Lien Revoke Entry')]");
	
	autoPicking.loadPropertyFile(LienRevokeInfoVerification);
		
}

@Parameters({"modelResponseVerificationinLienRevoke"})
@Test(priority=2)
public void modelResponseVerificationinLienRevoke(String modelResponseVerificationinLienRevoke) {
	
	reportHelper.ChildTest("modelResponseVerificationinLienRevoke");
	
	autoPicking.loadPropertyFile(modelResponseVerificationinLienRevoke);
		
}

@Parameters({"tableVerificationsInLienRevoke"})
@Test(priority=3)
public void tableVerificationsInLienRevoke(String tableVerificationsInLienRevoke) {
	
	reportHelper.ChildTest("tableVerificationsInLienRevoke");
	
	autoPicking.loadPropertyFile(tableVerificationsInLienRevoke);
		
}
}
