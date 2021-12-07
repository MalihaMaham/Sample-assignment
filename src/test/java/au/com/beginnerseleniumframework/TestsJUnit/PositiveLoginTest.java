package au.com.beginnerseleniumframework.TestsJUnit;

import au.com.beginnerseleniumframework.BaseJUnit.BaseTest;
import au.com.beginnerseleniumframework.Base.Users;
import au.com.beginnerseleniumframework.Pages.LoginPage;
import au.com.beginnerseleniumframework.Pages.SecureAreaPage;
import au.com.beginnerseleniumframework.Pages.WelcomePage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;

public class PositiveLoginTest extends BaseTest {
    @ParameterizedTest
    @CsvSource({
            "user@phptravels.com, demouser, chrome",
            "agent@phptravels.com, demoagent, chrome",
            "admin@phptravels.com, demoadmin, chrome",
            "supplier@phptravels.com, demosupplier, chrome"
    })
    public void LoginTest(String username, String password, String browser) {
        log.info("Starting login test.");

        // Click on Form Authentication link
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.openPage();

        Assertions.assertEquals(loginPage.getCurrentUrl(), loginPage.getPageUrl());

        // execute log in
        SecureAreaPage secureAreaPage = loginPage.login(username, password);

        // Verifications
        // New page url is expected
        Assertions.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

        // Assert menu option are visible
        Assertions.assertTrue(secureAreaPage.isLogOutButtonVisible(), "Couldn't find logout button.");
        log.info("Found Logout button.");
        // Assert badges are visible
        Assertions.assertTrue(secureAreaPage.isWalletBalanceVisible());
        Assertions.assertTrue(secureAreaPage.isTotalBookingsVisible());
        Assertions.assertTrue(secureAreaPage.isPendingInvoicesVisible());
        Assertions.assertTrue(secureAreaPage.isReviewsVisible());
    }

    // TODO: fix chromeheadless
    @ParameterizedTest
    @CsvSource({
            "chrome",
            "firefox",
            "firefoxheadless"
    })
    public void LoginNoParamTest(String browser) {
        log.info("Starting login test.");
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
        Users users = welcomePage.getUsers();
        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
        Assertions.assertEquals(loginPage.getCurrentUrl(), loginPage.getPageUrl());
        SecureAreaPage secureAreaPage = loginPage.login(users.getCustomer()[0], users.getCustomer()[1]);
        // New page url is expected
        Assertions.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());
    }
}
