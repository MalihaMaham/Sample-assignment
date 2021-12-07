package au.com.beginnerseleniumframework.Tests;

import au.com.beginnerseleniumframework.Base.BaseTest;
import au.com.beginnerseleniumframework.Pages.LoginPage;
import au.com.beginnerseleniumframework.Pages.SecureAreaPage;
import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeLoginTest extends BaseTest {
    @Test
    @Parameters({ "username"})
    public void LoginTest(@Optional("agent@phptravels.com") String username) {
        log.info("Starting login test.");
        ExtentTest test = extent.createTest("Login Test");
        // Click on Form Authentication link
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.openPage();

        Assert.assertEquals(loginPage.getCurrentUrl(), loginPage.getPageUrl());
        Faker faker = new Faker();
        String password = "demo" + faker.name().firstName();
        //Login
        loginPage.login(username, password);

        // Verifications
        // New page url is expected
        Assert.assertEquals(loginPage.getCurrentUrl(), loginPage.getFailPageUrl());

    }

}
