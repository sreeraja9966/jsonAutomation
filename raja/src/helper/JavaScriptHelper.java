package helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import testBase.TestBase;

public class JavaScriptHelper extends TestBase {
	FindElement findElement = new FindElement();
	private static final Logger log = Logger.getLogger(JavaScriptHelper.class);
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) d;
		log.info(script);
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) d;
		log.info(script);
		return exe.executeScript(script, args);
	}

	public void scrollToElemet(By ele) {
		WebElement element=findElement.searchClickableElement(ele);
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		log.info(element);
	}

	public void scrollToElemetAndClick(By element) {
		
		scrollToElemet(element);
		((WebElement) element).click();
		log.info(element);
	}

	public void scrollIntoView(By ele) {
		WebElement element=findElement.searchClickableElement(ele);
		((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(true);", element);
		log.info(element);
	}

	public void scrollIntoViewAndClick(By ele) {
		WebElement element=findElement.searchClickableElement(ele);
		scrollIntoView(ele);
		element.click();
		log.info(element);
	}

	public void scrollDownVertically() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollUpVertically() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void scrollDownByPixel() {
		executeScript("window.scrollBy(0,1500)");
	}

	public void scrollUpByPixel() {
		executeScript("window.scrollBy(0,-1500)");
	}

	public void ZoomInBypercentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	public void ZoomBy100percentage() {
		executeScript("document.body.style.zoom='100%'");
	}

}
