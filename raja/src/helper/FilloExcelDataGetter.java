package helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.python.antlr.ast.While;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import testBase.TestBase;

public class FilloExcelDataGetter extends TestBase{
	
	Fillo fillo=new Fillo();
	Connection connection;
	Recordset recordset;
	public void connectedToExcelDataFile() {
		try {
			connection=fillo.getConnection("D:\\Test.xls");
		} catch (FilloException e) {
			
			e.printStackTrace();
		}
	}
	
	public String getDataFromaColumnOfAnExcellSheet(String query,String fieldName) {

	
		 try {
			connectedToExcelDataFile();
			recordset=connection.executeQuery(query.trim());
					
				while(recordset.next()) {
					
					return recordset.getField(fieldName.trim());
							
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
	 
		
		
	
		
	
		
	}
	
	public void updateDatInaExcelSheetORInsert(String strQuery) {
	
			try {
				connection.executeUpdate(strQuery);
			} catch (FilloException e) {
				
				e.printStackTrace();
			}
			}
	
	
	
	public void closeExcelConnection() {
		recordset.close();
		connection.close();
	}
}
