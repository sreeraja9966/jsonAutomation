package helper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import testBase.TestBase;

public class BrowserHelper extends TestBase {
	private static final Logger log = Logger.getLogger(BrowserHelper.class);
	FindElement findElement=new FindElement();
	public void openUrl(String url) {
		d.get(url);
	}
	public void maximize() {
		d.manage().window().maximize();
	}
	public void goBack() {
		d.navigate().back();
		log.info("");
	}

	public void goForward() {
		log.info("press fprward button");
		d.navigate().forward();
		
	}

	public void refresh() {
		log.info("press refresh button");
		d.navigate().refresh();
		
	}

	public Set<String> getWindowHandlens() {
		log.info("retun windowhandle");
		return d.getWindowHandles();
	}

	public void switchToWindow(int index) {

		List<String> windowsId = new LinkedList<>(getWindowHandlens());

		if (index < 0 || index > windowsId.size()){
			throw new IllegalArgumentException("Invalid Index : " + index);
		}
		d.switchTo().window(windowsId.get(index));
		log.info("swithced to "+index+" index window");
	}

	public void switchToParentWindow() {
		List<String> windowsId = new LinkedList<>(getWindowHandlens());
		d.switchTo().window(windowsId.get(0));
		log.info("switched back to parent window");
	}

	public void switchToParentWithChildClose() {
		List<String> windowsId = new LinkedList<>(getWindowHandlens());

		for (int i = 1; i < windowsId.size(); i++) {
			log.info("Switched to "+windowsId.get(i));
			d.switchTo().window(windowsId.get(i));
			d.close();
			log.info("closed "+windowsId.get(i));
		}
log.info("switching to parent window");
		switchToParentWindow();
	}
	
	public   void switchToNewWindow(){
		try{
		
		for (String winHandle : d.getWindowHandles()) {
			log.info(winHandle);
		    d.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}}
		catch(Exception e){
			log.error("unable to switch to window",e);
			
		}
		}
	
	
	public void switchToFrame(String nameOrId) {
		d.switchTo().frame(nameOrId);
		log.info(nameOrId);
	}
	public  void switchToFrameByElement(String xpath){
		try {
			d.switchTo().frame(findElement.searchClickableElement(By.xpath(xpath)));
			log.info("Switched to frameByElement--->  "+xpath);
		} catch (Exception e) {

			log.error(xpath+"-->not found",e);
			
		}
	}

	public  void switchBackFromFrame(){
		try {
			d.switchTo().defaultContent();
			log.info("back from frame");
		} catch (Exception e) {
			
			log.error(e+"unable to switch back from frame");
			
		}
	}

public String getBrowserVersion() {
	
	 Capabilities cap = ((RemoteWebDriver) d).getCapabilities();
    String browserName = cap.getBrowserName().toLowerCase();
    String browserVersion=cap.getVersion();
   	return browserName+"/"+browserVersion;
	
}
}
