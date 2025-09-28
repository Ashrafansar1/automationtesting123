package AutomationTesting.AutomationTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://toneop.care/"); // Open website
    }

    @Test
    public void verifyHomePageTitle() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("ToneOp Care"), "Homepage title mismatch!");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit(); // Close browser safely
    }
}
