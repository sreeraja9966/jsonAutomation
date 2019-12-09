package oDAccount;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.ReportHelper;
import testBase.TestBase;

public class OdAccountCreation extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	@BeforeClass
	public void setUp() {
		
		reportHelper.appendToExstingReport("Current Account Opening");
		strartBrowser("chrome");
	}
@BeforeMethod
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

//autoPicking.loadPropertyFile(labelVerificationFile);
	
}
@Parameters({"openingDateVerification"})
@Test(priority=1)
public void openingDateVerification(String openingDateVerification) {
	reportHelper.ChildTest("opening Date Verification");

//autoPicking.loadPropertyFile(labelVerificationFile);
	
}

@Parameters({"ROIVerification"})
@Test(priority=2)
public void ROIVerification(String ROIVerification) {
	reportHelper.ChildTest("ROI Verification");

//autoPicking.loadPropertyFile(labelVerificationFile);
	
}
@Parameters({"e2eFlowForSingle"})
@Test(priority=3)
public void e2eFlowForSingle(String e2eFlowForSingle) {
	reportHelper.ChildTest("e2e Flow For Single account type");

//autoPicking.loadPropertyFile(labelVerificationFile);
	
}

@Parameters({"e2eFlowForJoint"})
@Test(priority=4)
public void e2eFlowForJoint(String e2eFlowForJoint) {
	reportHelper.ChildTest("e2e Flow For Single account joint");

//autoPicking.loadPropertyFile(labelVerificationFile);
	
}

@Parameters({"verificationOfFieldLevelValidation"})
@Test(priority=5)
public void verificationOfFieldLevelValidation(String verificationOfFieldLevelValidation) {
	reportHelper.ChildTest("verification Of Field Level Validation");

//autoPicking.loadPropertyFile(labelVerificationFile);
	
}
@Parameters({"endToEndFlowWithValidCustomer"})
@Test(priority=5)
public void endToEndFlowWithValidCustomer(String endToEndFlowWithValidCustomer) {
	reportHelper.ChildTest("endToEnd Flow With Valid Customer");
	
//	autoPicking.loadPropertyFile(endToEndFlowWithValidCustomer);
	
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
