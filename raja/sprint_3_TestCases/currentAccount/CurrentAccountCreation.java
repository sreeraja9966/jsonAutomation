package currentAccount;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.ReportHelper;
import testBase.TestBase;

public class CurrentAccountCreation extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Current Account Opening");
		strartBrowser("chrome");
	}
@BeforeClass
public void setNavigation() {
	d.navigate().to("https://www.google.com/");
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
	d.close();
	d.quit();
}
@Parameters({"labelVerificationFile"})
@Test(priority=0)
public void labelVerification(String labelVerificationFile) {
	reportHelper.ChildTest("Label Verification");

autoPicking.loadPropertyFile(labelVerificationFile);
	
}
@Parameters({"verificationOfFieldLevelValidation"})
@Test(priority=1)
public void verificationOfFieldLevelValidation(String verificationOfFieldLevelValidation) {
	reportHelper.ChildTest("verification Of Field Level Validation");

//	autoPicking.loadPropertyFile(verificationOfFieldLevelValidation);
	
}
@Parameters({"verificationOfValidationMessageinCaseOfInvalidCustomer"})
@Test(priority=2)
public void verificationOfValidationMessageinCaseOfInvalidCustomer(String verificationOfValidationMessageinCaseOfInvalidCustomer) {
	reportHelper.ChildTest("verification Of Validation Message in Case Of InvalidCustomer");
	
	//autoPicking.loadPropertyFile(verificationOfValidationMessageinCaseOfInvalidCustomer);
	
}
@Parameters({"comparisonCustomerInfoWithDb"})
@Test(priority=3)
public void comparisonCustomerInfoWithDb(String comparisonCustomerInfoWithDb) {
	reportHelper.ChildTest("comparison between CustomerInfo and Db");
	
	//autoPicking.loadPropertyFile(comparisonCustomerInfoWithDb);
	
}
@Parameters({"verificationOfAccountNoInSIScreen"})
@Test(priority=4)
public void verificationOfAccountNoInSIScreen(String verificationOfAccountNoInSIScreen) {
	reportHelper.ChildTest("verification Of AccountNo In SI Screen");
	
	//autoPicking.loadPropertyFile(verificationOfAccountNoInSIScreen);
	
}
@Parameters({"endToEndFlowWithValidCustomer"})
@Test(priority=5)
public void endToEndFlowWithValidCustomer(String endToEndFlowWithValidCustomer) {
	reportHelper.ChildTest("endToEnd Flow With Valid Customer");
	
//	autoPicking.loadPropertyFile(endToEndFlowWithValidCustomer);
	
}
@Parameters({"VerificationOfDataInsertion"})
@Test(priority=6)
public void verificationOfDataInsertion(String VerificationOfDataInsertion) {
	reportHelper.ChildTest("Verification Of DataInsertion ");
	
	//autoPicking.loadPropertyFile(VerificationOfDataInsertion);
	
}
@Parameters({"verifyStatusBeforeAuth"})
@Test(priority=7)
public void verifyStatusBeforeAuth(String verifyStatusBeforeAuth) {
	reportHelper.ChildTest("verify Status Before Auth");
	
	//autoPicking.loadPropertyFile(verifyStatusBeforeAuth);
	
}
@Parameters({"verifyStatusAfterAuth"})
@Test(priority=8)
public void verifyStatusAfterAuth(String verifyStatusAfterAuth) {
	reportHelper.ChildTest("verify Status After Auth");
	
	//autoPicking.loadPropertyFile(verifyStatusAfterAuth);
	
}
@Parameters({"verifyStatusAfterRejection"})
@Test(priority=9)
public void verifyStatusAfteeRejection(String verifyStatusAfteeRejection) {
	reportHelper.ChildTest("verify Status After Rejection");
	
	//autoPicking.loadPropertyFile(verifyStatusAfteeRejection);
	
}
@Parameters({"verifyAccDetailsInAuthScreen"})
@Test(priority=10)
public void verifyAccDetailsInAuthScreen(String verifyAccDetailsInAuthScreen) {
	reportHelper.ChildTest("verify Acc Details In AuthScreen");
	
	//autoPicking.loadPropertyFile(verifyAccDetailsInAuthScreen);
	
}
}
