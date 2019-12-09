package testBase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import helper.RepositoryHelper;

public class TestBase {
	public static WebDriver d;
	public  String localDir;
	public static  Logger log=Logger.getLogger(TestBase.class);


	public  WebDriver strartBrowser(String browser){
		 PropertyConfigurator.configure("Log4j.properties");
		log.info("-------> browser called");
		if(browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver",relativePath()+"\\Drivers\\geckodriver.exe");
			// - Chrome.lnk
			 d = new FirefoxDriver();
			log.info(browser+"---->Browser started");
			return d;
				}
		else if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", relativePath()+"\\Drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			/*String userProfile="C:\\Users\\sriraja.garlapati\\AppData\\Local\\Google\\Chrome\\User Data";
			options.addArguments("port=8080");
			options.addArguments("user-data-dir="+userProfile);
			 options.addArguments("profile-directory=Profile 2");*/
			// add parameter which will disable the extension
			options.addArguments("--disable-extensions");
			 
			// Start the chrome session
			 d = new ChromeDriver(options);
			 d.manage().window().maximize();
			 log.info(browser+"---->Browser started");
			return d;
			}
		else if(browser.equalsIgnoreCase("ie")){
			try{
			System.setProperty("webdriver.ie.driver", relativePath()+"\\Drivers\\IEDriverServer.exe");
			d= new InternetExplorerDriver();
			log.info(browser+"---->Browser started");
			return d;}
			catch(Exception e){
				log.error(e);
			}
			
			}
		else {
			
			log.info("browser not matched");
		}
		return null;
	}

public  String relativePath(){
	localDir = System.getProperty("user.dir");
	return localDir;
}


}
