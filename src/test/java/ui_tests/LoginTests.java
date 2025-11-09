package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.PropertiesReader;
import utils.RetryAnalyzer;

import java.lang.reflect.Method;

public class LoginTests extends ApplicationManager {

    //String validEmail = "test_mail@gmail.com";
    String validEmail = PropertiesReader.getProperty
            ("base.properties", "login");
    //String validPassword = "Qw12345!";
    String validPassword = PropertiesReader.getProperty
            ("base.properties", "password");

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginPositiveTest(Method method) {
        logger.info("Start test {}", method.getName()
        );
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(validEmail, validPassword);
        Assert.assertTrue(new ContactsPage(getDriver()).isTextContactsPresent("CONTACTS"));
    }

    @Test
    public void btnSingOut() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(validEmail, validPassword);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        contactsPage.clickBtnSingOut();
        Assert.assertTrue(loginPage.isLoginBtnVisible());
    }

    @Test
    public void loginNegativeTest_wrongPassword() {
        User user = new User(validEmail, "111");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFormWithUser(user);
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_wrongEmail() {
        User user = new User("email", validPassword);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFormWithUser(user);
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

    @Test
    public void loginNegativeTest_withoutData() {
        User user = new User("", "");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFormWithUser(user);
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }


}
