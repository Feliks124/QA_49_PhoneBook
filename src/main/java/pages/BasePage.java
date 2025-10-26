package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    static WebDriver driver;
    public static void setDriver(WebDriver wd){
        driver = wd;
    }

    public static void pause (int time){
        try {
            Thread.sleep(time*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isTextInElementPresent(WebElement element, String test){
       return element.getText().contains(test);

    }


}
