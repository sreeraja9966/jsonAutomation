package tdCalculator;

import helper.AssertionHelper;
import helper.ReadPropertyFile;
import helper.SeleniumHelper;
import testBase.TestBase;

public class TdCalculatorMethods extends TestBase {
SeleniumHelper seleniumHelper = new SeleniumHelper();
ReadPropertyFile  readPropertyFile  = new ReadPropertyFile ();	
AssertionHelper assertionHelper = new AssertionHelper();
	public void commonSimpleFDCalculator(String queryOn,String filePath) {
		
		try {
			if("Interest Rate".trim().equalsIgnoreCase(queryOn.trim())) {
				seleniumHelper.enterText("//input[@id='installAmt']", readPropertyFile.getValueFromPropertyFile(filePath, "installAmt"));
				seleniumHelper.enterText("//input[@id='depPeriodMonths']", readPropertyFile.getValueFromPropertyFile(filePath, "depPeriodMonths"));
				seleniumHelper.enterText("//input[@id='depPeriodDays']", readPropertyFile.getValueFromPropertyFile(filePath, "depPeriodDays"));
				seleniumHelper.enterText("//input[@id='maturityAmount']", readPropertyFile.getValueFromPropertyFile(filePath, "maturityAmount"));
				seleniumHelper.clickElement("//input[@name='interestCalculate']");
				Thread.sleep(1500);
				assertionHelper.verifyAmountEquals("//input[@id='result']", readPropertyFile.getValueFromPropertyFile(filePath, "intRate"));
			}
			else if("Installment Amount".trim().equalsIgnoreCase(queryOn.trim())) {
				seleniumHelper.enterText("//input[@id='intRate']", readPropertyFile.getValueFromPropertyFile(filePath, "intRate"));
				seleniumHelper.enterText("//input[@id='depPeriodMonths']", readPropertyFile.getValueFromPropertyFile(filePath, "depPeriodMonths"));
				seleniumHelper.enterText("//input[@id='depPeriodDays']", readPropertyFile.getValueFromPropertyFile(filePath, "depPeriodDays"));
				seleniumHelper.enterText("//input[@id='maturityAmount']", readPropertyFile.getValueFromPropertyFile(filePath, "maturityAmount"));
				seleniumHelper.clickElement("//input[@name='interestCalculate']");
				Thread.sleep(1500);
				assertionHelper.verifyAmountEquals("//input[@id='result']", readPropertyFile.getValueFromPropertyFile(filePath, "installAmt"));
			}
			else if("Deposit Period".trim().equalsIgnoreCase(queryOn.trim())) {
				seleniumHelper.enterText("//input[@id='intRate']", readPropertyFile.getValueFromPropertyFile(filePath, "intRate"));
				seleniumHelper.enterText("//input[@id='installAmt']", readPropertyFile.getValueFromPropertyFile(filePath, "installAmt"));
				seleniumHelper.enterText("//input[@id='maturityAmount']", readPropertyFile.getValueFromPropertyFile(filePath, "maturityAmount"));
				seleniumHelper.clickElement("//input[@name='interestCalculate']");
				Thread.sleep(1500);
				assertionHelper.verifyTextEquals("//input[@id='result']", readPropertyFile.getValueFromPropertyFile(filePath, "depPeriodMonths")+
						" Months "+readPropertyFile.getValueFromPropertyFile(filePath, "depPeriodDays")+"Days");
			}
			else if("Maturity Amount".trim().equalsIgnoreCase(queryOn.trim())) {
				seleniumHelper.enterText("//input[@id='intRate']", readPropertyFile.getValueFromPropertyFile(filePath, "intRate"));
				seleniumHelper.enterText("//input[@id='installAmt']", readPropertyFile.getValueFromPropertyFile(filePath, "installAmt"));
				seleniumHelper.enterText("//input[@id='depPeriodMonths']", readPropertyFile.getValueFromPropertyFile(filePath, "depPeriodMonths"));
				seleniumHelper.enterText("//input[@id='depPeriodDays']", readPropertyFile.getValueFromPropertyFile(filePath, "depPeriodDays"));
				seleniumHelper.clickElement("//input[@name='interestCalculate']");
				Thread.sleep(1500);
				assertionHelper.verifyAmountEquals("//input[@id='result']", readPropertyFile.getValueFromPropertyFile(filePath, "maturityAmount"));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
