package helper;

import java.util.Map;

import testBase.TestBase;

public class CommonMethodsForVerifications extends TestBase{

	AssertionHelper  assertionHelper  = new AssertionHelper ();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	ReportHelper reportHelper = new ReportHelper();
	CacheHelper cacheHelper = new CacheHelper();
	public void verifyAndSubmitModel(String successMessage ) {
		assertionHelper.verifyTextEquals("//h6[@id='modale']",successMessage);
		seleniumHelper.clickElement("//h6[@id='modale']//following::button[contains(text(),'OK')]");
	}
	
	
	public void getAccountNumberModelResponse(String Xpath,String accountNumberType,Map<String,String>cacheMap) {
		String textInModel=seleniumHelper.getTextFromAnElement("//h6[@id='modale']");
		String accInModel=seleniumHelper.getOnlyNumbersFromAnElement(Xpath);
		
			cacheHelper.setCacheWithaString(accountNumberType, accInModel,cacheMap);
			if(!cacheHelper.getCache(accountNumberType, cacheMap).equals(null)) {
			reportHelper.writeLogInCaseOfPassInChildTest(accountNumberType+" is sucessful with account no "
			+cacheHelper.getCache(accountNumberType, cacheMap));
			}
			
			else {
				reportHelper.writeLogInCaseOfFailInChildTest(accountNumberType+" creation is failed With reason "+textInModel);
				reportHelper.addScreenShotInCaseOfFailInReportInChild("Reason for"+accountNumberType+" creation failure is "+textInModel);
			}
		
	}
	
}
