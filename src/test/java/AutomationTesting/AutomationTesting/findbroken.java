package AutomationTesting.AutomationTesting;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class findbroken {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://food-order-app-frontend-hfku.onrender.com/");

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("🔗 Total Links Found: " + links.size());

        int brokenCount = 0;
        int validCount = 0;

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            String text = link.getText().trim();

            if (url == null || url.isEmpty()) {
                System.out.println("⚠️ Skipped empty/null href. Text: \"" + text + "\"");
                continue;
            }

            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("HEAD");
                conn.setConnectTimeout(3000);
                conn.connect();

                int responseCode = conn.getResponseCode();

                if (responseCode >= 400) {
                    System.out.println("❌ Broken link: " + url + " | Text: \"" + text + "\" --> " + responseCode);
                    brokenCount++;
                } else {
                    System.out.println("✅ Valid link: " + url + " | Text: \"" + text + "\" --> " + responseCode);
                    validCount++;
                }

            } catch (Exception e) {
                System.out.println("⚠️ Error checking link: " + url + " | Text: \"" + text + "\" --> " + e.getMessage());
                brokenCount++;
            }
        }

        // Summary
        System.out.println("\n📊 Test Summary:");
        System.out.println("✔️ Valid Links: " + validCount);
        System.out.println("❌ Broken Links: " + brokenCount);

        driver.quit();
    }
}
