package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.jline.internal.Log;
import org.testng.annotations.Test;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import testBase.TestBase;

public class DbHelper extends TestBase{
	private static final Logger log = Logger.getLogger(DbHelper.class);
	ReportHelper reportHelper = new ReportHelper();
FindElement findElement = new FindElement();
AssertionHelper assertionHelper = new AssertionHelper();
FilloExcelDataGetter  filloExcelDataGetter  = new FilloExcelDataGetter ();
public static Connection con=null;
public void connectionEstablishmentWithDb() {
	try{
		
		 Map<String, String> map = new HashMap<>();
		 BufferedReader in = new BufferedReader(new FileReader(relativePath()+"\\DataBaseSetup"));
	          String line = "";
	          String key=null;
	          String value=null;
	          String[] parts=null;
	        while ((line = in.readLine()) != null) {
	        	parts = line.split("=", 2);
	             if (parts.length >= 2)
	             {
	               key  = parts[0];
	               value  = parts[1];
	                 map.put(key, value);
	             } else {
	                 log.info("ignoring line: " + line);
	             }
	        }
		
		log.info("KEYSET------->"+map.keySet());
		String dbUrl =null;
		String username=null;
		String password =null;
		String dbName=null;

		log.info("DB URL------------->"+map.get("dataBaseURL"));
		log.info("DB Name------------->"+map.get("dataBaseUserID"));
		log.info("DB Password------------->"+map.get("dataBasePassWord"));
		
		dbUrl	=map.get("dataBaseURL").trim();           					
		username = map.get("dataBaseUserID").trim();            	
		password= map.get("dataBasePassWord").trim();      	
		
		dbName=map.get("typeofDb");
try {if("postgres".equalsIgnoreCase(dbName)){
	Class.forName("org.postgresql.Driver");
}
else if("oracle".equalsIgnoreCase(dbName)){
	Class.forName("oracle.jdbc.driver.OracleDriver");
}
else if("sql".equalsIgnoreCase(dbName)){
	Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
	
}
} catch (Exception e) {
log.error(e);
}			
DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
 con = DriverManager.getConnection(dbUrl,username,password);
 
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
	
	public  String connectToDb(String query) {	
		String myName=null;
		String query1=null;
		String dbName=null;
		query1=query.trim();
			log.info(query+"************QUERY**************");
			try{
			
		Statement stmt = con.createStatement();		
		log.info(stmt+"stmtstmtstmt");
		ResultSet rs=null;
		try {
			rs = stmt.executeQuery(query1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		log.info(rs.toString()+"--->result");
		
		String multipleResponse=null;
		String multipleResponse2=null;
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		try {
			while (rs.next()){
				 for (int i = 1; i <= columnsNumber; i++)	{	
				myName = rs.getString(i).trim();								        
			       			            	
			            multipleResponse=myName;
			            if(i==1) {
			            	multipleResponse2=myName;
			            }
			            if(i!=1) {
			            multipleResponse2=multipleResponse2+",".concat(multipleResponse);
			            //multipleResponse2=multipleResponse.concat(myName);
			            }
			            
			            log.info(multipleResponse2+"  ");
			         
			         	log.info(multipleResponse2+"--->multipleResponse2");
			         	multipleResponse=null;
			    }}
		} catch (Exception e) {
			
			log.error("query error",e);
		}	
		
		//con.close();		
		log.info(multipleResponse2+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@multipleResponse2");

			             	return multipleResponse2; 	
		}
		catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}
		return "fasak";}

public void executeQuery(String query) {
	String myName=null;
	String query1=null;
	String dbName=null;
	query1=query.trim();
	
		log.info(query+"************QUERY**************");
	try {
		Statement stmt = con.createStatement();		
		log.info(stmt+"stmtstmtstmt");
	 stmt.execute(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	public  void inputUsingDb(String xpath,String query){
		FindElement findElement = new FindElement();
		WebElement element=findElement.searchClickableElement(By.xpath(xpath));
	try {
		element.sendKeys(connectToDb(query));
	} 
	catch (Exception e) {
		log.error(e);	
		} 


	}
	
public void compareResultUsingDb(String query,String result) {
	
	String resultFromDb=connectToDb(query);
	String columnName=StringUtils.substringBetween(query.toUpperCase(), "SELECT", "FROM");
	String tableName=StringUtils.substringBetween(query.toUpperCase(), "FROM", "WHERE");
	System.out.println(columnName+tableName);
log.info(resultFromDb+"----------->resultFromDb");
	boolean test=result.equals(resultFromDb);
	
	if(test) {
		log.info("Expected Text---->"+result+ " is Matched with Db Values"+resultFromDb);
		reportHelper.writeLogInCaseOfPassInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text---->"+result+ " is Matched with Db Values"+resultFromDb);
	}
	else {
		reportHelper.writeLogInCaseOfFailInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text---->  "+result+" is NOT Matched with Db Values"+resultFromDb);
		reportHelper.addScreenShotInCaseOfFailInReportInChild("Expected Text---->"+result+ " is NOT Matched with Db Values"+resultFromDb);
	}
	
	
}

public void compareExcelResultUsingDb(String query,String queryToFetchresultFromExcel,String fieldName) {
	String result=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(queryToFetchresultFromExcel, fieldName);
	String resultFromDb=connectToDb(query);
	String columnName=StringUtils.substringBetween(query.toUpperCase(), "SELECT", "FROM");
	String tableName=StringUtils.substringBetween(query.toUpperCase(), "FROM", "WHERE");
log.info(resultFromDb+"----------->resultFromDb");
	boolean test=result.equals(resultFromDb);
	
	if(test) {
		log.info("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text---->"+result+ " is Matched with Db Values"+resultFromDb);
		reportHelper.writeLogInCaseOfPassInChildTest("Expected Text---->"+result+ " is Matched with Db Values"+resultFromDb);
	}
	else {
		reportHelper.writeLogInCaseOfFailInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text---->  "+result+" is NOT Matched with Db Values"+resultFromDb);
		reportHelper.addScreenShotInCaseOfFailInReportInChild("Expected Text---->"+result+ " is NOT Matched with Db Values"+resultFromDb);
	}
	
	
}

public void compareResultUsingDbUsingExcel(String query,String queryToFetchresultFromExcel,String fieldName) {
	
	String resultFromDb=connectToDb(query);
	String result=filloExcelDataGetter.getDataFromaColumnOfAnExcellSheet(queryToFetchresultFromExcel, fieldName);
	boolean test=result.equals(resultFromDb);
	String columnName=StringUtils.substringBetween(query.toUpperCase(), "SELECT", "FROM");
	String tableName=StringUtils.substringBetween(query.toUpperCase(), "FROM", "WHERE");
	
	if(test) {
		log.info("Expected Text---->"+result+ " is Matched with Db Values"+resultFromDb);
		reportHelper.writeLogInCaseOfPassInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text---->"+result+ " is Matched with Db Values"+resultFromDb);
	}
	else {
		reportHelper.writeLogInCaseOfFailInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text---->  "+result+" is NOT Matched with Db Values"+resultFromDb);
		reportHelper.addScreenShotInCaseOfFailInReportInChild("Expected Text---->"+result+ " is NOT Matched with Db Values"+resultFromDb);
	}
	
	
}


public void compareIntUsingDb(int result,String query) {
	String resultFromDb=connectToDb(query);
	String resultAsString=Integer.toString(result);
	boolean test=resultAsString.equals(resultFromDb);
	String columnName=StringUtils.substringBetween(query.toUpperCase(), "SELECT", "FROM");
	String tableName=StringUtils.substringBetween(query.toUpperCase(), "FROM", "WHERE");
	if(test) {
		log.info("Expected Text is--> "+resultAsString+" Matched with Db Values----> "+resultFromDb);
		reportHelper.writeLogInCaseOfPassInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text is--> "+resultAsString+" Matched with Db Values----> "+resultFromDb);
	}
	else {
		log.error("Expected Text is--> "+resultAsString+" NOT Matched with Db Values----> "+resultFromDb);
		reportHelper.writeLogInCaseOfFailInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text is--> "+resultAsString+" NOT Matched with Db Values----> "+resultFromDb);
		reportHelper.addScreenShotInCaseOfFailInReportInChild("DB Data not matched");
	}
	
	
}


public void verifyUiTextWithDb(String xpath,String query) {
	try {
		String resultFromDb=connectToDb(query);
		String columnName=StringUtils.substringBetween(query.toUpperCase(), "SELECT", "FROM");
		String tableName=StringUtils.substringBetween(query.toUpperCase(), "FROM", "WHERE");
		String textFromUI=findElement.searchClickableElement(By.xpath(xpath)).getText().trim();
		if(textFromUI.equals(null)||textFromUI.equals("")) {
			
			try {
				textFromUI=findElement.searchClickableElement(By.xpath(xpath)).getAttribute("ng-reflect-model").trim();
			} catch (Exception e) {
				textFromUI=findElement.searchClickableElement(By.xpath(xpath)).getAttribute("value").trim();
			}}
		boolean test=textFromUI.equalsIgnoreCase(resultFromDb);
		
		if(test) {
			log.info("Expected Text is-->"+textFromUI+" Which is Matched with Db Value--->"+resultFromDb);
			reportHelper.writeLogInCaseOfPassInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text is-->"+textFromUI+" Which is  Matched with Db Value--->"+resultFromDb);
		}
		else {
			assertionHelper.highlightElementInRedColour(xpath);
			log.error("Expected Text is-->"+textFromUI+" Which is NOT Matched with Db Value--->"+resultFromDb);
			reportHelper.writeLogInCaseOfFailInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text is-->"+textFromUI+" Which is NOT Matched with Db Value--->"+resultFromDb);
			reportHelper.addScreenShotInCaseOfFailInReportInChild("DB Data not matched");
			assertionHelper.changeElementColour(xpath);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}


public void verifyListOfUIElementsUsingDb(String FilePath,String Query) {
	try {	
	Properties table2=new SequencedProperties();
	File file = new File(relativePath()+FilePath+".properties");
	FileInputStream	 fi2=new FileInputStream(file);
	table2.load(fi2);
	List<String> lis=new ArrayList();
	HashMap<String, String>map = new LinkedHashMap<String,String>();
	for (final Entry<Object, Object> entry : table2.entrySet()) {
		map.put((String) entry.getKey(), (String) entry.getValue());
	      	lis.add((String) entry.getKey());
	   	
	}
	List<String> values =
		    lis.stream().map(map::get).collect(Collectors.toList());
	String ap;
	StringBuffer req=new StringBuffer();
	for(String val:values){
		if(!val.contains("select")) {
			WebElement ele=d.findElement(By.xpath(val));
		
		ap=ele.getText();
		
		req=req.append(ap).append(",");
		}
		else {
			Query=val;
		}
	}
	String actualResult=req.deleteCharAt(req.length()-1).toString();
	
	
	compareResultUsingDb(Query, actualResult);
	
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}

public void closeDbConnection() {
	try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void verifyUiAmountWithDb(String xpath,String query) {
	String textFromUI=findElement.searchClickableElement(By.xpath(xpath)).getText().trim();
	String columnName=StringUtils.substringBetween(query.toUpperCase(), "SELECT", "FROM");
	String tableName=StringUtils.substringBetween(query.toUpperCase(), "FROM", "WHERE");
	if(!textFromUI.equals(null)) {
	try {
		String resultFromDb;
		try {
			resultFromDb = connectToDb(query);
			if(resultFromDb.equals(null)||resultFromDb.equals("")) {
				resultFromDb="0";
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			resultFromDb="0";
		}
		
		
		if(textFromUI.equals(null)||textFromUI.equals("")) {
			
			try {
				textFromUI=findElement.searchClickableElement(By.xpath(xpath)).getAttribute("ng-reflect-model").trim();
			} catch (Exception e) {
				textFromUI=findElement.searchClickableElement(By.xpath(xpath)).getAttribute("value").trim();
			}}
		boolean test=
				false;
		test=textFromUI.equals(resultFromDb);
		
		if(test) {
			log.info("Expected Text is-->"+textFromUI+" Which is Matched with Db Value--->"+resultFromDb);
			reportHelper.writeLogInCaseOfPassInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text is-->"+textFromUI+" Which is  Matched with Db Value--->"+resultFromDb);
		}
		else {
			resultFromDb=resultFromDb.replace(",", "");
			textFromUI=textFromUI.replace(",", "");
			BigDecimal numberinIntFromDB=new BigDecimal(resultFromDb);
			BigDecimal numberinIntFromUI=new BigDecimal(textFromUI);
			
			if(numberinIntFromDB.compareTo(numberinIntFromUI)==0) {
				log.info("Expected Text is-->"+numberinIntFromUI+" Which is Matched with Db Value--->"+numberinIntFromDB);
				reportHelper.writeLogInCaseOfPassInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" Expected Text is-->"+numberinIntFromUI+" Which is  Matched with Db Value--->"+numberinIntFromDB);
						}
			else {
				log.error("actualText is :"+textFromUI+" expected text is: "+resultFromDb);
			assertionHelper.highlightElementInRedColour(xpath);
			log.error("Expected Text is-->"+textFromUI+" Which is NOT Matched with Db Value--->"+resultFromDb);
			reportHelper.writeLogInCaseOfFailInChildTest("Expected Text is-->"+textFromUI+" Which is NOT Matched with Db Value--->"+resultFromDb);
			reportHelper.addScreenShotInCaseOfFailInReportInChild("DB Data not matched");
			assertionHelper.changeElementColour(xpath);
				}
		}
	}

	 catch (Exception e) {
		reportHelper.writeLogInCaseOfFailInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" FAILED TO VERIFY UI AMOUNT WITH DB");
		e.printStackTrace();
	}
	}
	else {
		reportHelper.writeLogInCaseOfFailInChildTest("Comparing "+columnName+"column of "+tableName+"table"+" VAULE ON UI IS NULL");
	}
}

public void validateColumnsOfTwoTables(String queryOne,String queryTwo) {
	String resOfQueryOne=connectToDb(queryOne);
	String resOfQueryTwo=connectToDb(queryTwo);
	
	log.info(resOfQueryOne+"resOfQueryOneresOfQueryOneresOfQueryOneresOfQueryOne");
	log.info(resOfQueryTwo+"resOfQueryTworesOfQueryTworesOfQueryTworesOfQueryTwo");
assertionHelper.verifyTwoTexts(resOfQueryOne, resOfQueryTwo);

}

public void validateColumnsOfTwoTablesInCaseOfAmount(String queryOne,String queryTwo) {
	String resOfQueryOne=connectToDb(queryOne);
	String resOfQueryTwo=connectToDb(queryTwo);
	log.info(resOfQueryOne+"resOfQueryOneresOfQueryOneresOfQueryOneresOfQueryOne");
	log.info(resOfQueryTwo+"resOfQueryTworesOfQueryTworesOfQueryTworesOfQueryTwo");
assertionHelper.verifyTwoAmounts(resOfQueryOne, resOfQueryTwo);

}
public void verifyUiDateWithDB(String xpath,String query) {

	String resultFromDb=connectToDb(query);
	
	String textFromUI=findElement.searchClickableElement(By.xpath(xpath)).getText().trim();
	if(textFromUI.equals(null)||textFromUI.equals("")) {
		
		try {
			textFromUI=findElement.searchClickableElement(By.xpath(xpath)).getAttribute("ng-reflect-model").trim();
		} catch (Exception e) {
			textFromUI=findElement.searchClickableElement(By.xpath(xpath)).getAttribute("value").trim();
		}}
	boolean test=false;
	test=textFromUI.equals(resultFromDb);
	
	if(test) {
		log.info("Expected Text is-->"+textFromUI+" Which is Matched with Db Value--->"+resultFromDb);
		reportHelper.writeLogInCaseOfPassInChildTest("Expected Text is-->"+textFromUI+" Which is  Matched with Db Value--->"+resultFromDb);
	}
	
	else {
		textFromUI=textFromUI.replace("/", "-");
		test=textFromUI.equals(resultFromDb);
		if(test) {
			log.info("Expected Text is-->"+textFromUI+" Which is Matched with Db Value--->"+resultFromDb);
			reportHelper.writeLogInCaseOfPassInChildTest("Expected Text is-->"+textFromUI+" Which is  Matched with Db Value--->"+resultFromDb);
		}
		else {
			assertionHelper.highlightElementInRedColour(xpath);
			log.error("Expected Text is-->"+textFromUI+" Which is NOT Matched with Db Value--->"+resultFromDb);
			reportHelper.writeLogInCaseOfFailInChildTest("Expected Text is-->"+textFromUI+" Which is NOT Matched with Db Value--->"+resultFromDb);
			reportHelper.addScreenShotInCaseOfFailInReportInChild("DB Data not matched");
			assertionHelper.changeElementColour(xpath);
		}
	}
	
	
	

}


}
