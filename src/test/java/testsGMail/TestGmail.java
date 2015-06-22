package testsGMail;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import pages.HomePage;
import pages.LoginPage;

import com.google.common.collect.Ordering;

import static org.testng.Assert.*;
import driver.WebDriverFactory;

public class TestGmail {

    private static final String START_URL = "http://gmail.com";
    private static final String USER1 = "andrii.chorniak@gmail.com";
    private static final String USER2 = "andrii.chorniak2@gmail.com";
    private static final String PASSWORD1 = "123456am";
    private static final String PASSWORD2 = "1234567am";
    private static final String BODY = "aaa";
    private static final String BODY2 = "bbb";
    private static final String MESSAGE = "Настройки сохранены.";
    private WebDriver driver;
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriverWait wait;
    private LoginPage login;
    private HomePage home;

    @BeforeClass
    public void beforeClass() {
        try {
            driver = webDriverFactory.createWebdriver("firefox");
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.get(START_URL);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        login = PageFactory.initElements(driver, LoginPage.class);
        home = PageFactory.initElements(driver, HomePage.class);
    }

    @AfterClass
    public void afterClass() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {

    }
    
    @AfterMethod
    public void afterMethod() {
        

    }

    
    @Test(description = "The letter from user1 in Spam")
    public void testSpam() {
        doLogin(USER1, PASSWORD1);
        sendMessage(BODY);
        doLogout();
        login.linkEnterAnotherAcount.click();
        login.linkAddAcount.click();
        doLogin(USER2, PASSWORD2);
        markLetterAsSpam();
        doLogout();
        login.linkAddAcount.click();
        doLogin(USER1, PASSWORD1);
        sendMessage(BODY2);
        doLogout();
        login.linkAddAcount.click();
        doLogin(USER2, PASSWORD2);
        goToFolderSpam();
        assertEquals(home.linkBodyForVerification.getText(), " - " + BODY);
        removeFromSpam();
        removeFromInbox();
        doLogout();
        login.linkAddAcount.click();
    }
    
    @Test(description = "The letter from user1 in starred")
    public void testStarred(){
        doLogin(USER1, PASSWORD1);
        sendMessage(BODY);
        doLogout();
        login.linkAddAcount.click();
        doLogin(USER2, PASSWORD2);
        doStarred();
        home.staredVerification.getText();
        removeFromImportant();
        doLogout();
        login.linkAddAcount.click();
    }
    
    @Test(description = "Check that tema has changed")
    public void testTema(){
        doLogin(USER1, PASSWORD1);
        setTema();
        wait.until(ExpectedConditions.visibilityOf(home.message));
        assertEquals(home.message.getText(), MESSAGE);
        doLogout();
        
    }

    private void setTema() {
        wait.until(ExpectedConditions.visibilityOf(home.settings));
        home.settings.click();
        wait.until(ExpectedConditions.visibilityOf(home.tema));
        home.tema.click();
        wait.until(ExpectedConditions.visibilityOf(home.blueTema));
        home.blueTema.click();
        
    }

    private void removeFromImportant() {
        wait.until(ExpectedConditions.visibilityOf(home.selectAllCheckbox)).click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonDeleteStarred)).click();
        
    }

    private void doStarred() {
        home.star.click();
        home.linkMore.click();
        home.important.click();
        
    }

    private void removeFromInbox() {
        home.inbox.click();
        wait.until(ExpectedConditions.visibilityOf(home.selectAllCheckbox)).click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonDelete)).click();
        
    }

    private void removeFromSpam() {
        wait.until(ExpectedConditions.visibilityOf(home.linkBodyForVerification)).click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonNotSpam)).click();

    }

    private void goToFolderSpam() {
        home.linkMore.click();
        wait.until(ExpectedConditions.visibilityOf(home.spam)).click();
    }

    private void markLetterAsSpam() {
        home.inbox.click();
        home.checkbox.click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonSpam));
        home.buttonSpam.click();

    }

    private void sendMessage(String body) {
        home.buttonWrite.click();
        home.addressee.clear();
        wait.until(ExpectedConditions.visibilityOf(home.addressee));
        home.addressee.sendKeys(USER2);
        home.body.clear();
        home.body.sendKeys(body);
        home.buttonSend.click();

    }

    private void doLogout() {
        home.emailBeforeExit.click();
        home.buttonLogout.click();

    }

    private void doLogin(String user, String password) {
        wait.until(ExpectedConditions.visibilityOf(login.email));
        login.email.clear();
        login.email.sendKeys(user);
        wait.until(ExpectedConditions.visibilityOf(login.next)).click();
        wait.until(ExpectedConditions.visibilityOf(login.password));
        login.password.clear();
        login.password.sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(login.signIn)).click();

    }

}
