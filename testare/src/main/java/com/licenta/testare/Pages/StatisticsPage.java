package com.licenta.testare.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class StatisticsPage {
    protected WebDriver driver;

    private static final String semesterCircleLocator = "//div[1]/div[2]/div/circle-progress/svg";
    private static final String eventsProgressBarLocator = "//div[1]/div[1]/div/div";
    private static final String statisticsTabsLocator = "//div[2]/mat-tab-group/mat-tab-header/div[2]/div/div/div";
    private static final String yearSelectedDropdownLocator = "//div/div/div[1]/mat-form-field/div/div[1]";
    private static final String statisticChartViewLocator = "//div/div/div[2]/div/canvas";
    private static final String pieChartLocator = "//*[@id=\"chart\"]/apx-chart";
    private static final String loadingChartLocator = "//div[2]/div/div[2]/div[1]/div/h5";

    @FindBy(xpath = statisticsTabsLocator)
    private List<WebElement> statisticsTabs;

    @FindBy(xpath = yearSelectedDropdownLocator)
    private WebElement yearSelectedDropdown;

    @FindBy(xpath = statisticChartViewLocator)
    private WebElement statisticChartView;

    @FindBy(xpath = pieChartLocator)
    private WebElement pieChart;

    public StatisticsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkElementsArePresentOnPage() {
        try {
            driver.findElement(By.xpath(semesterCircleLocator));
            driver.findElement(By.xpath(eventsProgressBarLocator));
            driver.findElement(By.xpath(statisticsTabsLocator));

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void clickOnTab(String tabName) {
        for (WebElement tab : statisticsTabs
        ) {
            if (tab.getText().equals(tabName)) {
                tab.click();
                break;
            }
        }
    }

    public void selectYearFromDropdown(String year) throws InterruptedException {
        yearSelectedDropdown.click();
        Thread.sleep(200);
        List<WebElement> values = driver.findElements(By.tagName("mat-option"));
        for (WebElement val : values
        ) {
            if (val.getText().equals(year)) {
                Thread.sleep(200);
                val.click();
                break;
            }
        }
        Thread.sleep(500);
    }

    public boolean statisticChartIsVisible() throws InterruptedException {
        Thread.sleep(500);
        try {
            driver.findElement(By.xpath(statisticChartViewLocator));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkStatisticsTabs(List<String> tabs) {
        int i = 0;
        boolean ok = false;
        for (WebElement tab : statisticsTabs
        ) {
            if (tab.getText().equals(tabs.get(i))) {
                ok = true;
                i++;
            } else {
                ok = false;
                i++;
            }
        }
        return ok;
    }

    public boolean pieChartIsVisible() {
        try {
            driver.findElement(By.xpath(pieChartLocator));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void selectEvaluationFromDropdown(String evaluation) throws InterruptedException {
        yearSelectedDropdown.click();
        Thread.sleep(200);
        List<WebElement> values = driver.findElements(By.tagName("mat-option"));
        for (WebElement val : values
        ) {
            if (val.getText().equals(evaluation)) {
                Thread.sleep(200);
                val.click();
                break;
            }
        }
        Thread.sleep(500);
    }

    public boolean loadingChart() {
        try {
            return driver.findElement(By.xpath(loadingChartLocator)).getText().equals("Nu a fost ales niciun tip de evaluare");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
