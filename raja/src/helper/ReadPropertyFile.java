package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

import testBase.TestBase;

public class ReadPropertyFile extends TestBase {
	
public String  getValueFromPropertyFile(String filePath,String key) {
	String value=null;
	try {
		File file = new File(relativePath()+filePath+".properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();

		Enumeration enuKeys = properties.keys();
		
			value = properties.getProperty(key);
			
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return value;
}


}
