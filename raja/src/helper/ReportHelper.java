package helper;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import sysnik.BaseToHtml;
import testBase.TestBase;

public class ReportHelper extends TestBase{
	static  ExtentReports report;
	static ExtentTest logger;
	static ExtentTest child;
	static	String nameOfTheReport=null;
	DateHelper dateHelper = new DateHelper();
private static final Logger log = Logger.getLogger(ReportHelper.class);

	public  void setReportName(String nameOfTheRepor){
		nameOfTheReport=nameOfTheRepor;
		log.info("----------->    method called");
		report=new ExtentReports(relativePath()+"\\Reports/"+nameOfTheReport+".html");
	report.loadConfig(new File(relativePath()+"\\config.xml"));
	
	}
public  void startTest(String testName){
	
	logger=report.startTest(testName);
	
	
}
public  void ChildTest(String testName){
	child=report.startTest(testName);

}




public  void writeLogToReport(){
	report.flush();
}
public  void appendToExstingReport(String path){
	
	report=new ExtentReports(relativePath()+"\\Reports/"+nameOfTheReport+".html", false);
	logger=report.startTest(path);
	log.info(path+"   testcase is appended");
	
	report.loadConfig(new File(relativePath()+"\\config.xml"));
	
}
public  void writeLogInfo(String stepDetails){
	logger.log(LogStatus.INFO,stepDetails);
}
public  void writeLogInfoInChildTest(String stepDetails){
	try{
	child.log(LogStatus.INFO,stepDetails);
	}
	catch(Exception e){
		log.error(e);
	}
}
public  void writeLogInCaseOfPass(String stepDetails){
	logger.log(LogStatus.PASS,stepDetails);
}
public  void writeLogInCaseOfPassInChildTest(String stepDetails){
	child.log(LogStatus.PASS,stepDetails);
}
public  void writeLogInCaseOfFail(String stepDetails){
	logger.log(LogStatus.FAIL,stepDetails);
}
public  void writeLogInCaseOfFailInChildTest(String stepDetails){
	child.log(LogStatus.FAIL,stepDetails);
}
public  void writeLogInCaseOfSkip(String stepDetails){
	logger.log(LogStatus.SKIP,stepDetails);
}
public  void writeLogInCaseOfSkipInChildTest(String stepDetails){
	child.log(LogStatus.SKIP,stepDetails);
}

public  void endChild(){
	report.endTest(child);
	
	
	
	
}
public  void appendChild(){
	
	
	logger.appendChild(child);
	
	
}
public  void endParent(){
	
	
	report.endTest(logger);
	
}
public  void getcurrentRunStatus(){
	logger.getRunStatus();
}
public  void addScreenShotInReport(String name){
	String destination =null;
	
    //The below method will save the screen shot in d drive with name "screenshot.png"
	log.info("--------->addingScreenShotInReport");
	
	
	
	
	 try {
	 File scr = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
       
	
		 
	
		 
		 
	String date=dateHelper.getSystemDate("yyyy/MM/dd/hh/mm/ss/");
	
	 destination ="screenShots/"+date+name+".png";
	 File finalDestination = new File(relativePath()+"\\Reports\\screenShots/"+date+name+".png");
	 FileUtils.copyFile(scr, finalDestination);
	 } catch (Exception e) {
log.error(e);	}
	
	
	
	
	logger.log(LogStatus.INFO,"ScreentShot ::"+name+" "+logger.addScreenCapture(destination ));
}


public  void addScreenShotInReportInCaseOfChild(String name){
	String destination =null;
	
    //The below method will save the screen shot in d drive with name "screenshot.png"
	log.info("--------->addingScreenShotInReport");
	
	
	 try {
	 File scr = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
       
	log.info(scr);
		 
		 
		 
		 
	String date=dateHelper.getSystemDate("yyyy/MM/dd/hh/mm/ss/");
	
	 destination ="screenShots/"+date+name+".png";
	 
	 File finalDestination = new File(relativePath()+"\\Reports\\screenShots/"+date+name+".png");
	 FileUtils.copyFile(scr, finalDestination);
	 } catch (Exception e) {
log.error(e);}
	
	
	
	
	child.log(LogStatus.INFO,"ScreentShot ::"+name+" "+child.addScreenCapture(destination ));
}

public  void addScreenShotInCaseOfFailInReport(String name){
	String destination =null;
	
    
	log.info("--------->addingScreenShotInReport");
	
	
	 try {
	 File scr = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
       
		 
	String date=dateHelper.getSystemDate("yyyy/MM/dd/hh/mm/ss/");
	
	 destination ="screenShots/"+date+name+".png";
	 File finalDestination = new File(relativePath()+"\\Reports\\screenShots/"+date+name+".png");
	 FileUtils.copyFile(scr, finalDestination);
	 } catch (Exception e) {
	log.error(e);	}
	
	
	
	
	logger.log(LogStatus.FAIL,"ScreentShot ::"+name+" "+logger.addScreenCapture(destination ));
}
public void addScreenShotInCaseOfFailInReportInChild(String name){
	/*String destination =null;
	String name2=null;
	String date=null;
    //The below method will save the screen shot in d drive with name "screenshot.png"
	log.info("--------->addingScreenShotInReport");
	
	
	
	 try {
	 File scr = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
       
		 
	date=seleniumHelper.getSystemDate("yyyy/MM/dd/hh/mm/ss/");
	
	
	
		 name2=seleniumHelper.filterSpleChar(name);
		 destination ="screenShots/"+date+name2+".png";
     File finalDestination = new File(relativePath()+"\\Reports\\screenShots/"+date+name2+".png");
	 FileUtils.copyFile(scr, finalDestination);
	 } catch (Exception e) {
	log.error(e);	}*/
	 Base64Image base64Image = new Base64Image();
	child.log(LogStatus.FAIL,"ScreentShot :: "+name+child.addBase64ScreenShot(base64Image.getBase64Image(name) ));
}
}