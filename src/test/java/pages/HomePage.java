package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    private static final String BODY = "some text";
    
    @FindBy(xpath = "//div[@style='-moz-user-select: none;']//div[@role='button']")
    public WebElement buttonWrite;
    
    @FindBy(xpath = "//form[@enctype='multipart/form-data']")
    public WebElement form;
    
    @FindBy(xpath = "//textarea[@name='to']")
    public WebElement addressee;
    
    @FindBy(xpath = "//div[@role='textbox']")
    public WebElement body;
    
    @FindBy(xpath = "//div[@data-tooltip-delay='800']")
    public WebElement buttonSend;
    
    @FindBy(xpath = "//a[contains(@title,'Google:')]")
    public WebElement emailBeforeExit;
    
    @FindBy(xpath = "//a[contains(@href,'logout')]")
    public WebElement buttonLogout;
    
    @FindBy(xpath = "//span/a[contains(@href,'#inbox')]")
    public WebElement inbox;
    
    @FindBy(xpath = "//span[contains(.,'"+ BODY +"')]")
    public WebElement linkLetter;
    
    @FindBy(xpath = "//div[@style='']/div[@act='9']")
    public WebElement buttonSpam;
    
    @FindBy(xpath = "//div[@role='navigation']//span[@tabindex='0']")
    public WebElement linkMore;
    
    @FindBy(xpath = "//a[contains(@href,'spam')]")
    public WebElement spam;
    
    @FindBy(xpath = "//td[contains(@style,'cursor')]/div[@role='checkbox']")
    public WebElement linkBodyForVerification;
    
    @FindBy(xpath = "//div/div[@act='18']")
    public WebElement buttonNotSpam;
    
    @FindBy(xpath = "//tr[@tabindex='-1']/td[2]/div[1]")
    public WebElement checkbox;

}
