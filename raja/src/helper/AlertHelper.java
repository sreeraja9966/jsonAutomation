package helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import testBase.TestBase;

public class AlertHelper extends TestBase {
	private static final Logger log = Logger.getLogger(AlertHelper.class);
	ReportHelper reportHelper=new ReportHelper();
	
	public Alert getAlert() {
		
		log.debug("");
		return d.switchTo().alert();
	}
	
	public void AcceptAlert() {
		log.info("");
		getAlert().accept();
	}
	
	public void DismissAlert() {
		log.info("");
		getAlert().dismiss();
	}

	public String getAlertText() {
		String text = getAlert().getText();
		log.info(text+" --->text from Alert");
		return text;
	}
/**
 * isAlertPresent verifies whether we have any alert present on UI or not.
 * If Alert is available then it switches to the alert, if not it will add
 * screenshot in report.
 * @author sriraja.garlapati
 */
	public boolean isAlertPresent() {
		try {
			d.switchTo().alert();
			log.info("AlertPresent");
			return true;
		} catch (NoAlertPresentException e) {
			reportHelper.addScreenShotInCaseOfFailInReport("NoAlertPresent");
			reportHelper.writeLogInCaseOfFail("NoAlertPresent");
			log.info(e);
			return false;
		}
	}
/**
 * AcceptAlertIfPresent, accepts the alert present on UI
 * 
 * @author sriraja.garlapati
 */
	public void AcceptAlertIfPresent() {
		if (isAlertPresent()) {
			AcceptAlert();
		log.info("alert accepted");}
		else
		log.info("NoAlertPresent");
		
	}

	public void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		DismissAlert();
		log.info("");
	}
	
	public void AcceptPrompt(String text) {
		
		if (!isAlertPresent())
			return;
		
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		log.info(text);
	}
}
