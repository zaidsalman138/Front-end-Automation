package PageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Inventory {
	
	WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    
    private static final Logger logger = LogManager.getLogger(Inventory.class);

    // Constructor
    public Inventory(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 80);
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // Page Factory annotations
    @FindBy(xpath = "//div[@id='appnavigator']//span[@class='app-icon fa fa-bars']")
    private WebElement appNavigator;

    @FindBy(xpath = "//div[@id='INVENTORY_modules_dropdownMenu']//div[@class='menu-items-wrapper app-menu-items-wrapper']")
    private WebElement inventoryMenu;

    @FindBy(xpath = "//span[@class='module-name textOverflowEllipsis'][normalize-space()='Inventory']")
    private WebElement inventoryOption;

    @FindBy(xpath = "//button[@id='Mobileno_listView_basicAction_LBL_ADD_RECORD']")
    private WebElement addRecordButton;

    @FindBy(xpath = "//button[@title='Settings']")
    private WebElement settingsButton;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//tbody[@class='overflow-y']/tr")
    private List<WebElement> tableRows;

    // Methods to interact with elements
    public void clickAppNavigator() {
        wait.until(ExpectedConditions.elementToBeClickable(appNavigator)).click();
    }

    public void hoverOnInventoryMenu() {
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOf(inventoryMenu))).perform();
    }

    public void clickInventoryOption() {
        wait.until(ExpectedConditions.elementToBeClickable(inventoryOption)).click();
    }

    public void verifyAddRecordButton() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(addRecordButton)).isDisplayed(), "Add Record button is not displayed!");
    }

    public void verifySettingsButton() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(settingsButton)).isDisplayed(), "Settings button is not displayed!");
    }

    public void verifySearchButton() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(searchButton)).isDisplayed(), "Search button is not displayed!");
    }
    
    public void verifyTableDataIsPopulated() {
        // Wait until the table rows are visible
    	wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//tbody[@class='overflow-y']/tr"), 1));
        
        // Assert that the number of rows is greater than 1
        Assert.assertTrue(tableRows.size() > 1, "Table data is not populated as expected. Number of rows: " + tableRows.size());  
        logger.info("Table data is populated successfully with " + tableRows.size() + " rows.");
        
    }


}
