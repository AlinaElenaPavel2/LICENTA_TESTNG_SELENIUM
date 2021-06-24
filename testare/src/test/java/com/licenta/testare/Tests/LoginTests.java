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

    private static final int NOTIFICATION_REQUESTS = 4;

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
        Assert.assertTrue("The login was not successfully!User was not redirected to announce page.", announcesPage.checkTitlu("Avizier"));
        Assert.assertTrue("The number of announces are not correct!", announcesPage.checkSizeOfAnnounces(4));
        if (announcesPage.checkTitlu("Avizier")) {
            navbarComponent = new NavbarComponent(this.driver);
            Assert.assertTrue("There are no icons for every page!", navbarComponent.existsElementToEveryPage());
        }
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


    //    @Test
//    public void validateWrongFormatEmail() throws InterruptedException {
//        loginPage = new LoginPage(driver);
//        loginPage.fiiInCredentials("marin@student.ro", "marin@1998");
//
//
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement messageElement = wait.until(
//                ExpectedConditions.presenceOfElementLocated(By.id("error-wrong-format"))
//        );
//
//        String message = messageElement.getText();
//        String errorMsg= "Wrong format";
//        Assert.assertEquals(message, errorMsg);
//        driver.quit();
//    }

    @After
    public void logOutFromApplication() throws InterruptedException {
        navbarComponent.logout();
        driver.quit();
    }
}
