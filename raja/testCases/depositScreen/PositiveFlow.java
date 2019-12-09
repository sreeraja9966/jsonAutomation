package depositScreen;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.ReportHelper;
import testBase.TestBase;
public class PositiveFlow extends TestBase {
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	@BeforeClass
	public void test() {
		strartBrowser("chrome");
		d.manage().window().maximize();
		d.get("http://172.16.0.150:4200/dashboard/withdrawal");
		reportHelper.setReportName("Deposit screen Testing");
		reportHelper.startTest("Deposit Screen Verification");
		reportHelper.ChildTest("login");
		reportHelper.writeLogInfoInChildTest("URl opened");
		reportHelper.endChild();
		reportHelper.appendChild();
	
			}
	@Parameters({"locatorFile"})
	@Test (priority=1)
	public void postiveFlowVerification(String locatorFile ) {
		
		reportHelper.ChildTest("Auto Upload debugging Scenario one");
		autoPicking.loadPropertyFile(locatorFile);
		reportHelper.endChild();
		reportHelper.appendChild();
	}
	
	
	@AfterClass
	
	public void tearDown() {
		reportHelper.endParent();
		reportHelper.writeLogToReport();
		/*d.close();
		d.quit();
		*/
		
	}
	
}
