package AutomationTesting.AutomationTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PdpSearching {
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
    public void validTestLogin() {
        driver.get("https://mcstg-shop.larsonjuhl.com/en-US/customer/account/login/");

        WebElement usernameField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("pass"));
        WebElement loginButton = driver.findElement(By.xpath("//span[text()='Sign In']"));

        usernameField.sendKeys("shahilsharma@sourcemash.com");
        passwordField.sendKeys("Test@123");
        loginButton.click();
    }

    @Test(priority = 2)
    public void searchingPdp() throws InterruptedException {
        Thread.sleep(3000); // Ideally replace with explicit wait
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("154BL");

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit' and contains(@class, 'search')]"));
        searchButton.click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
  