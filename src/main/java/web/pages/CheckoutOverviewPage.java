package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage{
    public static final String BASE_URL = "https://www.saucedemo.com/checkout-step-two.html";
    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Checkout: Overview']");
    private static final By BUTTON_FINISH=By.id("finish");
    private static final By PRODUCT_NAME_LOCATOR = By.className("inventory_item_name");

    public CheckoutOverviewPage (WebDriver driver){
        super(driver);
        this.baseUrl=BASE_URL;
        this.basePageElementId = TITLE_LOCATOR;
    }
    public boolean findProductInDescription(String partialProductTitle) {
        return driver.findElement(PRODUCT_NAME_LOCATOR).isDisplayed();
    }

    public void clickFinish()
    {
        driver.findElement(BUTTON_FINISH).click();
    }
}
