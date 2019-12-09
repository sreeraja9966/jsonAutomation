package adhocLimit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helper.AssertionHelper;
import helper.CacheHelper;
import helper.DbHelper;
import helper.DropDownHelper;
import helper.FilloExcelDataGetter;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import net.sourceforge.htmlunit.corejs.javascript.ast.CatchClause;
import testBase.TestBase;

public class AdhocLimitMethods extends TestBase{
	FindElement findElement = new FindElement();
	CacheHelper cacheHelper = new CacheHelper();
	ReportHelper reportHelper = new ReportHelper();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	FilloExcelDataGetter filloExcelDataGetter = new FilloExcelDataGetter();
	AssertionHelper  assertionHelper = new AssertionHelper ();
	DropDownHelper dropDownHelper = new DropDownHelper();
	public void validateCustomerInfo(String accNumberQuery) {
		String accNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(accNumberQuery, "AccountNumber");
		dbHelper.verifyUiTextWithDb("//input[@id='customername']", "SELECT CUST_NAME  FROM CUST_MASTER WHERE CUST_ID IN(SELECT cust_id FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"' )");
		dbHelper.verifyUiAmountWithDb("//input[@id='rateOfInt']", "SELECT INT_RATE  FROM LOAN_MASTER  WHERE acc_id in(SELECT acc_id FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"' )");
		dbHelper.verifyUiAmountWithDb("//input[@id='sanctionedAmount']", "SELECT SANCTIONED_AMT  FROM LOAN_MASTER  WHERE acc_id in(SELECT acc_id FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"' )");
		dbHelper.verifyUiDateWithDB("//input[@id='expDate']", "SELECT EXPIRY_DT   FROM LOAN_MASTER  WHERE acc_id in(SELECT acc_id FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNumber+"' )");
	}
	
	/**
	 * 
	 * @param dateFormat : YYYY-MM-DD
	 */
public void verifyExpiryDateInAdhocLimit(String dateFormat) {
	
	String effectiveDate=seleniumHelper.getTextFromAnElement("//input[@id='effectDate']");
	Long noOfDays= Long.parseLong(seleniumHelper.getTextFromAnElement("//input[@id='periodInDays']"));
	log.info(effectiveDate+"11111111111111111effectiveDate"+noOfDays );
	String expectedText=seleniumHelper.datePlusDays(effectiveDate,noOfDays,dateFormat);
	expectedText=expectedText.replace("-", "/");
	assertionHelper.verifyCalendarText("//input[@id='newExpDate']", expectedText);
}
public void adhocLimitEntry(String querytoFetchDataFromExcel) {
	String accNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(querytoFetchDataFromExcel, "AccountNumber");
	seleniumHelper.enterText("//input[@id='accNumber']", accNumber);
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='effectDate']", querytoFetchDataFromExcel, "EffectiveDate");
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='periodInDays']", querytoFetchDataFromExcel, "Periodindays");
	seleniumHelper.enterTextUsinfExcelValue("//input[@id='adhocSanctionedAmount']", querytoFetchDataFromExcel, "Amount");
	dropDownHelper.SelectUsingVisibleText("//select[@id='sanctionAuthority']", "ADMIN");
	seleniumHelper.enterText("//input[@id='remark']", "Automation");
	
}

public void adhocEntryTableVerfications(String queryToFetchresultFromExcel,String fieldName) {
	String accNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(queryToFetchresultFromExcel, "AccountNumber");
	dbHelper.compareExcelResultUsingDb("SELECT ADHOC_AMOUNT FROM ADHOC_LIMIT WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_no='"+accNumber+"')", queryToFetchresultFromExcel, fieldName);
	dbHelper.compareResultUsingDb("SELECT STATUS FROM ADHOC_LIMIT WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_no='"+accNumber+"')", "E");
}

public void validateAdhocAuthScreenData(String queryToFetchresultFromExcel) {
	String accNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(queryToFetchresultFromExcel, "AccountNumber");
	dbHelper.verifyUiDateWithDB("//input[@id='effectDate']", "SELECT EFFECTIVE_DT FROM ADHOC_LIMIT WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"') AND STATUS='E'");
	dbHelper.verifyUiDateWithDB("//input[@id='newExpDate']", "SELECT NEW_EXP_DATE FROM ADHOC_LIMIT WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"') AND STATUS='E'");
	dbHelper.verifyUiDateWithDB("//input[@id='periodInDays']", "SELECT VALID_DAYS FROM ADHOC_LIMIT WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"') AND STATUS='E'");
	dbHelper.verifyUiAmountWithDb("//input[@id='adhocSanctionedAmount']", "SELECT ADHOC_AMOUNT FROM ADHOC_LIMIT WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"') AND STATUS='E'");
	
}
public void validateAdhocAuthTables(String queryToFetchAccNumberFromExcel,String queryTofetchDataFromExcel) {
	String accNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(queryToFetchAccNumberFromExcel, "AccountNumber");
	dbHelper.compareResultUsingDb("SELECT status FROM ADHOC_LIMIT WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')","A");
	dbHelper.compareResultUsingDbUsingExcel("SELECT ADHOC_LIM  FROM LOAN_MASTER WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNumber+"')",queryTofetchDataFromExcel,"Amount");
}
}
