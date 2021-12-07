package au.com.beginnerseleniumframework.Pages;

import au.com.beginnerseleniumframework.Config.PageConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginPage extends BasePage{
    private By usernameLocator = By.name("email");
    private By passwordLocator = By.name("password");
    private By cookieLocator = By.id("cookie_stop");
    private By loginButtonLocator = By.className("ladda-button");
    private By errorMessageLocator = By.id("flash");

    private final String path = "PageConfig.properties";

    private String pageUrl;

    /** Open LoginPage with it's url */
    public void openPage() {
        getPageUrl();
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Execute log in */
    public SecureAreaPage login(String username, String password) {
        log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
        click(cookieLocator);
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(loginButtonLocator);
        return new SecureAreaPage(driver, log);
    }

    public void negativeLogin(String username, String password) {
        log.info("Executing Negative LogIn with username [" + username + "] and password [" + password + "]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(loginButtonLocator);
    }

    /** Wait for error message to be visible on the page */
    public void waitForErrorMessage() {
        waitForVisibilityOf(errorMessageLocator, 5);
    }

    public String getErrorMessageText() {
        return find(errorMessageLocator).getText();
    }

    public String getPageUrl() {
        PageConfig config = ConfigFactory.create(PageConfig.class);
        pageUrl = config.loginPage();
        return pageUrl;
    }

    public String getFailPageUrl() {
        PageConfig config = ConfigFactory.create(PageConfig.class);
        pageUrl = config.loginFailPage();
        return pageUrl;
    }
    // TODO: get data from apache poi
}
