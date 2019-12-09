package helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import testBase.TestBase;

public class AssertionHelper extends TestBase {
	private static final Logger log = Logger.getLogger(AssertionHelper.class);
	public  ReportHelper reportHelper=new ReportHelper();
	public  FindElement findElement=new FindElement();
SeleniumHelper seleniumHelper = new SeleniumHelper();	
FilloExcelDataGetter filloExcelDataGetter = new FilloExcelDataGetter();	
DropDownHelper dropDownHelper = new DropDownHelper();
	public  synchronized boolean verifyElementPresent( WebElement element) {
	
		boolean isDispalyed = false;
		try {
			 isDispalyed= element.isDisplayed();
			log.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			reportHelper.writeLogInCaseOfFail(element+"---> element not found");
			reportHelper.addScreenShotInCaseOfFailInReport(element+"----> element not found");
			
			log.error(ex);
		}
		
		return isDispalyed;
	}
	
	public  synchronized boolean verifyElementNotPresent( WebElement element) {
		boolean isDispalyed = false;
		try {
			 element.isDisplayed();
			log.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			log.error("Element not found " + ex);
			isDispalyed = true;
		}
		
		return isDispalyed;
	}
	
	/**
	 * 
	 * @author sriraja.garlapati
	 * verifyTextEquals( String element,String expectedText) will verify the available text with the expected Text.
	 * It requires two parameters xpath (can be fetched from locator.properties) and expectedText(can be fetched from data.properties)
	 * 
	 */
	public  synchronized boolean verifyTextEquals( String element,String expectedText) {
		boolean flag = false;
		try { 
			String actualText=seleniumHelper.getTextFromAnElement(element);

			if(expectedText.trim().equalsIgnoreCase(actualText.trim())) {
				log.info("actualText is :"+actualText+" expected text is: "+expectedText);
				reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
				flag=true;
				return flag;
			}
			else {
				log.error("actualText is :"+actualText+" expected text is: "+expectedText);
				highlightElementInRedColour(element);
				reportHelper.writeLogInCaseOfFailInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
				reportHelper.addScreenShotInCaseOfFailInReportInChild(expectedText+" is not matched with actual text"+actualText);
				changeElementColour(element);
				return flag;
			}
		}
		catch(Exception ex) {
			log.error(ex);
			ex.printStackTrace();
			return flag;
		}
	}
	
	
	public  synchronized boolean verifyDropdownText( String element,String expectedText) {
		boolean flag = false;
		try { 
			String actualText=dropDownHelper.getSelectedValue(element);

			if(expectedText.trim().equalsIgnoreCase(actualText.trim())) {
				log.info("actualText is :"+actualText+" expected text is: "+expectedText);
				reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
				flag=true;
				return flag;
			}
			else {
				log.error("actualText is :"+actualText+" expected text is: "+expectedText);
				highlightElementInRedColour(element);
				reportHelper.writeLogInCaseOfFailInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
				reportHelper.addScreenShotInCaseOfFailInReportInChild(expectedText+" is not matched with actual text"+actualText);
				changeElementColour(element);
				return flag;
			}
		}
		catch(Exception ex) {
			log.error(ex);
			ex.printStackTrace();
			return flag;
		}
	}
	
	
	/**
	 * verifyCalendarText( String element,String expectedText) will verify available date with the given date.
	 * available date is fetched using getAtribute method.
	 * It requires two parameters xpath and expectedText which are fetched from locator and data property files respectively.
	 * 
	 * @author sriraja.garlapati
	 */
	
	public  synchronized boolean verifyCalendarText( String element,String expectedText) {
		boolean flag = false;
		try { 
			String actualText=seleniumHelper.getTextFromAnElement(element);
			if(expectedText.trim().equalsIgnoreCase(actualText.trim())) {
				log.info("actual Date is :"+actualText+" expected Date is: "+expectedText);
				reportHelper.writeLogInCaseOfPassInChildTest("actual Date is :"+actualText+" expected Date is: "+expectedText);
				flag=true;
				return flag;
			}
			else {
				log.error("actual Date is :"+actualText+" expected Date is: "+expectedText);
				highlightElementInRedColour(element);
				reportHelper.writeLogInCaseOfFailInChildTest("actual Date is :"+actualText+" expected Date is: "+expectedText);
				reportHelper.addScreenShotInCaseOfFailInReportInChild("expected Date is "+expectedText+" is not matched with actual Date"+actualText);
				changeElementColour(element);
				return flag;
			}
		}
		catch(Exception ex) {
			
			
			return flag;
		}
	}
	
	public  synchronized boolean verifyCalendarTextUsingExcel( String element,String query,String fieldName) {
		boolean flag = false;
		
		try { 
			String expectedText=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, fieldName);
			String actualText=seleniumHelper.getTextFromAnElement(element);
			if(expectedText.trim().equalsIgnoreCase(actualText.trim())) {
				log.info("actual Date is :"+actualText+" expected Date is: "+expectedText);
				reportHelper.writeLogInCaseOfPassInChildTest("actual Date is :"+actualText+" expected Date is: "+expectedText);
				flag=true;
				return flag;
			}
			else {
				log.error("actual Date is :"+actualText+" expected Date is: "+expectedText);
				highlightElementInRedColour(element);
				reportHelper.writeLogInCaseOfFailInChildTest("actual Date is :"+actualText+" expected Date is: "+expectedText);
				reportHelper.addScreenShotInCaseOfFailInReportInChild("expected Date is "+expectedText+" is not matched with actual Date"+actualText);
				changeElementColour(element);
				return flag;
			}
		}
		catch(Exception ex) {
			
			
			return flag;
		}
	}

/**
 * verifyTwoTexts( String actualText,String expectedText) verifies two texts.
 * Generally it used inside few methods. It requires two parameters actual & expected text.
 * @author sriraja.garlapati
 */

public  synchronized boolean verifyTwoTexts( String actualText,String expectedText) {
	boolean flag = false;
	try { 
			if(expectedText.trim().equalsIgnoreCase(actualText.trim())) {
			log.info("actualText is :"+actualText+" expected text is: "+expectedText);
			reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
			flag=true;
			return flag;
		}
		else {
			
			log.error("actualText is :"+actualText+" expected text is: "+expectedText);
			reportHelper.writeLogInCaseOfFailInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
			reportHelper.addScreenShotInCaseOfFailInReportInChild(expectedText+" is not matched with actual text"+actualText);
			return flag;
		}
	}
	catch(Exception ex) {
		
		
		return flag;
	}
}

/**
 * verifyListOfElements(List<String> list,List<String> expectedList) verifies list of elements with a list of expected values.
 * Generally it is used inside a method.
 * @author sriraja.garlapati
 */
public  void verifyListOfElements(List<String> list,List<String> expectedList) {
	int expectedListlength=expectedList.size();
	int actualListSixe=list.size();
	String reqText=null;
	String expectedText=null;
	
	log.info(expectedListlength+"********expectedListlength"+actualListSixe+"*************actualListSize");
	try {
		for(int i=0;i<actualListSixe;i++) {
			reqText=list.get(i);
			
				expectedText=expectedList.get(i);
			if(expectedText.trim().equalsIgnoreCase(reqText.trim())) {
			
				log.info("actualText is :"+reqText+" expected text is: "+expectedText);
				reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+reqText+" expected text is: "+expectedText);
		
	}
		
			
		
		else {
		
			log.error("actualText is :"+reqText+" expected text is: "+expectedText);
			reportHelper.writeLogInCaseOfFailInChildTest("actualText is :"+reqText+" expected text is: "+expectedText);
			reportHelper.addScreenShotInCaseOfFailInReportInChild(expectedText+" is not matched with actual text"+reqText);
			
		}}
	}	
	catch(Exception ex) {
		log.error(ex);
		
		
	}
	
}

public void highlightElementInRedColour(String xpath) {
	JavascriptExecutor js = (JavascriptExecutor) d;
	js.executeScript("arguments[0].setAttribute('style', arguments[1]);", d.findElement(By.xpath(xpath)),
			"color: black; background-color:#ffd351; border: 2px solid Red");
}
public void changeElementColour(String xpath) {
	JavascriptExecutor js = (JavascriptExecutor) d;
	js.executeScript("arguments[0].setAttribute('style', arguments[1]);", d.findElement(By.xpath(xpath)), "");

}
public  synchronized boolean verifyNumberEquals( String element,String expectedText) {
	boolean flag = false;
	String actualText=seleniumHelper.getTextFromAnElement(element);
	try { 
		

		if(expectedText.trim().equalsIgnoreCase(actualText.trim())) {
			log.info("actualText is :"+actualText+" expected text is: "+expectedText);
			reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
			flag=true;
			return flag;
		}
		else {
			String actualTextwithoutDecimal=new BigDecimal(actualText).setScale(0).toString();
			String expectedTextwithoutDecimal=new BigDecimal(expectedText).setScale(0).toString();
			if(expectedTextwithoutDecimal.trim().equalsIgnoreCase(actualTextwithoutDecimal.trim())) {
				log.info("actualText is :"+actualTextwithoutDecimal+" expected text is: "+expectedTextwithoutDecimal);
				reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+actualTextwithoutDecimal+" expected text is: "+expectedTextwithoutDecimal);
				flag=true;
				return flag;
			}
			else  {
				
				log.error("actualText is :"+actualTextwithoutDecimal+" expected text is: "+expectedTextwithoutDecimal);
				highlightElementInRedColour(element);
				reportHelper.writeLogInCaseOfFailInChildTest("actualText is :"+actualTextwithoutDecimal+" expected text is: "+expectedTextwithoutDecimal);
				reportHelper.addScreenShotInCaseOfFailInReportInChild(expectedTextwithoutDecimal+" is not matched with actual text"+actualTextwithoutDecimal);
				changeElementColour(element);
				return flag;
			}
			
		
		
		}
	}
	catch(Exception ex) {
ex.printStackTrace();
		return flag;}
}
public  synchronized boolean verifyTwoAmounts( String actualText,String expectedText) {
	boolean flag = false;
	try { log.info("actualText is :"+actualText+" expected text is: "+expectedText);
			if(expectedText.trim().equalsIgnoreCase(actualText.trim())) {
			log.info("actualText is :"+actualText+" expected text is: "+expectedText);
			reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
			flag=true;
			return flag;
		}
		/*else {
			String actualTextwithoutDecimal=new BigDecimal(actualText).setScale(0).toString();
			String expectedTextwithoutDecimal=new BigDecimal(expectedText).setScale(0).toString();
			if(expectedTextwithoutDecimal.trim().equalsIgnoreCase(actualTextwithoutDecimal.trim())) {
				log.info("actualText is :"+actualTextwithoutDecimal+" expected text is: "+expectedTextwithoutDecimal);
				reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+actualTextwithoutDecimal+" expected text is: "+expectedTextwithoutDecimal);
				flag=true;
				return flag;
			}
			else {
				log.error("actualText is :"+actualTextwithoutDecimal+" expected text is: "+expectedTextwithoutDecimal);
				
				reportHelper.writeLogInCaseOfFailInChildTest("actualText is :"+actualTextwithoutDecimal+" expected text is: "+expectedTextwithoutDecimal);
				reportHelper.addScreenShotInCaseOfFailInReportInChild(expectedTextwithoutDecimal+" is not matched with actual text"+actualTextwithoutDecimal);
				
				return flag;
			}
		}*/
			else {
				actualText=actualText.replace(",", "");
				expectedText=expectedText.replace(",", "");
				BigDecimal actualTextasBigdecimal=new BigDecimal(actualText);
				BigDecimal expectedTextasBigdecimal=new BigDecimal(expectedText);
				
				if(actualTextasBigdecimal.compareTo(expectedTextasBigdecimal)==0) {
					log.info("Expected Text is-->"+expectedTextasBigdecimal+" Which is Matched with Db Value--->"+actualTextasBigdecimal);
					reportHelper.writeLogInCaseOfPassInChildTest("Expected Text is-->"+expectedTextasBigdecimal+" Which is  Matched with Db Value--->"+actualTextasBigdecimal);
					flag=true;
					return flag;
					}
				else {
					reportHelper.writeLogInCaseOfFailInChildTest("actualText is :"+actualTextasBigdecimal+" expected text is: "+expectedTextasBigdecimal);
					reportHelper.addScreenShotInCaseOfFailInReportInChild(expectedTextasBigdecimal+" is not matched with actual text"+actualTextasBigdecimal);
				
					return flag;
					}
			}
	}
	catch(Exception ex) {
		ex.printStackTrace();
		return flag;
	}
}
public  synchronized boolean verifyAmountEquals( String element,String expectedText) {
	boolean flag = false;
	try { 
		String actualText=seleniumHelper.getTextFromAnElement(element);

		if(expectedText.trim().equalsIgnoreCase(actualText.trim())) {
			log.info("actualText is :"+actualText+" expected text is: "+expectedText);
			reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
			flag=true;
			return flag;
		}
		
		else {
			actualText=actualText.replace(",", "");
			expectedText=expectedText.replace(",", "");
			BigDecimal actualTextasBigdecimal=new BigDecimal(actualText);
			BigDecimal expectedTextasBigdecimal=new BigDecimal(expectedText);
			
			if(actualTextasBigdecimal.compareTo(expectedTextasBigdecimal)==0) {
				log.info("Expected Text is-->"+expectedTextasBigdecimal+" Which is Matched with Db Value--->"+actualTextasBigdecimal);
				reportHelper.writeLogInCaseOfPassInChildTest("Expected Text is-->"+expectedTextasBigdecimal+" Which is  Matched with Db Value--->"+actualTextasBigdecimal);
				flag=true;
				return flag;
				}
			else {	log.error("actualText is :"+actualText+" expected text is: "+expectedText);
			highlightElementInRedColour(element);
			reportHelper.writeLogInCaseOfFailInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
			reportHelper.addScreenShotInCaseOfFailInReportInChild(expectedText+" is not matched with actual text"+actualText);
			changeElementColour(element);
			return flag;
		}
			}
	}
	catch(Exception ex) {
		log.error(ex);
		ex.printStackTrace();
		return flag;
	}
}

public  synchronized boolean verifyAmountEqualsWithHigherRoundOff( String element,String expectedText) {
	boolean flag = false;
	try { 
		String actualText=seleniumHelper.getTextFromAnElement(element);

		if(expectedText.trim().equalsIgnoreCase(actualText.trim())) {
			log.info("actualText is :"+actualText+" expected text is: "+expectedText);
			reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
			flag=true;
			return flag;
		}
		
		else {
			actualText=actualText.replace(",", "");
			expectedText=expectedText.replace(",", "");
			BigDecimal actualTextasBigdecimal=new BigDecimal(actualText);
			BigDecimal expectedTextasBigdecimal=new BigDecimal(expectedText);
			actualTextasBigdecimal=actualTextasBigdecimal.setScale(0, RoundingMode.HALF_UP);
			if(actualTextasBigdecimal.compareTo(expectedTextasBigdecimal)==0) {
				log.info("Expected Text is-->"+expectedTextasBigdecimal+" Which is Matched with Db Value--->"+actualTextasBigdecimal);
				reportHelper.writeLogInCaseOfPassInChildTest("Expected Text is-->"+expectedTextasBigdecimal+" Which is  Matched with Db Value--->"+actualTextasBigdecimal);
				flag=true;
				return flag;
				}
			
			else {	
			log.error("actualText is :"+actualText+" expected text is: "+expectedText);
			highlightElementInRedColour(element);
			reportHelper.writeLogInCaseOfFailInChildTest("actualText is :"+actualText+" expected text is: "+expectedText);
			reportHelper.addScreenShotInCaseOfFailInReportInChild(expectedText+" is not matched with actual text"+actualText);
			changeElementColour(element);
			return flag;
		}
			}
	}
	catch(Exception ex) {
		log.error(ex);
		ex.printStackTrace();
		return flag;
	}
}
}
