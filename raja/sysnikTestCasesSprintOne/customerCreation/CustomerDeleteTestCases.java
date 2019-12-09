package customerCreation;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import autoPick.AutoPicking;
import helper.AssertionHelper;
import helper.DbHelper;
import helper.ExecutionOfQuiresBeforeAnyTestCase;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import testBase.TestBase;

public class CustomerDeleteTestCases extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	FindElement findElement = new FindElement();
	AssertionHelper assertionHelper = new AssertionHelper();
	DbHelper dbHelper=new DbHelper(); 
	ExecutionOfQuiresBeforeAnyTestCase executionOfQuiresBeforeAnyTestCase = new ExecutionOfQuiresBeforeAnyTestCase();
	@BeforeTest
	public void setUp() {
		executionOfQuiresBeforeAnyTestCase.executeQuriesFromExcel("select * from CustomerDelete where scenario='positive'", "ExecuteQuires");	
		reportHelper.appendToExstingReport("Customer Delete");
		
	}
@BeforeMethod
public void clickOnCustomerMenu() {
	
}
@AfterMethod
public void closingChildBranch() {
	reportHelper.endChild();
	reportHelper.appendChild();
	 
}
@AfterClass
public void tearUp() {
	reportHelper.endParent();
	reportHelper.writeLogToReport();
	//d.quit();
	
}
@Parameters({"customerDeleteDataVerification"})
@Test(priority=0)
public void customerDeleteDataVerification(String customerDeleteDataVerification) {
reportHelper.ChildTest("customerDeleteDataVerification");
seleniumHelper.searchMenu("Customer Delete");
seleniumHelper.clickElement("//a[contains(text(),'Customer Delete')]");	
autoPicking.loadPropertyFile(customerDeleteDataVerification);
	
}

@Parameters({"customerDeleteModelVerificationOnsubmit"})
@Test(priority=1)
public void customerDeleteModelVerificationOnsubmit(String customerDeleteModelVerificationOnsubmit) {
reportHelper.ChildTest("customerDeleteModelVerificationOnsubmit");

autoPicking.loadPropertyFile(customerDeleteModelVerificationOnsubmit);
	
}


@Parameters({"customerDeleteEntryTableVerifications"})
@Test(priority=2)
public void customerDeleteEntryTableVerifications(String customerDeleteEntryTableVerifications) {
reportHelper.ChildTest("customerDeleteEntryTableVerifications");

autoPicking.loadPropertyFile(customerDeleteEntryTableVerifications);
	
}



@Parameters({"customerDeleteAuthDataVerification"})
@Test(priority=3)
public void customerDeleteAuthDataVerification(String customerDeleteAuthDataVerification) {
reportHelper.ChildTest("customerDeleteAuthDataVerification");
seleniumHelper.searchMenu("Customer Delete Authorization");
seleniumHelper.clickElement("//a[contains(text(),'Customer Delete Authorization')]");	
autoPicking.loadPropertyFile(customerDeleteAuthDataVerification);
	
}
@Parameters({"customerDeleteAuthModelVerificationOnsubmit"})
@Test(priority=4)
public void customerDeleteAuthModelVerificationOnsubmit(String customerDeleteAuthModelVerificationOnsubmit) {
reportHelper.ChildTest("customerDeleteAuthModelVerificationOnsubmit");

autoPicking.loadPropertyFile(customerDeleteAuthModelVerificationOnsubmit);
	
}


@Parameters({"customerDeleteAuthTableVerifications"})
@Test(priority=5)
public void customerDeleteAuthTableVerifications(String customerDeleteAuthTableVerifications) {
reportHelper.ChildTest("customerDeleteAuthTableVerifications");

autoPicking.loadPropertyFile(customerDeleteAuthTableVerifications);
	
}

}
