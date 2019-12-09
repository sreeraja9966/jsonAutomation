package drawingPower;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import org.openqa.selenium.Keys;

import com.codoid.products.exception.FilloException;

import helper.AssertionHelper;
import helper.CacheHelper;
import helper.DbHelper;
import helper.DropDownHelper;
import helper.FilloExcelDataGetter;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import testBase.TestBase;

public class DrawingPowerMethods extends TestBase{

	FindElement findElement = new FindElement();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	ReportHelper reportHelper = new ReportHelper();
	DbHelper dbHelper=new DbHelper();
	AssertionHelper  assertionHelper  = new AssertionHelper ();
	DropDownHelper dropDownHelper = new DropDownHelper();
FilloExcelDataGetter filloExcelDataGetter = new FilloExcelDataGetter();	

public void registerDPSubmission(String query) {
	String accNo=null;
	try {
		
		accNo = filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, "AccountNumber");
		String effectiveDate = filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, "EffectiveDate");
		seleniumHelper.clickElement("//button[contains(text(),'Bank Information')]");
	seleniumHelper.enterText("//input[@id='accNumber']", accNo);
	Thread.sleep(3000);
	seleniumHelper.clickElement("//button[contains(text(),'Limit Information')]");
	seleniumHelper.enterText("//input[@id='effectiveDate']", effectiveDate);
	seleniumHelper.clickElement("//button[contains(text(),' Stock Details ')]");
	seleniumHelper.enterText("//input[@id='rawMaterial']", "10000");
	seleniumHelper.enterText("//input[@id='wipProcured']", "20000");
	seleniumHelper.enterText("//input[@id='wipOther']", "30000");
	seleniumHelper.enterText("//input[@id='goodsInTransit']", "40000");
	seleniumHelper.enterText("//input[@id='finishedGoods']", "50000");
	seleniumHelper.enterText("//input[@id='sundryCreditGoods']", "60000");
	seleniumHelper.enterText("//input[@id='bookDebts']", "5000");
	seleniumHelper.enterText("//input[@id='miscSecurities']", "80000");
	seleniumHelper.enterText("//input[@id='monthlySales']", "100000");
	seleniumHelper.enterText("//input[@id='monthlyPurchases']", "2500000");
	seleniumHelper.clickElement("//button[contains(text(),'Bank Information')]");
	seleniumHelper.enterText("//input[@id='submitDate']", effectiveDate);
	seleniumHelper.enterText("//input[@id='inspectDate']", effectiveDate);
	seleniumHelper.clickElement("//button[contains(text(),'Limit Information')]");
	String avaEffectiveDate=seleniumHelper.getTextFromAnElement("//input[@id='effectiveDate']");
	if(avaEffectiveDate.equals(null)||avaEffectiveDate.equals("")) {
		seleniumHelper.enterText("//input[@id='effectiveDate']", effectiveDate);
	}
	seleniumHelper.clickElement("//button[contains(text(),'Bank Information')]");
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public void verifyCustomerDetailsinDrawingPowerScreen(String query,String fieldName) {
	
		try {
			String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, fieldName);
			//seleniumHelper.clickElement("//button[contains(text(),' Limit Information ')]");
			//seleniumHelper.enterText("//input[@id='accNumber']", accNo);
			dbHelper.verifyUiTextWithDb("//input[@id='custName']","SELECT CUST_NAME  FROM CUST_MASTER WHERE CUST_ID IN(SELECT CUST_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"')");
			dbHelper.verifyUiAmountWithDb("//input[@id='sanctionLimit']","SELECT SANCTIONED_AMT   FROM loan_MASTER WHERE acc_ID IN(SELECT Acc_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"')");
			dbHelper.verifyUiDateWithDB("//input[@id='limitExpiryDate']","SELECT EXPIRY_DT   FROM loan_MASTER WHERE acc_ID IN(SELECT Acc_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"')");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	
	}
	
	public void validateMarginCalculation(String xpathOfValue,String xpathOfCalculatedLimit) {
		try {
			final BigDecimal ONE_HUNDRED = new BigDecimal(100);
			seleniumHelper.enterTab(xpathOfValue);
			Double margin=Double.parseDouble(dbHelper.connectToDb("SELECT MARGIN_PER FROM BANKPARAMETERS WHERE BANK_CODE =101"));
			Double value=Double.parseDouble(seleniumHelper.getTextFromAnElement(xpathOfValue));
			String calculatedLimit=seleniumHelper.getTextFromAnElement(xpathOfCalculatedLimit);
			Double expectedMargin=value-((value*margin)/100);
			assertionHelper.verifyTwoAmounts(calculatedLimit, Double.toString(expectedMargin));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void validateTotalDpArriviedAndAllowed(String query,String fieldName) {
		
		try {
			String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, fieldName);
			Float rawMat=Float.parseFloat(seleniumHelper.getTextFromAnElement("//input[@id='rawMaterialLimit']"));
			Float procuredOutside=Float.parseFloat(seleniumHelper.getTextFromAnElement("//input[@id='wipProcuredLimit']"));
			Float wipOther=Float.parseFloat(seleniumHelper.getTextFromAnElement("//input[@id='wipOtherLimit']"));
			Float goodsInTransit=Float.parseFloat(seleniumHelper.getTextFromAnElement("//input[@id='goodsInTransitLimit']"));
			Float finishedGoods=Float.parseFloat(seleniumHelper.getTextFromAnElement("//input[@id='finishedGoodsLimit']"));
			Float sundryCreditGoods=Float.parseFloat(seleniumHelper.getTextFromAnElement("//input[@id='sundryCreditGoodsLimit']"));
			Float bookDebts=Float.parseFloat(seleniumHelper.getTextFromAnElement("//input[@id='bookDebtsLimit']"));
			Float miscSecurities=Float.parseFloat(seleniumHelper.getTextFromAnElement("//input[@id='miscSecuritiesLimit']"));
			
			Float ActualDpArrivedInFloat=rawMat+procuredOutside+wipOther+goodsInTransit+finishedGoods-
					                     sundryCreditGoods+bookDebts+miscSecurities;
			
			
			seleniumHelper.clickElement("//button[contains(text(),'Bank Information')]");
			Float sanctionedAmount=Float.parseFloat(dbHelper.connectToDb("SELECT SANCTIONED_AMT   FROM loan_MASTER WHERE acc_ID IN(SELECT Acc_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"')"));
			Float allowedDpAmount=Float.parseFloat(seleniumHelper.getTextFromAnElement("//input[@id='totalDpAllowed']"));
			Float dparrived=Float.parseFloat(seleniumHelper.getTextFromAnElement("//input[@id='totalDpArrived']"));
			
			if(assertionHelper.verifyTwoAmounts(ActualDpArrivedInFloat.toString(), dparrived.toString())) {
				
				if(ActualDpArrivedInFloat.compareTo(sanctionedAmount)>0) {
					assertionHelper.verifyTwoAmounts(sanctionedAmount.toString(), allowedDpAmount.toString());
				}
				else if (ActualDpArrivedInFloat.compareTo(sanctionedAmount)<0) {
					assertionHelper.verifyTwoAmounts(ActualDpArrivedInFloat.toString(), allowedDpAmount.toString());
				}
				else if (ActualDpArrivedInFloat.compareTo(sanctionedAmount)==0) {
					assertionHelper.verifyTwoAmounts(ActualDpArrivedInFloat.toString(), allowedDpAmount.toString());
				}
				else {
					reportHelper.writeLogInCaseOfFailInChildTest("FAILED TO CALCULATE DP-ALLOWED");
					reportHelper.addScreenShotInCaseOfFailInReportInChild("FAILED TO CALCULATE DP-ALLOWED");
				}
						
			}
		} catch (Exception e) {
			reportHelper.writeLogInCaseOfFailInChildTest("FAILED TO CALCULATE DP-ALLOWED");
			reportHelper.addScreenShotInCaseOfFailInReportInChild("FAILED TO CALCULATE DP-ALLOWED");
			e.printStackTrace();
		}
		
	}
	
	public void submitVerificationsInDpEntry(String query,String fieldName) {
		try {
			String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, fieldName);
			assertionHelper.verifyTextEquals("//h6[@id='modale']","DP Created Successfully.");
			assertionHelper.verifyTextEquals("//h6[@id='modale']//following::li[1]","Account Number : "+accNo);
			dbHelper.compareResultUsingDb("SELECT status FROM DP_REGISTER WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"')", "E");
		seleniumHelper.clickElement("//h6[@id='modale']//following::button[contains(text(),'OK')]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void verifyDpEntryAuthScreen(String query,String Status) {
		try {
			String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, "AccountNumber");
			String effectiveDate=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, "EffectiveDate");
			seleniumHelper.enterText("//input[@id='accNumber']", accNo);
			
		seleniumHelper.clickElement("//button[contains(text(),' Stock Details ')]");
		
		dropDownHelper.selectByElement("//select[@id='effectiveDate']",effectiveDate);
		seleniumHelper.clickElement("//button[contains(text(),' Limit Information ')]");
		dbHelper.verifyUiTextWithDb("//input[@id='custName']","SELECT CUST_NAME  FROM CUST_MASTER WHERE CUST_ID IN(SELECT CUST_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"')");
			dbHelper.verifyUiAmountWithDb("//input[@id='sanctionLimit']","SELECT SANCTIONED_AMT   FROM loan_MASTER WHERE acc_ID IN(SELECT Acc_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"')");
			dbHelper.verifyUiDateWithDB("//input[@id='limitExpiryDate']","SELECT EXPIRY_DT   FROM loan_MASTER WHERE acc_ID IN(SELECT Acc_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"')");
			dbHelper.verifyUiDateWithDB("//input[@id='expiryDate']","SELECT EXPIRY_DATE  FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");		
			seleniumHelper.clickElement("//button[contains(text(),'Stock Details')]");
			dbHelper.verifyUiAmountWithDb("//input[@id='rawMaterial']", "SELECT RAW_MATERIAL  FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='rawMaterialLimit']", "SELECT RAW_MATERIAL_LIMIT  FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='wipProcured']", "SELECT WIP_PROCURED   FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='wipProcuredLimit']", "SELECT WIP_PROCURED_LIMIT   FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='wipOther']", "SELECT WIP_OTHER   FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='goodsInTransit']", "SELECT GOODS_IN_TRANSIT    FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='goodsInTransitLimit']", "SELECT GOODS_IN_TRANSIT_LIMIT  FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='finishedGoods']", "SELECT FINISHED_GOODS   FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='sundryCreditGoods']", "SELECT SUNDRY_CREDIT_GOODS    FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='sundryCreditGoodsLimit']", "SELECT SUNDRY_CREDIT_GOODS_LIMIT     FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='sundryCreditOthers']", "SELECT SUNDRY_CREDIT_OTHERS      FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='sundryCreditOthersLimit']", "SELECT SUNDRY_CREDIT_OTHERS_LIMIT FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='bookDebts']", "SELECT BOOK_DEBTS FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='bookDebtsLimit']", "SELECT BOOK_DEBTS_LIMIT  FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='miscSecurities']", "SELECT MISC_SECURITIES  FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='miscSecuritiesLimit']", "SELECT MISC_SECURITIES_LIMIT   FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='monthlySales']", "SELECT MONTHLY_SALES  FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='monthlyPurchases']", "SELECT MONTHLY_PURCHASE   FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			seleniumHelper.clickElement("//button[contains(text(),'Bank Information')]");
			dbHelper.verifyUiAmountWithDb("//input[@id='totalDpArrived']", "SELECT TOTAL_DP_ARRIVED  FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='totalDpAllowed']", "SELECT TOTAL_DP_ALLOWED   FROM DP_REGISTER WHERE ACC_ID IN (SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiAmountWithDb("//input[@id='limitAsOnDate']","SELECT SANCTIONED_AMT   FROM loan_MASTER WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"')");
			dbHelper.verifyUiDateWithDB("//input[@id='submitDate']","SELECT SUBMIT_DATE   FROM DP_REGISTER WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
			dbHelper.verifyUiDateWithDB("//input[@id='inspectDate']","SELECT INSPECT_DATE   FROM DP_REGISTER WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO ='"+accNo+"') and EFF_DATE='"+effectiveDate+"' and status='"+Status+"'");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    
	
	
	}
	
	public void authTableValidations(String excelQuery,String effectiveDate) {
		try {
			log.info("**************effectiveDateeffectiveDateeffectiveDateeffectiveDate**************"+effectiveDate);
			seleniumHelper.clickElement("//h6[@id='modale']//following::button[contains(text(),'OK')]");
			String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "AccountNumber");
			dbHelper.compareResultUsingDb("SELECT status FROM DP_REGISTER WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER"
					+ " WHERE ACC_NO ='"+accNo+"')", "A");
			dbHelper.validateColumnsOfTwoTablesInCaseOfAmount("SELECT DRAWING_POWER  FROM LOAN_MASTER WHERE ACC_ID IN(SELECT ACC_ID "
					+ "FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"')", "SELECT TOTAL_DP_ALLOWED  FROM DP_REGISTER WHERE ACC_ID "
							+ "IN(SELECT ACC_ID FROM ACCOUNT_MASTER WHERE ACC_NO='"+accNo+"') AND EFF_DATE ='"+effectiveDate+"'"
									+ " AND STATUS='A'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rejectTableVerifications(String excelQuery) {
		assertionHelper.verifyTextEquals("//h6[contains(text(),'DP request rejected successfully.')] ", "DP request rejected successfully.");
		seleniumHelper.clickElement("//h6[@id='modale']//following::button[contains(text(),'OK')]");
		String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "AccountNumber");
		dbHelper.compareResultUsingDb("SELECT status FROM DP_REGISTER WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER"
				+ " WHERE ACC_NO ='"+accNo+"')", "R");
	}
	
	public void cancelTableVerifications(String excelQuery) {
		String accNo=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, "AccountNumber");
		dbHelper.compareResultUsingDb("SELECT status FROM DP_REGISTER WHERE ACC_ID IN(SELECT ACC_ID FROM ACCOUNT_MASTER"
				+ " WHERE ACC_NO ='"+accNo+"')", "C");
	}
}
