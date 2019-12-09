package sysnik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.TestBase;

public class FilterSplChar extends TestBase{

	public static void main(String[] args) {
		
		 String c= "ELEMENT NOT FOUNDProxy element for: DefaultElementLocator 'By.xpath: //button[contains(text(),'Save')]'";
	        Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
	        Matcher match= pt.matcher(c);
	        while(match.find())
	        {
	            String s= match.group();
	        c=c.replaceAll("\\"+s, "");
	        }
	        System.out.println(c);
	}
}
