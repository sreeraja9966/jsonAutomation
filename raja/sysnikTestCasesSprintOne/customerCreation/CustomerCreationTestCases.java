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
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import testBase.TestBase;

public class CustomerCreationTestCases extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	FindElement findElement = new FindElement();
	AssertionHelper assertionHelper = new AssertionHelper();
	DbHelper dbHelper=new DbHelper(); 
	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Customer Creation");
		
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
@Parameters({"labelVerification"})
@Test(priority=0)
public void labelVerification(String labelVerification) {
reportHelper.ChildTest("labelVerification");
seleniumHelper.searchMenu("Customer Enrollment");
seleniumHelper.clickElement("//a[contains(text(),'Customer Enrollment')]");	
autoPicking.loadPropertyFile(labelVerification);
	
}


@Parameters({"customerCreationWithIntroducerName"})
@Test(priority=1)
public void customerCreationWithIntroducerName(String customerCreationWithIntroducerName) {
reportHelper.ChildTest("customer Creation With Introducer Name");
seleniumHelper.searchMenu("Customer Enrollment");
seleniumHelper.clickElement("//a[contains(text(),'Customer Enrollment')]");	
autoPicking.loadPropertyFile(customerCreationWithIntroducerName);

	
}

@Parameters({"customerAuthorizationDataVerification"})
@Test(priority=2)
public void customerAuthorizationDataVerification(String customerAuthorizationDataVerification) {
reportHelper.ChildTest("customerAuthorizationDataVerification");
seleniumHelper.searchMenu("Customer Authorization");
seleniumHelper.clickElement("//a[contains(text(),'Customer Authorization')]");	
autoPicking.loadPropertyFile(customerAuthorizationDataVerification);
		
}

@Parameters({"authorizationCustomerFlow"})
@Test(priority=3)
public void customerAuthorization(String authorizationCustomerFlow) {
reportHelper.ChildTest("authorizationCustomerFlow");
seleniumHelper.searchMenu("Customer Authorization");
seleniumHelper.clickElement("//a[contains(text(),'Customer Authorization')]");
autoPicking.loadPropertyFile(authorizationCustomerFlow);

}












}
