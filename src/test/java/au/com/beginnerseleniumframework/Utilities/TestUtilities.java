package au.com.beginnerseleniumframework.Utilities;

import java.io.IOException;

public interface TestUtilities {
    void sleep(long seconds);
    void takeScreenshot(String pathname) throws IOException;
}
