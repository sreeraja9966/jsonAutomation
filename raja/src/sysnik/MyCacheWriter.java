package sysnik;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import helper.FindElement;
import testBase.TestBase;

public class MyCacheWriter extends TestBase{
FindElement findElement = new FindElement();
Map<String,String> map=new HashMap();
	public  void setCache(String key,String xpath) {
		
		String reqText=findElement.searchClickableElement(By.xpath(xpath)).getText();
	
		map.put(key,reqText);
		
		
	}
	
	public String getCache(String key) {
		return map.get(key);
	}
}
