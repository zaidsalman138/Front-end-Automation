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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class Tickets {


    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    private static final Logger logger = LoggerFactory.getLogger(Tickets.class); // Initialize logger

    // Constructor
    public Tickets(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20); // 20 seconds explicit wait
        this.actions = new Actions(driver); // Initialize Actions
        PageFactory.initElements(driver, this); // Initialize web elements
        logger.info("Tickets Page Object initialized.");
    }

    // Locators using @FindBy
    @FindBy(xpath = "//div[@id='appnavigator']")
    WebElement appNavigator;

    @FindBy(xpath = "//span[normalize-space()='SUPPORT']")
    WebElement supportMenu;

    @FindBy(xpath = "//span[@class='module-name textOverflowEllipsis'][normalize-space()='Tickets']")
    WebElement ticketsModule;

    @FindBy(xpath = "//button[@id='HelpDesk_listView_basicAction_LBL_ADD_RECORD']")
    WebElement addRecordButton;

    @FindBy(xpath = "//button[@title='Settings']")
    WebElement settingsButton;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement searchButton;
    
    @FindBy(xpath = "//tbody[@class='overflow-y']/tr")
    private List<WebElement> tableRows;

    // Method to click on the navigation menu
    public void clickNavigation() {
        logger.info("Clicking on navigation menu.");
        wait.until(ExpectedConditions.elementToBeClickable(appNavigator));
        appNavigator.click();
        logger.info("Navigation menu clicked.");
    }

    // Method to hover over the SUPPORT menu
    public void hoverOverSupportMenu() {
        logger.info("Hovering over the SUPPORT menu.");
        wait.until(ExpectedConditions.visibilityOf(supportMenu));
        actions.moveToElement(supportMenu).perform(); // Use Actions to hover
        logger.info("Hovered over SUPPORT menu.");
    }

    // Method to click on the Tickets module
    public void clickTicketsModule() {
        logger.info("Clicking on Tickets module.");
        wait.until(ExpectedConditions.elementToBeClickable(ticketsModule));
        ticketsModule.click();
        logger.info("Tickets module clicked.");
    }

    // Method to verify the page title
    public void verifyPageTitle() {
        logger.info("Verifying the page title is 'Tickets'.");
        wait.until(ExpectedConditions.titleIs("Tickets"));
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Tickets", "Page title mismatch. Expected: 'Tickets', Found: " + actualTitle);
        logger.info("Page title verified successfully: {}", actualTitle);
    }

    // Method to verify the presence of the Add Record button
    public void verifyAddRecordButton() {
        logger.info("Verifying Add Record button is present.");
        boolean isPresent = wait.until(ExpectedConditions.visibilityOf(addRecordButton)).isDisplayed();
        Assert.assertTrue(isPresent, "Add Record button is not displayed.");
        logger.info("Add Record button verified as present.");
    }

    // Method to verify the presence of the Settings button
    public void verifySettingsButton() {
        logger.info("Verifying Settings button is present.");
        boolean isPresent = wait.until(ExpectedConditions.visibilityOf(settingsButton)).isDisplayed();
        Assert.assertTrue(isPresent, "Settings button is not displayed.");
        logger.info("Settings button verified as present.");
    }

    // Method to verify the presence of the Search button
    public void verifySearchButton() {
        logger.info("Verifying Search button is present.");
        boolean isPresent = wait.until(ExpectedConditions.visibilityOf(searchButton)).isDisplayed();
        Assert.assertTrue(isPresent, "Search button is not displayed.");
        logger.info("Search button verified as present.");
    }
    
    //Verify data is populating in rows
    public void verifyTableDataIsPopulated() {
    	logger.info("Verifying Data populates in the view");
        // Wait until the table rows are visible
    	wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//tbody[@class='overflow-y']/tr"), 1));
        
        // Assert that the number of rows is greater than 1
        Assert.assertTrue(tableRows.size() > 1, "Table data is not populated as expected. Number of rows: " + tableRows.size());  
        logger.info("Data population is verified.");
        
    }

}
