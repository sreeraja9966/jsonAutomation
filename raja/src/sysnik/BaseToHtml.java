package sysnik;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.sun.jersey.core.util.Base64;

import testBase.TestBase;

public class BaseToHtml extends TestBase{
	public  String image() throws Exception {
		 Date oDate = new Date();
		    SimpleDateFormat oSDF = new SimpleDateFormat("yyyyMMddHHmmss");
		    String sDate = oSDF.format(oDate);
		    String encodedBase64 = null;
		    FileInputStream fileInputStream = null;
		    TakesScreenshot screenshot = (TakesScreenshot) d;
		    File source = screenshot.getScreenshotAs(OutputType.FILE);
		    String destination =System.getProperty("user.dir")+"\\Foldername\\target\\cucumber-reports\\"+"Screenshot_" + sDate + ".png";
		    File finalDestination = new File(destination);
		    FileUtils.copyFile(source, finalDestination);

		    try {
		        fileInputStream =new FileInputStream(finalDestination);
		        byte[] bytes =new byte[(int)finalDestination.length()];
		        fileInputStream.read(bytes);
		        encodedBase64 = new String(Base64.encode(bytes));

		    }catch (FileNotFoundException e){
		        e.printStackTrace();
		    }
		    return "data:image/png;base64,"+encodedBase64;
	}
}
