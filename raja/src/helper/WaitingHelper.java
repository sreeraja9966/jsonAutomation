package helper;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class WaitingHelper extends TestBase {
	private static final Logger log = Logger.getLogger(WaitingHelper.class);

	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info(timeout);
		d.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}
	
	public void setPageLoadTimeout(long timeout, TimeUnit unit) {
		log.info(timeout);
		d.manage().timeouts().pageLoadTimeout(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}
	
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait =null;
		try {
			log.info(timeOutInSeconds+"----> timeOutInSeconds"+" & "+pollingEveryInMiliSec+"---->pollingEveryInMiliSec");
			 wait = new WebDriverWait(d, timeOutInSeconds);
			wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
			wait.ignoring(NoSuchElementException.class);
			wait.ignoring(ElementNotVisibleException.class);
			wait.ignoring(StaleElementReferenceException.class);
			wait.ignoring(NoSuchFrameException.class);
			
		} catch (Exception e) {
			log.error("error in getWait", e);
		}
		return wait;
	}
	
	public boolean waitForElementVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		ReportHelper reportHelper = new ReportHelper();
		log.info(locator);
		try {
			WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
			//wait.until(angularHasFinishedProcessing());
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			
			log.error(e+"Element not found------>"+locator);
			
			return false;
		}
	}
	public boolean waitForElementVisibleUtillAngularDone(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		ReportHelper reportHelper = new ReportHelper();
		log.info(locator);
		try {
			WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
			wait.until(angularHasFinishedProcessing());
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			
			log.error(e+"Element not found------>"+locator);
			
			return false;
		}
	}
	
	
	public void waitForElement( WebElement element, long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(d, timeout);
			wait.until(ExpectedConditions.visibilityOf(element));
			log.info("element found..."+element.getText());
		} catch (Exception e) {
			log.error(e+"Element not found--------->"+element);
			
		}
	}
	
	public WebElement waitForElement(long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(d, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static boolean isJQueryDone() {
        Object jsResponse = tryJavascript("return jQuery.active;");
        if (jsResponse instanceof Long) {
            return ((Long) jsResponse) == 0;
        } else if (jsResponse instanceof String) {
            String response = (String) jsResponse;
            return (response.startsWith("{\"hCode\"") || response.isEmpty());
        } else {
            return true;
        }
    }

    public static boolean isAngularDone() {
        Object jsResponse = tryJavascript("return window.getAllAngularTestabilities().filter(x=>!x.isStable()).length;");
        if (jsResponse instanceof Long) {
            return ((Long) jsResponse) == 0;
        } else if (jsResponse instanceof String) {
            String response = (String) jsResponse;
            return response.isEmpty();
        } else {
            return true;
        }
    }
    
    public static synchronized Object execJavascript(String script, Object... args) {
        JavascriptExecutor scriptExe = ((JavascriptExecutor) d);
        return scriptExe.executeScript(script, args);
    }

    public static synchronized Object tryJavascript(String script, Object... args) {
        try {
            return execJavascript(script, args);
        } catch (Exception ignore) {
            return "";
        }
    }
public void sleep(int time) {
	try {
		Thread.sleep(time);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static ExpectedCondition angularHasFinishedProcessing() {
    return new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver d) {
            String hasAngularFinishedScript = "var callback = arguments[arguments.length - 1];\n" +
                    "var el = document.querySelector('html');\n" +
                    "if (!window.angular) {\n" +
                    "    callback('false')\n" +
                    "}\n" +
                    "if (angular.getTestability) {\n" +
                    "    angular.getTestability(el).whenStable(function(){callback('true')});\n" +
                    "} else {\n" +
                    "    if (!angular.element(el).injector()) {\n" +
                    "        callback('false')\n" +
                    "    }\n" +
                    "    var browser = angular.element(el).injector().get('$browser');\n" +
                    "    browser.notifyWhenNoOutstandingRequests(function(){callback('true')});\n" +
                    "}";

            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) d;
            String isProcessingFinished = javascriptExecutor.executeAsyncScript(hasAngularFinishedScript).toString();

            return Boolean.valueOf(isProcessingFinished);
        }
    };
}

}
