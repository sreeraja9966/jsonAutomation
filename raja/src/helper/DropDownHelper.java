package helper;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import testBase.TestBase;

public class DropDownHelper extends TestBase{
	ReportHelper reportHelper = new ReportHelper();
	private static final Logger log = Logger.getLogger(DropDownHelper.class);
	FindElement findElement = new FindElement();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	FilloExcelDataGetter filloExcelDataGetter = new FilloExcelDataGetter();
	public void selectByElement(String element,String text) {
		WebElement ele=findElement.searchClickableElement(By.xpath(element));
		Select select = new Select(ele);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<>();
		log.info(elementList.size()+"=====================> size of dropdown list");
		for (WebElement dropEle : elementList) {
		
			log.info(dropEle.getText());
			valueList.add(dropEle.getText());
			if(text.equalsIgnoreCase(dropEle.getText().trim())) {
				SelectUsingVisibleText(element, dropEle.getText());
			}
		}
	}
	public String getSelectedValue(String xpath) {
		WebElement element=findElement.searchClickableElement(By.xpath(xpath));
		String value = new Select(element).getFirstSelectedOption().getText();
		log.info("WebELement : " + element + " Value : "+ value);
		return value;
	}
	
	public void SelectUsingIndex(String element,int index) {
		try {
			WebElement ele=findElement.searchClickableElement(By.xpath(element)); 
			Select select = new Select(ele);
			select.selectByIndex(index);
			log.info("Locator : " + element + " Value : " + index);
		
		} catch (Exception e) {
			reportHelper.addScreenShotInCaseOfFailInReportInChild(index+":: not found in the dropdown");
			log.error(e);
			
		}
	}
	
	public void SelectUsingVisibleText(String element,String text) {
		try {
			WebElement ele=findElement.searchClickableElement(By.xpath(element)); 
			Select select = new Select(ele);
			select.selectByVisibleText(text);
			log.info("Locator : " + element + " Value : " + text);
		
		} catch (Exception e) {
			reportHelper.addScreenShotInCaseOfFailInReportInChild(text+":: not found in the dropdown");
			log.error(e);
			
		}
	}
	
	public void SelectUsingVisibleTextWithExcelText(String element,String excelQuery,String fieldName) {
		String text=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(excelQuery, fieldName);
		try {
			WebElement ele=findElement.searchClickableElement(By.xpath(element)); 
			
			Select select = new Select(ele);
			select.selectByVisibleText(text);
			log.info("Locator : " + element + " Value : " + text);
		
		} catch (Exception e) {
			reportHelper.addScreenShotInCaseOfFailInReportInChild(text+":: not found in the dropdown");
			log.error(e);
			
		}
	}
	
	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<>();
		
		for (WebElement element : elementList) {
			log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
}
