package denominationVerification;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import autoPick.AutoPicking;
import helper.DbHelper;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import helper.WebTableHelper;
import testBase.TestBase;

public class DenominationTestCases extends TestBase{

	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	ReportHelper reportHelper = new ReportHelper();
	AutoPicking autoPicking = new AutoPicking();
	WebTableHelper webTableHelper = new WebTableHelper();
	FindElement findElement = new FindElement();
DenominationVerificationMethods denominationVerificationMethods = new DenominationVerificationMethods();
	@BeforeTest
	public void setUp() {
		
		reportHelper.appendToExstingReport("Denomination Verification");
		
	}

@AfterMethod
public void closingChildBranch() {
	reportHelper.endChild();
	reportHelper.appendChild();
}
@BeforeMethod
public void clickMenu() {
	
	seleniumHelper.clickElement("//a[@id='calculator']");
	seleniumHelper.clickElement("//a[contains(text(),'TD Interest Calculator')]");
	seleniumHelper.clickElement("//a[@id='cash_handling']");
	seleniumHelper.clickElement("//a[contains(text(),'Cash Denomination')]");
	boolean condition=findElement.isElementPresent(By.xpath("//input[@id='fromUserId']"));
	
	if(!condition) {
		seleniumHelper.clickElement("//a[contains(text(),'Cash Denomination')]");
	}
	}
@AfterClass
public void tearUp() {
	reportHelper.endParent();
	reportHelper.writeLogToReport();
	
}	
	
	@Parameters({"userDenomAmountTotalVerification"})
	@Test(priority=0)
	public void userWiseDenomVerification(String userDenomAmountTotalVerification) {
		reportHelper.ChildTest("userDenomAmountTotalVerification");
		
		autoPicking.loadPropertyFile(userDenomAmountTotalVerification);
				
	}
	@Parameters({"userDenomNoOfNotesVerification"})
	@Test(priority=1)
	public void userDenomNoOfNotesVerification(String userDenomNoOfNotesVerification) {
		reportHelper.ChildTest("userDenomNoOfNotesVerification");
		
		autoPicking.loadPropertyFile(userDenomNoOfNotesVerification);
				
	}
	
	@Parameters({"userDenomTransferAmountVerification"})
	@Test(priority=2)
	public void userDenomTransferAmountVerification(String userDenomTransferAmountVerification) {
		reportHelper.ChildTest("userDenomTransferAmountVerification");
		
		autoPicking.loadPropertyFile(userDenomTransferAmountVerification);
				
	}
	
}
