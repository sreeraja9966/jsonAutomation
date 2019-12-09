package sysnik;
import org.openqa.selenium.WebDriver;

import testBase.TestBase;
public class MultiWindow extends TestBase {
	 public static void switchToModalDialog(String parent) { 
         //Switch to Modal dialog
     if (d.getWindowHandles().size() == 2) {
         for (String window : d.getWindowHandles()) {
             if (!window.equals(parent)) {
                 d.switchTo().window(window);
                 System.out.println("Modal dialog found");
                 break;
             }
         }
     }
}
}