package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.sun.jersey.core.util.Base64;

import testBase.TestBase;

public class Base64Image extends TestBase{

	/**
	 * getBase64Image convert's Image into a string which will reduces maintenance. 
	 * 
	 * @author sriraja.garlapati
	 */
	SeleniumHelper seleniumHelper = new SeleniumHelper();
	DateHelper dateHelper = new DateHelper();
	public String getBase64Image(String name) {
		String date=null;
		String destination=null;
		String name2=null;
		Date oDate = new Date();
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	    String sDate = simpleDateFormat.format(oDate);
	    String encodedBase64 = null;
	    FileInputStream fileInputStream = null;
	    TakesScreenshot screenshot = (TakesScreenshot) d;
	    File scr = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
	   	date=dateHelper.getSystemDate("yyyy/MM/dd/hh/mm/ss/");
		name2=seleniumHelper.filterSpleChar(name);
		destination ="screenShots/"+date+name2+".png";
	     File finalDestination = new File(relativePath()+"\\Reports\\screenShots/"+date+name2+".png");
	    try {
		    FileUtils.copyFile(scr, finalDestination);
		    fileInputStream =new FileInputStream(finalDestination);
	        byte[] bytes =new byte[(int)finalDestination.length()];
	
				fileInputStream.read(bytes);
			
	        encodedBase64 = new String(Base64.encode(bytes));

	    }catch (Exception e){
	       log.error(e);
	    }
	    return "data:image/png;base64,"+encodedBase64;
}
	}

