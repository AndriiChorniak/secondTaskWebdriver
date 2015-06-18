package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    
    @FindBy(xpath = "//input[@type='email']")
    public WebElement email;
    
    @FindBy(xpath = "//input[@id='next']")
    public WebElement next;
    
    @FindBy(xpath = "//input[@id='signIn']")
    public WebElement signIn;
    
    @FindBy(xpath = "//input[@name='Passwd']")
    public WebElement password;
    
    @FindBy(xpath = "//a[contains(@href,'/AccountChooser')]")
    public WebElement linkEnterAnotherAcount;
    
    @FindBy(xpath = "//a[contains(@href,'ServiceLogin')]")
    public WebElement linkAddAcount;

}
