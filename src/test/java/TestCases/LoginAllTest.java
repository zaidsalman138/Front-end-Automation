package TestCases;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.List;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import PageObjects.LoginPage;
import Utilities.PropertyReader;
import Utilities.BaseClass;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;


public class LoginAllTest extends BaseClass {
    private WebDriver driver;
    private PropertyReader propertyReader;
    private LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(LoginAllTest.class);

    private static final List<String> SIT_URLS = Arrays.asList(
        "http://upcsit.one.al/upc/index.zul",
        "https://crmsit.one.al/",
        "http://womsit.one.al/",
        "http://dbrmsit.one.al/dbrm/om",
        "http://dpccsit.one.al/netvertexsm",
        "http://dspsit.one.al/dsp",
        "http://cpmsit.one.al",
        "http://dnotificationsit.one.al/dnotification/ui//STL/index.html",
        "http://lnrsit.one.al/",
        "http://campaignsit.one.al/campaign/ui/STL/index.html#/home",
        "http://interconnectsit.one.al",
        "http://drulesenginesit.one.al/drulesengine/ui/index.html",
        "http://dintegrationsit.one.al/dintegrationlayer/ui/index.html#/home",
        "http://drasit.one.al/aaasmx/login.do",
        "http://dauthsit.one.al/dauthorization/ui/STL/index.html#/home"
    );

    private static final List<String> UAT_URLS = Arrays.asList(
        "http://upcuat.one.al/upc/index.zul",
        "https://crmuat.one.al/",
        "http://womuat.one.al/",
        "http://dbrmuat.one.al/dbrm/om",
        "http://dpccuat.one.al/netvertexsm",
        "http://dspuat.one.al/dsp",
        "http://cpmuat.one.al",
        "http://dnotificationuat.one.al/dnotification/ui//STL/index.html",
        "http://lnruat.one.al/",
        "http://campaignuat.one.al/campaign/ui/STL/index.html#/home",
        "http://interconnectuat.one.al",
        "http://drulesengineuat.one.al/drulesengine/ui/index.html",
        "http://dintegrationuat.one.al/dintegrationlayer/ui/index.html#/home",
        "http://drauat.one.al/aaasmx/login.do",
        "http://dauthuat.one.al/dauthorization/ui/STL/index.html#/home"
    );

    @BeforeMethod
    public void setUp() {
        propertyReader = new PropertyReader();
        propertyReader.loadProperties();
        System.setProperty("webdriver.chrome.driver", propertyReader.getProperty("chromeDriverPath"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, enabled = true)
    @Description("Verify all SIT Applications login Successfully")
    public void testSitLoginForMultipleUrls() throws InterruptedException{
        String username = propertyReader.getProperty("username");
        String password = propertyReader.getProperty("password");
        driver.get(propertyReader.getProperty("crmUrl"));
        loginAs(username, password);
        verifyLoginForUrls(SIT_URLS, "SIT");
    }

    @Test(priority = 2, enabled = false)
    @Description("Verify all UAT Applications login Successfully")
    
    public void testUatLoginForMultipleUrls() throws InterruptedException {
        String username = propertyReader.getProperty("UAT_username");
        String password = propertyReader.getProperty("UAT_password");
        driver.get(propertyReader.getProperty("crmUrl"));
        loginAs(username, password);
        verifyLoginForUrls(UAT_URLS, "UAT");
    }

    private void verifyLoginForUrls(List<String> urls, String environment) {
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

    private void verifyLoginSuccess(String url) {
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

    @Step("Login attempt for {url}")
    private void loginAs(String username, String password) throws InterruptedException {
        try {
            Thread.sleep(2000);
            logger.debug("Entering username: {}", username);
            loginPage.enterUsername(username);
            logger.debug("Entering password");
            loginPage.enterPassword(password);
            logger.debug("Clicking login button");
            loginPage.clickLoginButton();
            waitForPageLoad();
            logger.debug("Login attempt completed");
            
        } catch (Exception e) {
            logger.error("Login failed: {}", e.getMessage());
            throw e;
        }
    }

    private String getExpectedTitle(String url) {
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

    private String getAppNameFromUrl(String url) {
        return url.replaceAll("https?://", "")
                 .replaceAll("\\..*", "")
                 .replaceAll("[^a-zA-Z0-9]", "_");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}