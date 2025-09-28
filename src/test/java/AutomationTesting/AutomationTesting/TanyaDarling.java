package AutomationTesting.AutomationTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TanyaDarling {

    // Define the URL of the website to be tested.
    private static final String URL = "https://toneop.care/";
    
    // Create a WebDriver instance.
    private WebDriver driver;

    // This method sets up the WebDriver before each test.
    public void setup() {
        // You'll need to set the path to your ChromeDriver executable.
        // For example: System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        // Or, use WebDriverManager for automatic driver management.
        // This example assumes ChromeDriver is in your system's PATH.
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    // This method closes the WebDriver after each test.
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // --- Test Case 1: Positive Test - Basic Search Functionality ---
    public void testPositiveSearch() {
        System.out.println("--- Running Test Case 1: Positive Search ---");
        try {
            // Locate the search input field by its placeholder text.
            WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Type text to search...']"));
            searchBar.sendKeys("flax seeds");
            searchBar.sendKeys(Keys.ENTER);

            // Wait for the search results to be visible.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement resultsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Search results for')]")));
            
            // Check if the expected header text is displayed.
            if (resultsHeader.getText().contains("flax seeds")) {
                System.out.println("Test Passed: Search results for 'flax seeds' were displayed.");
            } else {
                System.out.println("Test Failed: Search results for 'flax seeds' were not displayed.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed with Exception: " + e.getMessage());
        }
    }

    // --- Test Case 2: Negative Test - No Results Found ---
    public void testNegativeSearch() {
        System.out.println("\n--- Running Test Case 2: Negative Search ---");
        try {
            WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Type text to search...']"));
            searchBar.sendKeys("nonexistentproduct");
            searchBar.sendKeys(Keys.ENTER);

            // Wait for the "No results" message.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement noResultsMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'No results found')]")));

            // Check if the "No results" message is displayed.
            if (noResultsMessage.isDisplayed()) {
                System.out.println("Test Passed: 'No results found' message was displayed.");
            } else {
                System.out.println("Test Failed: 'No results found' message was not displayed.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed with Exception: " + e.getMessage());
        }
    }

    // --- Test Case 3: Edge Case - Search with Special Characters ---
    public void testEdgeCaseSearch() {
        System.out.println("\n--- Running Test Case 3: Edge Case Search ---");
        try {
            WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Type text to search...']"));
            searchBar.sendKeys("@#$&*");
            searchBar.sendKeys(Keys.ENTER);

            // Wait for the "No results" message.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement noResultsMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'No results found')]")));
            
            // Check if the "No results" message is displayed.
            if (noResultsMessage.isDisplayed()) {
                System.out.println("Test Passed: 'No results found' message was displayed for special characters.");
            } else {
                System.out.println("Test Failed: 'No results found' message was not displayed for special characters.");
            }
        } catch (Exception e) {
            System.out.println("Test Failed with Exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TanyaDarling tester = new TanyaDarling();
        
        // Run Test Case 1
        tester.setup();
        tester.testPositiveSearch();
        tester.teardown();

        // Run Test Case 2
        tester.setup();
        tester.testNegativeSearch();
        tester.teardown();

        // Run Test Case 3
        tester.setup();
        tester.testEdgeCaseSearch();
        tester.teardown();
    }
}
