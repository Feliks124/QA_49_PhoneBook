package ui_tests;

import dto.Contact;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ContactFactory;
import utils.HeaderMenuItem;

import static pages.BasePage.clickButtonHeader;
import static utils.PropertiesReader.getProperty;

public class AddNewContactTests extends ApplicationManager {

    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;
    int numberOfContacts;

    @BeforeMethod
    public void login(){
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        //loginPage.typeLoginForm("a@mail.ru", "Password123!");
        loginPage.typeLoginForm(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        contactsPage = new ContactsPage(getDriver());
        numberOfContacts = contactsPage.getNumberOfContacts();
        addPage = clickButtonHeader(HeaderMenuItem.ADD);
    }

    @Test
    public void addNewContactPositiveTest(){
        addPage.typeContactForm(ContactFactory.positiveContact());
        int numberOfContactsAfterAdd = contactsPage.getNumberOfContacts();
        Assert.assertEquals(numberOfContactsAfterAdd, numberOfContacts + 1);
    }

    @Test
    public void addNewContactPositiveTestValidateList(){
        Contact contact = ContactFactory.positiveContact();
        addPage.typeContactForm(contact);
        Assert.assertTrue(contactsPage.isContactPresent(contact));
    }

    @Test
    public void addNewContactPositiveTestValidateScrollToLastContact(){
        Contact contact = ContactFactory.positiveContact();
        addPage.typeContactForm(contact);
        Assert.assertTrue(contactsPage.isContactPresentWithScroll(contact));
    }

}
