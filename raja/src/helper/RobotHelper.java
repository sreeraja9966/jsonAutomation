package helper;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;

import testBase.TestBase;




public class RobotHelper extends TestBase {
	Robot robot ;
	 private static final Logger log = Logger.getLogger(RobotHelper.class);
	BrowserHelper browserHelper = new BrowserHelper();
	ReportHelper reportHelper = new ReportHelper();
	public void fileUploadUsingRobot(String filePath) {
		try{
			robot= new Robot();
		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		robot.delay(3000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);
		browserHelper.switchToParentWindow();
		reportHelper.writeLogInfoInChildTest("File uploaded");
		
		}
		catch(Exception e) {
			log.error(e);
		}
	}
	public void pressTab() {
		robot.delay(10000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.delay(1000);
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	public void pressDownArrow() {
		robot.keyPress(KeyEvent.VK_KP_DOWN);
		robot.delay(1000);
		//robot.keyRelease(KeyEvent.VK_KP_DOWN);
	}
	public void pressUpArrow() {
		
		robot.keyPress(KeyEvent.VK_KP_UP);
		robot.delay(1000);
		//robot.keyRelease(KeyEvent.VK_KP_UP);
	}
	public void enterStringUsingKeyBoard(String keyBoard) {
		for (char c : keyBoard.toCharArray()) {
	        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
	        if (KeyEvent.CHAR_UNDEFINED == keyCode) {
	            throw new RuntimeException(
	                "Key code not found for character '" + c + "'");
	        }
	        robot.keyPress(keyCode);
	        robot.delay(100);
	        robot.keyRelease(keyCode);
	        robot.delay(100);
	    		
	}
	}

	
}
