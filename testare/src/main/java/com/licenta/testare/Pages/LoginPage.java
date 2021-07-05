package com.licenta.testare.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    protected WebDriver driver;
    @FindBy(id= "email")
    public WebElement usernmameInput;

    @FindBy(id="password")
    protected WebElement passwordInput;

    @FindBy(id="submit")
    protected WebElement submitButton;

    @FindBy(xpath="//notifier-notification/p")
    protected WebElement notification;

    public LoginPage(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    public AnnouncePage fiiInCredentials(String username, String password) throws InterruptedException {
        usernmameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        Thread.sleep(2000);
        submitButton.click();
        Thread.sleep(2000);
        return new AnnouncePage(driver);
    }
    public boolean userIsNotified(String notification) throws InterruptedException {
        Thread.sleep(1000);
        return  this.notification.getText().equals(notification);
    }
}
