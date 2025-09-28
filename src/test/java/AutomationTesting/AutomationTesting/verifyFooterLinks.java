package AutomationTesting.AutomationTesting;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class verifyFooterLinks {
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

        // Wait for homepage image to confirm login success
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//img[contains(@src, 'newsletter-home_1.png')]")));
    }

    @Test(priority = 2)
    public void clickAllFooterLinks() throws InterruptedException {
        List<WebElement> footerLinks = driver.findElements(By.xpath("//footer//a"));

        for (int i = 0; i < footerLinks.size(); i++) {
            try {
                // Re-find footer links after each navigation
                footerLinks = driver.findElements(By.xpath("//footer//a"));
                WebElement link = footerLinks.get(i);
                String linkText = link.getText().trim();

                if (!linkText.isEmpty()) {
                    System.out.println(" Clicking: " + linkText);

                    // Scroll to and click via JS
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);

                    // Wait for page load
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
                    System.out.println(" Navigated to: " + driver.getCurrentUrl());

                    // Go back
                    driver.navigate().back();

                    // Wait again for footer
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//footer")));
                }

            } catch (Exception e) {
                System.out.println("⚠️ Error on link index [" + i + "]: " + e.getMessage());
            }
        }

        driver.quit(); // Optional: Close browser after test
    }
}