package au.com.beginnerseleniumframework.Base;

import au.com.beginnerseleniumframework.Utilities.TestUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest implements TestUtilities {
    protected WebDriver driver;
    protected Logger log;
    protected ExtentReports extent;

    @Parameters({ "browser" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, ITestContext ctx) {
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();

        driver.manage().window().maximize();

        sleep(5);
    }

    @BeforeTest
    public void config(ITestContext ctx) {
        String path = System.getProperty("user.dir")+"\\reports\\" + ctx.getCurrentXmlTest().getName();
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Maven Selenium Automation Framework");
        reporter.config().setDocumentTitle("Test Results");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Reese Gunardi");
    }

    @AfterTest
    public void stop() {
        extent.flush();
    }

    @AfterMethod(alwaysRun = true)
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
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("screenshots\\" + pathname));
    }
}
