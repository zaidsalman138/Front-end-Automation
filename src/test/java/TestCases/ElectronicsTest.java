package TestCases;

import Utilities.BaseClass;
import PageObjects.Electronics;
import Utilities.ExcelUtils;
import Utilities.PropertyReader;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ElectronicsTest extends BaseClass {
    @Test
    public void TestElectronicsSection(){
 // Initialize the LoginPage object
        Electronics electronics = new Electronics(driver);

        // Read test data from Excel
        ExcelUtils excelUtils = new ExcelUtils();
        PropertyReader propertyReader = new PropertyReader();
        String testDataPath = propertyReader.getProperty("testDataPath");
        excelUtils.setExcelFile(testDataPath, "test");
       // String username = excelUtils.getCellData(1, 1);
        //String password = excelUtils.getCellData(1, 2);
        // Perform login
        
        electronics.electronicsTest();

        // Assert the login success by checking if the URL is as expected
      //  String expectedUrl = PropertyReader.getProperty("baseUrl");
       // String actualUrl = driver.getCurrentUrl();
       String ele_value  = driver.findElement(By.xpath("//*[text()='Keyboard and Combos']")).getText();
       Assert.assertEquals(ele_value, "Keyboard and Combos", "verifying Electronic device name");
    }

    }

