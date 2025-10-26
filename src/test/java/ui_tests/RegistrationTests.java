package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;


import static utils.UserFactory.*;
import static utils.UserFactory.randomValidEmail;

public class RegistrationTests extends ApplicationManager {

    LoginPage loginPage;

    @BeforeMethod
    public void goToRegPage(){
        new HomePage(getDriver()).clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void registrationPositiveTest(){
        User user = randomUser(8);
        loginPage.typeRegForm(user);
        Assert.assertTrue(new ContactsPage(getDriver())
                .isTextNoContactsPresent("No Contacts here!"));
    }

    @Test
    public void registrationNegativeTest_wrongEmail(){
        User user = new User("wrong email", generatePassword(8));
        loginPage.typeRegForm(user);
        Assert.assertTrue(loginPage.closeAlertReturnText()
                .contains("Wrong email or password format"));
    }

    @Test
    public void registrationNegativeTest_wrongPassword(){
        User user = new User(randomValidEmail(),"wrong password");
        loginPage.typeRegForm(user);
        Assert.assertTrue(loginPage.closeAlertReturnText()
                .contains("Wrong email or password format"));
    }

    @Test
    public void registrationNegativeTest_withoutData(){
        User user = new User("","");
        loginPage.typeRegForm(user);
        Assert.assertTrue(loginPage.closeAlertReturnText()
                .contains("Wrong email or password format"));
    }

    @Test
    public void registrationNegativeTest_DuplicateUser(){
        User user = randomUser(8);
        loginPage.typeRegForm(user);
        Assert.assertTrue(new ContactsPage(getDriver())
                .isTextNoContactsPresent("No Contacts here!"));
        ContactsPage contactsPage = new ContactsPage(getDriver());
        contactsPage.clickBtnSingOut();
        loginPage.typeRegForm(user);
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("User already exist"));
    }


}
