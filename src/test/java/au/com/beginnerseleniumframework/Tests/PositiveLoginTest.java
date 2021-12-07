package au.com.beginnerseleniumframework.Tests;

import au.com.beginnerseleniumframework.Base.BaseTest;
import au.com.beginnerseleniumframework.Base.Excel;
import au.com.beginnerseleniumframework.Base.Users;
import au.com.beginnerseleniumframework.Config.FileConfig;
import au.com.beginnerseleniumframework.Pages.WelcomePage;
import au.com.beginnerseleniumframework.Pages.LoginPage;
import au.com.beginnerseleniumframework.Pages.SecureAreaPage;

import com.aventstack.extentreports.ExtentTest;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class PositiveLoginTest extends BaseTest {
    @Test
    @Parameters({ "username", "password" })
    public void LoginTest(@Optional("agent@phptravels.com") String username, @Optional("demoagent") String password) {
        log.info("Starting login test.");
        ExtentTest test = extent.createTest("Login Test");
        // Click on Form Authentication link
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.openPage();

        Assert.assertEquals(loginPage.getCurrentUrl(), loginPage.getPageUrl());

        // execute log in
        SecureAreaPage secureAreaPage = loginPage.login(username, password);

        // Verifications
        // New page url is expected
        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

        // Assert menu option are visible
        Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(), "Couldn't find logout button.");
        log.info("Found Logout button.");
        // Assert badges are visible
        Assert.assertTrue(secureAreaPage.isWalletBalanceVisible());
        Assert.assertTrue(secureAreaPage.isTotalBookingsVisible());
        Assert.assertTrue(secureAreaPage.isPendingInvoicesVisible());
        Assert.assertTrue(secureAreaPage.isReviewsVisible());
    }

    // TODO: fix chromeheadless
    @Test
    public void LoginNoParamTest() throws IOException {
        log.info("Starting login test.");
        ExtentTest test = extent.createTest("Login No Parameter Test");
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        Users users = welcomePage.getUsers();
        takeScreenshot(users.getCustomer()[0] + "welcomepage.png");
        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
        takeScreenshot(users.getCustomer()[0] + "loginpage.png");
        Assert.assertEquals(loginPage.getCurrentUrl(), loginPage.getPageUrl());
        SecureAreaPage secureAreaPage = loginPage.login(users.getCustomer()[0], users.getCustomer()[1]);
        takeScreenshot(users.getCustomer()[0] + "securepage.png");
        // New page url is expected
        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());
    }


}
