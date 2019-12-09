package sysnik;


import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import helper.AssertionHelper;
import helper.BrowserHelper;
import helper.DataProviderHelper;
import helper.DateHelper;
import helper.FindElement;
import helper.ReportHelper;
import helper.SeleniumHelper;
import helper.WaitingHelper;
import testBase.TestBase;

public class calenderHamdling extends TestBase{
	BrowserHelper browserHelper=new BrowserHelper();
	ReportHelper reportHelper=new ReportHelper();
	AssertionHelper assertionHelper=new AssertionHelper();
	WaitingHelper waitingHelper=new WaitingHelper();
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	FindElement name = new FindElement();
	DataProviderHelper dataProviderHelper = new DataProviderHelper();
	    private String today;
	 
	    //Setup Driver
	    @BeforeClass
	    public  void setupTest() {
	    	browserHelper.strartBrowser("chrome");
	        
	        d.navigate().to("https://www.airasia.com/booking/home/en/gb");
	        d.manage().window().maximize();
	    }

           @Test
	    public void datePickerTest() {
	        //Declare a implicit wait for synchronisation
	   
	        WebElement table= name.searchClickableElement(By.xpath("//input[@id='home-depart-date-heatmap']"));
	   	 String fullTable="//tbody[@class='ng-tns-c28-2']";
	        DateHelper dateHelper = new DateHelper();
	        dateHelper.enterCurrentDate("//input[@id='home-depart-date-heatmap']", fullTable);
	        /*//Get Today's number
	        today = getCurrentDay();
	        System.out.println("Today's number: " + today + "\n");
	 
	        //Click and open the datepickers
	     
	        //This is from date picker table
	//        WebElement dateWidgetFrom = d.findElement(By.xpath("//tbody[@class='ng-tns-c28-2']"));
	 
	        //This are the rows of the from date picker table
	        //List<WebElement> rows = dateWidgetFrom.findElements(By.tagName("tr"));
	 
	        //This are the columns of the from date picker table
	        List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
	 
	        //DatePicker is a table. Thus we can navigate to each cell
	        //and if a cell matches with the current date then we will click it.
	        for (WebElement cell: columns) {
	            
	            //If you want to click 18th Date
	            if (cell.getText().equals("18")) {
	            
	            //Select Today's Date
	            if (cell.getText().equals(today)) {
	                cell.click();
	                break;
	            }
	        }
	 
	        //Wait for 4 Seconds to see Today's date selected.
	        try {
	            Thread.sleep(4000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }*/
	    }
	 
	    //Close Driver
	   /* @AfterClass
	    public static void quitDriver() {
	        d.quit();
	    }*/
	 
	    //Get The Current Day
	    private String getCurrentDay (){
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
}
