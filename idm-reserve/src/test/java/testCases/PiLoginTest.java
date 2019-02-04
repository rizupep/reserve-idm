package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import core.BaseClass;
import pageObjects.PiLoginPage;

public class PiLoginTest extends BaseClass {
	
	private WebDriver driver = null;
	private ExtentTest test = null;
	public PiLoginPage piLoginPage ;
	
	@Test
	public void piLoginUserNamePassword() throws Exception
	{
		System.out.println(propertyFileReader("username")+"  "+propertyFileReader("password"));
		/*driver.findElement(By.name("username")).sendKeys("idmuser1");
		driver.findElement(By.name("password")).sendKeys("Password1");*/
		
		piLoginPage.setUsernameTextField(propertyFileReader("username")).setPasswordPasswordField(propertyFileReader("password")).clickSignInButton();
	System.out.println(piLoginPage);
	}

}
