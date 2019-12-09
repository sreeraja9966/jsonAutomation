package helper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.codoid.products.exception.FilloException;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import helper.ReportHelper;
import helper.WaitingHelper;
import helper.FindElement;

import testBase.TestBase;

public class SeleniumHelper extends TestBase {
RobotHelper robotHelper = new RobotHelper();	
	FindElement findElement=new FindElement();
	WaitingHelper waitingHelper = new WaitingHelper();
	 ReportHelper reportHelper = new ReportHelper();
	 CacheHelper cacheHelper = new CacheHelper();
	 FilloExcelDataGetter  filloExcelDataGetter  = new FilloExcelDataGetter ();
	private static final Logger log = Logger.getLogger(SeleniumHelper.class);

public  void takeScreenShot(String filename){
		 File scrFile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
        
		 try {
			 
			 FileUtils.copyFile(scrFile, new File(relativePath()+"\\Reports\\screenshots\\"+ filename+".png"));
				
		 } catch (IOException e) {
			log.error(e);	}
	}
		
	
	
public   void mousehover(String movetoele, String clickele){

				Actions act = new Actions(d);
				WebElement a = findElement.searchClickableElement(By.xpath(movetoele));
				WebElement b = findElement.searchClickableElement(By.xpath(clickele));
				act.moveToElement(a).perform();
				
				act.moveToElement(b).click().perform();
				
				}
	
public void enterText(String xpath,String text) {
			 try {
				 log.info("trying enter-->"+text+"  in--->"+xpath);
				
				 findElement.searchClickableElement(By.xpath(xpath)).clear();
				findElement.searchClickableElement(By.xpath(xpath)).sendKeys(text);
				findElement.searchClickableElement(By.xpath(xpath)).sendKeys(Keys.TAB);
				reportHelper.writeLogInCaseOfPassInChildTest(text+" Entered in "+xpath);
			} catch (Exception e) {
				
				log.error(e);
			}
		 }
	
 public void clickElement(String xpath) {
			 try {
				findElement.searchClickableElement(By.xpath(xpath)).click();
				reportHelper.writeLogInfoInChildTest("Element is clicked "+xpath);
			}catch(WebDriverException e){
				try {
			
				log.info("****************tring scroll to element********************");
				JavaScriptHelper javaScriptHelper = new JavaScriptHelper();
				javaScriptHelper.scrollToElemet(By.xpath(xpath));
				findElement.searchClickableElement(By.xpath(xpath)).click();
				reportHelper.writeLogInfoInChildTest("Element is clicked "+xpath);
			} catch (Exception e1) { 
				log.error(e);
			}
				
		 }
			 
			 catch (Exception e) {
				log.error(e);
			}
 }
	public String filterSpleChar(String splCharString) {
		 String c= splCharString;
	        Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
	        Matcher match= pt.matcher(c);
	        while(match.find())
	        {
	            String s= match.group();
	        c=c.replaceAll("\\"+s, "");
	        }
			return c;
	}
	
	
	public void searchUsingAutoComplete(String element,String reuiredTextToClick) {
		WebDriverWait wait = new WebDriverWait(d,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(element)));
		
		List<WebElement> list=d.findElements(By.xpath(element));
	log.info("Auto Suggest List ::" + list.size());
		int i;
		for( i = 0 ;i< list.size();i++)
		{
			log.info(list.get(i).getText());
			
			if(reuiredTextToClick.equalsIgnoreCase(list.get(i).getText()))
			{
				list.get(i).click();
				break;
			}else {
				log.info(list.get(i).getText()+" not equals "+reuiredTextToClick);
			}
			if(i==list.size()-1) {log.error(reuiredTextToClick+"  not found");
			ReportHelper reportHelper = new ReportHelper();
		reportHelper.writeLogInCaseOfFailInChildTest(reuiredTextToClick+" not found");
			//throw new ElementNotVisibleException("Element not found with "+ reuiredTextToClick+" text"); 
				
			}
					}
		
		
	}
	public List<String> listFromTextFile(String filePath){

	    List<String> lines = Collections.emptyList(); 
	    try
	    { 
	      lines = 
	       Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8); 
	    } 
	  
	    catch (IOException e) 
	    { 
	  
	      // do something 
	      e.printStackTrace(); 
	    } 
	    return lines; 
	}
	public int getNoOfFile(String location) {
	File directory = new File(relativePath()+"location");
		  int fileCount=directory.list().length;
		  return fileCount;
	}
	
	public  void verifyTitle(String title){
		
		
		try{
			
			
			
		Assert.assertEquals(d.getTitle(), title);
		
		
		reportHelper.writeLogInCaseOfPassInChildTest("Title Matched with "+d.getTitle());
		
		
			
		
		}
		catch(Exception e){
			log.info("TITLE NOT MATCHED");
		log.info("Expected String is "+"' "+title+" '"+"  but the String present on the screen is "+"' "+d.getTitle()+" '");
		reportHelper.writeLogInCaseOfFailInChildTest("Expected String is "+"' "+title+" '"+"  but the String present on the screen is "+"' "+d.getTitle()+" '");
		reportHelper.addScreenShotInCaseOfFailInReportInChild(title+"  Expected String is Not Matched");
		}
			catch(Error e){
				log.info("Expected String is "+"' "+title+" '"+"  but the String present on the screen is "+"' "+d.getTitle()+" '");
			reportHelper.writeLogInCaseOfFail("Expected String is "+"' "+title+" '"+"  but the String present on the screen is "+"' "+d.getTitle()+" '");
			reportHelper.addScreenShotInCaseOfFailInReportInChild(title+"  Expected String is Not Matched");
			}
	
	}	
	/**
	 * before using verifyUItextWithCacheValue need store the value in cache using setCache method
	 */
	public boolean verifyUItextWithCacheValue(String xpath,String key,Map<String,String> cacheMap) {
		boolean flag = false;
		try { 
			
		String textOnUI=findElement.searchClickableElement(By.xpath(xpath)).getText().trim();
		String textInCache=cacheHelper.getCache(key,cacheMap);
		if(textInCache.equalsIgnoreCase(textOnUI)) {
			log.info("actualText is :"+textOnUI+" expected text is: "+textInCache);
			reportHelper.writeLogInCaseOfPassInChildTest("actualText is :"+textOnUI+" expected text is: "+textInCache);
			flag=true;
			return flag;
		}
		else {
			log.error("actualText is :"+textOnUI+" expected text is: "+textInCache);
			reportHelper.writeLogInCaseOfFailInChildTest("actualText is :"+textOnUI+" expected text is: "+textInCache);
			reportHelper.addScreenShotInCaseOfFailInReportInChild(textInCache+" is not matched with actual text"+textOnUI);
			return flag;
		}
	}
	catch(Exception ex) {
		
		
		return flag;
	}
		}
		
	
	public String  getTextFromAnElement(String element) {
		String actualText=findElement.searchClickableElement(By.xpath(element)).getText().trim();
		if((actualText==null)||actualText.equals("")) {
			log.info("trying if block");
			try {
				actualText=findElement.searchClickableElement(By.xpath(element)).getAttribute("ng-reflect-model").trim();
				log.info(actualText+"!@##@!#!$!$");
				if((actualText!=null)||actualText.equals("")) {
					log.info("Trying with attribute VALUE to get text from an element");
					actualText=findElement.searchClickableElement(By.xpath(element)).getAttribute("value").trim();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info(element);
				try {
					actualText=findElement.searchClickableElement(By.xpath(element)).getAttribute("value").trim();
					log.info(actualText+"!!!!!!!!!!!@@@@@@@@@@@@@@@@!!!!!!!!!!!!!!!!!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
	}
		return actualText;
	}
	
	
	public void searchMenu(String menu) {
		int spaceCount = 0;
		for (char c : menu.toCharArray()) {
		    if (c == ' ') {
		         spaceCount++;
		    }
		}
		WebElement menuXpath=findElement.searchClickableElement(By.xpath("//input[@placeholder='Search']"));
log.info("spaceCountspaceCountspaceCountspaceCountspaceCountspaceCountspaceCount------>"+spaceCount);
		for(int i=0;i<=spaceCount+2;i++) {
			menuXpath.sendKeys(Keys.CONTROL,Keys.BACK_SPACE);
		}
		
	
		menuXpath.sendKeys(menu);
		
		menuXpath.sendKeys(Keys.ENTER);
		
	}
	
	public String getOnlyNumbersFromAnElement(String xpath) {
		String req=	getTextFromAnElement(xpath);
		req=req.replaceAll("[^0-9]", "");
		return req;
	}
		public  String datePlusDays(String presentdate,long daysToAdd,String pattern) {
		
			 //DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
			log.info(presentdate);
			presentdate=presentdate.replace("/", "-");
			log.info(presentdate);
			log.info(daysToAdd);
			
			LocalDate date=LocalDate.parse(presentdate);

			LocalDate reqDate=date.plusDays(daysToAdd);
			log.info(reqDate);
			log.info(pattern);
			return reqDate.toString();
			
		}
		public void enterTab(String xpath) {
			findElement.searchClickableElement(By.xpath(xpath)).sendKeys(Keys.TAB);
		}
		
		
		public void enterTextUsinfExcelValue(String xpath,String query,String fieldName) {
		try {
			//log.info(filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, fieldName)+"--------->enterTextUsingExcelValue");
			
			enterText(xpath, filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, fieldName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
}
	
	
	
	
	
	
	
	
	
	
	

