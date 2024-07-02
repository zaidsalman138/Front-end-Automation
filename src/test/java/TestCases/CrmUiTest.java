package TestCases;

import io.qameta.allure.*;
import Utilities.BaseClass;
import PageObjects.LoginPage;
import PageObjects.PrepaidOrder;

import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.ExcelUtils;
import Utilities.PropertyReader;
import PageObjects.CreateCustomer;


public class CrmUiTest extends BaseClass {

    @Test(priority = 1, enabled = true)
    @Description("Test Description: Logging in with valid credentials.")
    public void testLoginFunctionality() throws Exception {
        // Initialize the LoginPage object
        LoginPage loginPage = new LoginPage(driver);

        // Read test data from Excel
        ExcelUtils excelUtils = new ExcelUtils();
        PropertyReader propertyReader = new PropertyReader();
        String testDataPath = propertyReader.getProperty("testDataPath");
        excelUtils.setExcelFile(testDataPath, "test");
        String username = propertyReader.getProperty("username");
        String password = propertyReader.getProperty("password");
        // Perform login
        
        loginPage.loginAs(username, password);
        // Assert the login success by checking if the URL is as expected
        String expectedUrl = propertyReader.getProperty("expectedLoginUrl");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "The user is not redirected to the dashboard after login.");
        Allure.step("Step Description: Enter username and password.");
    }


    @Test(priority = 2, enabled = false)
    public void testCreateCustomer() throws Exception {
        // Test method for creating a customer
        CreateCustomer createCustomer = new CreateCustomer(driver);
        createCustomer.createcustomer();
       
        
    }
    @Test(priority = 3, enabled = true)
    public void testPrepaidOrder() throws Exception{
        PrepaidOrder prepaidOrder = new PrepaidOrder(driver);
        prepaidOrder.prepaidOrder();
    }
    @Test(priority = 4 ,enabled = false)
    public void testVerificationCABrm() throws Exception{
        //CreateCustomer searchCustomer = new CreateCustomer(driver);
       // searchCustomer.verificationcanobrm();
    }

}
