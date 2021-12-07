package au.com.beginnerseleniumframework.Pages;

import au.com.beginnerseleniumframework.Base.Users;
import au.com.beginnerseleniumframework.Config.PageConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WelcomePage extends BasePage {
    private String pageUrl;
    private By buttonLinkLocator = By.xpath("//a[@class='btn btn-primary btn-lg btn-block']");
    private By customerLocator = By.className("col-md-10");
    private By resourceLocator = By.className("animated");
    private Users[] users = new Users[4];
    private final String path = "PageConfig.properties";

    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Open WelcomePage with it's url */
    public void openPage() {
        PageConfig config = ConfigFactory.create(PageConfig.class);
        pageUrl = config.welcomePage();
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    public Users getUsers() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document.body.style.zoom = '0.75'");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Expected wait for everything to be visible.
        WebElement resource = wait.until(
                ExpectedConditions.visibilityOfElementLocated(resourceLocator));
        List<WebElement> userElements = (driver.findElements(customerLocator));
        List<String> rawUserDetails = new ArrayList<String>();
        for (WebElement element : userElements) {
            rawUserDetails.add(element.getText());
        }

        String[] customerDetails = rawUserDetails.get(0).replaceAll("Email", "")
                .replaceAll("Password", "").split("\n");
        String[] agentDetails = rawUserDetails.get(1).replaceAll("Email", "")
                .replaceAll("Password", "").split("\n");
        String[] adminDetails = rawUserDetails.get(2).replaceAll("Email", "")
                .replaceAll("Password", "").split("\n");
        String[] supplierDetails = rawUserDetails.get(3).replaceAll("Email", "")
                .replaceAll("Password", "").split("\n");
        Users users = new Users();
        users.setCustomer(customerDetails[0].strip(), customerDetails[1].strip());
        users.setAgent(agentDetails[0].strip(), agentDetails[1].strip());
        users.setAdmin(adminDetails[0].strip(), adminDetails[1].strip());
        users.setSupplier(supplierDetails[0].strip(), supplierDetails[1].strip());

        executor.executeScript("document.body.style.zoom = '1'");
        return users;
    }

    /** Open LoginPage by clicking on Form Authentication Link */
    public LoginPage clickFormAuthenticationLink() {
        log.info("Clicking Form Authentication link on Welcome Page");
        click(buttonLinkLocator);
        // store window handle ids
        ArrayList<String> w = new ArrayList<String>(driver.getWindowHandles());
        //switch to open tab
        driver.switchTo().window(w.get(1));
        // explicit wait - to wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div")));
        return new LoginPage(driver, log);
    }
}
