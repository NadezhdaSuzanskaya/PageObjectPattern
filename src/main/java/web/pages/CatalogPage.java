package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.SQLOutput;

public class CatalogPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/inventory.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Products']");
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By LOGOUT_BUTTON = By.xpath("//a[@class='bm-item menu-item' and text()='Logout']");
    private static final By COUNT_PRODUCT_IN_CART = By.className("shopping_cart_badge");
    //div[contains(text(),'Test.allTheThings()')]/ancestor::div[@class='inventory_item']//button

    private static final String PRODUCT_XPATH_PATTERN =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//button";

    private static final String PRODUCT_IMG_XPATH_PATTERN =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//img";
              //div[contains(text(),'Test.allTheThings()')]/ancestor::div[@class='inventory_item']//img


    private static final String PRODUCT_PRICE_XPATH_PATTERN =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']";

    private static final String PRODUCT_DESC_XPATH_PATTERN =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//div[@class='inventory_item_desc']";

    public CatalogPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.basePageElementId = TITLE_LOCATOR;
    }

    public void addProductToCart(String partialProductTitle) {
        driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN, partialProductTitle))).click();
    }

    public String  getCountOfProductInCart() {
     return   driver.findElement(COUNT_PRODUCT_IN_CART).getText();
    }

    public String  findProductPrice(String partialProductTitle) {
        return   driver.findElement(By.xpath(String.format(PRODUCT_PRICE_XPATH_PATTERN, partialProductTitle))).getText();
    }

    public String  findProductAddButton(String partialProductTitle) {
        return   driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN, partialProductTitle))).getText();
    }

    public String  findProductDesc(String partialProductTitle) {

        return   driver.findElement(By.xpath(String.format(PRODUCT_DESC_XPATH_PATTERN, partialProductTitle))).getText();
    }

    public boolean findProductImg(String partialProductTitle)
    {
        return   driver.findElement(By.xpath(String.format(PRODUCT_IMG_XPATH_PATTERN, partialProductTitle))).isDisplayed();}


    public void logout() {
        driver.findElement(MENU_BUTTON).click();
        //проверить что на выплывающей панеле есть крестик
        driver.findElement(LOGOUT_BUTTON).click();
        //проверить что открыта неавторизованная страница
    }

}
