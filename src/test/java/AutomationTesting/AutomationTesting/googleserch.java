package AutomationTesting.AutomationTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class googleserch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      WebDriver driver=new ChromeDriver();
      driver.get("https://www.google.com");
      WebElement serchbox=driver.findElement(By.className("q"));
      serchbox.sendKeys("selenium");
      serchbox.submit();
      ;
	}

}
