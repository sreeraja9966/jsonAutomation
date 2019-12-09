package sysnik;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;
import org.openqa.selenium.support.FindBy;
import helper.RepositoryHelper;
import testBase.TestBase;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
@Test
public class AutoRead extends TestBase {/*
	RepositoryHelper repositoryHelper = new RepositoryHelper();
	File file;
	HashMap<String,String> map2;
	Properties table=new Properties();
	public void loadPFile() throws IOException {
		file = new File(relativePath()+"\\StaticRepo\\"+"BrowserVersion.Properties");
		
		FileInputStream fi=new FileInputStream(file);
		
		table.load(fi);
		
		System.out.println(table.get("BrowserVersion"));
		Map<String, String> map = new HashMap();

		map.putAll(table.entrySet()
		                     .stream()
		                     .collect(Collectors.toMap(e -> e.getKey().toString(), 
		                                               e -> e.getValue().toString())));
		for (String name: map.keySet()){

            String key =name.toString();
            String value = map.get(name).toString();  
            System.out.println(key + " " + value);  
            String theFirst = "//tag[@id='']";
        	String sam="\"" + theFirst + "\"";
        	System.out.println(sam);
          

} 
		
		
		} public static String theFirst = "//tag[@id='']";
	@FindBy (xpath="theFirst"+"")
	public WebElement raja;
	
*/}
