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
    private static final String BODY = "some text";
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

//    @AfterClass
//    public void afterClass() {
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.quit();
//    }

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {

    }

    @Test(description = "The letter from user1 in Spam")
    public void testSpam() {
        doLogin(USER1, PASSWORD1);
        sendMessage();
        doLogout();
        login.linkEnterAnotherAcount.click();
        login.linkAddAcount.click();
        doLogin(USER2, PASSWORD2);
        markLetterAsSpam();
        doLogout();
        login.linkAddAcount.click();
        //login.linkEnterAnotherAcount.click();
        doLogin(USER1, PASSWORD1);
        sendMessage();
        doLogout();
       // login.linkEnterAnotherAcount.click();
        login.linkAddAcount.click();
        doLogin(USER2, PASSWORD2);
        goToFolderSpam();
      //  wait.until(ExpectedConditions.visibilityOf(home.linkBodyForVerification));
      //  assertEquals(home.linkBodyForVerification.getText(),"Андрей Чорняк");
        removeFromSpam();
        doLogout();

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
        //wait.until(ExpectedConditions.visibilityOf(home.linkLetter)).click();
       home.checkbox.click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonSpam));
        home.buttonSpam.click();
        

    }

    private void sendMessage() {
        home.buttonWrite.click();
       // wait.until(ExpectedConditions.visibilityOf(home.form));
        home.addressee.clear();
        wait.until(ExpectedConditions.visibilityOf(home.addressee));
        home.addressee.sendKeys(USER2);
        home.body.clear();
        home.body.sendKeys(BODY);
        home.buttonSend.click();

    }

    private void doLogout() {
        home.emailBeforeExit.click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonLogout)).click();

    }

    private void doLogin(String user, String password) {
        wait.until(ExpectedConditions.visibilityOf(login.email));
        login.email.clear();
        login.email.sendKeys(user);
        wait.until(ExpectedConditions.visibilityOf(login.next)).click();
        login.password.clear();
        login.password.sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(login.signIn)).click();

    }

}
