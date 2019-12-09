package sysnik;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import helper.AssertionHelper;
import helper.DbHelper;
import testBase.TestBase;

public class QueryTesting extends TestBase {
DbHelper dbHelper = new DbHelper();
TestBase testBase=new TestBase();
AssertionHelper assertionHelper = new AssertionHelper();

	@Test
	public void fireQuery() {
		try {
			//testBase.strartBrowser("chrome");
		dbHelper.connectionEstablishmentWithDb();
	String result=dbHelper.connectToDb("select PRD_ID from ACCOUNT_MASTER where ACC_ID=53835");
	System.out.println("result--->"+result);
	String result2=dbHelper.connectToDb("select PRD_ID from ACCOUNT_MASTER where ACC_ID=53838");
	System.out.println("result222222222--->"+result2);
//String wrong=dbHelper.connectToDb("SELECT EFF_DATE  FROM DP_REGISTER WHERE ACC_ID =54108");
	//System.out.println(wrong+"+++++++++++++++>wrong");
	
	dbHelper.compareResultUsingDb("SELECT EFF_DATE  FROM DP_REGISTER WHERE ACC_ID =54108", "2019-02-22");
		
		
		
	dbHelper.closeDbConnection();
boolean test="113,18000.00,50000.00,8000.00".equals(result);
	System.out.println(test);
			
			/*String query="SELECT EFF_DATE  FROM DP_REGISTER WHERE ACC_ID =54108";
			System.out.println(StringUtils.substringBetween(query.toUpperCase(), "SELECT", "FROM"));
			*/
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
