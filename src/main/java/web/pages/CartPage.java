package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/cart.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Your Cart']");
    private static final By PRODUCRT_NAME_LOCATOR = By.className("inventory_item_name");
    private static final By BUTTON_CHECKOUT=By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
        this.baseUrl=BASE_URL;
        this.basePageElementId = TITLE_LOCATOR;
    }
    public boolean findProductInCard(String partialProductTitle) {
        return driver.findElement(PRODUCRT_NAME_LOCATOR).isDisplayed();
    }

    public void checkout()
    {
        driver.findElement(BUTTON_CHECKOUT).click();
    }

}
