package FDAccountOpening;

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

public class FDAccountCreationMethods extends TestBase {
	FindElement findElement = new FindElement();
	CacheHelper cacheHelper = new CacheHelper();
	ReportHelper reportHelper = new ReportHelper();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	AssertionHelper assertionHelper = new AssertionHelper();
	DropDownHelper  dropDownHelper  = new DropDownHelper ();
FilloExcelDataGetter  filloExcelDataGetter  = new FilloExcelDataGetter ();
	
public void entryFDCreation(String excelQuery) {
	dropDownHelper.SelectUsingVisibleTextWithExcelText("//select[@id='prdId']",excelQuery, "product");
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='customerNumber']",excelQuery,"custNumber");
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='introducerNumber']",excelQuery,"instroducerCustId");
	dropDownHelper.SelectUsingVisibleText("//select[@id='natureOfAcc']", "SELF");
	seleniumHelper.clickElement("//a[@id='depositDetails']");
	dropDownHelper.SelectUsingVisibleText("//select[@id='operatingInstruction']", "SELF");
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='installAmt']",excelQuery,"InstallmentAmount");
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='depPeriodMonths']",excelQuery,"periodInMonths");
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='depPeriodDays']",excelQuery,"periodInDays");
	dropDownHelper.SelectUsingVisibleText("//select[@id='intCalcType']", "Account wise");
	dropDownHelper.SelectUsingVisibleTextWithExcelText("//select[@id='intPaymentFreq']",excelQuery, "intPaymentFrequency");
	seleniumHelper.clickElement("//a[@id='renewalPolicy']");
	
}

public void verifyMaturityDate(String excelQuery) {
	seleniumHelper.clickElement("//a[@id='personalInformation']");
	String noOfMonths=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "periodInMonths");
	String noOfDays=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "periodInDays");
	String openingDate=seleniumHelper.getTextFromAnElement("//input[@id='accOpeningDate']");
	openingDate=openingDate.replace("/", "-");
	LocalDate date=LocalDate.parse(openingDate);
	date=date.plusMonths(Integer.parseInt(noOfMonths));
	date=date.plusDays(Integer.parseInt(noOfDays));
	seleniumHelper.clickElement("//a[@id='depositDetails']");
	assertionHelper.verifyCalendarText("//input[@id='matDate']",	date.toString().replace("-", "/"));
		
}
	
public void maturityAmountVerificationForSimpleInt(String excelQuery) {
	String principalAmount=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "InstallmentAmount");
	String intPaymentFreq=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "intPaymentFrequency");
	String depositPeriodInMonths=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "periodInMonths");
	String depositPeriodInDays=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "periodInDays");
	String intRate=seleniumHelper.getTextFromAnElement("//input[@id='intRate']");
	BigDecimal principalAmountInInteger=new BigDecimal(principalAmount);

	BigDecimal depositPeriodInMonthsInInteger=new BigDecimal(depositPeriodInMonths);
	BigDecimal depositPeriodInDaysInInteger=new BigDecimal(depositPeriodInDays);
	BigDecimal intRateInInteger=new BigDecimal(intRate);
	BigDecimal divisor=new BigDecimal("1200");
	if("On Maturity".equalsIgnoreCase(intPaymentFreq)) {
				
		BigDecimal actualMatAmount=((principalAmountInInteger.multiply(intRateInInteger).multiply(depositPeriodInMonthsInInteger))).divide(divisor, RoundingMode.HALF_UP);
		actualMatAmount=actualMatAmount.add(principalAmountInInteger);
		assertionHelper.verifyAmountEquals("//input[@id='matAmt']", actualMatAmount.toString());	
	}
	
	else {
		BigDecimal actualMatAmount=(principalAmountInInteger.multiply(intRateInInteger).multiply(depositPeriodInMonthsInInteger)).divide(divisor.add(intRateInInteger));
		actualMatAmount=actualMatAmount.add(principalAmountInInteger);
		assertionHelper.verifyAmountEquals("//input[@id='matAmt']", actualMatAmount.toString());	
	}
}

public void verifyCustCategoryInFDOpening(String excelQuery) {
	String custNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "custNumber");
	dbHelper.verifyUiTextWithDb("//input[@id='custCategory']", "SELECT DESCRIPTION  FROM LOOKUP WHERE code IN (SELECT CUST_CATEGORY  FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"')");
}

public void fdAccountOpeningTablelevelVerification(String expectedStatus,String excelQuery,String cacheKeyName,Map<String,String>cacheMap ) {
	String accNumber=cacheHelper.getCache(cacheKeyName, cacheMap);
	String custNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "custNumber");
	String instAmount=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "InstallmentAmount");
	String custId=dbHelper.connectToDb("select cust_id from cust_master where cust_number='"+custNumber+"'");
	dbHelper.compareResultUsingDb("select status from account_master where acc_no='"+accNumber+"'", expectedStatus);
	dbHelper.compareResultUsingDb("SELECT cust_id FROM CUSTOMER_ACCOUNT WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')", custId);
	String instAmtFromDB=dbHelper.connectToDb("SELECT INSTALL_AMT FROM DEPOSIT_MASTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
	assertionHelper.verifyTwoAmounts(instAmount, instAmtFromDB);
	
}
public void fdAuthUIVerifications(String excelQuery,Map<String,String>cacheMap,String fdAccCacheKey) {
	String custNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "custNumber");
	String accNumber=cacheHelper.getCache(fdAccCacheKey, cacheMap);
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
	dbHelper.verifyUiTextWithDb("//input[@id='depPeriodDays']", "SELECT DEP_PERIOD_DAYS  FROM DEPOSIT_MASTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
	dbHelper.verifyUiDateWithDB("//input[@id='matDate']", "SELECT MATURITY_DT  FROM DEPOSIT_MASTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
	dbHelper.verifyUiAmountWithDb("//input[@id='matAmt']", "SELECT MAT_AMT  FROM DEPOSIT_MASTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
    seleniumHelper.clickElement("//a[@id='renewaPolicy']");
    dbHelper.verifyUiTextWithDb("//input[@id='existingMobile']", "SELECT value FROM CUST_CONTACT WHERE CONTACT_TYPE ='mob' and  CUST_ID IN (SELECT CUST_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')");
}
}
