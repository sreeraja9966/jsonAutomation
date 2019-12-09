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

public class LienNotingTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();
ExecutionOfQuiresBeforeAnyTestCase executionOfQuiresBeforeAnyTestCase = new ExecutionOfQuiresBeforeAnyTestCase();
	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Lien Noting Verification");
		executionOfQuiresBeforeAnyTestCase.executeQuriesFromExcel("select * from LienNoting where scenario='positive'", "ExecuteQuires");
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
@Parameters({"customerInfoVerificationInLienNoting"})
@Test(priority=1)
public void customerInfoVerificationInLienNoting(String customerInfoVerificationInLienNoting) {
	
	reportHelper.ChildTest("customerInfoVerificationInLienNoting");
	seleniumHelper.searchMenu("lien noting entry");
	seleniumHelper.clickElement("//a[contains(text(),'Lien Noting Entry')]");
	
	autoPicking.loadPropertyFile(customerInfoVerificationInLienNoting);
		
}

@Parameters({"depositDetailsVerificationInLienNoting"})
@Test(priority=2)
public void depositDetailsVerificationInLienNoting(String depositDetailsVerificationInLienNoting) {
	
	reportHelper.ChildTest("depositDetailsVerificationInLienNoting");

	
	autoPicking.loadPropertyFile(depositDetailsVerificationInLienNoting);
		
}
@Parameters({"validateModelResponse"})
@Test(priority=3)
public void validateModelResponse(String validateModelResponse) {
	
	reportHelper.ChildTest("validateModelResponse");

	
	autoPicking.loadPropertyFile(validateModelResponse);
		
}

@Parameters({"tableVerificationOnSubmitInLienNoting"})
@Test(priority=4)
public void tableVerificationOnSubmitInLienNoting(String tableVerificationOnSubmitInLienNoting) {
	
	reportHelper.ChildTest("tableVerificationOnSubmitInLienNoting");

	
	autoPicking.loadPropertyFile(tableVerificationOnSubmitInLienNoting);
		
}


@Parameters({"validateRemaingLienAmount"})
@Test(priority=5)
public void validateRemaingLienAmount(String validateRemaingLienAmount) {
	
	reportHelper.ChildTest("validateRemaingLienAmount");

	
	autoPicking.loadPropertyFile(validateRemaingLienAmount);
		
}

}
