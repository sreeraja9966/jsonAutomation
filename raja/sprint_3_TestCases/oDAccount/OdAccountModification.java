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

public class OdAccountModification extends TestBase {
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	@BeforeClass
	public void setUp() {
		
		reportHelper.startTest("OD Account Modification");
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
	reportHelper.writeLogInfoInChildTest("Label Verification");
//	/autoPicking.loadPropertyFile(labelVerificationFile);
	
}

@Parameters({"verificationOfDataWithDB"})
@Test(priority=1)
public void verificationOfDataWithDB(String verificationOfDataWithDB) {
	reportHelper.ChildTest("verification Of Data With DB");
	reportHelper.writeLogInfoInChildTest("verification Of Data With DB");
	//autoPicking.loadPropertyFile(verificationOfDataWithDB);
	
}

@Parameters({"verificationOfModifiedDataInsertionInDB"})
@Test(priority=2)
public void verificationOfModifiedDataInsertionInDB(String verificationOfModifiedDataInsertionInDB) {
	reportHelper.ChildTest("verification Of ModifiedData Insertion In DB");
	reportHelper.writeLogInfoInChildTest("verificationOfModifiedDataInsertionInDB");
	//autoPicking.loadPropertyFile(verificationOfModifiedDataInsertionInDB);
	
}
@Parameters({"flowWithInvalidAccountNum"})
@Test(priority=3)
public void flowWithInvalidAccountNum(String flowWithInvalidAccountNum) {
	reportHelper.ChildTest("flow With Invalid AccountNum");
	reportHelper.writeLogInfoInChildTest("verification Of ModifiedData Insertion In DB");
	//autoPicking.loadPropertyFile(flowWithInvalidAccountNum);
	
}

@Parameters({"verificationOfFieldLevelValidation"})
@Test(priority=4)
public void verificationOfFieldLevelValidation(String verificationOfFieldLevelValidation) {
	reportHelper.ChildTest("verification Of Field Level Validation");
	reportHelper.writeLogInfoInChildTest("verification Of Field Level Validation");
	//autoPicking.loadPropertyFile(verificationOfFieldLevelValidation);
	
}

@Parameters({"verifyStatusOfaModifiedAccount"})
@Test(priority=5)
public void verifyStatusOfaModifiedAccount(String verifyStatusOfaModifiedAccount) {
	reportHelper.ChildTest("verify Status Of a Modified Account Before Authorisation");
	reportHelper.writeLogInfoInChildTest("verify Status Of a Modified Account Before Authorisation");
	//autoPicking.loadPropertyFile(verifyStatusOfaModifiedAccount);

}
@Parameters({"verifyStatusOfaModifiedAccountAfterAuth"})
@Test(priority=6)
public void verifyStatusOfaModifiedAccountAfterAuth(String verifyStatusOfaModifiedAccountAfterAuth) {
	reportHelper.ChildTest("verify Status Of a Modified Account After Authorisation");
	reportHelper.writeLogInfoInChildTest("verify Status Of a Modified Account After Authorisation");
	//autoPicking.loadPropertyFile(verifyStatusOfaModifiedAccountAfterAuth);
}

@Parameters({"verifyStatusOfaModifiedAccountAfterRejection"})
@Test(priority=7)
public void verifyStatusOfaModifiedAccountAfterRejection(String verifyStatusOfaModifiedAccountAfterRejection) {
	reportHelper.ChildTest("verify Status Of a Modified Account After Rejection");
	reportHelper.writeLogInfoInChildTest("verify Status Of a Modified Account After Rejection");
	//autoPicking.loadPropertyFile(verifyStatusOfaModifiedAccountAfterRejection);
	
}

@Parameters({"endToEndAuthScenarion"})
@Test(priority=8)
public void endToEndAuthScenarion(String endToEndAuthScenarion) {
	reportHelper.ChildTest("end To End Modification AuthScenario");
	reportHelper.writeLogInfoInChildTest("end To End Modification AuthScenario");
	//autoPicking.loadPropertyFile(endToEndAuthScenarion);
	
}

@Parameters({"endToEndRejectionScenarion"})
@Test(priority=9)
public void endToEndRejectionScenarion(String endToEndRejectionScenarion) {
	reportHelper.ChildTest("end To End Modification Rejection Scenario");
	reportHelper.writeLogInfoInChildTest("end To End Modification Rejection Scenario");
	//autoPicking.loadPropertyFile(endToEndRejectionScenarion);
	
}







}
