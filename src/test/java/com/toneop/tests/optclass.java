package com.toneop.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class optclass {
    WebDriver driver;

    @BeforeClass   
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testHomePageLoads() {
        driver.get("https://toneop.care/");
        String title = driver.getTitle();
        Assert.assertTrue(title.length() > 0, "Home page title is empty!");
    }

    @Test(priority = 2)
    public void testLoginPageLoads() {
        driver.get("https://toneop.care/login");
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("login"), "Login page URL incorrect!");
    }

    @Test(priority = 3)
    public void testRegisterPageLoads() {
        driver.get("https://toneop.care/register");
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("register"), "Register page URL incorrect!");
    }

    @Test(priority = 4)
    public void testPlansPageLoads() {
        driver.get("https://toneop.care/plans");
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("plans"), "Plans page URL incorrect!");
    }

    @Test(priority = 5)
    public void testNavigationLinksExist() {
        driver.get("https://toneop.care/");
        List<WebElement> links = driver.findElements(By.tagName("a"));
        Assert.assertTrue(links.size() > 0, "No navigation links found!");
    }

    @Test(priority = 6)
    public void testFooterExists() {
        driver.get("https://toneop.care/");
        WebElement footer = driver.findElement(By.tagName("footer"));
        Assert.assertTrue(footer.isDisplayed(), "Footer not visible!");
    }

    @Test(priority = 7)
    public void testSimpleButton() {
        driver.get("https://toneop.care/");
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        Assert.assertTrue(buttons.size() > 0, "No buttons found!");
    }

    @Test(priority = 8)
    public void testPageSourceNotEmpty() {
        driver.get("https://toneop.care/");
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.length() > 100, "Page source is too short!");
    }

    @AfterClass   
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
