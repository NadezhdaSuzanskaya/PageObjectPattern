package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.Test;
import web.pages.LoginPage;


public class РurchaseTest extends LoginTest {

    public static final String TEST_PRODUCT_TITLE = "Test.allTheThings() T-Shirt (Red)";

    @Test
    public void makePurchaseTest() {
        catalogPage.addProductToCart(TEST_PRODUCT_TITLE);
        cartPage.open();
        cartPage.isPageLoaded();
        Assert.assertTrue(cartPage.findProductInCard(TEST_PRODUCT_TITLE), "Selected product is not in the cart");
        //click the button
        cartPage.checkout();
        //enter data
        checkoutInfoPage.open();
        checkoutInfoPage.isPageLoaded();
        checkoutInfoPage.enterData(FIRST_NAME, LAST_NAME, ZIP_CODE);
        checkoutInfoPage.clickContinue();
        //click the button "Continue"
        checkoutOverviewPage.open();
        checkoutOverviewPage.isPageLoaded();
        Assert.assertTrue(checkoutOverviewPage.findProductInDescription(TEST_PRODUCT_TITLE), "Selected product is not in the cart");
        checkoutOverviewPage.clickFinish();
        checkoutCompletePage.open();
        checkoutCompletePage.isPageLoaded();




    }

}