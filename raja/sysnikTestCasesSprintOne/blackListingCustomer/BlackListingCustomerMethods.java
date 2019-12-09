package blackListingCustomer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import helper.CacheHelper;
import helper.DbHelper;
import helper.FilloExcelDataGetter;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import net.sourceforge.htmlunit.corejs.javascript.ast.CatchClause;
import testBase.TestBase;

public class BlackListingCustomerMethods extends TestBase{
	FindElement findElement = new FindElement();
	CacheHelper cacheHelper = new CacheHelper();
	ReportHelper reportHelper = new ReportHelper();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
FilloExcelDataGetter   filloExcelDataGetter   = new FilloExcelDataGetter  ();
	public void customerInfoVerificationInBlackListingCustomer(String query) {
		String custNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, "custNumber");
		dbHelper.verifyUiTextWithDb("//input[@id='customerName']", "SELECT cust_name FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'");
	}
	
	public void blackListingTableVerification(String xpath,String queryofExcel) {
		String custNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(queryofExcel, "custNumber");
		List<String> tableData = null;
		try {
			List<WebElement>  col = d.findElements(By.xpath(xpath+"/table/thead/tr/th"));
			List<WebElement>  row = d.findElements(By.xpath(xpath+"/table/tbody/tr/td[1]"));
			String headerValue=null;
			String colValue=null;
			log.info("no.of col--------> "+col.size());
			log.info("no.of row--------> "+row.size());
			tableData = new ArrayList<>();
			for(int i=1;i<=row.size();i++) {
				
				for(int j=1;j<=col.size();j++) {
					headerValue=findElement.searchClickableElement(By.xpath((xpath+"/table/thead/tr/th["+j+"]"))).getText();
					colValue=findElement.searchClickableElement(By.xpath(xpath+"/table/tbody/tr["+i+"]/td["+j+"]")).getText();
					log.info(i+")"+j+")"+headerValue+colValue);
					if(headerValue.equalsIgnoreCase("Account Type")) {
						dbHelper.compareResultUsingDb("SELECT ACC_TYPE  FROM ACCOUNT_MASTER WHERE CUST_ID IN "+
					"(SELECT CUST_ID FROM CUST_MASTER WHERE CUST_NUMBER='"+custNumber+"')", colValue);
				
					}
					else if (headerValue.equalsIgnoreCase("Account Number")) {
						dbHelper.compareResultUsingDb("SELECT ACC_NO  FROM ACCOUNT_MASTER WHERE CUST_ID IN"
								+ "(SELECT CUST_ID FROM CUST_MASTER WHERE CUST_NUMBER='"+custNumber+"')",colValue);
						
					}
                    else if (headerValue.equalsIgnoreCase("Balance")) {
                    	dbHelper.verifyUiAmountWithDb(xpath+"/table/tbody/tr["+i+"]/td["+j+"]", "SELECT AVAILABLE_BAL  FROM ACCOUNT_MASTER WHERE CUST_ID IN"
								+ "(SELECT CUST_ID FROM CUST_MASTER WHERE CUST_NUMBER='"+custNumber+"')");
						
					}
							
					
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
public void tableVerificaitonOnSubmitInBlackListing(String queryofExcel,String status) {
	String custNumber=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(queryofExcel, "custNumber");
	dbHelper.compareResultUsingDb("SELECT status FROM CUST_MASTER WHERE CUST_Number='"+custNumber+"'", "B");
	dbHelper.compareResultUsingDb("SELECT status FROM BLACK_LIST_MASTER where cust_id in (select cust_id from cust_master where cust_number='"+custNumber+"')", status);
dbHelper.compareResultUsingDb("SELECT status FROM BLACK_LIST_DETAILS WHERE BLACK_LIST_ID IN (SELECT BLACK_LIST_ID FROM BLACK_LIST_MASTER WHERE CUST_ID IN(SELECT CUST_ID "
+ "FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'))", status);

dbHelper.compareResultUsingDb("SELECT status FROM ACCOUNT_MASTER WHERE CUST_ID IN (SELECT CUST_ID FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"')", "B");



}
}
