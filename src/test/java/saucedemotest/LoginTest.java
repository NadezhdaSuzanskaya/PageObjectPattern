package saucedemotest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.LoginPage;

public class LoginTest extends BaseTest {


    public void openLoginPage() {
        loginPage.open();
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
    }

    @BeforeMethod
    public void validCredentialsLoginTest() {
        openLoginPage();
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(catalogPage.isPageLoaded(), "Catalog page is not loaded");
    }

    @Test
    public void USERNAME_PLACEHOLDER_TEST() {
        openLoginPage();
        Assert.assertEquals(
                loginPage.getUsernamePlaceholder(),
                LoginPage.USERNAME_TEXT_FIELD_PLACEHOLDER,
                "Username placeholder is not valid"
        );
    }

    @Test
    public void invalidCredentialsLoginTest() {
        openLoginPage();
        loginPage.login(WRONG_USERNAME, WRONG_PASSWORD);
        Assert.assertEquals(loginPage.getErrorText(),"Epic sadface: Username and password do not match any user in this service",
                "The text message when credentials are wrong is not correct");
    }

    @Test
    public void requiredUserNameTest() {
        openLoginPage();
        loginPage.loginWithoutUsername(WRONG_PASSWORD);
        Assert.assertEquals(loginPage.getErrorText(),"Epic sadface: Username is required",
                "The text message when username is absent  is not correct");
    }

    @Test
    public void requiredPasswordTest() {
        openLoginPage();
        loginPage.loginWithoutPassword(WRONG_USERNAME);
        Assert.assertEquals(loginPage.getErrorText(),"Epic sadface: Password is required",
                "The text message when password is absent  is not correct");
    }

}
