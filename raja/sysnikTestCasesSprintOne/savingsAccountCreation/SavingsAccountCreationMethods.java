package savingsAccountCreation;

import java.util.Map;

import org.openqa.selenium.By;

import helper.AssertionHelper;
import helper.CacheHelper;
import helper.DbHelper;
import helper.DropDownHelper;
import helper.FilloExcelDataGetter;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import testBase.TestBase;

public class SavingsAccountCreationMethods extends TestBase {
	FindElement findElement = new FindElement();
	CacheHelper cacheHelper = new CacheHelper();
	ReportHelper reportHelper = new ReportHelper();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	AssertionHelper assertionHelper = new AssertionHelper();
	DropDownHelper  dropDownHelper  = new DropDownHelper ();
FilloExcelDataGetter  filloExcelDataGetter  = new FilloExcelDataGetter ();
	public void getSavingsAccountNumberModelResponse(String Xpath,Map<String,String>cacheMap) {
		String textInModel=seleniumHelper.getTextFromAnElement("//h6[@id='modale']");
		String accInModel=seleniumHelper.getOnlyNumbersFromAnElement(Xpath);
		if(!textInModel.contains("Record inserted successfully")) {
			reportHelper.writeLogInCaseOfFailInChildTest("Savings Account creation is failed With reason "+textInModel);
			reportHelper.addScreenShotInCaseOfFailInReportInChild("Reason for Savings Account creation failure is "+textInModel);
		}
		else {
			cacheHelper.setCacheWithaString("savingsAccountNumber", accInModel,cacheMap);	
			reportHelper.writeLogInCaseOfPassInChildTest("Savings Account creation is sucessful with account no "
			+cacheHelper.getCache("savingsAccountNumber", cacheMap));
			
		}
	}
	
	public void tableVerificationOfSBAccountOnSubmit(String expectedStatus,Map<String,String>cacheMap) {
		String accNumber=cacheHelper.getCache("savingsAccountNumber", cacheMap);
		dbHelper.compareResultUsingDb("select status from account_master where acc_no='"+accNumber+"'", expectedStatus);
	}
	
	public void accountMasterAndUIVerification(String excelQuery,Map<String,String>cacheMap) {
		
		String accNumber=cacheHelper.getCache("savingsAccountNumber", cacheMap);
		String custNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "custNumber");
		dbHelper.verifyUiTextWithDb("//input[@id='prdId']", "SELECT prd_name FROM PRODUCT WHERE PRD_ID IN(SELECT PRD_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"')");
		dbHelper.verifyUiTextWithDb("//input[@id='custId']", "SELECT cust_name FROM cust_master WHERE cust_ID IN(SELECT cust_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"')");
		String natureOfacc=dropDownHelper.getSelectedValue("//select[@id='natureOfAcc']");
		dbHelper.compareResultUsingDb("SELECT DESCRIPTION  FROM LOOKUP WHERE code IN (SELECT NATURE_OF_ACC FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"')",natureOfacc);
	    dbHelper.verifyUiDateWithDB("//input[@id='accOpeningDate']", "SELECT OPEN_DT  FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"'");
	    seleniumHelper.clickElement("//a[@id='openingdetails']");
	    dbHelper.verifyUiTextWithDb("//input[@id='custCategory']", "SELECT DESCRIPTION  FROM LOOKUP WHERE code IN (SELECT CUST_CATEGORY  FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"')");
	    dbHelper.verifyUiTextWithDb("//input[@id='operatingInstruction']", "SELECT DESCRIPTION  FROM LOOKUP WHERE code IN (SELECT OPERATING_INSTR   FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"')");
	    seleniumHelper.clickElement("//a[@id='notificationDetails']");
	    dbHelper.verifyUiTextWithDb("//input[@id='existingEmail']", "SELECT value FROM CUST_CONTACT WHERE CONTACT_TYPE ='email' and  CUST_ID IN (SELECT CUST_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
	    dbHelper.verifyUiTextWithDb("//input[@id='existingMobile']", "SELECT value FROM CUST_CONTACT WHERE CONTACT_TYPE ='mob' and  CUST_ID IN (SELECT CUST_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
	
		
	}
	
	
	
	
	
	
	
	
	
}
