package au.com.beginnerseleniumframework.Pages;

import au.com.beginnerseleniumframework.Config.PageConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePage {
    private String pageUrl;

    private By logOutButton = By.className("la-power-off");
    private By message = By.id("flash-messages");
    private By walletBalance = By.className("la-wallet");
    private By totalBookings = By.className("la-shopping-cart");
    private By pendingInvoices = By.className("la-clock");
    private By reviews = By.className("la-star");

    public SecureAreaPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Get URL variable from PageObject */
    public String getPageUrl() {
        PageConfig config = ConfigFactory.create(PageConfig.class);
        pageUrl = config.secureAreaPage();
        return pageUrl;
    }

    /** Verification if logOutButton is visible on the page */
    public boolean isLogOutButtonVisible() {
        return find(logOutButton).isDisplayed();
    }

    public boolean isWalletBalanceVisible() {
        return find(walletBalance).isDisplayed();
    }

    public boolean isTotalBookingsVisible() {
        return find(totalBookings).isDisplayed();
    }

    public boolean isPendingInvoicesVisible() {
        return find(pendingInvoices).isDisplayed();
    }

    public boolean isReviewsVisible() {
        return find(reviews).isDisplayed();
    }

    /** Return text from success message */
    public String getSuccessMessageText() {
        return find(message).getText();
    }
}
