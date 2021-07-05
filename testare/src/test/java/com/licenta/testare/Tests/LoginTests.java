package com.licenta.testare.Tests;

import com.licenta.testare.Pages.AnnouncePage;
import com.licenta.testare.Pages.LoginPage;
import com.licenta.testare.Pages.NavbarComponent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTests {
    private LoginPage loginPage;
    private AnnouncePage announcesPage;
    private NavbarComponent navbarComponent;
    public WebDriver driver;

    private static final String LOGIN_URL = "http://localhost:4200/university/login";

    private static final String USER_ROLE = "Student";
    private static final String USERNAME_PROFESOR = "oana.stoleriu@profesor.tuiasi.ro";
    private static final String PASSWORD_PROFESOR = "rrkrfr";
    private static final String USERNAME_STUDENT = "sorina.sosea@student.tuiasi.ro";
    private static final String PASSWORD_STUDENT = "m53oho";
    private boolean INVALID_USER = false;
    private static final String INVALID_USERNAME = "sorina.sosea@student";
    private static final String INVALID_PASSWORD = "m53oho";
    private static final int NOTIFICATION_REQUESTS = 4;
    private static final String NOTIFICATION_MESSAGE = "Username or password are not valid!";
    private static final int ANNOUNCES_NUMBER = 4;

    @Before
    public void logInTheApplication() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validCredentials() throws InterruptedException {
        if (USER_ROLE.equals("Profesor")) {
            announcesPage = loginPage.fiiInCredentials(USERNAME_PROFESOR, PASSWORD_PROFESOR);
        } else {
            announcesPage = loginPage.fiiInCredentials(USERNAME_STUDENT, PASSWORD_STUDENT);
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Assert.assertTrue("The login was not successfully! User was not redirected to announce page.", announcesPage.checkTitlu("Avizier"));
        Assert.assertTrue("The number of announces are not correct!", announcesPage.checkSizeOfAnnounces(ANNOUNCES_NUMBER));
        if (announcesPage.checkTitlu("Avizier")) {
            navbarComponent = new NavbarComponent(this.driver);
            Assert.assertTrue("There are no icons for every page!", navbarComponent.existsElementToEveryPage());
        }
    }

    @Test
    public void invalidCredentials() throws InterruptedException {
        this.INVALID_USER = true;
        announcesPage = loginPage.fiiInCredentials(INVALID_USERNAME, INVALID_PASSWORD);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Assert.assertTrue("The login was  successfully!User was  redirected to announce page.", loginPage.userIsNotified(NOTIFICATION_MESSAGE));

    }


    @Test
    public void profesorNotification() throws InterruptedException {
        this.validCredentials();
        if (USER_ROLE.equals("Profesor")) {
            Assert.assertTrue("There are no icons for every page!", navbarComponent.notificationsAreDisplayed());
            navbarComponent.clickOnNotifications();
            Thread.sleep(1000);
            Assert.assertTrue("The number of notification requests is not correct!", navbarComponent.checkNbOfNotificationRequest(NOTIFICATION_REQUESTS));
        }
    }

    @After
    public void logOutFromApplication() throws InterruptedException {
        if (!this.INVALID_USER) {
            navbarComponent.logout();
            driver.quit();
        }else
        {
            driver.quit();
        }
    }
}
