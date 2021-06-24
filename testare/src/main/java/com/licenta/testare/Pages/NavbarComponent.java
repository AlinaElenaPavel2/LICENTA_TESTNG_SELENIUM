package com.licenta.testare.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavbarComponent {
    protected WebDriver driver;
    private static final String userNameLocator="//*[@id=\"navbarCollapse\"]/links/ul/li[3]/div/h5";
    private static final String profilePictureLocator="//*[@id=\"imgProfile\"]";

    private static final String moreOptionsLocator="//*[@id=\"navbarCollapse\"]/links/ul/li[4]/div[1]/a";
    private static final String announcesPageLocator="//app-announces/app-navigation-bar/div/ul/li[1]/div/a";
    private static final String dashboardPagePageLocator="//app-announces/app-navigation-bar/div/ul/li[2]/div/a";
    private static final String calendarPageLocator="//app-announces/app-navigation-bar/div/ul/li[3]/div/a";
    private static final String libraryPageLocator="//app-announces/app-navigation-bar/div/ul/li[4]/div/a";
    private static final String statisticsPageLocator="//app-announces/app-navigation-bar/div/ul/div/li/div/a";

    @FindBy(xpath= userNameLocator)
    private WebElement userName;

    @FindBy(xpath=profilePictureLocator)
    private WebElement profilePicture;

    @FindBy(xpath=moreOptionsLocator)
    private WebElement moreOptions;

    @FindBy(xpath=announcesPageLocator)
    private WebElement announcesPage;

    @FindBy(xpath=dashboardPagePageLocator)
    private WebElement dashboardPage;

    @FindBy(xpath=calendarPageLocator)
    private WebElement calendarPage;

    @FindBy(xpath=libraryPageLocator)
    private WebElement libraryPage;

    @FindBy(xpath=statisticsPageLocator)
    private WebElement statisticsPage;

    @FindBy(xpath="//*[@id=\"navbarCollapse\"]/links/ul/li[4]/div[2]/a[2]")
    private WebElement logoutButton;

    public NavbarComponent(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public boolean existsElementToEveryPage() {
        try {
            driver.findElement(By.xpath(userNameLocator));
            Thread.sleep(1000);
            driver.findElement(By.xpath(profilePictureLocator));
            driver.findElement(By.xpath(moreOptionsLocator));
            driver.findElement(By.xpath(announcesPageLocator));
            driver.findElement(By.xpath(dashboardPagePageLocator));
            driver.findElement(By.xpath(calendarPageLocator));
            driver.findElement(By.xpath(libraryPageLocator));
            Thread.sleep(1000);
            driver.findElement(By.xpath(statisticsPageLocator));
        } catch (NoSuchElementException | InterruptedException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public CoursePage goToDashboardPage() throws InterruptedException {
        this.getDashboardPage().click();
        Thread.sleep(2000);
        return new CoursePage(driver);
    }

    public CalendarPage goToCalendarPage() throws InterruptedException {
        this.getCalendarPage().click();
        Thread.sleep(2000);
        return new CalendarPage(driver);
    }

    public LibraryPage goToLibraryPage() throws InterruptedException {
        this.getLibraryPage().click();
        Thread.sleep(2000);
        return new LibraryPage(driver);
    }

    public WebElement getProfilePicture() {
        return profilePicture;
    }

    public WebElement getMoreOptions() {
        return moreOptions;
    }

    public WebElement getAnnouncesPage() {
        return announcesPage;
    }

    public WebElement getDashboardPage() {
        return dashboardPage;
    }

    public WebElement getCalendarPage() {
        return calendarPage;
    }

    public WebElement getLibraryPage() {
        return libraryPage;
    }

    public WebElement getStatisticsPage() {
        return statisticsPage;
    }

    public LoginPage logout() throws InterruptedException {
        Thread.sleep(500);
        moreOptions.click();
        Thread.sleep(100);
        logoutButton.click();
        Thread.sleep(1000);
        return new LoginPage(driver);
    }
}
