package AutomationTesting.AutomationTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NavigationMenuTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://toneop.care/");
    }

    @Test
    public void clickAllProducts() throws InterruptedException {
        // Click "All Products" menu
        driver.findElement(By.xpath("//a[text()='All Products']")).click();
        Thread.sleep(1000); // wait for page load

        // Verify URL contains "/product/all-product"
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/product/all-product"),
                "URL is incorrect. Current URL: " + currentUrl);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
