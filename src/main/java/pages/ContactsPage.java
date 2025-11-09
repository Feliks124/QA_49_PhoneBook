package pages;

import dto.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ContactsPage extends BasePage {

    public ContactsPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//a[@href='/contacts']")
    WebElement btnContactsHeader;

    @FindBy(xpath = "//div[@class='contact-page_message__2qafk']")
    WebElement divTextNoContacts;

    @FindBy(xpath = "//*[@id='root']/div[1]/button")
    WebElement btnSingOut;

    @FindBy(className = "contact-item_card__2SOIM")
    List<WebElement> contactsList;

    @FindBy(xpath = "//div[@class='contact-page_leftdiv__yhyke']/div/div[last()]/h2")
    WebElement lastElementList;

    public void clickBtnSingOut() {
        btnSingOut.click();
    }

    public boolean isTextContactsPresent(String text) {
        return isTextInElementPresent(btnContactsHeader, text);
    }

    public boolean isTextNoContactsPresent(String text) {
        System.out.println(divTextNoContacts.getText());
        return isTextInElementPresent(divTextNoContacts, text);
    }

    public boolean isContactPresent(Contact contact) {
        for (WebElement element : contactsList) {
            if (element.getText().contains(contact.getName())
                    && element.getText().contains(contact.getPhone())) {
                System.out.println(element.getText());
                return true;
            }
        }
        return false;
    }

    public int getNumberOfContacts() {
        return contactsList.size();
    }

    public void clickLastContact() {
        lastElementList.click();
    }


    public boolean isContactPresentWithScroll(Contact contact) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        for (WebElement element : contactsList) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.visibilityOf(element));

            if (element.getText().contains(contact.getName())
                    && element.getText().contains(contact.getPhone())) {
                System.out.println(element.getText());
                return true;
            }
        }
        return false;
    }

    @FindBy(xpath = "//div[@class='contact-page_leftdiv__yhyke']/div")
    WebElement divElementsList;

    public void scrollToLastElementList() {
        Actions actions = new Actions(driver);
        int deltaY = divElementsList.getSize().getHeight();
        //actions.scrollToElement(lastElementList).perform();   exp1
//            int deltaY = driver.findElement(By.xpath
//                    ("//*[@id='root']/div[2]/div/div/div[1]/div")).
//                    getSize().getHeight();
        WheelInput.ScrollOrigin scrollOrigin =
                WheelInput.ScrollOrigin.fromElement(contactsList.get(0));
        actions.scrollFromOrigin(scrollOrigin, 0, deltaY).perform();

    }


}


