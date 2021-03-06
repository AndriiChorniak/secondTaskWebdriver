package pages;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class HomePage {
    private static final String BODY = "aaa";
    private static final String FILE_NAME = "SpaghettiTests2Task.xlsx";
    
    @FindBy(xpath = "//div[contains(text(),'НАПИСАТЬ')]")
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
    
    @FindBy(xpath = "//span[contains(.,'"+ BODY +"')]")
    public WebElement linkBodyForVerification;
    
    @FindBy(xpath = "//div[@gh='mtb']//div[@act='18']")
    public WebElement buttonNotSpam;
    
    @FindBy(xpath = "//tr[@tabindex='-1']/td[2]/div[1]")
    public WebElement checkbox;
    
    @FindBy(name = "Андрей Чорняк")
    public WebElement name;
    
    @FindBy(xpath = "//div[@gh='tm']//span[@role='checkbox']")
    public WebElement selectAllCheckbox;
    
    @FindBy(xpath = "//div[@act='10']")
    public WebElement buttonDelete;
    
    @FindBy(xpath = "//div[@data-tooltip='Удалить']")
    public WebElement buttonDeleteStarred;
    
    @FindBy(xpath = "//span[@aria-label='Не помечено']")
    public WebElement star;
    
    @FindBy(xpath = "//a[contains(@href,'#starred')]")
    public WebElement starred;
    
    @FindBy(xpath = "//div/span[contains(text(),'"+BODY+"')]")
    public WebElement starredVerification;
    
    @FindBy(xpath = "//div[@gh='s']/div[@tabindex='0']")
    public WebElement settings;
    
    @FindBy(xpath = "//div[@role='menuitem']/div[contains(.,'Темы')]")
    public WebElement tema;
    
    @FindBy(xpath = "//div/img[contains(@src,'newblue')]")
    public WebElement blueTema;
    
    @FindBy(xpath = "//div[contains(text(),'Настройки сохранены.')]")
    public WebElement message;
    
    @FindBy(xpath = "//div[@command='Files']")
    public WebElement attachFile;
    
    @FindBy(xpath = "//div[contains(@aria-label,'Прикрепленный файл')]")
    public WebElement attachedFileName;
    
    @FindBy(xpath = "//div[contains(text(),'"+BODY+"')]")
    public WebElement verifiedTextOfMessage;
    
    @FindBy(xpath = "//div/span[contains(text(),'"+FILE_NAME+"')]")
    public WebElement verifiedFile;
    

}
