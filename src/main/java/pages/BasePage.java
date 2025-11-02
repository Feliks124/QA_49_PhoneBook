package pages;

import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HeaderMenuItem;

import java.time.Duration;

public abstract class BasePage {

    @Setter
    static WebDriver driver;

    public static <T extends BasePage> T clickButtonHeader(HeaderMenuItem item){
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath(item.getLocator())));
        element.click();
        switch (item){
            case HOME -> {
                return (T) new HomePage(driver);
            }
            case CONTACTS -> {
                return (T) new ContactsPage(driver);
            }
            case LOGIN -> {
                return (T) new LoginPage(driver);
            }
            case ADD -> {
                return (T) new AddPage(driver);
            }
            default -> throw new IllegalArgumentException("Invalid parameter headerMenuItem");
        }
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isTextInElementPresent(WebElement element, String test) {
        return element.getText().contains(test);

    }


}
