package au.com.beginnerseleniumframework.Tests;

import au.com.beginnerseleniumframework.Base.BaseTest;
import au.com.beginnerseleniumframework.Pages.LoginPage;
import au.com.beginnerseleniumframework.Pages.SecureAreaPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookingTest extends BaseTest {
    @Test
    @Parameters({ "source", "destination"})
    public void PositiveSearchTest(String source, String destination) {

    }
}
