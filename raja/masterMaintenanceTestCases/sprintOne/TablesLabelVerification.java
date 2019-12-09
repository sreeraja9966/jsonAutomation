package sprintOne;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.ReportHelper;
import testBase.TestBase;

public class TablesLabelVerification extends TestBase{

	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	@Parameters({"branchMasterlocatorFile"})
	@Test (priority=1)
	public void branchMaster(String branchMasterlocatorFile ) {
		reportHelper.appendToExstingReport("Tables Label Verification");
		reportHelper.ChildTest("BranchMaster label Verification");
		autoPicking.loadPropertyFile(branchMasterlocatorFile);
		reportHelper.endChild();
		reportHelper.appendChild();
	}
	@Parameters({"BookingslocatorFile"})
	@Test (priority=2)
	public void bookings(String BookingslocatorFile ) {
		reportHelper.ChildTest("bookings label Verification");
		autoPicking.loadPropertyFile(BookingslocatorFile);
		reportHelper.endChild();
		reportHelper.appendChild();
	}
	@Parameters({"sbBranchDef"})
	@Test (priority=3)
	public void sbBranchDef(String sbBranchDef ) {
		reportHelper.ChildTest("sbBranchDef label Verification");
		autoPicking.loadPropertyFile(sbBranchDef);
		reportHelper.endChild();
		reportHelper.appendChild();
	}
	@Parameters({"gLAccount"})
	@Test (priority=4)
	public void gLAccount(String gLAccount ) {
		reportHelper.ChildTest("gLAccount label Verification");
		autoPicking.loadPropertyFile(gLAccount);
		reportHelper.endChild();
		reportHelper.appendChild();
	}
	@Parameters({"users"})
	@Test (priority=5)
	public void users(String users ) {
		reportHelper.ChildTest("users label Verification");
		autoPicking.loadPropertyFile(users);
		reportHelper.endChild();
		reportHelper.appendChild();
	}
	@Parameters({"BANKPARAMETERS"})
	@Test (priority=6)
	public void BANKPARAMETERS(String BANKPARAMETERS ) {
		reportHelper.ChildTest("BANKPARAMETERS label Verification");
		autoPicking.loadPropertyFile(BANKPARAMETERS);
		reportHelper.endChild();
		reportHelper.appendChild();
	}
	
	@Parameters({"Customer"})
	@Test (priority=7)
	public void Customer(String Customer ) {
		reportHelper.ChildTest("Customer label Verification");
		autoPicking.loadPropertyFile(Customer);
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
