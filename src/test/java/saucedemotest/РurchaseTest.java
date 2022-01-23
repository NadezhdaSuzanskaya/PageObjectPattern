package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class РurchaseTest extends BaseTest {

    public static final String TEST_PRODUCT_TITLE = "Test.allTheThings() T-Shirt (Red)";
    public static final String TEST_PRODUCT_TITLE1 = "Sauce Labs Backpack";

    @BeforeMethod
    private void login() {
        loginPage.open();
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(catalogPage.isPageLoaded(), "Catalog page is not loaded");
    }

    @Test
    public void makePurchaseTest() {
        String price = catalogPage.findProductPrice(TEST_PRODUCT_TITLE);
        String desc = catalogPage.findProductDesc(TEST_PRODUCT_TITLE);
        catalogPage.addProductToCart(TEST_PRODUCT_TITLE);

        //the page cart
        cartPage.open();
        cartPage.isPageLoaded();
        //check that in the cart is the same product that was added from the list of products
        checkNameOfProductInCart();
        checkProductDetailsInCart(price, desc);
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

    private void checkProductDetailsInCart(String price, String desc) {
        Assert.assertEquals(cartPage.findProductPrice(TEST_PRODUCT_TITLE), price, "The price is not for selected product");
        Assert.assertEquals(cartPage.findProductDesc(TEST_PRODUCT_TITLE), desc, "The dscription is for not selected product");
    }

    private void checkProductInSecondConfirmationPage(String price, String desc) {
        Assert.assertEquals(checkoutOverviewPage.findProductByNameInDescription(TEST_PRODUCT_TITLE), TEST_PRODUCT_TITLE, "Selected product is not in the cart");
        Assert.assertEquals(checkoutOverviewPage.findProductPrice(TEST_PRODUCT_TITLE), price, "The price is not for selected product");
        Assert.assertEquals(checkoutOverviewPage.findProductDesc(TEST_PRODUCT_TITLE), desc, "The description is not for selected product");
    }

    private void checkNameOfProductInCart() {
        //the page cart
        String product = "";
        cartPage.open();
        cartPage.isPageLoaded();
        if (cartPage.ListOfProductsInCart() != null) {
            for (int i = 0; i < cartPage.ListOfProductsInCart().size(); i++) {
                if (cartPage.ListOfProductsInCart().get(i).equals(TEST_PRODUCT_TITLE)) {
                    product = cartPage.ListOfProductsInCart().get(i);
                }
            }
        }
        Assert.assertEquals(product, TEST_PRODUCT_TITLE, "Selected product is not in the cart");
    }

    @Test
    public void checkCounIconInCartTest() {
        String str1 = "";
        catalogPage.addProductToCart(TEST_PRODUCT_TITLE);
        cartPage.open();
        cartPage.isPageLoaded();
        str1 = Integer.toString(cartPage.ListOfProductsInCart().size());
        Assert.assertEquals(str1, cartPage.CheckCountIcon(), "Value of added products is not right");
    }

    @Test
    public void checkEmptyIconInCartTest() {
        cartPage.open();
        cartPage.isPageLoaded();
        Assert.assertTrue(cartPage.CheckCountIconIsNull(), "Some value is displayed on the icon 'Cart'");
    }

    @AfterMethod
    private void removeFromCart() {
        if ((cartPage.findContinueShoppingButton()) && (loginPage.findLoginButton())) {
            catalogPage.returnToOriginalState();
        } else if (loginPage.findLoginButton()) {
            cartPage.returnToProductsPage();
            catalogPage.returnToOriginalState();
        }

    }

}