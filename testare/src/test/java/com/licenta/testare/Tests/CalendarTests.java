package com.licenta.testare.Tests;

import com.licenta.testare.Pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class CalendarTests {
    private LoginPage loginPage;
    private AnnouncePage announcesPage;
    private NavbarComponent navbarComponent;
    private CalendarPage calendarPage;
    public WebDriver driver;

    private static final String LOGIN_URL = "http://localhost:4200/university/login";

    private static final String ROLE = "Student";
    private static final String USERNAME_PROFESOR = "oana.stoleriu@profesor.tuiasi.ro";
    private static final String PASSWORD_PROFESOR = "rrkrfr";
    private static final String USERNAME_STUDENT = "sorina.sosea@student.tuiasi.ro";
    private static final String PASSWORD_STUDENT = "m53oho";

    private static final String CURRENT_MONTH = "June 2021";
    private static final List<String> MONTH_OPTIONS = Arrays.asList("Anterioara", "Curenta", "Urmatoare");
    private static final List<String> CHANGING_VIEW_OPTIONS = Arrays.asList("Luna", "Saptamana", "Zi");

    @Before
    public void logInTheApplication() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);
        if (ROLE.equals("Profesor")) {
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
    public void accesarePaginaCalendar() throws InterruptedException {
        calendarPage = navbarComponent.goToCalendarPage();
        calendarPage.checkCurentMonth(CURRENT_MONTH);
        Assert.assertTrue("The calendar it is not displayed!", calendarPage.calendarView());
        Assert.assertTrue("The month options are not displayed it is not displayed!", calendarPage.checktMonthChange(MONTH_OPTIONS));
        Assert.assertTrue("The changing view options are not displayed it is not displayed!", calendarPage.checkChangingView(CHANGING_VIEW_OPTIONS));
        if (ROLE.equals("Profesor")) {
            Assert.assertTrue("Button for adding event it is not displayed!", calendarPage.checkAdaugareEveniment());
            Assert.assertTrue("Button for adding event it is not displayed!", calendarPage.checkEditEvents());
        }

    }

    @After
    public void logOutFromApplication() throws InterruptedException {
        navbarComponent.logout();
        driver.quit();
    }
}
