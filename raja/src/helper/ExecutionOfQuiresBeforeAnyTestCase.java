package helper;

import testBase.TestBase;

public class ExecutionOfQuiresBeforeAnyTestCase extends TestBase{

	FilloExcelDataGetter filloExcelDataGetter = new FilloExcelDataGetter();
	DbHelper dbHelper = new DbHelper();
	public String fetchQuiresFromExcel(String query,String fieldName) {
		return filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(query, fieldName);
	}
	
	public void executeQuriesFromExcel(String query,String fieldName) {
		String splitedQuires[]=fetchQuiresFromExcel(query, fieldName).split(",");
		for(String quires:splitedQuires) {
			log.info("%%%%%%%%%%%%%%%%%%%%%%%%"+quires+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			dbHelper.executeQuery(quires);
		}
	}
	
}
