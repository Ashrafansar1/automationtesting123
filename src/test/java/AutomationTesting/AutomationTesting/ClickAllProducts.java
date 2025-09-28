package AutomationTesting.AutomationTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ClickAllProducts {
    public static void main(String[] args) {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        

        try {
            driver.manage().window().maximize();
            driver.get("https://toneop.care/");

            // ✅ Locate "All Products" menu and click
            WebElement allProducts = driver.findElement(By.xpath("//a[text()='All Products']"));
            allProducts.click();

            System.out.println("✅ Clicked on All Products successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}