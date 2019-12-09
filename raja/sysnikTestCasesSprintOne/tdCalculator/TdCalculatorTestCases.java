package tdCalculator;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import autoPick.AutoPicking;
import helper.DbHelper;
import helper.FindElement;
import helper.ReadPropertyFile;
import helper.ReportHelper;
import helper.SeleniumHelper;
import helper.WebTableHelper;
import testBase.TestBase;

public class TdCalculatorTestCases extends TestBase{
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();
ReadPropertyFile readPropertyFile = new ReadPropertyFile();
	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("TD Calculator Verification");
		
	}

@AfterMethod
public void closingChildBranch() {
	reportHelper.endChild();
	reportHelper.appendChild();
}
@BeforeMethod
public void clickMenu() {
	
	}
@AfterClass
public void tearUp() {
	reportHelper.endParent();
	reportHelper.writeLogToReport();
	
}	

@Parameters({"MaturityAmountE2EFlow"})
@Test(priority=0)
public void MaturityAmountE2EFlow(String MaturityAmountE2EFlow) {
	reportHelper.ChildTest("Maturity Amount Calculation Verification");
	seleniumHelper.searchMenu("TD Interest Calculator");
	seleniumHelper.clickElement("//a[contains(text(),'TD Interest Calculator')]");
	
	autoPicking.loadPropertyFile(MaturityAmountE2EFlow);
			
}

@Parameters({"InterestRateE2EFlow"})
@Test(priority=1)
public void InterestRateE2EFlow(String InterestRateE2EFlow) {
	reportHelper.ChildTest("Interest Rate Calculation Verification");
	
	autoPicking.loadPropertyFile(InterestRateE2EFlow);
			
}
@Parameters({"InstallMentAmountE2eFlow"})
@Test(priority=2)
public void InstallmentAmountE2EFlow(String InstallMentAmountE2eFlow) {
	reportHelper.ChildTest("Installment Amount Calculation Verification");
	
	
	autoPicking.loadPropertyFile(InstallMentAmountE2eFlow);
			
}
@Parameters({"DepositPeriodE2EFlow"})
@Test(priority=3)
public void DepositPeriodE2EFlow(String DepositPeriodE2EFlow) {
	reportHelper.ChildTest("Deposit Period Calculation Verification");
	
	autoPicking.loadPropertyFile(DepositPeriodE2EFlow);
			
}



@Parameters({"MaturityAmountE2EFlowWithOutDiscount"})
@Test(priority=4)
public void MaturityAmountE2EFlowWithOutDiscount(String MaturityAmountE2EFlowWithOutDiscount) {
	reportHelper.ChildTest("Maturity Amount Calculation Verification  Without Discounting");
		
	autoPicking.loadPropertyFile(MaturityAmountE2EFlowWithOutDiscount);
			
}

@Parameters({"InterestRateE2EFlowWithOutDiscount"})
@Test(priority=5)
public void InterestRateE2EFlowWithOutDiscount(String InterestRateE2EFlowWithOutDiscount) {
	reportHelper.ChildTest("Interest Rate Calculation Verification WithOut Discount");
	
	autoPicking.loadPropertyFile(InterestRateE2EFlowWithOutDiscount);
			
}
@Parameters({"InstallMentAmountE2eFlowWithOutDiscount"})
@Test(priority=6)
public void InstallMentAmountE2eFlowWithOutDiscount(String InstallMentAmountE2eFlowWithOutDiscount) {
	reportHelper.ChildTest("Installment Amount Calculation Verification wihtout discount");
	
	autoPicking.loadPropertyFile(InstallMentAmountE2eFlowWithOutDiscount);
			
}
@Parameters({"DepositPeriodE2EFlowwithoutDiscount"})
@Test(priority=7)
public void DepositPeriodE2EFlowwithoutDiscount(String DepositPeriodE2EFlowwithoutDiscount) {
	reportHelper.ChildTest("Deposit Period Calculation Verification without discount");
	
	autoPicking.loadPropertyFile(DepositPeriodE2EFlowwithoutDiscount);
			
}


@Parameters({"MaturityAmountE2EFlowCompoundQuaterly"})
@Test(priority=8)
public void MaturityAmountE2EFlowCompoundQuaterly(String MaturityAmountE2EFlowCompoundQuaterly) {
	reportHelper.ChildTest("Maturity Amount Calculation Verification Compound Quaterly");
		
	autoPicking.loadPropertyFile(MaturityAmountE2EFlowCompoundQuaterly);
			
}

@Parameters({"InterestRateE2EFlowCompoundQuaterly"})
@Test(priority=9)
public void InterestRateE2EFlowCompoundQuaterly(String InterestRateE2EFlowCompoundQuaterly) {
	reportHelper.ChildTest("Interest Rate Calculation Verification Compound Quaterly");
	
	autoPicking.loadPropertyFile(InterestRateE2EFlowCompoundQuaterly);
			
}
@Parameters({"InstallMentAmountE2eFlowCompoundQuaterly"})
@Test(priority=10)
public void InstallMentAmountE2eCompoundQuaterly(String InstallMentAmountE2eCompoundQuaterly) {
	reportHelper.ChildTest("Installment Amount Calculation Verification Compound Quaterly");
	
	autoPicking.loadPropertyFile(InstallMentAmountE2eCompoundQuaterly);
			
}
@Parameters({"DepositPeriodE2EFlowCompoundQuaterly"})
@Test(priority=11)
public void DepositPeriodE2EFlowCompoundQuaterly(String DepositPeriodE2EFlowCompoundQuaterly) {
	reportHelper.ChildTest("Deposit Period Calculation Verification Compound Quaterly");
	
	autoPicking.loadPropertyFile(DepositPeriodE2EFlowCompoundQuaterly);
			
}

}
