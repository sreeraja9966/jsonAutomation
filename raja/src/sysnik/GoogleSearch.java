package sysnik;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import helper.SeleniumHelper;
import helper.WebTableHelper;
import testBase.TestBase;
public class GoogleSearch extends TestBase {
	
	 public static void main(String[] args) throws Exception 
	    {
		 
		 System.out.println(getRandomString(155));
		 System.out.println(getRandomNumber(25));
	    }
	 public static String getRandomString(int size) {
	        return getRandom(size, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
	    }

	    public static String getRandomNumber(int size) {
	        return getRandom(size, "0123456789");
	    }
	    public static String getRandom(int size, String input) {
	        StringBuilder sb = new StringBuilder();
	        while (sb.length() < size)
	            sb.append(input.charAt(getRandomIndex(input.length())));
	        return sb.toString();
	    }
	    public static int getRandomIntBetween(int min, int max) {
	        return new java.util.Random().nextInt(max + 1 - min) + min;
	    }

	    public static int getRandomIndex(int size) {
	        return getRandomIntBetween(0, size - 1);
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Test
	public  void s() throws Exception {
MyCacheWriter myCacheWriter = new MyCacheWriter();
		TestBase testBase=new TestBase();
		testBase.strartBrowser("chrome");
	  
		d.get("https://www.google.com/");
		
		SeleniumHelper seleniumHelper = new SeleniumHelper();
		
		myCacheWriter.setCache("GmailText","//a[contains(text(),'Gmail')]");
		myCacheWriter.setCache("ImageText","//a[contains(text(),'Images')]");
		System.out.println(myCacheWriter.getCache("ImageText"));
		
		d.findElement(By.xpath("//input[@id='search-input-location']")).sendKeys("lon");
		seleniumHelper.searchUsingAutoComplete("//ul[@id='ui-id-1']//li", "Londonderry");
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		// locate the drop area
		WebElement droparea = d.findElement(By.cssSelector("#holder"));
		WebElement ele= d.findElement(By.cssSelector("#holder"));
		Point point = ele.getLocation();
		int xcord = point.getX();
		int ycord = point.getY();
		System.out.println(xcord+ycord);
		// drop the file
	CopyPaste name = new CopyPaste();
	File file=new File("C:\\Users\\sriraja.garlapati\\Desktop\\test.png");
	System.out.println(file);
	name.DropFile(file, droparea,xcord,ycord);
		
		
		
		
		
		
		
		
		
		
		*//**
		 * Example for Visibility of Elements located by
		 *//*
		SeleniumHelper seleniumHelper = new SeleniumHelper();
		seleniumHelper.searchUsingAutoComplete("//ul[@role='listbox']//li", "selenium download");
		WebdWait wait = new WebdWait(d,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@role='listbox']//li")));
		
		List<WebElement> list = d.findElements(By.xpath("//ul[@role='listbox']//li"));
		
		System.out.println("Auto Suggest List ::" + list.size());
		int i;
		for( i = 0 ;i< list.size();i++)
		{
			System.out.println(list.get(i).getText());
			
			if(list.get(i).getText().equals("selenium"))
			{
				list.get(i).click();
				break;
			}
			if(i==list.size()-1) {throw new ElementNotVisibleException("Element not found with that text"); 
				
			}
					}d.close();d.quit();
		
}*/}
