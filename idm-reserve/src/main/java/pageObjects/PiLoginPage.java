package pageObjects;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import core.BaseClass;

public class PiLoginPage extends BaseClass {
	
	/** The driver. */
	private WebDriver driver = null;

	/** The logger. */
	private ExtentTest test = null;

	@FindBy(id = "password")
	@CacheLookup
	private WebElement password;

	@FindBy(id = "username")
	@CacheLookup
	private WebElement username;

	@FindBy(id = "mainButton")
	@CacheLookup
	private WebElement signIn;

	public PiLoginPage(WebDriver driver, ExtentTest test) throws Exception {
		//this.driver = driver;
		//this.test = test;
		super(driver, test);
		PageFactory.initElements(driver, this);
		//waitForDocumentReady();
	}

	public PiLoginPage submit() {
		clickSignInButton();
		return this;
	}

	public PiLoginPage setUsernameTextField(String usernameValue) {
		//driver.findElement(By.name("username")).sendKeys(usernameValue);
		username.sendKeys(usernameValue);
		return this;
	}

	public PiLoginPage setPasswordPasswordField(String passwordValue) throws Exception {
		password.sendKeys(passwordValue);
		return this;
	}

	public PiLoginPage clickSignInButton() {
		signIn.click();
		return this;
	}

}
