package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;

import Utilities.BaseClass;

public class SearchCustomerAccount extends BaseClass{
	private static final Logger logger = LoggerFactory.getLogger(SearchCustomerAccount.class);
    private Actions actions;
    private WebDriverWait wait;

    // Locators using @FindBy
    @FindBy(xpath = "//a[@id='account-a']")
    private WebElement accountMenu;

    @FindBy(xpath = "//span[normalize-space()='Search Customer Account']")
    private WebElement searchCustomerAccountOption;

    @FindBy(xpath = "//iframe[@id='zk_comp_87']")
    private WebElement iframe;

    @FindBy(xpath = "//button[@id='btnsearch']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@id='btnreset']")
    private WebElement resetButton;

    @FindBy(xpath = "//span[@id='deposittab-cnt']")
    private WebElement depositTab;

    @FindBy(xpath = "//span[@id='selectsearchtypecombo']")
    private WebElement searchTypeDropdown;

    @FindBy(xpath = "//span[normalize-space()='Account Number']")
    private WebElement accountNumberOption;

    @FindBy(xpath = "//input[@id='c_strparametervalue']")
    private WebElement parameterValueField;

    @FindBy(xpath = "//button[@id='c_btnsearch2']")
    private WebElement searchButton2;
    
    @FindBy(xpath = "(//a[normalize-space()='CA0000001902'])[1]")
    private WebElement accountLink;

    @FindBy(xpath = "//div[@id='name-cell']")
    private WebElement nameCell;

    @FindBy(xpath = "//div[@id='account-cap']")
    private WebElement accountCap;

    @FindBy(xpath = "//div[@id='history-cap']")
    private WebElement historyCap;

    // Constructor
    public SearchCustomerAccount(WebDriver driver) {
        super.driver = driver; // Initialize BaseClass driver
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 50); 
    }

    // Method to hover over the "account" menu
    public void hoverOverAccountMenu() {
        logger.info("Hovering over the account menu.");
        try {
            actions.moveToElement(accountMenu).perform();
            logger.info("Hovered over the account menu.");
        } catch (Exception e) {
            logger.error("Failed to hover over the account menu: {}", e.getMessage());
        }
    }

    // Method to click on "Search Customer Account"
    public void clickSearchCustomerAccountOption() {
        logger.info("Clicking on 'Search Customer Account' option.");
        clickOnElement(driver, "xpath", "//span[normalize-space()='Search Customer Account']");
        logger.info("Clicked on 'Search Customer Account' option.");
    }

    // Method to switch to iframe
    public void switchToIframe() {
        logger.info("Switching to the iframe.");
        switchToFrame(driver, "xpath", "//iframe[@id='zk_comp_87']");
        logger.info("Switched to the iframe.");
    }

    // Method to assert the presence of the "Search" button
    public void assertSearchButtonPresence() {
        logger.info("Asserting the presence of the Search button.");
        Assert.assertTrue(searchButton.isDisplayed(), "Search button is not displayed inside the iframe!");
        logger.info("Search button is present.");
    }

    // Method to assert the presence of the "Reset" button
    public void assertResetButtonPresence() {
        logger.info("Asserting the presence of the Reset button.");
        Assert.assertTrue(resetButton.isDisplayed(), "Reset button is not displayed inside the iframe!");
        logger.info("Reset button is present.");
    }

    // Method to validate the "Reset" field
    public void validateResetFieldPresence() {
        logger.info("Validating the presence of the Reset field.");
        Assert.assertTrue(resetButton.isDisplayed(), "Reset field is not displayed inside the iframe!");
        logger.info("Reset field is present.");
    }

    // Method to click on the Deposit tab
    public void clickDepositTab() {
        logger.info("Clicking on the Deposit tab.");
        clickOnElement("xpath", "//span[@id='deposittab-cnt']");
        logger.info("Clicked on the Deposit tab.");
    }

    // Method to assert the presence of the Search Type dropdown
    public void assertSearchTypeDropdownPresence() {
        logger.info("Asserting the presence of the Search Type dropdown.");
        Assert.assertTrue(searchTypeDropdown.isDisplayed(), "Search Type dropdown is not displayed!");
        logger.info("Search Type dropdown is present.");
    }

    // Method to select "Account Number" from the dropdown
    public void selectAccountNumberSearchType() {
        logger.info("Selecting 'Account Number' from the Search Type dropdown.");

        // Step 1: Wait for the overlay to disappear
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("zk_proc-m")));
            logger.info("Overlay has disappeared.");
        } catch (TimeoutException e) {
            logger.warn("Overlay did not disappear in time. Proceeding with caution.");
        }

        // Step 2: Click the dropdown to open options
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchTypeDropdown)).click();
            logger.info("Clicked on the Search Type dropdown.");
        } catch (ElementClickInterceptedException e) {
            logger.warn("Dropdown click intercepted. Using JavaScript to click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchTypeDropdown);
        }

        // Step 3: Wait for the 'Account Number' option
        WebElement accountNumberOptionElement = wait.until(ExpectedConditions.elementToBeClickable(accountNumberOption));

        // Step 4: Scroll into view if not visible
        if (!accountNumberOptionElement.isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accountNumberOptionElement);
            logger.info("Scrolled to 'Account Number' option.");
        }

        // Step 5: Click the 'Account Number' option
        try {
            accountNumberOptionElement.click();
            logger.info("Selected 'Account Number' from the Search Type dropdown.");
        } catch (ElementClickInterceptedException e) {
            logger.warn("'Account Number' option click intercepted. Using JavaScript to click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", accountNumberOptionElement);
        }
    }

    // Method to enter a value into the parameter field
    public void enterParameterValue(String value) {
        logger.info("Entering value '{}' into the parameter field.", value);
        sendTextOnElement(driver,"xpath", "//input[@id='c_strparametervalue']", value);
        logger.info("Entered value '{}' into the parameter field.", value);
    }

    // Method to click the second Search button
    public void clickSearchButton2() {
        logger.info("Waiting for any modal overlays to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("zk_proc-m")));
        
        logger.info("Clicking the second Search button.");
        wait.until(ExpectedConditions.elementToBeClickable(searchButton2)).click();
        logger.info("Clicked the second Search button.");
    }
    
    public void validateSearchResult(String expectedAccountNumber) {
        logger.info("Validating the search result for account number: {}", expectedAccountNumber);

        // XPath to locate the search result by account number
        String resultXPath = "//a[@id='name1' and text()='" + expectedAccountNumber + "']";

        // Wait for the element to be visible
        waitForElementToBeClickable(By.xpath(resultXPath), 10);

        // Locate the element
        WebElement resultElement = driver.findElement(By.xpath(resultXPath));

        // Assert the element is displayed
        Assert.assertTrue(resultElement.isDisplayed(), "Expected account number " + expectedAccountNumber + " is not displayed!");
        logger.info("Search result validated successfully. Account number '{}' is present.", expectedAccountNumber);
    }

    
    	// Method to wait and click the first occurrence of the account link
    	public void clickAccountLink() {
        logger.info("Clicking on the account link for the account number.");
        wait.until(ExpectedConditions.elementToBeClickable(accountLink)).click();
        logger.info("Clicked on the account link.");
    }

    // Method to assert the presence of the name-cell element
    public void assertNameCellPresence() {
        logger.info("Asserting the presence of the name-cell element.");
        wait.until(ExpectedConditions.visibilityOf(nameCell));
        Assert.assertTrue(nameCell.isDisplayed(), "The name-cell element is not displayed!");
        logger.info("The name-cell element is present.");
    }

    // Method to assert the presence of the account-cap element
    public void assertAccountCapPresence() {
        logger.info("Asserting the presence of the account-cap element.");
        wait.until(ExpectedConditions.visibilityOf(accountCap));
        Assert.assertTrue(accountCap.isDisplayed(), "The account-cap element is not displayed!");
        logger.info("The account-cap element is present.");
    }

    // Method to validate the presence of the history-cap element
    public void validateHistoryCapPresence() {
        logger.info("Validating the presence of the history-cap element.");
        wait.until(ExpectedConditions.visibilityOf(historyCap));
        Assert.assertTrue(historyCap.isDisplayed(), "The history-cap element is not displayed!");
        logger.info("The history-cap element is present.");
    }
}
