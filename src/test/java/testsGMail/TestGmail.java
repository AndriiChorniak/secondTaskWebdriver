package testsGMail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
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
    private static final String PATH = System.getProperty("user.dir") +"\\src\\test\\resources\\SpaghettiTests2Task.xlsx";
    private WebDriver driver;
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriverWait wait;
    private LoginPage login;
    private HomePage home;

    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeMethod() {
        try {
            driver = webDriverFactory.createWebdriver("firefox");
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        driver.manage().window().maximize();
        driver.get(START_URL);
        wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        login = PageFactory.initElements(driver, LoginPage.class);
        home = PageFactory.initElements(driver, HomePage.class);
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.quit();
    }

    @Test(description = "The letter from user1 in Spam")
    public void testSpam() {
        doLogin(USER1, PASSWORD1);
        sendMessage(BODY);
        doLogout();
        wait.until(ExpectedConditions.visibilityOf(login.linkEnterAnotherAcount)).click();
        wait.until(ExpectedConditions.visibilityOf(login.linkAddAcount)).click();
        doLogin(USER2, PASSWORD2);
        markLetterAsSpam();
        doLogout();
        wait.until(ExpectedConditions.visibilityOf(login.linkAddAcount)).click();
        doLogin(USER1, PASSWORD1);
        sendMessage(BODY2);
        doLogout();
        wait.until(ExpectedConditions.visibilityOf(login.linkAddAcount)).click();
        doLogin(USER2, PASSWORD2);
        goToFolderSpam();
        wait.until(ExpectedConditions.visibilityOf(home.linkBodyForVerification));
        assertEquals(home.linkBodyForVerification.getText(), " - " + BODY);
        removeFromSpam();
        removeFromInbox();
        doLogout();
    }

    @Test(description = "The letter from user1 in starred")
    public void testStarred() {
        doLogin(USER1, PASSWORD1);
        sendMessage(BODY);
        doLogout();
        login.linkEnterAnotherAcount.click();
        login.linkAddAcount.click();
        doLogin(USER2, PASSWORD2);
        doStarred();
        home.starredVerification.getText();
        removeFromStarred();
        doLogout();

    }

    @Test(description = "Check that tema has changed")
    public void testTema() {
        doLogin(USER1, PASSWORD1);
        setTema();
        wait.until(ExpectedConditions.visibilityOf(home.message));
        assertEquals(home.message.getText(), MESSAGE);
        doLogout();

    }

    @Test(description = "The letter from user1 in Inbox + file")
    public void testAttachFile() {
        doLogin(USER1, PASSWORD1);
        sendMessageWithAttachedFile();
        doLogout();
        login.linkEnterAnotherAcount.click();
        login.linkAddAcount.click();
        doLogin(USER2, PASSWORD2);
        verifyMessageWithAttachedFile();
        wait.until(ExpectedConditions.visibilityOf(home.inbox)).click();
        removeFromInbox();
        doLogout();

    }

    private void verifyMessageWithAttachedFile() {
        wait.until(ExpectedConditions.visibilityOf(home.linkBodyForVerification)).click();
        wait.until(ExpectedConditions.visibilityOf(home.verifiedTextOfMessage));
        wait.until(ExpectedConditions.visibilityOf(home.verifiedFile));
        
    }

    private void sendMessageWithAttachedFile() {
        fillAddresseeAndBody(BODY);
        wait.until(ExpectedConditions.visibilityOf(home.attachFile)).click();
        delay(10000);
        uploadFile(PATH);
        wait.until(ExpectedConditions.visibilityOf(home.attachedFileName));
        wait.until(ExpectedConditions.visibilityOf(home.buttonSend)).click();

    }

    private void setClipboardData(String path) {
        StringSelection stringSelection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    private void uploadFile(String filePath) {
        try {
            setClipboardData(filePath);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void setTema() {
        wait.until(ExpectedConditions.visibilityOf(home.settings));
        home.settings.click();
        wait.until(ExpectedConditions.visibilityOf(home.tema));
        home.tema.click();
        wait.until(ExpectedConditions.visibilityOf(home.blueTema));
        home.blueTema.click();

    }

    private void removeFromStarred() {
        wait.until(ExpectedConditions.visibilityOf(home.selectAllCheckbox)).click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonDeleteStarred)).click();

    }

    private void doStarred() {
        home.star.click();
        home.starred.click();

    }

    private void removeFromInbox() {
        wait.until(ExpectedConditions.visibilityOf(home.inbox)).click();
        wait.until(ExpectedConditions.visibilityOf(home.selectAllCheckbox)).click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonDelete)).click();

    }

    private void removeFromSpam() {
        wait.until(ExpectedConditions.visibilityOf(home.linkBodyForVerification)).click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonNotSpam)).click();

    }

    private void goToFolderSpam() {
        wait.until(ExpectedConditions.visibilityOf(home.linkMore)).click();
        wait.until(ExpectedConditions.visibilityOf(home.spam)).click();
    }

    private void markLetterAsSpam() {
        wait.until(ExpectedConditions.visibilityOf(home.inbox)).click();
        wait.until(ExpectedConditions.visibilityOf(home.checkbox)).click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonSpam));
        home.buttonSpam.click();

    }

    private void sendMessage(String body) {
        fillAddresseeAndBody(body);
        wait.until(ExpectedConditions.visibilityOf(home.buttonSend)).click();

    }

    private void fillAddresseeAndBody(String body) {
        wait.until(ExpectedConditions.visibilityOf(home.buttonWrite));
        home.buttonWrite.click();
        wait.until(ExpectedConditions.visibilityOf(home.addressee));
        home.addressee.clear();
        home.addressee.sendKeys(USER2);
        wait.until(ExpectedConditions.visibilityOf(home.body));
        home.body.clear();
        home.body.sendKeys(body);
    }

    private void doLogout() {
        wait.until(ExpectedConditions.visibilityOf(home.emailBeforeExit)).click();
        wait.until(ExpectedConditions.visibilityOf(home.buttonLogout)).click();

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
    
    private static void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException!");
        }
    }

}
