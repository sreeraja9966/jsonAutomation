package denominationVerification;

import java.util.Map;

import helper.CacheHelper;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import testBase.TestBase;

public class DenominationVerificationMethods extends TestBase{

	FindElement findElement = new FindElement();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	ReportHelper reportHelper = new ReportHelper();
	/**
	 * This method will give you the total denom transfer amount
	 * TransferDenomOnUI=totalAmountOnUI-totalamountTransfering
	 * @param xpath of total amount
	 * @param key to store the total value in cacheMap
	 * @param cacheHelper
	 * @param cacheMap
	 */
	public void denomTransferVerification(String xpath,String key,CacheHelper cacheHelper,Map<String,String>cacheMap) {
		String avilableDenom=seleniumHelper.getTextFromAnElement(xpath+"/table/tbody/tr/td/span/b[contains(text(),'Total')]//following::td[3]");
		log.info(avilableDenom+"===================>avilableDenom");
		String transferDenom=seleniumHelper.getTextFromAnElement(xpath+"/table/tbody/tr/td/span/b[contains(text(),'Total')]//following::td[5]");
		log.info(transferDenom+"===================>transferDenom");
		 int balanceDenomofFromUser=Integer.parseInt(avilableDenom)-Integer.parseInt(transferDenom);
		 reportHelper.writeLogInfoInChildTest("balance amount according to UI is "+"avilableDenom-transferDenom=balanceDenom("+avilableDenom+"-"+transferDenom+"="+Integer.toString(balanceDenomofFromUser)+")");
		 String valueinString=Integer.toString(balanceDenomofFromUser);
		 cacheHelper.setCacheWithaString(key, valueinString,cacheMap);
		 
	}
	
}
