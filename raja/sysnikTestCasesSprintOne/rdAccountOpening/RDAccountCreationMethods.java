package rdAccountOpening;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.joda.time.LocalDate;
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

public class RDAccountCreationMethods extends TestBase {
	FindElement findElement = new FindElement();
	CacheHelper cacheHelper = new CacheHelper();
	ReportHelper reportHelper = new ReportHelper();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	AssertionHelper assertionHelper = new AssertionHelper();
	DropDownHelper  dropDownHelper  = new DropDownHelper ();
FilloExcelDataGetter  filloExcelDataGetter  = new FilloExcelDataGetter ();
	
public void RDAccountEntry(String excelQuery ) {
	dropDownHelper.SelectUsingVisibleTextWithExcelText("//select[@id='prdId']",excelQuery, "product");
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='customerNumber']",excelQuery,"custNumber");
	dropDownHelper.SelectUsingVisibleText("//select[@id='natureOfAcc']", "SELF");
	seleniumHelper.clickElement("//a[@id='depositDetails']");
	dropDownHelper.SelectUsingVisibleText("//select[@id='operatingInstruction']", "SELF");
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='installAmt']",excelQuery,"InstallmentAmount");
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='depPeriodMonths']",excelQuery,"periodInMonths");
	seleniumHelper.clickElement("//a[@id='notificationDetails']");
	dropDownHelper.SelectUsingIndex("//select[@id='existingMobile']",1);
	
}



public void verifyMaturityDateInRDAccountOpening(String excelQuery) {
	seleniumHelper.clickElement("//a[@id='personalInformation']");
	String noOfMonths=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "periodInMonths");
	String openingDate=seleniumHelper.getTextFromAnElement("//input[@id='accOpeningDate']");
	openingDate=openingDate.replace("/", "-");
	LocalDate date=LocalDate.parse(openingDate);
	date=date.plusMonths(Integer.parseInt(noOfMonths));
	seleniumHelper.clickElement("//a[@id='depositDetails']");
	assertionHelper.verifyCalendarText("//input[@id='matDate']",	date.toString().replace("-", "/"));
		
}

public void RDAuthUIVerifications(String excelQuery,Map<String,String>cacheMap,String RdAccCacheKey) {
	String custNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "custNumber");
	String accNumber=cacheHelper.getCache(RdAccCacheKey, cacheMap);
	String prdName=dropDownHelper.getSelectedValue("//select[@id='prdId']");
	String natureOfAcc=dropDownHelper.getSelectedValue("//select[@id='natureOfAcc']");
	
	
	dbHelper.compareResultUsingDb("SELECT prd_name FROM PRODUCT WHERE PRD_ID IN(SELECT PRD_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"')",prdName);
	dbHelper.verifyUiTextWithDb("//input[@id='custName']", "SELECT cust_name FROM cust_master WHERE cust_ID IN(SELECT cust_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"')");
	dbHelper.compareResultUsingDb("SELECT DESCRIPTION  FROM LOOKUP WHERE code IN (SELECT NATURE_OF_ACC FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"')",natureOfAcc);
	seleniumHelper.clickElement("//a[@id='depositDetails']");

	dbHelper.verifyUiTextWithDb("//input[@id='custCategory']", "SELECT DESCRIPTION  FROM LOOKUP WHERE code IN (SELECT CUST_CATEGORY  FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"')");
	String optrInstr=dropDownHelper.getSelectedValue("//select[@id='operatingInstruction']");
	dbHelper.compareResultUsingDb( "SELECT DESCRIPTION  FROM LOOKUP WHERE code IN (SELECT OPERATING_INSTR   FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"')",optrInstr);
	dbHelper.verifyUiAmountWithDb("//input[@id='depositAmount']", "SELECT INSTALL_AMT  FROM DEPOSIT_MASTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");  
	dbHelper.verifyUiTextWithDb("//input[@id='depPeriodMonths']", "SELECT DEP_PERIOD_MONTHS  FROM DEPOSIT_MASTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
	
	dbHelper.verifyUiDateWithDB("//input[@id='matDate']", "SELECT MATURITY_DT  FROM DEPOSIT_MASTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
	dbHelper.verifyUiAmountWithDb("//input[@id='matAmt']", "SELECT MAT_AMT  FROM DEPOSIT_MASTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
    seleniumHelper.clickElement("//a[@id='notificationDetails']");
    dbHelper.verifyUiTextWithDb("//input[@id='existingMobile']", "SELECT value FROM CUST_CONTACT WHERE CONTACT_TYPE ='mob' and  CUST_ID IN (SELECT CUST_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
}
}
