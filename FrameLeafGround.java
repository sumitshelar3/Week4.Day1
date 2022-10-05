package week4.day1.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameLeafGround {

	public static ChromeDriver driver;

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.leafground.com/frame.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		clickMeInsideFrame();
		clickMeInsideNestedFrame();
		frameCount();
		driver.close();
	}

	public static void clickMeInsideFrame() {
		WebElement frameElement = driver
				.findElement(By.xpath("//h5[text()=' Click Me (Inside frame)']/following-sibling::iframe"));
		// To switch to frame using parameter WebElement
		driver.switchTo().frame(frameElement);
		WebElement clickElement = driver.findElement(By.xpath("//button[@onclick='change()']"));
		clickElement.click();
		// By using getAttribute() method ,if there is no value present just mention
		// "innerHTML"
		System.out.println("Getting Text using getAttribute() method: " + clickElement.getAttribute("innerHTML"));
		// Getting Text using getText() method
		System.out.println("Getting Text using getText() method: " + clickElement.getText());
		// Switching back to main webPage
		driver.switchTo().parentFrame();
	}

	public static void clickMeInsideNestedFrame() {

		WebElement frame = driver
				.findElement(By.xpath("//h5[text()=' Click Me (Inside Nested frame)']/following-sibling::iframe"));
		// switch to 1st frame
		driver.switchTo().frame(frame);
		// Switch to inner frame using ID or Name parameter
		driver.switchTo().frame("frame2");
		// click on button Click Me
		WebElement clickElement = driver.findElement(By.xpath("(//button[@onclick='change()'])[1]"));
		clickElement.click();
		System.out.println("Text displayed on Button: " + clickElement.getText()); // Getting Text using getText()method
		// Getting out from all frames
		driver.switchTo().defaultContent();

	}

	public static void frameCount() {

		// To find number of Frames present on WebPage
		List<WebElement> outerFrame = driver.findElements(By.tagName("iframe"));
		int size = outerFrame.size();
		int count = 0;
		for (int i = 0; i < size; i++) {
			driver.switchTo().frame(i);
			List<WebElement> innerFrame = driver.findElements(By.tagName("iframe"));
			count = size + innerFrame.size();
			driver.switchTo().defaultContent();
		}
		System.out.println("Number of Frames presents in WebPage with nested Frames: " + count);
	}

}
