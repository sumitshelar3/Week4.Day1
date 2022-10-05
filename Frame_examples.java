package week4.day1.assignments;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Frame_examples {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Switch to 1st Frame
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Testing");

		// Switch to nested frame 3rd
		driver.switchTo().frame("frame3");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		// Get back to main Web-page
		driver.switchTo().defaultContent();

		// Switch to 2nd Frame
		driver.switchTo().frame("frame2");
		WebElement dropDown = driver.findElement(By.id("animals"));
		Select s = new Select(dropDown);
		s.selectByIndex(3); // Selecting the index-wise 3rd value
		System.out.println(s.getFirstSelectedOption().getText());// Select 1st option from dropDown

		// Get back to main Web-page
		driver.switchTo().defaultContent();
		driver.quit();
	}

}
