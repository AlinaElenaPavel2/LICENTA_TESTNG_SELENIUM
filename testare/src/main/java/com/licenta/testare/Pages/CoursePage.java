package com.licenta.testare.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CoursePage {
    protected WebDriver driver;
    String tableLocator="//div/div[2]/table";
    String recuperareButtonLocator="//div/div[1]/button";
    String anunturiLocator="//div/mat-grid-list/div/mat-grid-tile[1]/figure/div/h4";
    String profesoriLocator="//div/mat-grid-list/div/mat-grid-tile[2]/figure/div/h4";
    String evaluareLocator="//div/mat-grid-list/div/mat-grid-tile[2]/figure/div/h4";

    @FindBy(xpath="//app-dashboard/div/div/div/div/mat-tab-group/mat-tab-header/div[2]/div/div/div")
    private List<WebElement> yearsTab;
    @FindBy(xpath="//div/mat-tab-group/mat-tab-header/div[2]/div/div/div")
    private List<WebElement> semestruTab;

    @FindBy(className = "w3-card")
    private List<WebElement> courses;

    @FindBy(xpath = "//app-course/div/h1")
    private WebElement courseTile;

    @FindBy(xpath = "//div/div[1]/h3")
    private WebElement cursuri;

    @FindBy(xpath = "//div/div[2]/h3")
    private WebElement laboratoare;

    @FindBy(xpath = "(//div/div[2]/div)[2]")
    private WebElement laboratoareLinks;

    @FindBy(xpath = "(//div/div[1]/div[1])[4]")
    private WebElement cursuriLinks;

    @FindBy(xpath = "//*[@class=\"mat-tab-labels\"]/div")
    private List<WebElement> courseTabs;


    public CoursePage(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnCourse(String name) throws InterruptedException {
        Thread.sleep(1000);
        for (WebElement course:courses
             ) {
            if(course.findElement(By.tagName("h5")).getText().equals(name))
            {
                Thread.sleep(500);
                course.findElement(By.tagName("a")).click();
                break;
            }
        }
    }

    public boolean getNbOfYears(int years) throws InterruptedException {
        Thread.sleep(2000);
        return yearsTab.size()==years;

    }

    public boolean getNbOfSemestre() throws InterruptedException {
        Thread.sleep(1000);
        return semestruTab.size()-yearsTab.size() ==2;

    }

    public boolean courseTileVerify(String courseTitle){

        return this.courseTile.getText().equals(courseTitle);
    }

    public boolean checkCourseContent() throws InterruptedException {
        boolean present=false;
        Thread.sleep(500);
        if(this.cursuri.getText().equals("Cursuri") && this.laboratoare.getText().equals("Laboratoare") && cursuriLinks.findElements(By.tagName("div")).size()> 0 && laboratoareLinks.findElements(By.tagName("div")).size()> 0)
        {
            present=true;
        }
        return present;
    }

    public boolean checkTabsTitle(List<String > tabsTiles)
    {
        int i=0;
        boolean ok=false;
        for (WebElement tab:courseTabs
             ) {
            if(tab.getText().equals(tabsTiles.get(i)))
            {
                ok=true;
                i++;
            }
        }
        return ok;
    }

    public boolean checkPrezenteTabContent() throws InterruptedException {
        courseTabs.get(1).click();
        Thread.sleep(500);
        try {
            driver.findElement(By.xpath(recuperareButtonLocator));
            Thread.sleep(100);
            driver.findElement(By.xpath(tableLocator));
        } catch (NoSuchElementException | InterruptedException e) {
            return false;
        }
        return true;
    }

    public boolean checkDetaliisiAnunturiTabContent() throws InterruptedException {
        courseTabs.get(2).click();
        Thread.sleep(500);
        try {
            driver.findElement(By.xpath(anunturiLocator));
            Thread.sleep(100);
            driver.findElement(By.xpath(profesoriLocator));
            Thread.sleep(100);
            driver.findElement(By.xpath(evaluareLocator));
            Thread.sleep(100);
        } catch (NoSuchElementException | InterruptedException e) {
            return false;
        }
        return true;
    }
}
