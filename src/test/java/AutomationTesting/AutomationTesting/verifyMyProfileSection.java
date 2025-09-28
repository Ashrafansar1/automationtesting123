package AutomationTesting.AutomationTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class verifyMyProfileSection {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void loginToSite() {
        driver.get("https://mcstg-shop.larsonjuhl.com/en-US/customer/account/login/");

        driver.findElement(By.id("email")).sendKeys("shahilsharma@sourcemash.com");
        driver.findElement(By.id("pass")).sendKeys("Test@123");
        driver.findElement(By.xpath("//span[text()='Sign In']")).click();

        // Wait for profile image to ensure login was successful
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='profile_img']")));
    }

    @Test(priority = 2)
    public void verifyMyProfileSection1() {
        try {
            WebElement profileSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'my-account')]"))); // Adjust if needed

            if (profileSection.isDisplayed()) {
                System.out.println("✅ My Profile section is visible on the homepage.");
            } else {
                System.out.println("❌ My Profile section is NOT visible.");
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error while verifying My Profile section: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void hoverOverProfileImage() {
        try {
            WebElement profileImg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//img[@class='profile_img']")));

            Actions actions = new Actions(driver);
            actions.moveToElement(profileImg).perform();
            Thread.sleep(4000);

            System.out.println("✅ Hovered over profile image successfully.");
        } catch (Exception e) {
            System.out.println("⚠️ Hover action failed: " + e.getMessage());
        }
    }
    @Test(priority = 4)
    public void clickSignOut() {
        try {
            WebElement signout = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[normalize-space(text())='Sign Out']")));
            signout.click();
            System.out.println("✅ Clicked on Sign Out successfully.");
        } catch (Exception e) {
            System.out.println("⚠️ Error clicking on Sign Out: " + e.getMessage());
        }
    }
    
}