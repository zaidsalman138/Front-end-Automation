package TestCases;

import Utilities.BaseClass;
import PageObjects.Pcc;
import Utilities.ExcelUtils;
import Utilities.PropertyReader;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PccTest extends BaseClass {
    @Test
    public void TestPccSection(){
 // Initialize the LoginPage object
        Pcc pcc = new Pcc(driver);

        // Read test data from Excel
        ExcelUtils excelUtils = new ExcelUtils();
        PropertyReader propertyReader = new PropertyReader();
        String testDataPath = propertyReader.getProperty("testDataPath");
        excelUtils.setExcelFile(testDataPath, "test");
       // String username = excelUtils.getCellData(1, 1);
        //String password = excelUtils.getCellData(1, 2);
        // Perform login
        
        pcc.pccTest();

        // Assert the login success by checking if the URL is as expected
      //  String expectedUrl = PropertyReader.getProperty("baseUrl");
       // String actualUrl = driver.getCurrentUrl();
       String ele_value  = driver.findElement(By.xpath("//*[text()='Keyboard and Combos']")).getText();
       Assert.assertEquals(ele_value, "Keyboard and Combos", "verifying Electronic device name");
    }

    }

