package com.licenta.testare.Pages;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AnnouncePage {
    protected WebDriver driver;
    @FindBy(xpath = "//app-announces/div/div[1]/div[2]/p")
    private WebElement titlu;

    @FindBy(className = "aboutMongContent")
    private List<WebElement> anunturi;

    @FindBy(xpath = "//mat-dialog-actions/button[1]/span[1]")
    private WebElement yesButtonDialogBoxLogout;
    @FindBy(xpath = "//app-nav-bar/ul/li[4]/button")
    private WebElement logoutButton;

    public AnnouncePage(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkTitlu(String titlu) {
        try {
            this.titlu.getText().equals(titlu);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean checkSizeOfAnnounces(int size) {
        try {
            return this.anunturi.size()==size;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
