package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Electronics {

    private WebDriver driver;
    public Electronics (WebDriver driver) {
        this.driver = driver;
    }
    public void electronicsTest() {


        // Click on Electronics
        WebElement electronics = driver.findElement(By.xpath("//span[text()='Electronics']"));
        electronics.click();

        // Move to Bluetooth
        WebElement laptopAccessoriesElement = driver.findElement(By.xpath("//*[text()='Laptop Accessories']"));
        laptopAccessoriesElement.click();

       // WebElement all = driver.findElement(By.xpath("//*[text()='All']"));
        //all.click();
    }
    
}
