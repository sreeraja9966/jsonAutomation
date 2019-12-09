/*package sysnik;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class ElementFinder extends TestBase{

	private static JavascriptExecutor jse;
	private static final Logger logger = Logger.getLogger(ElementFinder.class);
	private By elementProperty;
	private int elementId;
	private int x_loc;
	private int y_loc;
	
public void setElementProp(By value) {
		logger.debug("Property set :" + value);
		this.elementProperty=value;
	}

	public By getElementProperty() {
		logger.info("getElementProperty .. : "+elementProperty);
		return this.elementProperty;
	}
	
	public void setElementId(int elementid) {
		logger.debug("Element Id = " + elementid);
		this.elementId=elementid;
	}
	
	public int getElementId() {
		logger.info("Element Id =" + elementId);
		return this.elementId;
	}
	
	public WebElement verify_IB_Element(WebDriver driver, String PageName, String strObjectName, String textToReplace)
			throws Exception {
		WebElement WebElement = null;
		List<WebElement> WebElelst;
		String tableName = Config.DB_IB_REPO_TABLE;
		String locator = getLocator(PageName, strObjectName, tableName);
		logger.info(":: Locator details extracted from Database : " + strObjectName + "==>" + locator);
		if (locator.contains(Config.WEB_ELEMENT_VAR_TEXT)) {
			logger.info(":: Locator has replacable text, replacing with :: " + textToReplace.trim());
			locator = locator.replace(Config.WEB_ELEMENT_VAR_TEXT, textToReplace);
			logger.info(":: Locator details after reforming : " + locator);
		}
		if (Config.ERRORS_CHECK && ExistsErrors(driver)) {
			throw new Exception("Error screen related object identified on current screen ");
		} else {
			WebElelst = find_IB_Elmtns(driver, locator, Config.WAIT_FOR_ELEMENT_COUNTER, Config.SHORT_WAIT);
			if (WebElelst.size() == 1) {
				WebElement = WebElelst.get(0);
				highlightElement(driver, WebElement);
			}else {
				logger.error("Current element locator/selector matches multiple elements on the page");
				throw new Exception(
						"Current element locator/selector matches multiple elements on the page and size of elements "
								+ WebElelst.size());
			}
		}
		return WebElement;
	}

	public List<WebElement> verify_IB_Elements(WebDriver driver, String PageName, String strObjectName,
			String textToReplace) throws Exception {
		List<WebElement> webElelst;
		String tableName = Config.DB_IB_REPO_TABLE;
		String locator = getLocator(PageName, strObjectName, tableName);
		logger.info(":: Locator details extracted from Database : " + strObjectName + "==>" + locator);
		if (locator.contains(Config.WEB_ELEMENT_VAR_TEXT)) {
			logger.info(":: Locator has replacable text, replacing with :: " + textToReplace);
			locator = locator.replace(Config.WEB_ELEMENT_VAR_TEXT, textToReplace.trim());
			logger.info(":: Locator details after reforming : " + locator);
		} 
		if (Config.ERRORS_CHECK && ExistsErrors(driver)) {
			throw new Exception("Error screen related object identified on current screen ");
		} else {
			if (driver.getClass().getSimpleName().equalsIgnoreCase("webdriverfacade")) {
				webElelst = find_IB_Elmtns(driver, locator, Config.WAIT_FOR_ELEMENT_COUNTER, Config.SHORT_WAIT);
			} else {
				webElelst = find_IB_Elmtns(driver, locator, Config.WAIT_FOR_ELEMENT_COUNTER, Config.SHORT_WAIT);
			}
		}
		return webElelst;
	}

	public WebElement find_IB_Elmtn( WebElement element, int counter, long waitTime) {
		By webelementProperty = null;
		WebElement webelement = element;
		WebElement webElelst = null;
		// locator="XPATH~"+locator;
		
		WebDriverWait wait = new WebDriverWait(d, waitTime);
		wait.pollingEvery(1, TimeUnit.SECONDS);
		String locatorType = "xpath";
		if (locator.toLowerCase().contains("xpath")) {
			locator = locator.substring(locator.indexOf("xpath=") + 6);
			locatorType = "xpath";
		} else if (locator.toLowerCase().startsWith("id=")) {
			locator = locator.substring(locator.indexOf("id=") + 3);
			locatorType = "id";
		}
		
		
		logger.info(element + " will be verified for " + counter + " times");
		for (int i = 0; i <= counter && webelement == null; i++) {
			try {
				switch (locatorType.toUpperCase()) {
				case "XPATH":
					jse = (JavascriptExecutor) d;
					jse.executeScript("arguments[0].scrollIntoView(false)", webelement);
					wait.until(ExpectedConditions.visibilityOf(webelement));
					
					logger.info(element + " VERIFICATION " + counter + " times");	
					
				}
				webElelst =webelement;
				//if (webElelst.size() == 1 && webElelst.get(0).isDisplayed())
					return webElelst;
			} catch (Exception e) { // Object does not exists
				if (i < counter)
					continue;
				if (i > counter)
				logger.error("locator not found"+e);
					throw e;
				
			}
		}
		return webElelst;
	}

	public MobileElement verify_MB_Element(AppiumDriver driver, String PageName, String strObjectName,
			String textToReplace) throws Exception {
		MobileElement MbElement = null;
		List<MobileElement> MbElelst;
		String tableName = Config.DB_MB_REPO_TABLE;
		String locator = getLocator(PageName, strObjectName, tableName);
		logger.info(":: Locator details extracted from Database : " + strObjectName + "==>" + locator);
		if (locator.contains(Config.WEB_ELEMENT_VAR_TEXT)) {
			logger.info(":: Locator has replacable text, replacing with :: " + textToReplace);
			locator = locator.replace(Config.WEB_ELEMENT_VAR_TEXT, textToReplace.trim());
			logger.info(":: Locator details after reforming : " + locator);
		}
		if (Config.ERRORS_CHECK && ExistsErrors(driver)) {
			throw new Exception("Error screen related object identified on current screen ");
		} else {
			MbElelst = find_MB_Elmtns(driver, locator, Config.WAIT_FOR_ELEMENT_COUNTER, Config.SHORT_WAIT);
			if (MbElelst.size() == 0) {
				return null;
				// highlightElement(driver, WebElement);
			}else if (MbElelst.size() == 1) {
				return MbElelst.get(0);
				// highlightElement(driver, WebElement);
			} else {
				logger.error("Current element locator/selector matches multiple elements on the page");
				throw new Exception(
						"Current element locator/selector matches multiple elements on the page and size of elements "
								+ MbElelst.size());
			}
		}

	}

	public List<MobileElement> verify_MB_Elements(AppiumDriver driver, String PageName, String strObjectName,
			String textToReplace) throws Exception {
		List<MobileElement> MbElelst;
		String tableName = Config.DB_MB_REPO_TABLE;
		String locator = getLocator(PageName, strObjectName, tableName);
		logger.info(":: Locator details extracted from Database : " + strObjectName + "==>" + locator);
		if (locator.contains(Config.WEB_ELEMENT_VAR_TEXT)) {
			logger.info(":: Locator has replacable text, replacing with :: " + textToReplace);
			locator = locator.replace(Config.WEB_ELEMENT_VAR_TEXT, textToReplace.trim());
			logger.info(":: Locator details after reforming : " + locator);
		}
		if (Config.ERRORS_CHECK && ExistsErrors(driver)) {
			throw new Exception("Error screen related object identified on current screen ");
		} else {
			MbElelst = find_MB_Elmtns(driver, locator, Config.WAIT_FOR_ELEMENT_COUNTER, Config.SHORT_WAIT);
		}
		return MbElelst;
	}

	public MobileElement verify_MB_Element(AppiumDriver driver, String PageName, String strObjectName,
			String textToReplace, long waitTime, int counter) throws Exception {
		MobileElement MbElement = null;
		List<MobileElement> MbElelst;
		String tableName = Config.DB_MB_REPO_TABLE;
		String locator = getLocator(PageName, strObjectName, tableName);
		logger.info(":: Locator details extracted from Database : " + strObjectName + "==>" + locator);
		if (locator.contains(Config.WEB_ELEMENT_VAR_TEXT)) {
			logger.info(":: Locator has replacable text, replacing with :: " + textToReplace);
			locator = locator.replace(Config.WEB_ELEMENT_VAR_TEXT, textToReplace.trim());
			logger.info(":: Locator details after reforming : " + locator);
		}
		if (Config.ERRORS_CHECK && ExistsErrors(driver)) {
			throw new Exception("Error screen related object identified on current screen ");
		} else {
			MbElelst = find_MB_Elmtns(driver, locator, counter, waitTime);
			if (MbElelst.size() == 0) {
				return null;
				// highlightElement(driver, WebElement);
			}else if (MbElelst.size() == 1) {
				return MbElelst.get(0);
				// highlightElement(driver, WebElement);
			} else {
				logger.error("Current element locator/selector matches multiple elements on the page");
				throw new Exception(
						"Current element locator/selector matches multiple elements on the page and size of elements "
								+ MbElelst.size());
			}
		}

	}

	public List<MobileElement> verify_MB_Elements(AppiumDriver driver, String PageName, String strObjectName,
			String textToReplace, long waitTime, int counter) throws Exception {
		List<MobileElement> MbElelst;
		String tableName = Config.DB_MB_REPO_TABLE;
		String locator = getLocator(PageName, strObjectName, tableName);
		logger.info(":: Locator details extracted from Database : " + strObjectName + "==>" + locator);
		if (locator.contains(Config.WEB_ELEMENT_VAR_TEXT)) {
			logger.info(":: Locator has replacable text, replacing with :: " + textToReplace);
			locator = locator.replace(Config.WEB_ELEMENT_VAR_TEXT, textToReplace.trim());
			logger.info(":: Locator details after reforming : " + locator);
		}
		if (Config.ERRORS_CHECK && ExistsErrors(driver)) {
			throw new Exception("Error screen related object identified on current screen ");
		} else {
			MbElelst = find_MB_Elmtns(driver, locator, counter, waitTime);
		}
		return MbElelst;
	}

	
	
	
	
	public List<MobileElement> find_MB_Elmtns(AppiumDriver driver, String locator, int counter, long waitTime) throws Exception {
		By mbElementProperty = null;
		MobileElement MbElement = null;
		List<MobileElement> MbEleLst = null;
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.pollingEvery(Duration.ofMillis(500));
		String locatorType = "xpath";
		if (locator.toLowerCase().contains("xpath")) {
			locator = locator.substring(locator.indexOf("xpath=") + 6);
			locatorType = "xpath";
		} else if (locator.toLowerCase().startsWith("id=")) {
			locator = locator.substring(locator.indexOf("id=") + 3);
			locatorType = "id";
		}else if(locator.toLowerCase().replace(" ", "").startsWith("accessibilityid")){
			locator = locator.substring(locator.indexOf("AccessibilityId="));
			locatorType = "AccessibilityId";
		}
		if(isImageObjectPropExists()) {
			MbElement=null;
			getImagePoints(driver);
			TouchAction ta = new TouchAction(driver);
			ta.tap(PointOption.point(x_loc, y_loc)).perform();
		}
		logger.info(locator + " will be verified for " + counter + " times");
		for (int i = 0; i <= counter && MbElement == null; i++) {
			try {
				switch (locatorType.toUpperCase()) {
				case "XPATH":
					logger.info("Searching for the element using xPath " + i +"th time");
					mbElementProperty = MobileBy.xpath(locator);
					this.setElementProp(mbElementProperty);
					wait.until(ExpectedConditions.presenceOfElementLocated(mbElementProperty));
					break;
				case "AccessibilityId":
					logger.info("Searching for the element using AccessibilityId " + i +"th time");
					mbElementProperty = MobileBy.AccessibilityId(locator);
					this.setElementProp(mbElementProperty);
					wait.until(ExpectedConditions.presenceOfElementLocated(mbElementProperty));
				case "ID":
					mbElementProperty = MobileBy.id(locator);
					break;
				case "NAME":
					mbElementProperty = MobileBy.name(locator);
					break;
				case "CLASS":
					mbElementProperty = MobileBy.className(locator);
					break;
				case "CSSSELECTOR":
					mbElementProperty = MobileBy.cssSelector(locator);
					break;
				case "LINKTEXT":
					mbElementProperty = MobileBy.linkText(locator);
					break;
				}
				MbEleLst = driver.findElements(mbElementProperty);
				if (MbEleLst.size() != 0 && MbEleLst.get(0) != null) {
					logger.info("MBlist size" + MbEleLst.size() + " and element "
							+ MbEleLst.get(0).getClass().getSimpleName());
					wait.until(ExpectedConditions.presenceOfElementLocated(mbElementProperty));
					return MbEleLst;
				}
			} catch (Exception e) {  Object does not exists 
				logger.debug("Object not exist : " + i);
				if (i < counter)
					continue;
				if (i > counter)
					throw e;
			}
		}
		return MbEleLst;
	}
	
	public List<MobileElement> find_MB_Elmtns(AppiumDriver driver, By elementProperty, long waitTime) {
		MobileElement MbElement = null;
		List<MobileElement> MbEleLst = null;
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.pollingEvery(Duration.ofMillis(500));
		logger.info(":find_MB_Elmtns: with By=> " + elementProperty.toString());
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(elementProperty));
				MbEleLst = driver.findElements(elementProperty);
				if (MbEleLst.size() != 0 && MbEleLst.get(0) != null) {
					logger.info("MBlist size" + MbEleLst.size() + " and element "
							+ MbEleLst.get(0).getClass().getSimpleName());
					return MbEleLst;
				}
			} catch (Exception e) {  Object does not exists 
				return null;
			}
		return MbEleLst;
	}
	private static void highlightElement(WebDriver d, WebElement webelement) throws InterruptedException {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) d;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webelement,
					"color: blue; border: 2px solid blue;");
			Thread.sleep(250);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webelement, "");
		}
	}

	public boolean ExistsErrors(WebDriver d) {
		
		 * String errorMsg = ""; String locator = ""; int counter = 0; int waitTime = 1;
		 * MobileElement mbElement = null; try { //code to call the findelements metho }
		 * catch (Exception e) { return false; }
		 
		return true;
	}

	private String getLocator(String PageName, String objectName, String tableName) {
		String locator = null;
		String sql = "select * from " + tableName + " where Page_name = '" + PageName + "' and object_name = '"
				+ objectName + "';";
		logger.info(sql);
		try {
			DataBaseActions db = new DataBaseActions();
			ResultSet rs = db.execute(sql);
			while (rs.next()) {
				setElementId(rs.getInt("sno"));
				switch (Config.COUNTRY.toUpperCase()) {
				case "IN":
					locator = getCNLocator(rs, tableName, "");
					break;
				case "ID":
					locator = getCNLocator(rs, tableName, "");
					break;
				case "SG":
					locator = getCNLocator(rs, tableName, "");
					break;
				case "TW":
					switch (Config.language.toUpperCase()) {
					case "EN":
						locator = getCNLocator(rs, tableName, Config.language.toUpperCase());
						break;
					case "ZH":
						locator = getCNLocator(rs, tableName, Config.language.toUpperCase());
						break;
					}
					break;
				}
			}
		} catch (SQLException e) {
			logger.error("error while extracting object from DB :: " + e.getLocalizedMessage());
			Config.gracefulEnd(e, logger);
		}
		return locator;
	}

	private String getCNLocator(ResultSet rs, String tableName, String locale) throws SQLException {
		String CN_locator = null;
		switch (DriverManagerFactory.getDriverData("Run On Cloud").toUpperCase()) {
		case "TRUE":
			if (tableName.equalsIgnoreCase(Config.DB_IB_REPO_TABLE)) {
				if (!locale.equals("") || (null == locale))
					CN_locator = rs.getString("LOCATOR_" + locale);
				else
					CN_locator = rs.getString("LOCATOR");
			} else {
				if (DriverManagerFactory.getDriverData("Platform Name").equalsIgnoreCase("ios")) {
					if (!locale.equals("")|| (null == locale))
						CN_locator = rs.getString("iphone_cloud_locator_" + locale);
					else
						CN_locator = rs.getString("iphone_cloud_locator");
				} else {
					if (!locale.equals("")|| (null == locale))
						CN_locator = rs.getString("android_cloud_locator_" + locale);
					else
						CN_locator = rs.getString("android_cloud_locator");
				}
			}
			break;

		case "FALSE":
			if (tableName.equalsIgnoreCase(Config.DB_IB_REPO_TABLE)) {
				if (!locale.equals("") && locale.equalsIgnoreCase("CN"))
					CN_locator = rs.getString("LOCATOR_" + locale);
				else
					CN_locator = rs.getString("LOCATOR");
			} else {
				if (DriverManagerFactory.getDriverData("Platform Name").equalsIgnoreCase("ios")) {
					if (!locale.equals("") && locale.equalsIgnoreCase("CN"))
						CN_locator = rs.getString("iphone_locator_" + locale);
					else
						CN_locator = rs.getString("iphone_locator");
				} else {
					if (!locale.equals("") && locale.equalsIgnoreCase("CN"))
						CN_locator = rs.getString("android_cloud_locator_" + locale);
					else
						CN_locator = rs.getString("android_locator");
				}
			}
			break;
		}
		return CN_locator;
	}
	private boolean isImageObjectPropExists() throws Exception {
		boolean flag=false;
		if(Config.USE_IMAGE_DRIVER) {
			DataBaseActions db = new DataBaseActions();
			ResultSet rs = db.execute(Config.ELEMENT_FINDER_IMAGES_SQL + elementId );
			while(rs.next()) {
				int imagesCount = rs.getInt(1);
				if (imagesCount>0)
					flag=true;
			}
		}	
		logger.info("Image object available ?" + flag +", allowed to use image Driver ? " + Config.USE_IMAGE_DRIVER);
		return flag;
	}
	
	private void getImagePoints(AppiumDriver driver) throws Exception{
		File driverScreenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//InputStream driverScreenIn = new ByteArrayInputStream(driverScreenBytes);
		//ObjectInputStream ois = new ObjectInputStream(driverScreenIn);
		BufferedImage driverImage = ImageIO.read(driverScreenFile);
		String locator="";
		boolean mobileDriver=false,webDriver=false;
		try {
			if(Config.USE_IMAGE_DRIVER) {
				logger.info(":getElementFromImage: ImageDriver operations");
				String device_version = DriverManagerFactory.getDriverData("Platform Version").trim();
				String run_device = DriverManagerFactory.getDriverData("RunTestOn").trim();
				String platform = DriverManagerFactory.getDriverData("Platform Name").trim();
				String sql=Config.ELEMENT_FINDER_IMAGE_SQL;
				sql= sql.replace("{replace1}", String.valueOf(elementId));
				sql = sql.replace("{replace3}", device_version);
				platform=platform.substring(0,1).toUpperCase()+platform.substring(1);
				sql = sql.replace("{replace2}", platform);
				DataBaseActions db = new DataBaseActions();
				ResultSet rs = db.execute(sql );
				logger.info("Image details : " + rs.getFetchSize());
				while (rs.next()) {
					Blob blb = rs.getBlob("Image");
					String source = rs.getString("Image_Source");
					String version = rs.getString("version");
					InputStream in = blb.getBinaryStream();
					BufferedImage bfi = ImageIO.read(in);
					logger.info("Image : [" +bfi.getWidth() + ","+bfi.getHeight()+"]");

					findImagePoints(driverImage,bfi);
				}
			}	
		}catch (Exception e) {
			logger.error("\n " + Config.ELEMENT_FINDER_IMAGE_SQL + elementId +" <== error while extracting object from DB :: " + e.getLocalizedMessage());
			Config.gracefulEnd(e, logger);
		}
	}
	public void findImagePoints(BufferedImage driverImage, BufferedImage image) throws Exception {
		ImageTarget target = new ImageTarget(image);
		ImageTarget driverScreen = new ImageTarget(driverImage);
		logger.info(":findImagePoints: ImageTarget=" + target.getMinScore());
		ScreenRegion imageRegion=findPictureRegion(target,driverScreen);
		if(imageRegion!=null){
			ScreenLocation center = imageRegion.getCenter();
			this.x_loc=center.getX();
			this.y_loc=center.getY();
			logger.info(":getElementFromImage: x_loc,y_loc =["+x_loc+","+y_loc+":]");
		}else{
			throw new Exception("Element matching the image was not found in the current page");
		}
	}
	public static ScreenRegion findPictureRegion(ImageTarget target,ImageTarget driverScreen){
	    target.setMinScore(0.5); // Precision of recognization from 0 to 1.
	    ScreenRegion screenRegion = new StaticImageScreenRegion(driverScreen.getImage());
	    return screenRegion.find(target);
	}
	
}*/