package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/cart.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Your Cart']");
    private static final By PRODUCRT_NAME_LOCATOR = By.className("inventory_item_name");
    private static final By BUTTON_CHECKOUT=By.id("checkout");
    private static final String PRODUCT_DESC_LOCATOR ="//div[contains(text(),'%s')]/ancestor::div[@class='cart_item']//div[@class='inventory_item_desc']";
    private static final String PRODUCT_PRICE_LOCATOR ="//div[contains(text(),'%s')]/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']";



    public CartPage(WebDriver driver) {
        super(driver);
        this.baseUrl=BASE_URL;
        this.basePageElementId = TITLE_LOCATOR;
    }
    public String findProductByNameInCard(String partialProductTitle) {
        return driver.findElement(PRODUCRT_NAME_LOCATOR).getText();
    }

    public String  findProductPrice(String partialProductTitle) {
        return   driver.findElement(By.xpath(String.format(PRODUCT_PRICE_LOCATOR , partialProductTitle))).getText();
    }

    public String  findProductDesc(String partialProductTitle) {

        return   driver.findElement(By.xpath(String.format(PRODUCT_DESC_LOCATOR, partialProductTitle))).getText();
    }

    public void checkout()
    {
        driver.findElement(BUTTON_CHECKOUT).click();
    }

}
