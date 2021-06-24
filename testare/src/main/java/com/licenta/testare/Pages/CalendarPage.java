package com.licenta.testare.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalendarPage {
    protected WebDriver driver;
    private String adugareEvenimentModal = "h2";
    private String adugareTitlyEvenimentModal = "//app-add-new-event/input[1]";
    private String adugareDescriereEvenimentModal = "//app-add-new-event/input[2]";
    private String adugareStartDateEvenimentModal = "//app-add-new-event/input[3]";
    private String adugareEndDateEvenimentModal = "//app-add-new-event/input[4]";
    private String adugareEvenimentButon = "//app-add-new-event/button[1]";
    private String anulareEvenimentModal = "//app-add-new-event/button[2]";
    private String calendarLocator = "//app-calendar/div/div/div[2]/mwl-calendar-month-view/div/div";

    @FindBy(xpath = "//app-calendar/div/div/div[1]/div[2]/h3")
    private WebElement currentMonth;

    @FindBy(xpath = "//app-root/app-calendar/div/div/div[1]/div[1]/div/div")
    private List<WebElement> changingMonthButtons;

    @FindBy(xpath = "//app-root/app-calendar/div/div/div[1]/div[3]/div/div")
    private List<WebElement> changingViewsButtons;

    @FindBy(xpath = "//app-calendar/div/div/div[3]/div/div/div/div[1]/button")
    private WebElement addNewEventButton;

    @FindBy(xpath = "//app-calendar/div/div/div[3]/div/div/div/div[2]/button")
    private WebElement editareEvenimenteButton;

    @FindBy(xpath = "//app-calendar/div/div/div[3]/div/div[2]/div/table/tbody/tr")
    private List<WebElement> evenimente;


    public CalendarPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkCurentMonth(String month) {
        return currentMonth.getText().equals(month);
    }

    public boolean calendarView() {
        try {
            Thread.sleep(500);
            driver.findElement(By.xpath(calendarLocator));
        } catch (NoSuchElementException | InterruptedException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean checktMonthChange(List<String> month) {
        boolean ok = false;
        int i = 0;

        System.out.println(changingMonthButtons.size());
        for (WebElement button : changingMonthButtons
        ) {
            System.out.println(button.getText());
            if (button.getText().equals(month.get(i))) {
                ok = true;
                i++;
            } else {
                ok = false;
                i++;
            }
        }
        return ok;
    }
    public boolean checkChangingView(List<String> month) {
        boolean ok = false;
        int i = 0;

        System.out.println(changingViewsButtons.size());
        for (WebElement button : changingViewsButtons
        ) {
            System.out.println(button.getText());
            if (button.getText().equals(month.get(i))) {
                ok = true;
                i++;
            } else {
                ok = false;
                i++;
            }
        }
        return ok;
    }

    public boolean checkAdaugareEveniment() {
        try {
            addNewEventButton.click();
            Thread.sleep(500);
            driver.findElement(By.tagName(adugareEvenimentModal)).getText().equals("Adaugare eveniment");
            driver.findElement(By.xpath(adugareTitlyEvenimentModal));
            driver.findElement(By.xpath(adugareDescriereEvenimentModal));
            driver.findElement(By.xpath(adugareStartDateEvenimentModal));
            driver.findElement(By.xpath(adugareEndDateEvenimentModal));
            driver.findElement(By.xpath(adugareEvenimentButon));
            driver.findElement(By.xpath(anulareEvenimentModal));
            Thread.sleep(500);
            driver.findElement(By.xpath(anulareEvenimentModal)).click();
        } catch (NoSuchElementException | InterruptedException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkEditEvents() throws InterruptedException {
        editareEvenimenteButton.click();
        Thread.sleep(200);
        editareEvenimenteButton.click();
        if(evenimente.size() > 0)
        {
            return true;
        }
        return false;
    }
}
