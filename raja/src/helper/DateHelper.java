package helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import testBase.TestBase;
public class DateHelper extends TestBase {
	FindElement findElement = new FindElement();
	public  String getSystemTime(){
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		
		return dateFormat.format(date);
	}
public String getCurrentDay (){
    //Create a Calendar Object
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

    //Get Current Day as a number
    int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
    System.out.println("Today Int: " + todayInt +"\n");

    //Integer to String Conversion
    String todayStr = Integer.toString(todayInt);
    System.out.println("Today Str: " + todayStr + "\n");

    return todayStr;
}
public  String getSystemDate(String sampleDateFormat) {
		try {
			String date1=sampleDateFormat.toLowerCase();
			String date2=null;
			String date3=null;
			if(date1.contains("mm")){
				date2=date1.replace("mm","MM");	
				date3=date2.replace("m","M");
				
			}
			DateFormat dateFormat = new SimpleDateFormat(date3);
			Date date = new Date();
			return dateFormat.format(date);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

public void enterSystemDate(String xpath,String dateFormat) {
	try {
		log.info("systemdate-->"+getSystemDate(dateFormat));
		findElement.searchClickableElement(By.xpath(xpath)).sendKeys(getSystemDate(dateFormat));
	} catch (Exception e) {
	log.error(e);
	}
	
}

public void enterStaticDateForCBSCalender(String xpath) {
	try {
		findElement.searchClickableElement(By.xpath(xpath)).clear();
		findElement.searchClickableElement(By.xpath(xpath)).sendKeys("13"+"Jan"+Keys.ARROW_RIGHT+"2018");
	
	} catch (Exception e) {
	log.error(e);
	}
	
}
public void enterCurrentDate(String dateEntryLocation,String fulltableLoactor) {
	/**
	 * Auto pic the current Day from the calender
	 */
String today = getCurrentDay();
     findElement.searchClickableElement(By.xpath(dateEntryLocation)).click();
     log.info(dateEntryLocation+"----------->clicked");
     WebElement dateWidgetFrom =  findElement.searchClickableElement(By.xpath(fulltableLoactor));
     List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
     for (WebElement cell: columns) {
         if (cell.getText().equals(today)) {
             cell.click();
             break;
         }
     }
    
}
	
}
