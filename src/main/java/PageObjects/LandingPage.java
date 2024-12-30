package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LandingPage {
	
	private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Locators
    private By appNavigator = By.xpath("//div[@id='appnavigator']//div[@class='row app-navigator']");
    private By paymentModuleDropdown = By.xpath("//div[@id='PAYMENT_modules_dropdownMenu']//div[@class='menu-items-wrapper app-menu-items-wrapper']");
    private By paymentModule = By.xpath("//span[normalize-space()='Payment']");
    private By addRecordButton = By.xpath("//button[@id='Payment_listView_basicAction_LBL_ADD_RECORD']");
    private By searchButton = By.xpath("//button[normalize-space()='Search']");
    private By customizeButton = By.xpath("//button[@title='Settings']"); // Added Settings button locator

    // Constructor
    public LandingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 100);
        this.actions = new Actions(driver);
    }

    // Methods to interact with locators
    public void clickAppNavigator() {
        WebElement navigatorElement = wait.until(ExpectedConditions.elementToBeClickable(appNavigator));
        navigatorElement.click();
        
    }

    public void hoverOverPaymentModuleDropdown() {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentModuleDropdown));
        actions.moveToElement(dropdownElement).perform();
        
    }

    public void clickPaymentModule() {
        WebElement moduleElement = wait.until(ExpectedConditions.elementToBeClickable(paymentModule));
        moduleElement.click();
        
    }
    
    // **Added Methods for Assertions**
    public void verifyAddRecordButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(addRecordButton));
        Assert.assertTrue(button.isDisplayed(), "Add Record button is not visible.");
        System.out.println("Verified: Add Record button is visible.");
    }

    public void verifySearchButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        Assert.assertTrue(button.isDisplayed(), "Search button is not visible.");
        System.out.println("Verified: Search button is visible.");
    }
    
 // Method to verify page title
    public void verifyPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title is incorrect!");
        System.out.println("Verified: Page title is '" + expectedTitle + "'.");
    }
    
 // **Verify Settings Button**
    public void verifyCustomizeButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(customizeButton));
        Assert.assertTrue(button.isDisplayed(), "Settings button is not visible.");
        System.out.println("Verified: Settings button is visible.");
    }
    

}
