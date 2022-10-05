package week4.day1.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafgroundWindow {

	public static ChromeDriver driver;

	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.leafground.com/window.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		windowOpens();
		closeAllWindowsExceptPrimary();
		findNoOfOpenTabs();
		delayOpenTab();
		driver.quit();
	}

	public static void windowOpens() throws Exception {

		// click on open button
		driver.findElement(By.xpath("//span[@class='ui-button-text ui-c'][text()='Open']")).click();
		// Get all window handles/open windows ID into Set
		Set<String> allWindows = driver.getWindowHandles();
		// Convert Set into List
		List<String> windowsList = new ArrayList<String>(allWindows);
		// Get the title before switching window
		System.out.println("Get the title before switching window: " + driver.getTitle());
		// Size for window handles
		System.out.println("Get the size of windows: " + windowsList.size());
		// Switch to new window & get Title after switching
		driver.switchTo().window(windowsList.get(1));
		System.out.println("Get the title after switching window: " + driver.getTitle());

		String title = "Dashboard";
		if (title.equalsIgnoreCase(driver.getTitle())) {
			System.out.println("This is to confirm that you have switched to new window");
		} else {
			System.out.println("You have not switched to new window");
		}
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(windowsList.get(0));

	}

	public static void closeAllWindowsExceptPrimary() throws Exception {

		driver.findElement(By.xpath("//h5[text()='Close all windows except Primary']/following::button")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<String>(windowHandles);
		int size = windowsList.size();
		System.out.println("Size of Windows: " + size);
		driver.switchTo().window(windowsList.get(1));
		Thread.sleep(5000);
		for (int i = 1; i < size; i++) {
			driver.switchTo().window(windowsList.get(i));
			driver.close();
		}

		driver.switchTo().window(windowsList.get(0));
	}

	public static void findNoOfOpenTabs() {

		driver.findElement(By.xpath("//span[text()='Open Multiple']")).click();

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<String>(windowHandles);
		int size = windowsList.size();
		System.out.println("Number of open Tabs: " + size);

	}

	public static void delayOpenTab() {

		driver.findElement(By.xpath("//span[text()='Open with delay']")).click();

	}

}
