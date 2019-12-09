package sysnik;

import helper.DbHelper;
import testBase.TestBase;
import org.testng.annotations.Test;
public class DbSingleConnection extends TestBase{

	DbHelper  dbHelper = new DbHelper ();
	@Test
	public  void trdt() {
	
		dbHelper.connectToDb("select PRD_ID,LEDGER_BAL,AVAILABLE_BAL,SHADOW_BAL from ACCOUNT_MASTER where ACC_ID=50116");
	}
	
	
}
