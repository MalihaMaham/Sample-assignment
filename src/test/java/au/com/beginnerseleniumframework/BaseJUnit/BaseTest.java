package au.com.beginnerseleniumframework.BaseJUnit;

import au.com.beginnerseleniumframework.Base.BrowserDriverFactory;
import au.com.beginnerseleniumframework.Utilities.TestUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;

@ExtendWith(CustomParameterResolver.class)
public class BaseTest implements TestUtilities {
    protected WebDriver driver;
    protected Logger log;

    @BeforeEach
    public void setUp(String browser) {
        log = LogManager.getLogger("JUnit Test");

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();

        driver.manage().window().maximize();

        sleep(5);
    }

    @AfterEach
    public void tearDown() {
        log.info("Close driver");
        // Close browser
        driver.quit();
    }

    @Override
    public void sleep(long seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    @Override
    public void takeScreenshot(String pathname) throws IOException {

    }
}
