package customerCreation;

import java.util.Map;

import org.aspectj.weaver.tools.cache.GeneratedCachedClassHandler;
import org.openqa.selenium.By;

import helper.AssertionHelper;
import helper.CacheHelper;
import helper.DbHelper;
import helper.FindElement;
import helper.JsWaiter;
import helper.ReportHelper;
import helper.SeleniumHelper;
import testBase.TestBase;

public class CustomerCreationMethods extends TestBase{

	FindElement findElement = new FindElement();
	CacheHelper cacheHelper = new CacheHelper();
	ReportHelper reportHelper = new ReportHelper();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DbHelper dbHelper = new DbHelper();
	AssertionHelper  assertionHelper  = new AssertionHelper ();
	JsWaiter  jsWaiter  = new JsWaiter ();
	public void getCustomerCreationModelResponse(String Xpath,Map<String,String>cacheMap) {
		String textInModel = null;
		
			textInModel = seleniumHelper.getTextFromAnElement(Xpath);
		
		if(!textInModel.contains("Customer Number")) {
			reportHelper.writeLogInCaseOfFailInChildTest("Customer Creation is failed With reason "+textInModel);
			reportHelper.addScreenShotInCaseOfFailInReportInChild("Reason for customer creation failure is "+textInModel);
			
		}
		else {
			cacheHelper.setCacheWithOnlyNumbers("CustomerNumber", Xpath,cacheMap);			
			reportHelper.writeLogInCaseOfPassInChildTest("Customer is created sucessfully with number "
			+cacheHelper.getCache("CustomerNumber", cacheMap));
		}
	}
	
public void tableVerificationsInCustomerCreation(Map<String,String>cacheMap,String expectedResult) {
	String custNumber=cacheHelper.getCache("CustomerNumber", cacheMap);
	dbHelper.compareResultUsingDb("SELECT STATUS  FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'",expectedResult);
	String noOfRowsIncustContact=dbHelper.connectToDb("SELECT count(*) FROM CUST_CONTACT WHERE CUST_ID IN (SELECT CUST_ID FROM CUST_MASTER WHERE CUST_NUMBER='"+custNumber+"')");
	if(expectedResult.equalsIgnoreCase("E")) {
	if(Integer.parseInt(noOfRowsIncustContact)>0) {
		reportHelper.writeLogInCaseOfPassInChildTest("Records Inserted in Cust_contact table for custNumber "+custNumber);
	}
	else {
		reportHelper.writeLogInCaseOfFailInChildTest("Records were NOT Inserted in Cust_contact table for custNumber "+custNumber);
	}
	}
}
	public void getCustomerAuthorizationModelResponse(String Xpath) {
		String textInModel=seleniumHelper.getTextFromAnElement(Xpath);
	
		if(!textInModel.contains("Customer authorised successfully")) {
			reportHelper.writeLogInCaseOfFailInChildTest("Customer Creation is failed With reason "+textInModel);
			reportHelper.addScreenShotInCaseOfFailInReportInChild("Reason for customer creation failure is "+textInModel);
		}
		else {
			
			seleniumHelper.clickElement("//button[contains(text(),'OK')]");	
			reportHelper.writeLogInCaseOfPassInChildTest("Customer Authorized Successfully");
		
		}
	}
	
	
	
	
	
	public void custMasterAndUIVerification(String xpath,String query,Map<String,String>cacheMap) {
		String reqQuery="";
		String custNumber=cacheHelper.getCache("CustomerNumber", cacheMap);
		
		
		if("customerFristName".equalsIgnoreCase(query)) {
			reqQuery="SELECT cust_first_name FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
		log.info(reqQuery);
		}
		else if("customerMiddleName".equalsIgnoreCase(query)) {
			reqQuery="SELECT cust_middle_name FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}
		
		else if("customerLastName".equalsIgnoreCase(query)) {
			reqQuery="SELECT cust_last_name FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}
		else if("customerfullName".equalsIgnoreCase(query)) {
			reqQuery="SELECT cust_name FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}
		else if("fatherFirstName".equalsIgnoreCase(query)) {
			reqQuery="SELECT father_spouse_Fname FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}
		else if("fatherMiddleName".equalsIgnoreCase(query)) {
			reqQuery="SELECT father_spouse_Mname FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}
		else if("fatherLastName".equalsIgnoreCase(query)) {
			reqQuery="SELECT father_spouse_Lname FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}
		else if("fatherFullName".equalsIgnoreCase(query)) {
			reqQuery="SELECT father_spouse_name FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}
		else if("gender".equalsIgnoreCase(query)) {
			reqQuery="SELECT gender FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			if("M".equalsIgnoreCase(reqQuery)) {
				reqQuery="Male";
			}
			else if("F".equalsIgnoreCase(reqQuery)){
				reqQuery="Female";
			}
			log.info(reqQuery);
		}
		else if("MaritalStatus".equalsIgnoreCase(query)) {
			reqQuery="SELECT MARITAL_STATUS FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			if("S".equalsIgnoreCase(reqQuery)) {
				reqQuery="single";
			}
			else if("M".equalsIgnoreCase(reqQuery)) {
				reqQuery="married";
			}
			log.info(reqQuery);
		}
		
		else if("BloodGroup".equalsIgnoreCase(query)) {
			reqQuery="SELECT blood_group FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}
		
		else if("panNumber".equalsIgnoreCase(query)) {
			reqQuery="SELECT PAN_NO FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}
		else if("riskGrade".equalsIgnoreCase(query)) {
			reqQuery="SELECT risk_grade FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}
		
		else if("introName".equalsIgnoreCase(query)) {
			reqQuery="SELECT INTRODUCER_NAME FROM CUST_MASTER WHERE CUST_NUMBER ='"+custNumber+"'";
			log.info(reqQuery);
		}

		else if("email".equalsIgnoreCase(query)) {
			reqQuery="SELECT value FROM CUST_CONTACT WHERE CONTACT_TYPE='EMAIL' and CUST_ID IN"
					+ "(SELECT CUST_ID FROM CUST_MASTER WHERE CUST_NUMBER='"+custNumber+"')";
			log.info(reqQuery);
		}
		
		else if("mobile".equalsIgnoreCase(query)) {
			reqQuery="SELECT value FROM CUST_CONTACT WHERE CONTACT_TYPE='MOB' and CUST_ID IN"
					+ "(SELECT CUST_ID FROM CUST_MASTER WHERE CUST_NUMBER='"+custNumber+"')";
			log.info(reqQuery);
		}
		
		else {
			log.info("Wrong query");
		}
		//reqQuery="select "+query+" from ACCOUNT_MASTER where ACC_NO='"+cacheHelper.getCache("savingsAccountNumber", cacheMap)+"'";
		String expectedText=dbHelper.connectToDb(reqQuery);
		
		assertionHelper.verifyTextEquals(xpath, expectedText);
	
		
	}
	
	
}
