package TestCases;

import io.qameta.allure.*;
import Utilities.BaseClass;
import PageObjects.LoginPage;
import PageObjects.PrepaidOrder;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utilities.ExcelUtils;
import Utilities.PropertyReader;
import PageObjects.CreateCustomer;


public class CrmUiTest extends BaseClass {

    @BeforeMethod
    public void setUp() {
        // Initialize the WebDriver for each test
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1, enabled = true)
    @Description("Test Description: CRM Logging in with valid credentials.")
    public void testCrmLoginFunctionality() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        ExcelUtils excelUtils = new ExcelUtils();
        PropertyReader propertyReader = new PropertyReader();
        String testDataPath = propertyReader.getProperty("testDataPath");
        excelUtils.setExcelFile(testDataPath, "test");
        String username = propertyReader.getProperty("username");
        String password = propertyReader.getProperty("password");
        
        // Navigate and wait for page load
        driver.get(propertyReader.getProperty("crmUrl"));
        waitForPageLoad(driver);
        
        // Perform login
        loginPage.loginAs(driver,username, password);
        waitForPageLoad(driver);  // Wait after login
        
        String expectedCrmUrl = propertyReader.getProperty("expectedCrmUrl");
        Thread.sleep(5000);
        String actualCrmUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualCrmUrl, expectedCrmUrl, "The user is not redirected to the dashboard after login.");
        
        // Do not call any WebDriver methods after this point
        // tearDown(); // This should not be called manually
    }

    @Test(priority = 2, enabled = true)
    @Description("Test Description: BRM Logging in with valid credentials.")
    
    public void testBrmLoginFunctionality() throws Exception {
        Allure.step("Step Description: Enter username and password.");
        //initializeDriver();
        LoginPage loginPage = new LoginPage(driver);
        
        ExcelUtils excelUtils = new ExcelUtils();
        PropertyReader propertyReader = new PropertyReader();
        String testDataPath = propertyReader.getProperty("testDataPath");
        excelUtils.setExcelFile(testDataPath, "test");
        String username = propertyReader.getProperty("username");
        String password = propertyReader.getProperty("password");
        // Perform login
        driver.get(propertyReader.getProperty("brmUrl"));
        loginPage.loginAs(driver,username, password);
        waitForPageLoad(driver);  // Wait after login
        String expectedBrmTitle = propertyReader.getProperty("expectedBrmTitle");
        Thread.sleep(5000);
        String actualBrmTitle = driver.getTitle();
        Assert.assertEquals(actualBrmTitle, expectedBrmTitle, "The user is redirected to the dashboard after login.");
        tearDown(); // Properly close session
        Thread.sleep(2000); // Wait for 5 seconds to ensure previous session is properly closed
    }

    @Test(priority = 3, enabled = false)
    public void testCreateCustomer() throws Exception {
        // Test method for creating a customer
        CreateCustomer createCustomer = new CreateCustomer(driver);
        createCustomer.createcustomer();
       
        
    }
    @Test(priority = 4, enabled = false)
    public void testPrepaidOrder() throws Exception{
        PrepaidOrder prepaidOrder = new PrepaidOrder(driver);
        prepaidOrder.prepaidOrder();
    }
    @Test(priority = 5 ,enabled = false)
    public void testVerificationCABrm() throws Exception{
        //CreateCustomer searchCustomer = new CreateCustomer(driver);
       // searchCustomer.verificationcanobrm();
    }
    
    
}
