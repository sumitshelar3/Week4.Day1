package week4.day1.assignments;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAssignment {

	public static ChromeDriver driver;

	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("disable-notificatios");
		driver = new ChromeDriver(ops);
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// Search as oneplus 9 pro
		WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchBox.sendKeys("oneplus 9 pro");
		driver.findElement(By.xpath("//input[@value='Go']")).click(); // OR searchBox.sendKeys(Keys.ENTER);

		// Get the price of the first product
		String prize = driver.findElement(By.xpath("//span[@class='a-price']//span[@class=\"a-price-whole\"]"))
				.getText();
		System.out.println("Prize of first product is:  " + prize);
		// Remove special characters from String i.e.prize
		String firstProductPrize = prize.replaceAll("\\D", "");
		System.out.println(firstProductPrize);

		// Print the number of customer ratings for the first displayed product
		String rating = driver.findElement(By.xpath("//div[@class=\"a-row a-size-small\"]//a/span")).getText();
		System.out.println("Number of customer ratings for the first displayed product: " + rating);

		// Click the first text link of the first image
		driver.findElement(By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']//span")).click();

		// Switching to new opened window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listOfW = new ArrayList<String>(windowHandles);
		driver.switchTo().window(listOfW.get(1));

		// Take a screen shot of the product displayed
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./Screenshot/product.png");
		FileUtils.copyFile(source, destination);

		// Click 'Add to Cart' button
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(5000);

		// Get the cart Subtotal & verify if it is correct.
		WebElement amount = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']"));
		String text = amount.getText();
		String cartSubtotal = text.replaceAll("\\D","");
		// int parseInt = Integer.parseInt(text1);
		System.out.println(cartSubtotal);

		if (firstProductPrize.equals(cartSubtotal)) {
			System.out.println("Verified product prize & Cart SubTotal successfully");
		} else {
			System.out.println("product prize & Cart SubTotal is not matching");
		}

		// Close all the windows/tabs open
		driver.quit();
	

	}

}
