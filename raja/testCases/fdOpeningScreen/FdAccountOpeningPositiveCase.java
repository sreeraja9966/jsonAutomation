package fdOpeningScreen;
import org.testng.annotations.BeforeClass;

import autoPick.AutoPicking;
import helper.ReportHelper;
import testBase.TestBase;
public class FdAccountOpeningPositiveCase extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	@BeforeClass
	public void setUp() {
		strartBrowser("chrome");
		d.manage().window().maximize();
		d.get("http://172.16.0.137:4200/sample1");
		reportHelper.setReportName("Deposit screen Testing");
		reportHelper.startTest("Deposit Screen Verification");
		reportHelper.ChildTest("login");
		reportHelper.writeLogInfoInChildTest("URl opened");
		reportHelper.endChild();
		reportHelper.appendChild();
	}
	
	
	
}
