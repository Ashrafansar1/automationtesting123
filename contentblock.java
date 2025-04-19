package AutomationTesting.AutomationTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class contentblock {
	WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test(priority = 1)
    public void validtestLogin() {
        driver.get("https://mcstg-shop.larsonjuhl.com/en-US/customer/account/login/");
        // Find elements
        WebElement usernameField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("pass"));
        WebElement loginButton = driver.findElement(By.xpath("//span[@title=''][text()='Sign In']"));
        // Perform actions
        usernameField.sendKeys("shahilsharma@sourcemash.com");
        passwordField.sendKeys("Test@123");
        loginButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 2000);");
        WebElement contententblock = driver.findElement(By.xpath("//img[@alt='Promise Statement | Moulding']"));
        contententblock .click();
    }
}