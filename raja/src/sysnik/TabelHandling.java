/*package sysnik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import helper.DataProviderHelper;
import helper.WebTableHelper;
import testBase.TestBase;

public class TabelHandling extends TestBase {
	DataProviderHelper dataProviderHelper = new DataProviderHelper();
static TestBase testBase=new TestBase();
transferScreen.TestCase transferScreen=new transferScreen.TestCase();
WebTableHelper webTableHelper = new WebTableHelper();
List<String> expectedList=new ArrayList<>();
@BeforeClass
public void start() {
	transferScreen.starttest();
}
@Test (priority=3,dataProvider="dataOfTransferPositiveFlow")
public void insertion(String scenario,String accountType,String productType,String accNumber, String transAmount,String remarks,String instrmentTYpe,String instrumentNumber,String InstrumentBatchCode ) {
	

transferScreen.endToEndFlow(scenario, accountType, productType, accNumber, transAmount, remarks, instrmentTYpe, instrumentNumber, InstrumentBatchCode);



expectedList.add("Cr/Dr=Cr");
expectedList.add("Account Type=cd");
expectedList.add("Product Name=5sb");
expectedList.add("Account Number=102457");
expectedList.add("Remark=POSITIVE FLOW VERIFICATION");
expectedList.add("Cr/Dr=Cr");
expectedList.add("Account Type=cd");
expectedList.add("Product Name=5sb");
expectedList.add("Account Number=102457");
expectedList.add("Remark=POSITIVE FLOW VERIFICATION");
expectedList.add("Cr/Dr=Cr");
expectedList.add("Account Type=cd");
expectedList.add("Product Name=5sb");
expectedList.add("Account Number=3333");
expectedList.add("Remark=verification of table values");



List  col = d.findElements(By.xpath("//div[@class='table-responsive']/table/thead/tr/th"));
System.out.println("Total No of columns are : " +col.size());

List  row = d.findElements(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[1]"));
System.out.println("Total No of columns are : " +row.size());

for(int i=1;i<=col.size();i++) {
	System.out.println(d.findElement(By.xpath("//div[@class='table-responsive']/table/thead/tr/th["+i+"]")).getText());
	for(int j=1;j<=row.size();j++) {
		System.out.println(d.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr["+j+"]/td["+i+"]")).getText());
	}
}

}
@AfterClass
public void validateTable() {
	webTableHelper.validateTableValues("//div[@class='table-responsive']", expectedList);
}
@DataProvider(name="dataOfTransferPositiveFlow")
public Object[][] postData(){

	return dataProviderHelper.data(relativePath()+"\\datamaintenance\\TransferScreen\\datadrivertesting.xls","Sheet1");
}
	
	
	
}
*/