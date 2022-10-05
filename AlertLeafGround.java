package week4.day1.assignments;

import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertLeafGround {

	public static ChromeDriver driver;
	
	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.leafground.com/alert.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		alertSimpleDialog();
		alertConfirmDialog();
		sweetAlertSimpleDialog();
		sweetModalDialog();
		alertPromptAlert();
		sweetAlertConfirmation();
		minimizeAndMaximize();
		driver.close();

	}

	public static void alertSimpleDialog()

	{
		driver.findElement(By.xpath("//button[@id='j_idt88:j_idt91']/span[text()='Show']")).click();
		Alert alert = driver.switchTo().alert();
		System.out.println("Text displayed on Alert: " + alert.getText());
		alert.accept();

		String expectedMsg = "You have successfully clicked an alert";
		String actualMsg = driver.findElement(By.id("simple_result")).getText();

		if (actualMsg.equalsIgnoreCase(expectedMsg)) {
			System.out.println("Successfully completed Test");
		} else {
			System.out.println("Test is not pass");
		}
	}

	public static void alertConfirmDialog() {
		
		driver.findElement(By.xpath("//button[@id='j_idt88:j_idt93']/span[text()='Show']")).click();
		Alert alert = driver.switchTo().alert();
		System.out.println("Text displayed on Alert: " + alert.getText());
		alert.dismiss();

		String expectedMsg = "User Clicked : Cancel";
		String actualMsg = driver.findElement(By.id("result")).getText();

		if (actualMsg.equalsIgnoreCase(expectedMsg)) {
			System.out.println("Successfully completed Test");
		} else {
			System.out.println("Test is not pass");
		}

	}

	public static void sweetAlertSimpleDialog() throws Exception {
		
		driver.findElement(By.xpath("//button[@id='j_idt88:j_idt95']/span[text()='Show']")).click();
		Thread.sleep(3000);
		String openDialogMsg = driver.findElement(By.xpath("//div[@id='j_idt88:j_idt96_content']/p")).getText();
		driver.findElement(By.xpath("//button[@id='j_idt88:j_idt98']/span[text()='Dismiss']")).click();
		String expMsg = "You have clicked and open a dialog that can be inspectable.";

		if (expMsg.equalsIgnoreCase(openDialogMsg)) {
			System.out.println("Open dialog box message has been verified");
		} else {
			System.out.println("Test is not pass");
		}

	}

	public static void sweetModalDialog() throws Exception {
		
		driver.findElement(By.xpath("//h5[text()='Sweet Modal Dialog']/following-sibling::button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Modal Dialog (Sweet Alert)']/following::span")).click();

	}

	public static void alertPromptAlert() {
		
		driver.findElement(By.xpath("//button[@id='j_idt88:j_idt104']/span[text()='Show']")).click();
		Alert alert = driver.switchTo().alert();
		System.out.println("Text displayed on Alert: " + alert.getText());
		alert.sendKeys("Testing");
		alert.accept();

		String actualMsg = driver.findElement(By.id("confirm_result")).getText();
		String expMsg = "User entered name as: Testing";

		if (expMsg.equalsIgnoreCase(actualMsg)) {
			System.out.println("Alert_Prompt_Alert Verified");
		} else {
			System.out.println("Test is not pass");
		}
	}

	public static void sweetAlertConfirmation() {

		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		String actualMsg = driver.findElement(By.xpath("//span[text()='Are you sure you want to proceed?']")).getText();
		driver.findElement(By.xpath("//span[text()='Yes']")).click();
		String expMsg = "Are you sure you want to proceed?";
		if (actualMsg.equalsIgnoreCase(expMsg)) {
			System.out.println("Sweet_Alert_Confirmation Verified");
		} else {
			System.out.println("Test is not pass");
		}
	}

	public static void minimizeAndMaximize() throws Exception {
		
		driver.findElement(By.xpath("//button[@id='j_idt88:j_idt111']/span[text()='Show']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[@class='ui-dialog-titlebar-icon ui-dialog-titlebar-maximize ui-corner-all']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@id='j_idt88:j_idt112_title']/following::span")).click();

	}
}
