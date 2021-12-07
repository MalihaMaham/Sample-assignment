package au.com.beginnerseleniumframework.Tests;

import au.com.beginnerseleniumframework.Base.BaseTest;
import au.com.beginnerseleniumframework.Base.CSV;
import au.com.beginnerseleniumframework.Base.Excel;
import au.com.beginnerseleniumframework.Config.FileConfig;
import au.com.beginnerseleniumframework.Pages.LoginPage;
import au.com.beginnerseleniumframework.Pages.SecureAreaPage;
import com.aventstack.extentreports.ExtentTest;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileLoginTest extends BaseTest {
    @Test
    @Parameters({"userType"})
    public void LoginTestWithExcel(@Optional("user") String userType) throws Exception {
        ExtentTest test = extent.createTest("Login Test With Excel");
        FileConfig config = ConfigFactory.create(FileConfig.class);
        String[][] tabArray = Excel.get(config.excelPath());

        String username;
        String password;
        switch(userType) {
            case "agent":
                username = tabArray[1][0];
                password = tabArray[1][1];
                break;
            case "admin":
                username = tabArray[2][0];
                password = tabArray[2][1];
                break;
            case "supplier":
                username = tabArray[3][0];
                password = tabArray[3][1];
                break;
            default:
                username = tabArray[0][0];
                password = tabArray[0][1];
                break;
        }

        log.info("Starting login test.");

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

    @Test
    @Parameters({"userType"})
    public void LoginTestWithCSV(@Optional("user") String userType) throws Exception {
        ExtentTest test = extent.createTest("Login With CSV Data Test");
        FileConfig config = ConfigFactory.create(FileConfig.class);
        BufferedReader reader = new BufferedReader(new FileReader(config.csvPath()));
        List<String[]> tabArray = CSV.readAll(reader);

        String username;
        String password;
        switch(userType) {
            case "agent":
                username = tabArray.get(1)[0];
                password = tabArray.get(1)[1];
                break;
            case "admin":
                username = tabArray.get(2)[0];
                password = tabArray.get(2)[1];
                break;
            case "supplier":
                username = tabArray.get(3)[0];
                password = tabArray.get(3)[1];
                break;
            default:
                username = tabArray.get(0)[0];
                password = tabArray.get(0)[1];
                break;
        }

        log.info("Starting login test.");

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
}
