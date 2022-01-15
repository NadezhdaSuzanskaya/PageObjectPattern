package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    WebDriver driver;
    protected String baseUrl;
    protected By basePageElementId;
    protected By firstname;
    protected By lastname;
    protected By zipcode;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage open() {
        driver.get(baseUrl);
        return this;
    }

    public boolean isPageLoaded() {
        return driver.findElement(basePageElementId).isDisplayed();
    }

}
