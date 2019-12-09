package lienNoting;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.openqa.selenium.By;

import helper.AssertionHelper;
import helper.DbHelper;
import helper.FilloExcelDataGetter;
import helper.FindElement;
import helper.SeleniumHelper;
import testBase.TestBase;

public class LienNotingMethods extends TestBase{
FilloExcelDataGetter filloExcelDataGetter = new FilloExcelDataGetter();
	DbHelper dbHelper = new DbHelper();
	AssertionHelper  assertionHelper  = new AssertionHelper ();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
FindElement  findElement  = new FindElement ();
	public void lienAmountVerification(String xpath,String depAccNumberExcelQuery) {
		//1012101510000107
		/*String productId=depAccNumber.substring(7, 9);
		log.info(productId+"-------------------->productId");*/
		String depAccNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(depAccNumberExcelQuery, "DepositAccountNumber");
		String margin_percentage=dbHelper.connectToDb("SELECT MARGIN_PER FROM BANKPARAMETERS  WHERE BANK_CODE =101");
		String availableAmount=dbHelper.connectToDb("SELECT AVAILABLE_BAL  FROM ACCOUNT_MASTER where acc_no='"+depAccNumber+"'");
		log.info(availableAmount+"availableAmount as String");
		String lienAmount;
		String presentLienAmount;
		try {
			lienAmount = dbHelper.connectToDb("SELECT sum(lien_amt) FROM LIEN_EARMARK WHERE STATUS in('A','E') and DEPOSIT_ACC_ID in (select acc_id from account_master where acc_no='"+depAccNumber+"')");
		if(lienAmount==null) {
			lienAmount ="0.00";
			log.info(lienAmount+"@##@#@#@#@@#@#@#@#");
		}
		} catch (Exception e) {
			e.printStackTrace();
			lienAmount ="0.00";
			log.info(lienAmount+"@##@#@#@#@@#@#@#@#");
		}
		
		try {
			presentLienAmount = dbHelper.connectToDb("SELECT sum(lien_amt) FROM LIEN_EARMARK WHERE STATUS in('A','E') and DEPOSIT_ACC_ID in (select acc_id from account_master where acc_no='"+depAccNumber+"')");
		if(presentLienAmount==null) {
			presentLienAmount ="0.00";
			log.info(presentLienAmount+"@##@#@#@#@@#@#@#@#");
		}
		} 
		catch (Exception e) {
			e.printStackTrace();
			presentLienAmount ="0.00";
			log.info(presentLienAmount+"@##@#@#@#@@#@#@#@#");
		}
		
		BigDecimal availableAmountInInt=new BigDecimal(availableAmount);
		
		BigDecimal margin_percentageInInt=new BigDecimal(margin_percentage);
		BigDecimal lienAmountInInt=new BigDecimal(lienAmount);
		BigDecimal presentLienAmountInInt=new BigDecimal(presentLienAmount);
		BigDecimal percentage=new BigDecimal(0.01);
		log.info(availableAmountInInt+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		log.info(margin_percentageInInt+"#######################################");
		log.info(lienAmountInInt+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	
	
		BigDecimal balanceAmount= availableAmountInInt.subtract((availableAmountInInt.multiply((margin_percentageInInt.multiply(percentage))))).subtract(lienAmountInInt).setScale(2, RoundingMode.HALF_UP);
	
	String actualText=seleniumHelper.getTextFromAnElement(xpath);
	String  presentLienAmountText=seleniumHelper.getTextFromAnElement("//input[@id='presentLienAmount']");
	log.info(balanceAmount+"*&%*&^%$^%$&^%*&");
	assertionHelper.verifyTwoAmounts(actualText, balanceAmount.toString());	
	assertionHelper.verifyTwoAmounts(presentLienAmountText, presentLienAmountInInt.toString());	
	
	}
	
	public void customerInfoInLien(String xpath,String Excelquery) {
		String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(Excelquery, "LoanAccountNumber");
		dbHelper.verifyUiTextWithDb(xpath, "SELECT CUST_NAME FROM CUST_MASTER WHERE CUST_ID in (select cust_id from account_master where acc_no='"+accNo+"')");
	}
	public void depositAccValidationInLien(String xpath,String Excelquery) {
		String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(Excelquery, "DepositAccountNumber");
		
		dbHelper.verifyUiAmountWithDb(xpath, "SELECT AVAILABLE_BAL  FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"'");
		
	}
	public void lienNotingEntry(String excelQuery) {
		try {
			String loanAccNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "LoanAccountNumber");
			
			String depAccNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "DepositAccountNumber");
			String lienAmount=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "Amount");
			seleniumHelper.enterText("//input[@id='loanAccountNumber']", loanAccNumber);
			seleniumHelper.clickElement("//input[@id='customerName']");
			Thread.sleep(500);
			seleniumHelper.enterText("//input[@id='depositeAccountNumber']", depAccNumber);
			seleniumHelper.clickElement("//input[@id='customerName']");
			Thread.sleep(500);
			lienAmountVerification("//input[@id='lienAmount']", excelQuery);
			seleniumHelper.enterText("//input[@id='lienAmount']", lienAmount);
			seleniumHelper.enterText("//input[@id='remark']", "Automation");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public void tableVerificationOnSubmitInLienNoting(String excelQuery) {
	String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "LoanAccountNumber");
	String LienAmount=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "Amount");
	dbHelper.compareResultUsingDb("SELECT status FROM LIEN_EARMARK WHERE LOAN_ACC_ID IN (SELECT acc_id FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') AND STATUS ='E'", "E");
	dbHelper.compareResultUsingDb("SELECT LIEN_AMT FROM LIEN_EARMARK WHERE LOAN_ACC_ID IN (SELECT acc_id FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') AND STATUS ='E'", LienAmount);
}

public void lienNotingAuthScreenVerifications(String tableName,String excelQuery) {
	try {
		String loanAccNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "LoanAccountNumber");
		
		seleniumHelper.enterText("//input[@id='loanAccountNumber']", loanAccNo);
		Thread.sleep(3000);
		seleniumHelper.clickElement("//input[@id='customerName']");
		dbHelper.verifyUiTextWithDb("//input[@id='customerName']", "SELECT CUST_NAME FROM CUST_MASTER WHERE CUST_ID in (select cust_id from account_master where acc_no='"+loanAccNo+"')");
		String depAccNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "DepositAccountNumber");
		assertionHelper.verifyTextEquals("//div[@id='"+tableName+"']/table/tbody/tr/td[1]", depAccNo);
		dbHelper.verifyUiAmountWithDb("//div[@id='"+tableName+"']/table/tbody/tr/td[2]", "SELECT AVAILABLE_BAL  FROM ACCOUNT_MASTER WHERE ACC_NO='"+depAccNo+"'");
		dbHelper.verifyUiAmountWithDb("//div[@id='"+tableName+"']/table/tbody/tr/td[4]", "SELECT lien_amt FROM LIEN_EARMARK WHERE LOAN_ACC_ID IN (SELECT acc_id FROM ACCOUNT_MASTER WHERE ACC_NO='"+loanAccNo+"') ");
		dbHelper.verifyUiAmountWithDb("//div[@id='"+tableName+"']/table/tbody/tr/td[3]", "SELECT sum(lien_amt) FROM LIEN_EARMARK WHERE STATUS in('E','A') and LOAN_ACC_ID IN (SELECT acc_id FROM ACCOUNT_MASTER WHERE ACC_NO='"+loanAccNo+"') ");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void tableVerificationOnSunbitInLienAuth(String excelQuery,String expectedStatus) {
	String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "LoanAccountNumber");
	dbHelper.compareResultUsingDb("SELECT status FROM LIEN_EARMARK WHERE LOAN_ACC_ID IN (SELECT acc_id FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') ", expectedStatus);

}

}
