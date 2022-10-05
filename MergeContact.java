package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static ChromeDriver driver;

	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Enter the Username
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		// Enter the Password
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		// Click on Login button
		driver.findElement(By.className("decorativeSubmit")).click();
		// Click on CRM/SFA hypelink
		driver.findElement(By.linkText("CRM/SFA")).click();

		// Click on Contacts & Merge Contacts
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		// Click on Widget of From Contact
		driver.findElement(By.xpath("(//span[text()='From Contact']/following::img)[1]")).click();
		Thread.sleep(5000);
		// Switch to the new window & Convert the Set values to List for switching windows
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listW = new ArrayList<String>(windowHandles);
		// Switch to the second window using get method
		driver.switchTo().window(listW.get(1));
		// Click on First Resulting Contact
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")).click();
		// Switch back to parent window
		driver.switchTo().window(listW.get(0));
		// Click on Widget of To Contact
		driver.findElement(By.xpath("(//span[text()='From Contact']/following::img)[2]")).click();
		Thread.sleep(5000);
		// Switch to the new window & Convert the Set values to List for switching windows
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> listW1 = new ArrayList<String>(windowHandles1);
		// Switch to the second window using get method
		driver.switchTo().window(listW1.get(1));
		// Click on Second Resulting Contact
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
		// Switch back to parent window
		driver.switchTo().window(listW1.get(0));
		//Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		Thread.sleep(2000);
		//Accept the Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		// Verify the title of the page
		System.out.println("Get Title of the final page: " + driver.getTitle());

		driver.close();
	}

}
