package customerCreation;

import java.util.ArrayList;
import java.util.List;

import helper.AssertionHelper;
import helper.CacheHelper;
import helper.DbHelper;
import helper.FilloExcelDataGetter;
import helper.FindElement;
import helper.JsWaiter;
import helper.ReportHelper;
import helper.SeleniumHelper;
import testBase.TestBase;

public class CustomerDeleteMethods extends TestBase{

	FindElement findElement = new FindElement();
	CacheHelper cacheHelper = new CacheHelper();
	ReportHelper reportHelper = new ReportHelper();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	AssertionHelper  assertionHelper  = new AssertionHelper ();
	JsWaiter  jsWaiter  = new JsWaiter ();
	FilloExcelDataGetter filloExcelDataGetter = new FilloExcelDataGetter();
	
	public void customerDeleteDataVerification(String excelQuery) {
		String customerNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "custNumber");
		seleniumHelper.enterText("//input[@id='custNumber']", customerNumber);
		
		//div[@id='AccountInformation']/table/tbody/tr[2]/td[2]
		dbHelper.verifyUiTextWithDb("//div[@id='AccountInformation']/table/tbody/tr[1]/td[2]", "SELECT ACC_NO  FROM ACCOUNT_MASTER "
				+ "WHERE ACC_No IN (SELECT ACC_No FROM CUSTOMER_ACCOUNT WHERE CUST_ID IN "
				+ "(SELECT CUST_ID FROM CUST_MASTER WHERE CUST_NUMBER ='"+customerNumber+"'))");
		
		dbHelper.verifyUiTextWithDb("//div[@id='AccountInformation']/table/tbody/tr[1]/td[3]", "SELECT CUST_NAME  FROM CUST_MASTER WHERE CUST_NUMBER ='"+customerNumber+"'");
		dbHelper.verifyUiTextWithDb("//div[@id='AccountInformation']/table/tbody/tr[1]/td[6]", "SELECT status  FROM ACCOUNT_MASTER "
				+ "WHERE ACC_No IN (SELECT ACC_No FROM CUSTOMER_ACCOUNT WHERE CUST_ID IN "
				+ "(SELECT CUST_ID FROM CUST_MASTER WHERE CUST_NUMBER ='"+customerNumber+"'))");
		
	}
	
public void customerDeleteTableVerifications(String excelQuery,String status) {
	String customerNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "custNumber"); 
	
	dbHelper.compareResultUsingDb("SELECT status FROM CUST_master WHERE cust_number='"+customerNumber+"'", "C");
	dbHelper.compareResultUsingDb("SELECT status FROM CUSTOMER_DELETE WHERE CUST_ID IN (SELECT CUST_ID FROM CUST_MASTER WHERE CUST_NUMBER ='"+customerNumber+"')", status);
	
}
}
