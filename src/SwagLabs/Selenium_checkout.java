/*
 * Author:Jessy David
 * Java Version: "19.0.1" 2022-10-18
 */
package SwagLabs;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;
import com.github.javafaker.Faker;

public class Selenium_checkout {

	WebDriver driver;

	@BeforeTest
	public void SetUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		String baseurl = "https://www.saucedemo.com";
		driver.manage().window().maximize();
		driver.get(baseurl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@Test(priority = -3)
	public void login() {
		// Login Page
		WebElement User = driver.findElement(By.id("user-name"));
		User.click();
		User.clear();
		User.sendKeys("standard_user");
		WebElement pass = driver.findElement(By.id("password"));
		pass.click();
		pass.clear();
		pass.sendKeys("secret_sauce");
		WebElement login = driver.findElement(By.id("login-button"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login.click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = -2)
	public void addProductToCart() {
		// Item Add to Cart
		WebElement AddToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
		AddToCart.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = -1)
	public void checkoutAndContinue() {
		// Add to Cart ICON
		WebElement AddToCart2 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div[1]/div[3]/a"));
		AddToCart2.click();
		// Checkout
		WebElement checkoutbutton = driver.findElement(By.id("checkout"));
		checkoutbutton.click();
		// Faker
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String zipCode = faker.address().zipCode();
		// first-name
		WebElement FirstName = driver.findElement(By.id("first-name"));
		FirstName.click();
		FirstName.clear();
		FirstName.sendKeys(firstName);
		// last-name
		WebElement LastName = driver.findElement(By.id("last-name"));
		LastName.click();
		LastName.clear();
		LastName.sendKeys(lastName);
		// postal-code
		WebElement postalCode = driver.findElement(By.id("postal-code"));
		postalCode.click();
		postalCode.clear();
		postalCode.sendKeys(zipCode);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void validateChekout() {
		WebElement Continue = driver.findElement(By.id("continue"));
		Continue.click();
		WebElement Finish = driver.findElement(By.id("finish"));
		Finish.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement TextComplete = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div[2]/span"));
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Assert.assertEquals(TextComplete.getText(), "CHECKOUT: COMPLETE!");
		System.out.println("--------------------------------------");
		System.out.println("CHECKOUT SUCCESSFULLY ASSERTED");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
		System.out.println("--------------------------------------");
	}

}
