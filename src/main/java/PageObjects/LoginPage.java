package PageObjects;

import Utilities.ActionMap;
import Utilities.BaseClass;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage extends BaseClass {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    private WebDriver driver;
     // Create an instance of JavascriptExecutor
    JavascriptExecutor js = (JavascriptExecutor) driver;
    ActionMap actionMap = new ActionMap();
    
    // Locators for the login page elements
    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.id("kc-login");

    // Constructor to initialize the WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Method to enter username
    public void enterUsername(String username) {

        WebElement usernameField = driver.findElement(usernameLocator);
        WebDriverWait wait = new WebDriverWait(driver, 30); // Wait for up to 10 seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator)); // Replace with
        
        if (usernameField.isDisplayed()) {
            usernameField.sendKeys(username);
        }
    }

    // Method to enter password
    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(passwordLocator);
        if (passwordField.isDisplayed()) {
            passwordField.sendKeys(password);
        }
    }

    // Method to click on the login button
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        if (loginButton.isDisplayed() && loginButton.isEnabled()) {
            loginButton.click();
        }
    }

    // Method to perform login
    public void loginAs(String username, String password) {
       // openBrowser();
        waitForPageLoad();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        enterUsername(username);
        scrollDown(0,1000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        enterPassword(password);
        clickLoginButton();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public void verifyLoginForUrls(List<String> urls, String environment) {
        logger.info("Starting {} environment login verification for {} URLs", environment, urls.size());

        for (String url : urls) {
            try {
                logger.info("Attempting to login to: {}", url);
                driver.get(url);
                waitForPageLoad();

                // Additional wait for specific slow-loading elements
                try {
                    WebDriverWait wait = new WebDriverWait(driver, 30);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));
                } catch (Exception e) {
                    logger.warn("Timeout waiting for page elements: {}", url);
                }

                verifyLoginSuccess(url);
                Thread.sleep(2000); // Additional wait before screenshot
                captureScreenshot(driver, "success_" + environment + "_" + getAppNameFromUrl(url));
                logger.info("Successfully verified: {}", url);

            } catch (AssertionError e) {
                logger.error("Title verification failed for {}: {}", url, e.getMessage());
                captureScreenshot(driver, "failed_assertion_" + environment + "_" + getAppNameFromUrl(url));

            } catch (Exception e) {
                logger.error("Failed to verify {}: {}", url, e.getMessage());
                captureScreenshot(driver, "failed_exception_" + environment + "_" + getAppNameFromUrl(url));
            }
        }
    }

    public void verifyLoginSuccess(String url) {
        try {
            String currentTitle = driver.getTitle();
            String expectedTitle = getExpectedTitle(url);
            logger.debug("Verifying title for {}: Expected [{}], Found [{}]", url, expectedTitle, currentTitle);

            Assert.assertEquals(currentTitle, expectedTitle,
                    String.format("Title mismatch for %s - Expected: [%s], Found: [%s]",
                            url, expectedTitle, currentTitle));

        } catch (AssertionError e) {
            logger.error("Title verification failed for {}", url);
            throw e;
        }
    }
    public String getExpectedTitle(String url) {
        if (url.contains("crm")) return "Dashboard";
        if (url.contains("dbrm")) return "STL dBRM";
        if (url.contains("upc")) return "STL UPC";
        if (url.contains("wom")) return "Crestel WOM";
        if (url.contains("dpcc")) return "NetVertex";
        if (url.contains("dsp")) return "dSP";
        if (url.contains("cpm")) return "Partner BSS - Elitecore Technologies Pvt. Ltd.";
        if (url.contains("dnotification")) return "STL dNM";
        if (url.contains("lnr")) return "Crestel LNR";
        if (url.contains("campaign")) return "STL-CAMPS";
        if (url.contains("drulesengine")) return "STL dRulesEngine ";
        if (url.contains("dauth")) return "STL dAuthorization ";

        return "Unknown";
    }

    public String getAppNameFromUrl(String url) {
        return url.replaceAll("https?://", "")
                .replaceAll("\\..*", "")
                .replaceAll("[^a-zA-Z0-9]", "_");
    }


}
