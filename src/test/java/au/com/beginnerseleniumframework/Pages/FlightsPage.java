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

public class FlightsPage extends BasePage{
    private String pageUrl;
    private final String path = "PageConfig.properties";
    private By sourceLocator = By.name("from");
    private By destinationLocator = By.name("to");
    private By searchButtonLocator = By.id("flights-search");

    public FlightsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public String getPageUrl() {
        PageConfig config = ConfigFactory.create(PageConfig.class);
        pageUrl = config.flightsPage();
        return pageUrl;
    }

    public SearchResultsPage SearchResults(String source, String destination) {
        type(source, sourceLocator);
        type(destination, destinationLocator);
        click(searchButtonLocator);
        return new SearchResultsPage(driver, log);
    }

}
