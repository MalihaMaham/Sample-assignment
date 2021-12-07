package au.com.beginnerseleniumframework.Config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:FileConfig.properties"})
public interface FileConfig extends Config {
    String excelPath();
    String csvPath();
}
