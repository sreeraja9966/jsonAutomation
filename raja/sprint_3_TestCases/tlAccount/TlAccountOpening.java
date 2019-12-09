package tlAccount;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.ReportHelper;
import testBase.TestBase;

public class TlAccountOpening extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	@BeforeClass
	public void setUp() {
		
		reportHelper.appendToExstingReport("TL Account Opening");
		strartBrowser("chrome");
	}
@BeforeMethod
public void setNavigation() {
	d.navigate().to("http://172.16.0.189:4200/dynamic-form");
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
	
}
@Parameters({"labelVerificationFile"})
@Test(priority=0)
public void labelVerification(String labelVerificationFile) {
	reportHelper.ChildTest("Label Verification");

autoPicking.loadPropertyFile(labelVerificationFile);
	
}

@Parameters({"e2eFlow"})
@Test(priority=0)
public void e2eFlow(String e2eFlow) {
	reportHelper.ChildTest("e2e Flow");

autoPicking.loadPropertyFile(e2eFlow);
	
}


}
