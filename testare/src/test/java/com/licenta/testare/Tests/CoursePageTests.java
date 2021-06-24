package com.licenta.testare.Tests;

import com.licenta.testare.Pages.AnnouncePage;
import com.licenta.testare.Pages.CoursePage;
import com.licenta.testare.Pages.LoginPage;
import com.licenta.testare.Pages.NavbarComponent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.Arrays;

public class CoursePageTests {
    private LoginPage loginPage;
    private AnnouncePage announcesPage;
    private NavbarComponent navbarComponent;
    private CoursePage coursePage;
    public WebDriver driver;

    @Before
    public void logInTheApplication() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/university/login");
        loginPage = new LoginPage(driver);
        announcesPage = loginPage.fiiInCredentials("sorina.sosea@student.tuiasi.ro", "m53oho");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        Assert.assertTrue("The login was not successfully!User was not redirected to announce page.", announcesPage.checkTitlu("Avizier"));
        Assert.assertTrue("The number of announces are not correct!", announcesPage.checkSizeOfAnnounces(4));
        if (announcesPage.checkTitlu("Avizier")) {
            navbarComponent = new NavbarComponent(this.driver);
            Assert.assertTrue("There are no icons for every page!", navbarComponent.existsElementToEveryPage());
        }
    }

    @Test
    public void accesareCurs() throws InterruptedException {
        coursePage = navbarComponent.goToDashboardPage();
        Assert.assertTrue("The number of years are not correct!", coursePage.getNbOfYears(3));
        Assert.assertTrue("The number of semesters are not correct!", coursePage.getNbOfSemestre());
        coursePage.clickOnCourse("Marketing");
        Thread.sleep(2000);
        coursePage.courseTileVerify("Marketing");
        Assert.assertTrue("The course content is not present!", coursePage.checkCourseContent());
        Assert.assertTrue("Course tabs are correct!", coursePage.checkTabsTitle(Arrays.asList("Cursuri", "Prezente", "Detalii disciplina si anunturi")));
        Assert.assertTrue("Prezente tab content is not complete!", coursePage.checkPrezenteTabContent());
        Assert.assertTrue("Detalii si anunturi tab content is not complete!", coursePage.checkDetaliisiAnunturiTabContent());

    }

    @After
    public void logOutFromApplication() throws InterruptedException {
        navbarComponent.logout();
        driver.quit();
    }
}
