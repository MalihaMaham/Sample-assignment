package au.com.beginnerseleniumframework.Config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:PageConfig.properties"})
public interface PageConfig extends Config {
    @Key("login.page")
    String loginPage();
    @Key("welcome.page")
    String welcomePage();
    @Key("secure.area.page")
    String secureAreaPage();
    @Key("flights.page")
    String flightsPage();
    @Key("login.fail.page")
    String loginFailPage();
}
