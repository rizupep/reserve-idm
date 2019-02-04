package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pageObjects.PiLoginPage;

public class BaseClass {
	protected WebDriver driver;
	public static ExtentTest test;
	public static ExtentReports extent;
	protected WebDriverWait wait;
	private Actions action;
	
	public static String systemPath = System.getProperty("user.dir");
	private static String OS = System.getProperty("os.name").toLowerCase();

	private String value;

	public BaseClass(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		this.test = test;
		action = new Actions(driver);
	}

	public BaseClass() {

	}

	@BeforeTest
	public void establishConnection() throws IOException {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Host").addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "Mohamed Rizwan");
		extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		setupEnvironment();
		navigateUrl();

	}

	private void setupEnvironment() throws IOException {

		System.out.println(propertyFileReader("browser") + System.getProperty("user.dir"));
		if (propertyFileReader("browser").equalsIgnoreCase("CHROME")) {
			if (isMac()) {
				System.setProperty("webdriver.chromedriver", System.getProperty("user.dir") + "/chromedriver");
				driver = new ChromeDriver();
			}
			if (isWindows()) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
				driver = new ChromeDriver();
			}
		}
	}

	@AfterTest
	public void tearDown() {
		// extent.flush();
		// extent.close();
		// driver.quit();
	}

	public String propertyFileReader(String key) throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"/Users/mohamedrizwan/eclipse-workspace/idm-reserve/data.properties");
		props.load(file);
		value = props.getProperty(key);
		return value;
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	protected void navigateUrl() {
		try {
			driver.get(propertyFileReader("url"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected boolean waitForDocumentReady() {
		boolean result = false;
		result = wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete")
						&& Boolean
								.valueOf((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.isReady"))
								.equals(true);

			}
		});
		return result;
	}
}
