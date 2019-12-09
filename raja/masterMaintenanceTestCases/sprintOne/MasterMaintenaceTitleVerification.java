package sprintOne;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.ReportHelper;
import helper.SeleniumHelper;
import testBase.TestBase;
public class MasterMaintenaceTitleVerification extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	@Test
	public void startReport() {
		strartBrowser("chrome");
		d.manage().window().maximize();
		d.get("http://172.16.0.39:91");
		reportHelper.setReportName("Master Maintenance Automation Testing");
		reportHelper.startTest("Master Maintenance title Verification");
		reportHelper.ChildTest("Title Verification");
		reportHelper.writeLogInfoInChildTest("URl opened");
		seleniumHelper.verifyTitle("Master Maintenance");
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
