  package withdrawalScreen;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testBase.TestBase;
import autoPick.AutoPicking;
import helper.ReportHelper;

public class PositiveFlow extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	@BeforeClass
	public void test() {
		/*strartBrowser("chrome");
		d.manage().window().maximize();
		d.get("http://172.16.0.150:4200/dashboard/withdrawal");*/
		reportHelper.appendToExstingReport("Withdrawal Screen Verification");
		reportHelper.ChildTest("login");
		reportHelper.writeLogInfoInChildTest("URl opened");
		reportHelper.endChild();
		reportHelper.appendChild();
	
			}
	@Parameters({"withdrawalScreenLocatorFile"})
	@Test
	public void postiveFlowVerification(String withdrawalScreenLocatorFile ) {
		reportHelper.ChildTest("Auto Upload debugging Scenario one");
		autoPicking.loadPropertyFile(withdrawalScreenLocatorFile);
		reportHelper.endChild();
		reportHelper.appendChild();
	}
	
	@AfterClass
	public void tearDown() {
		d.close();
		d.quit();
		
		reportHelper.endParent();
		reportHelper.writeLogToReport();
	}
}
