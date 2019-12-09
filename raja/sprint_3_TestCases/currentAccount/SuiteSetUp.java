package currentAccount;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import helper.EmailHelper;
import helper.ReportHelper;
import testBase.TestBase;

public class SuiteSetUp extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	EmailHelper emailHelper = new EmailHelper();
@BeforeSuite
public void setReport() {
	reportHelper.setReportName("Sprint 3 TestCases");
	
}

@AfterSuite
public void tearDownThings() {
	//emailHelper.sendReport("sriraja.garlapati@sysnik.com", "Sprint 3 TestCases", "Sprint 3 Automation Report");
}
}
