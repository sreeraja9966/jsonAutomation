package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testBase.TestBase;

public class ExcelHelper extends TestBase{
	public  Workbook wb;
	public  Sheet sh; 
	public Row row;
	public  Cell cell;
	
	private static final Logger log = Logger.getLogger(ExcelHelper.class);
	public  void setExcelFile(String Path, String SheetName)  {

		try {
			log.info(Path+"--->Path"+" --->SheetName   "+SheetName);
			String fileType = Path.substring(Path.indexOf(".")); 
			File f = new File(Path);
			log.info("file----->"+f);
			log.info(fileType+"----> fileType");
			FileInputStream is = new FileInputStream(f);
			if(fileType.contains(".xlsx"))
				wb = new XSSFWorkbook(is);
			else 
				wb = new HSSFWorkbook(is);
			log.info("workbook---->  "+wb);
			sh=wb.getSheet(SheetName);
		} catch (Exception e){
				log.error(e);
		}
	}
	
	public  int getRowCount(String sheetName){
		int index = wb.getSheetIndex(sheetName);
		sh = wb.getSheetAt(index);
		return sh.getLastRowNum()+1;
		
		 
	}

	public  int getColumnCount(String sheetName){

		sh= wb.getSheet(sheetName);
		row = sh.getRow(0);
		
		return row.getLastCellNum();

	}
	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

	public  String getCellData(String Path, String SheetName,int RowNum, int ColNum) {

	try{
		setExcelFile(Path, SheetName);
		
			cell = sh.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getStringCellValue();
			log.info("Celldata from excel------->  "+CellData);
			if(CellData!=null)
			return CellData;

		}catch (Exception e){
			
	log.error("POI is failed to read data",e);
			
				
		}
	return"";
	}
	
	
	public  int getCellDataAsInt(String Path, String SheetName,int RowNum, int ColNum) {

		try{setExcelFile(Path, SheetName);
			
		cell = sh.getRow(RowNum).getCell(ColNum);
		
		String CellData = cell.getStringCellValue();
		
		return Integer.parseInt(CellData);
		
		 
		}
		catch (Exception e){
			
			log.error("POI is failed to read data",e);
			
				
		}
	return 0;
		
		
	}
	
	//This method is to write in the Excel cell, Row num and Col num are the parameters

	public  void setCellData(String Path,String Result,  int RowNum, int ColNum) throws Exception	{

	try{
			row  = sh.getRow(RowNum);
			cell = row.getCell(ColNum);

			if (cell == null) {
					cell = row.createCell(ColNum);
					cell.setCellValue(Result);
			
			} else {
					//Cell = Row.createCell(ColNum+2);
					cell.setCellValue(Result);
			}

	FileOutputStream fileOut = new FileOutputStream(Path);

	wb.write(fileOut);
	fileOut.flush();
	fileOut.close();

	}catch(Exception e){
		throw (e);
			}

		}


}
