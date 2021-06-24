package com.licenta.testare.Tests;

import com.licenta.testare.Pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class StatisticsTests {
    private LoginPage loginPage;
    private AnnouncePage announcesPage;
    private NavbarComponent navbarComponent;
    private StatisticsPage statisticsPage;
    public WebDriver driver;

    private static final String LOGIN_URL = "http://localhost:4200/university/login";

    private static final String USER_ROLE = "Profesor";
    private static final String USERNAME_PROFESOR = "oana.stoleriu@profesor.tuiasi.ro";
    private static final String PASSWORD_PROFESOR = "rrkrfr";
    private static final String USERNAME_STUDENT = "sorina.sosea@student.tuiasi.ro";
    private static final String PASSWORD_STUDENT = "m53oho";

    private static final List<String> STATISTICS_TABS = Arrays.asList("Note", "Evolutie", "Examinari");

    @Before
    public void logInTheApplication() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);
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
    public void getEvolutionChart() throws InterruptedException {
        statisticsPage = navbarComponent.goToStatisticsPage();
        if (USER_ROLE.equals("Student")) {
            Assert.assertTrue("The name of tabs are not correct!", statisticsPage.checkStatisticsTabs(STATISTICS_TABS));
            statisticsPage.clickOnTab("Evolutie");
            statisticsPage.selectYearFromDropdown("2");
            Assert.assertTrue("The chart is not visible!", statisticsPage.statisticChartIsVisible());
        }
    }

    @Test
    public void getMarksChart() throws InterruptedException {
        statisticsPage = navbarComponent.goToStatisticsPage();
        if (USER_ROLE.equals("Student")) {
            Assert.assertTrue("The name of tabs are not correct!", statisticsPage.checkStatisticsTabs(STATISTICS_TABS));
            statisticsPage.clickOnTab("Note");
            statisticsPage.selectYearFromDropdown("2");
            Assert.assertTrue("The chart is not visible!", statisticsPage.statisticChartIsVisible());
        }
    }

    @Test
    public void getExamsChart() throws InterruptedException {
        statisticsPage = navbarComponent.goToStatisticsPage();
        if (USER_ROLE.equals("Student")) {
            Assert.assertTrue("The name of tabs are not correct!", statisticsPage.checkStatisticsTabs(STATISTICS_TABS));
            statisticsPage.clickOnTab("Examinari");
            Assert.assertTrue("The chart is not visible!", statisticsPage.pieChartIsVisible());
        }
    }

    @Test
    public void getEvaluationChart() throws InterruptedException {
        statisticsPage = navbarComponent.goToStatisticsPage();
        if (USER_ROLE.equals("Profesor")) {
            Assert.assertTrue("The loading spinner is not present on page", statisticsPage.loadingChart());
            statisticsPage.selectEvaluationFromDropdown("partial");
            Assert.assertTrue("The chart is not visible!", statisticsPage.statisticChartIsVisible());
        }
    }


    @After
    public void logOutFromApplication() throws InterruptedException {
        navbarComponent.logout();
        driver.quit();
    }
}
