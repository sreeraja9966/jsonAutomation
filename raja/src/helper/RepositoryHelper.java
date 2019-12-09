package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import testBase.TestBase;

public class RepositoryHelper extends TestBase {
	 File file;
	 private static final Logger log = Logger.getLogger(RepositoryHelper.class);
	public String loadPropertyFile(String fileName,String filepath,String Key) {
		Properties obj = new Properties();					
		log.info(relativePath()+filepath+fileName);
	    file=new File(relativePath()+filepath+fileName);
		 
		try {
			FileInputStream	objfile = new FileInputStream(file);
			obj.load(objfile);	
          log.info("value from property file is---->"+obj.getProperty(Key));
			}
		catch (Exception e) {
			log.error(e);	
			}									
	  return obj.getProperty(Key);
	}

	 
	 void saveProperties(Properties p) throws IOException
    {
        FileOutputStream fr = new FileOutputStream(file);
        p.store(fr, "Properties");
        fr.close();
           }

     void loadProperties(Properties p)throws IOException
    {
        FileInputStream fi=new FileInputStream(file);
        p.load(fi);
       // fi.close();
          }

    public  void writeRepoFile(String fileName,String value)throws IOException
    {
        try {
			file = new File(relativePath()+"\\StaticRepo\\"+fileName);
			Properties table = new Properties();
			table.setProperty("BrowserVersion",value);
			
			
			// saving the properties in file
			saveProperties(table);
			// changing the property
     
			
			// saving the properties in file
			saveProperties(table);
			// loading the saved properties
			loadProperties(table);
		} catch (Exception e) {
                             log.error(e);
		}
    }
}
