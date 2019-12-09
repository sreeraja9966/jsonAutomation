package sysnik;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.Map.Entry;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import autoPick.AutoPicking;

import org.apache.velocity.runtime.directive.Foreach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import helper.DbHelper;
import helper.ReportHelper;
import helper.SequencedProperties;
import testBase.TestBase;
public class TestingAListOfDataBaseValues extends TestBase{
	 DbHelper dbHelper = new DbHelper();
	 TestBase testBase = new TestBase();
	Properties table=null;
	 Properties table2=null;
	 File file=null;
	 ReportHelper reportHelper = new ReportHelper();
	 @BeforeClass
		public void test() {
			strartBrowser("chrome");
			d.manage().window().maximize();
			d.get("https://www.google.com/");
			reportHelper.setReportName("Deposit screen Testing");
			reportHelper.startTest("Deposit Screen Verification");
			reportHelper.ChildTest("login");
			reportHelper.writeLogInfoInChildTest("URl opened");
			reportHelper.endChild();
			reportHelper.appendChild();
		
				}
	 
	 
	 
	 
	 
	 @Test
public  void main() throws IOException, InterruptedException {
		
AutoPicking autoPicking = new AutoPicking();
//autoPicking.loadPropertyFile("//datamaintenance//DepositScreen//ScenarioOne");


	JavascriptExecutor js = (JavascriptExecutor) d;
	js.executeScript("arguments[0].setAttribute('style', arguments[1]);", d.findElement(By.xpath("//a[contains(text(),'Gmail')]")),
			"color: black; background-color:#ffd351; border: 2px solid Red");
	Thread.sleep(250);
	//js.executeScript("arguments[0].setAttribute('style', arguments[1]);", d.findElement(By.xpath("//a[contains(text(),'Gmail')]")), "");


		// dbHelper.verifyListOfUIElementsUsingDb("//datamaintenance//DepositScreen//ScenarioOne", "select PRD_ID,LEDGER_BAL,AVAILABLE_BAL,SHADOW_BAL from ACCOUNT_MASTER WHERE ACC_NO=1010201213000002");
	/*table2=new SequencedProperties();
	file = new File(relativePath()+"//datamaintenance//DepositScreen//ScenarioOne"+"//locator.properties");
	FileInputStream	 fi2=new FileInputStream(file);
	table2.load(fi2);	
	List<String> lis=new ArrayList();
	HashMap<String, String>map = new LinkedHashMap<String,String>();
	for (final Entry<Object, Object> entry : table2.entrySet()) {
		map.put((String) entry.getKey(), (String) entry.getValue());
	      	lis.add((String) entry.getKey());
	   	
	}
	List<String> values =
		    lis.stream()
		        .map(map::get)
		        .collect(Collectors.toList());
	

	testBase.strartBrowser("chrome");
	d.get("https://www.google.com/");

String ap;
StringBuffer req=new StringBuffer();
for(String val:values){
	
		WebElement ele=d.findElement(By.xpath(val));
	
	ap=ele.getText();
	
	req=req.append(ap).append(",");
	
}
	
	String result=dbHelper.connectToDb("select PRD_ID,LEDGER_BAL,AVAILABLE_BAL,SHADOW_BAL from ACCOUNT_MASTER WHERE ACC_NO=1010201213000002");
	System.out.println("req----->  "+req);
	System.out.println("result--->"+result);
	System.out.println("actual----->  "+req.deleteCharAt(req.length()-1));

boolean test=req.deleteCharAt(req.length()-1).equals(result);
	System.out.println(test);*/
	
}
	 @AfterClass
		
		public void tearDown() {
			reportHelper.endParent();
			reportHelper.writeLogToReport();
			/*d.close();
			d.quit();
			*/
			
		}
}
