package sysnik;

import org.apache.log4j.Logger;

import helper.DbHelper;
import helper.ExecutionOfQuiresBeforeAnyTestCase;
import helper.FilloExcelDataGetter;
import testBase.TestBase;

public class SplitingStringWithMultioccurance extends TestBase{
	private static final Logger log = Logger.getLogger(SplitingStringWithMultioccurance.class);
public static void main(String[] args) {
	String a="1,23,300,4000";
	String b[]=a.split(",");
	for(String ab:b) {
		System.out.println(ab);
		
	}
	
	ExecutionOfQuiresBeforeAnyTestCase filloExcelDataGetter = new ExecutionOfQuiresBeforeAnyTestCase();

filloExcelDataGetter.executeQuriesFromExcel("select * from Adhoc_limit where Scenario='positive'", "ExecuteQuires");
	
	
	}
}
