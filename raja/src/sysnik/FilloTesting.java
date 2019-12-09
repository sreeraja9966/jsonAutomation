package sysnik;



import java.util.Date;

import org.testng.annotations.Test;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import helper.FilloExcelDataGetter;
import testBase.TestBase;
public class FilloTesting extends TestBase{
	@Test
public  void fil() throws FilloException {
		long startTime = System.nanoTime();
Fillo fillo=new Fillo();
FilloExcelDataGetter filloExcelDataGetter = new FilloExcelDataGetter();
//filloExcelDataGetter.connectedToExcelDataFile();
filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet("Select * from DrawingPowerEntry", "AccountNumber");
/*//filloExcelDataGetter.updateDatInaExcelSheetORInsert("update DrawingPowerEntry set updateColumn='UPDATED VALUE' where  Scenario='positive' and EffectiveDate='2019-02-22'");
for()
filloExcelDataGetter.updateDatInaExcelSheetORInsert("Insert into DrawingPowerEntry(AccountNumber,EffectiveDate,ExpiryDate,Scenario,updateColumn) values(1,2,3,4,5)");
*/


//filloExcelDataGetter.closeExcelConnection();

long endTime   = System.nanoTime();
long totalTime = endTime - startTime;
NANOSECONDS.toSeconds(totalTime);
System.out.println(totalTime+"--------->totalTime");
	}}
