package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.SQLOutput;

public class CatalogPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/inventory.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Products']");
    //div[contains(text(),'Test.allTheThings()')]/ancestor::div[@class='inventory_item']//button

    private static final String PRODUCT_XPATH_PATTERN =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//button";



    public CatalogPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.basePageElementId = TITLE_LOCATOR;
    }

    public void addProductToCart(String partialProductTitle) {
        driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN, partialProductTitle))).click();
    }


}
