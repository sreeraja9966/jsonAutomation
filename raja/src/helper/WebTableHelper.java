package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import autoPick.AutoPicking;
import testBase.TestBase;

public class WebTableHelper extends TestBase{
	private static final Logger log = Logger.getLogger(WebTableHelper.class);
ReportHelper reportHelper = new ReportHelper();
AssertionHelper assertionHelper = new AssertionHelper();
FindElement findElement=new FindElement();
RepositoryHelper repositoryHelper = new RepositoryHelper();
HashMap<String,String> map;
String[] key;
String key1;
String key2;
List<String> leftColValues;
List<String> rightColValues;
List<String> tableDatafromPropertyFile = null;
	public List<String> getDataFromWebTable(String xpath) {
	
		List<String> tableData = null;
		try {
			List<WebElement>  col = d.findElements(By.xpath(xpath+"/table/thead/tr/th"));
			List<WebElement>  row = d.findElements(By.xpath(xpath+"/table/tbody/tr/td[1]"));
			String headerValue=null;
			String colValue=null;
			log.info("no.of col--------> "+col.size());
			log.info("no.of row--------> "+row.size());
			tableData = new ArrayList<>();
			for(int i=1;i<=row.size();i++) {
				
				for(int j=1;j<=col.size();j++) {
					headerValue=findElement.searchClickableElement(By.xpath((xpath+"/table/thead/tr/th["+j+"]"))).getText().replaceAll("\\s+","");
					colValue=findElement.searchClickableElement(By.xpath(xpath+"/table/tbody/tr["+i+"]/td["+j+"]")).getText().replaceAll("\\s+","");
					tableData.add(headerValue+"="+colValue);		
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableData;

	}
	
public List<String> getTableDataFromPropertyFile(String xpath,String locatorFile,Properties pro) {
		
		
		try {
			List<WebElement>  col = d.findElements(By.xpath(xpath+"/table/thead/tr/th"));
			List<WebElement>  row = d.findElements(By.xpath(xpath+"/table/tbody/tr/td[1]"));
			File directory = new File(relativePath()+locatorFile);
			// int fileCount=directory.list().length;
			 List<String> propertyFiles = new ArrayList<>();
			 for(File file:directory.listFiles()) {
				 if(file.getName().endsWith(".properties")) {
					 propertyFiles.add(file.getName());
				 }
			 }
			 AutoPicking autoPicking = new AutoPicking();
		map=autoPicking.loadingPropertyFile(locatorFile, (propertyFiles.size()-2));
		  leftColValues=new ArrayList<>();
		  rightColValues=new ArrayList<>();
		for (String name: map.keySet()){

	         key =name.toString().split("_");
	         
	        key1=key[0];
	        key2=key[1];
	        leftColValues.add(key1);
	        rightColValues.add(key2);
	        }
			String headerValue=null;
			String headerValueWithoutSpace=null;
			
			log.info("no.of col--------> "+col.size());
			log.info("no.of row--------> "+row.size());
			
			tableDatafromPropertyFile=new ArrayList<>();
			
				for(int j=1;j<=col.size();j++) {
					headerValue=findElement.searchClickableElement(By.xpath((xpath+"/table/thead/tr/th["+j+"]"))).getText();
					log.info(j+headerValue+"-------->headerValue");
					headerValueWithoutSpace =headerValue.replaceAll("\\s+","");
					log.info(headerValueWithoutSpace+"==========================>headerValueWithoutSpace");
					for(int k=1;k<leftColValues.size();k++) {
						
						 if (!"label".equalsIgnoreCase(rightColValues.get(k))) {
					if((leftColValues.get(k).equalsIgnoreCase(headerValueWithoutSpace))) {
						log.info(k+pro.getProperty(headerValueWithoutSpace+"_"+(rightColValues.get(k)))+"===========> Value from property file");
							tableDatafromPropertyFile.add(headerValue+"="+pro.getProperty(headerValueWithoutSpace+"_"+(rightColValues.get(k))));
							  }
				}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tableDatafromPropertyFile;

	}

/*
 * @param it require a filePath where the expected result is listed
 * 1) this should be a property file
 * 2) it should contain all expected result row wise
 * 3) the should be saved in another folder(not in locator.properties file folder)
 */
public List<String> getTableDataFromTextFile(String fileName){
	
	
	
	Properties tablePro=new SequencedProperties();
	File tableFile=new File(relativePath()+fileName+".properties");
	
	FileInputStream fi;
	
	try {
		fi = new FileInputStream(tableFile);
		tablePro.load(fi);
		map = new LinkedHashMap<String,String>();
		for (final Entry<Object, Object> entry : tablePro.entrySet()) {
	       map.put((String) entry.getKey(), (String) entry.getValue());
		 }
	
	} catch (Exception e) {
	log.error(e);
	
	}
	
	List<String> leftDataFromTableFile=new ArrayList<>();
	
	List<String> actuallTableList=new ArrayList<>();
	for (String name: map.keySet()){

		leftDataFromTableFile.add(name);
       }
	log.info("no.of col--------> "+leftDataFromTableFile.size());

	
	for(int i=0;i<leftDataFromTableFile.size();i++) {
		actuallTableList.add(leftDataFromTableFile.get(i)+"="+map.get(leftDataFromTableFile.get(i)));
	}
	
	return actuallTableList;
	
}
	
	public void validateTableValues(String xpath,String locatorFile,Properties pro,int i) {
		
		if(!"".equals(locatorFile)) {
		try {
			
			assertionHelper.verifyListOfElements(getDataFromWebTable(xpath), getTableDataFromPropertyFile(xpath, locatorFile,pro));
		} catch (Error e) {log.error("values not matched");}
		}
	}
	
	/*
	 * @param xpath of the table (only div tag example:- //div[@class='value']
	 * @param filePath of the expected values listed file
	 */
public void validateTableValuesUsingExternalFile(String xpath,String filePath) {
		
		if(!"".equals(filePath)) {
		try {
			
			assertionHelper.verifyListOfElements(getDataFromWebTable(xpath), getTableDataFromTextFile(filePath));
		} catch (Error e) {log.error("values not matched");}
		}
	}
/**
 * 
 * @param xpath://div[@id='userwiseDenominationModel']/table/tbody/tr/td[3]
 * @return
 */

public List<WebElement> getListOfaTableColumn(String xpath){
	findElement.searchClickableElement(By.xpath(xpath));
	List<WebElement>  row=null;
		row=new ArrayList<>();
	row = d.findElements(By.xpath(xpath));

	
	return row;
}




public int getSumOfaWebElementList(String xpath) {
	Integer sum = null;
	List<WebElement> listOfWebElements=null;
	List<String> textAsString=null;
	List<Integer> textAsInt=null;
	try {
		listOfWebElements=new ArrayList<>();
		listOfWebElements	=getListOfaTableColumn(xpath);
	
		textAsString=new ArrayList<>();

		for(int i=0;i<listOfWebElements.size()-1;i++)
		 {
			
	textAsString.add(listOfWebElements.get(i).getText());
	}
		
textAsInt=new ArrayList<Integer>();
for(String s : textAsString) {
	if(!s.equals("-")&&!s.equals("")) {
		
		textAsInt.add(Integer.valueOf(s));		
	}

}
		List<Integer> integers = textAsInt;
		sum = integers.stream().collect(Collectors.summingInt(Integer::intValue));
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return sum;
}


}
