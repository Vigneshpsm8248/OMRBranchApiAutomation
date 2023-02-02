package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Vignesh
 * @see Reusable Methods
 */

public class BaseClass {
	

	public static WebDriver driver;
	JavascriptExecutor js;
	FileInputStream stream;
	// WorkBook book = null;
	private int locator;
	private String file;
	
	RequestSpecification reqSpec;
	 public Response response;
	public void addHeader(String key,String value) {
		    reqSpec = RestAssured.given().headers(key, value);
	}
	public void addPathParam(String key,String value) {
	reqSpec = reqSpec.pathParam(key, value);

	}
	
	public void addHeaders(Headers headers) {
		reqSpec = RestAssured.given().headers(headers);

	}
	public void addBody(Object body) {
		reqSpec = reqSpec.body(body);

	}
	public void addQueryParam(String key,String value) {
		reqSpec = reqSpec.queryParam(key, value);
	}
	public void addBody(String body) {
		reqSpec = reqSpec.body(body);
	}
	public void addBasicAuth(String username,String password) {
		reqSpec = reqSpec.auth().preemptive().basic(username, password);

	}
	
	public Response requestType(String type,String endpoint) {
		switch(type) {
		case "GET":
			 response = reqSpec.log().all().get(endpoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endpoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endpoint);
			break;
		default:
			break;
		}
		return response;
	}
	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;

	}
	public String getResBodyAsString(Response response) {
		String asString = response.asString();
		return asString;
	}
	public String getResBodyAsPreetyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;

	}
	
	
	/**
	 * @see Takes Screenshot once the process done
	 * @return byte[]
	 */

	public byte[] takeScreenShot() {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		byte[] b = screenshot.getScreenshotAs(OutputType.BYTES);
		return b;

	}

	/**
	 * @see Initializes the given Url
	 * @param url
	 */

	public void launchUrl(String url) {
		driver.get(url);
	}

	/**
	 * @see Gets the Existing text in WebPage
	 * @param element
	 * @return String
	 */

	public String getText(WebElement element) {
		String text = element.getText();
		return text;
	}

	/**
	 * @see Finds the Locator According to the name of the Locator
	 * @param name
	 * @param value
	 * @return WebElement
	 */

	public WebElement findByLocator(String name, String value) {

		WebElement ele = null;
		switch (name) {
		case "id":
			WebElement findElement = driver.findElement(By.id(value));

			break;
		case "name":
			WebElement findElement2 = driver.findElement(By.name(value));
			break;

		case "classname":
			WebElement findElement3 = driver.findElement(By.className(value));
		default:
			break;
		case "xpath":
			WebElement findElement4 = driver.findElement(By.xpath(value));
			break;
		}
		return ele;
	}

	/**
	 * @see Waits Until the driver finds the Locator it is common for all Method
	 */
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	/**
	 * @see Wait Until the element is Visible it is applicable only For Particular
	 *      Method
	 * @param element
	 */
	public void elementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public Alert a;

	public void alertts() {
		a = driver.switchTo().alert();

	}

	/**
	 * @see It accepts the alert
	 */
	public void alertsAccept() {
		driver.switchTo().alert().accept();

	}

	/**
	 * @see It Dismiss the Alert
	 */
	public void alertSDeney() {
		driver.switchTo().alert().dismiss();

	}

	/**
	 * @see To get the Path of the Project has Done
	 * @return
	 */
	public static String getProjectpath() {
		String property = System.getProperty("user.dir");
		return property;

	}

	public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(getProjectpath() + "\\Config\\Config.properties"));
		return (String) properties.get(key);

	}

	public void clear(WebElement element)
	{
		element.click();
	}
	
	
	
	
	
	
	
}
	