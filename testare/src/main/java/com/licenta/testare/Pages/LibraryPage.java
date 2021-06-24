package com.licenta.testare.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LibraryPage {
    protected WebDriver driver;

    private String uploadBookLocator="//div/div[1]/h1";
    private String postLinksLocator="//*[@id=\"mat-tab-content-0-0\"]/div/div[2]/h1";

    @FindBy(id = "filtrare")
    private WebElement filtrareCourseInput;

    @FindBy(xpath = "//div/h3[1]")
    private WebElement linkuriTitle;

    @FindBy(xpath = "//div/h3[2]")
    private WebElement cartiTitle;

    @FindBy(xpath = "//app-library/div/div/div[1]/div")
    private List<WebElement> linkuri;

    @FindBy(xpath = "//app-library/div/div/div[2]/div/div[1]/div")
    private List<WebElement> carti;

    @FindBy(xpath = "//app-library/div/div/div/mat-tab-group/mat-tab-header/div[2]/div/div/div")
    private List<WebElement> libraryTabs;

    @FindBy(xpath = "//div/div[1]/h1")
    private List<WebElement> uploadBooks;

    @FindBy(xpath = "//*[@id=\"mat-tab-content-0-0\"]/div/div[2]/h1")
    private List<WebElement> postLinks;

    @FindBy(className = "linksContainer")
    private WebElement links;

    @FindBy(className = "grid-books")
    private WebElement books;

    public LibraryPage(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public boolean filtrare(String disciplina) throws InterruptedException {
        int linksSize = linkuri.size();
        int booksSize = carti.size();
        filtrareCourseInput.sendKeys(disciplina);
        Thread.sleep(1000);
        int linksSizeAfterFiltering = linkuri.size();
        int booksSizeAfterFiltering = carti.size();
        boolean ok=false;
        System.out.println(linksSize);
        System.out.println(booksSize);
        System.out.println(linksSizeAfterFiltering);
        System.out.println(booksSizeAfterFiltering);

        if(linksSizeAfterFiltering <= linksSize && booksSizeAfterFiltering <= booksSize)
        {
            ok=true;
        }
        return ok;
    }

    public boolean checkLinkuriTile()
    {
        return linkuriTitle.getText().equals("Link-uri");
    }

    public boolean checkBooksTitle()
    {
        return cartiTitle.getText().equals("Carti");
    }

    public boolean checkUploadBooks() {
        try {
            driver.findElement(By.xpath(uploadBookLocator));
            Thread.sleep(1000);

        } catch (NoSuchElementException | InterruptedException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkPostLinks() {
        try {

            Thread.sleep(1000);
            driver.findElement(By.xpath(postLinksLocator));
        } catch (NoSuchElementException | InterruptedException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean checkLibraryTabs(List<String> tabs) {
        int i=0;
        boolean ok=false;
        for (WebElement tab:libraryTabs
             ) {
            System.out.println(tab.getText());
            if(tab.getText().equals(tabs.get(i)))
            {
                ok=true;
                i++;
            }else{
                ok=false;
                i++;
            }
        }
        return ok;
    }

    public void clickOnSecondTab() throws InterruptedException {
        libraryTabs.get(1).click();
        Thread.sleep(500);
    }

    public boolean checkPostedLinks() throws InterruptedException {
       return  links.findElements(By.tagName("div")).size() > 0;
    }

    public boolean checkPostedBooks() throws InterruptedException {
        return books.findElements(By.tagName("div")).size() > 0;
    }
}
