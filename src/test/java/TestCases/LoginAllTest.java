package TestCases;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.List;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import PageObjects.LoginPage;
import PageObjects.CustomerView;
import Utilities.PropertyReader;
import Utilities.BaseClass;
//import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class LoginAllTest extends BaseClass {
    private WebDriver driver;
    private PropertyReader propertyReader;
    private LoginPage loginPage;
    private CustomerView customerView;
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
        customerView = new CustomerView(driver);
    }

    @Test(priority = 1, enabled = false)
    @Description("Verify all SIT Applications login Successfully")
    public void testSitLoginForMultipleUrls() throws InterruptedException{
        String username = propertyReader.getProperty("username");
        String password = propertyReader.getProperty("password");
        driver.get(propertyReader.getProperty("crmUrl"));
        loginAs(username, password);
        loginPage.verifyLoginForUrls(SIT_URLS, "SIT");
    }

    @Test(priority = 2, enabled = false)
    @Description("Verify all UAT Applications login Successfully")
    
    public void testUatLoginForMultipleUrls() throws InterruptedException {
        String username = propertyReader.getProperty("UAT_username");
        String password = propertyReader.getProperty("UAT_password");
        driver.get(propertyReader.getProperty("crmUrl"));
        loginAs(username, password);
        loginPage.verifyLoginForUrls(UAT_URLS, "UAT");
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
    @Test(priority = 2, enabled = false)
    @Description("Verify Customer 360 View")
    public void testSitCustomer360View() throws InterruptedException {
        String username = propertyReader.getProperty("username");
        String password = propertyReader.getProperty("password");
        driver.get(propertyReader.getProperty("crmUrl"));
        loginAs(username, password);
        customerView.customer360view();
        String siaTitle = driver.getTitle();
        Assert.assertEquals(siaTitle, "Service Instance Account", "SIA Title does not matched");
    
    }
    @Test(priority = 3, enabled = true)
    @Description("Verify Customer Account")
    public void testCustomerAccount() throws InterruptedException{
        String username = propertyReader.getProperty("username");
        String password = propertyReader.getProperty("password");
        driver.get(propertyReader.getProperty("crmUrl"));
        loginAs(username, password);
        customerView.customerAccount();
        captureScreenshot(driver, "customerAccount_end_of_test");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}