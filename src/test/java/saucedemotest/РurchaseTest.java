package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.Test;
import web.pages.LoginPage;


public class РurchaseTest extends LoginTest {

    public static final String TEST_PRODUCT_TITLE = "Test.allTheThings() T-Shirt (Red)";


    @Test
    public void makePurchaseTest() {
        String price =catalogPage.findProductPrice(TEST_PRODUCT_TITLE);
        String desc =catalogPage.findProductDesc(TEST_PRODUCT_TITLE);
        catalogPage.addProductToCart(TEST_PRODUCT_TITLE);

        //the page cart
        cartPage.open();
        cartPage.isPageLoaded();
        //check that in the cart is the same product that was added from the list of products
        checkProductInCart(price, desc);
        //click the button 'checkout'
        cartPage.checkout();

        //the page 1-step confirmation
        checkoutInfoPage.open();
        checkoutInfoPage.isPageLoaded();
        //enter data
        checkoutInfoPage.enterData(FIRST_NAME, LAST_NAME, ZIP_CODE);
        //click the button "Continue"
        checkoutInfoPage.clickContinue();

        //the page 2-step confirmation
        checkoutOverviewPage.open();
        checkoutOverviewPage.isPageLoaded();
        checkProductInSecondConfirmationPage(price, desc);
        checkoutOverviewPage.clickFinish();

        //the page 3-step confirmation
        checkoutCompletePage.open();
        checkoutCompletePage.isPageLoaded();
    }

    private void checkProductInCart(String price, String desc)
    {
        Assert.assertEquals(cartPage.findProductByNameInCard(TEST_PRODUCT_TITLE),TEST_PRODUCT_TITLE, "Selected product is not in the cart");
        Assert.assertEquals(cartPage.findProductPrice(TEST_PRODUCT_TITLE),price, "The price is not for selected product");
        Assert.assertEquals(cartPage.findProductDesc(TEST_PRODUCT_TITLE), desc,"The dscription is for not selected product");
    }

    private void checkProductInSecondConfirmationPage(String price, String desc)
    {
        Assert.assertEquals(checkoutOverviewPage.findProductByNameInDescription(TEST_PRODUCT_TITLE),TEST_PRODUCT_TITLE, "Selected product is not in the cart");
        Assert.assertEquals(checkoutOverviewPage.findProductPrice(TEST_PRODUCT_TITLE),price, "The price is not for selected product");
        Assert.assertEquals(checkoutOverviewPage.findProductDesc(TEST_PRODUCT_TITLE), desc,"The description is not for selected product");
    }

}