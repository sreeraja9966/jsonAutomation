package sprintOne;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.ReportHelper;
import testBase.TestBase;

public class TableTitleVerification extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	@Parameters({"locatorFile"})
	@Test (priority=1)
	public void postiveFlowVerification(String locatorFile ) {
		reportHelper.appendToExstingReport("Table Title Verification");
		reportHelper.ChildTest("Table Title Verification");
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
