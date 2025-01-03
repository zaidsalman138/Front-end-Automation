package PageObjects;

import Utilities.BaseClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchBill extends BaseClass{
	
	private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(SearchBill.class);

    // Locators using @FindBy
    @FindBy(xpath = "//a[@id='billing-a']")
    private WebElement billingMenu;

    @FindBy(xpath = "//a[@id='searchbill-a']")
    private WebElement searchBillOption;

    @FindBy(xpath = "//iframe[@id='zk_comp_87']")
    private WebElement billingFrame;

    @FindBy(xpath = "//button[@id='btnsearch']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//input[@id='cmbbillingcycle-real']")
    private WebElement billingCycleField;

    @FindBy(xpath = "//input[@id='txtbillnumber']")
    private WebElement billNumberField;

    @FindBy(xpath = "//input[@id='txtaccountnumber']")
    private WebElement accountNumberField;
    
    @FindBy(xpath = "//label[@id='rdbtnaccounttypevalueba-cnt']")
    private WebElement accountTypeValueBa;

    @FindBy(xpath = "//label[@id='rdbtnaccounttypevaluebaa-cnt']")
    private WebElement accountTypeValueBaa;

    @FindBy(xpath = "//input[@id='txtaccountname']")
    private WebElement accountNameField;

    @FindBy(xpath = "//input[@id='cmbdocumenttype-real']")
    private WebElement documentTypeField;

    @FindBy(xpath = "//input[@id='dtbappliedfromdate-real']")
    private WebElement appliedFromDateField;

    @FindBy(xpath = "//input[@id='dtbappliedtodate-real']")
    private WebElement appliedToDateField;

    // Constructor
    public SearchBill(WebDriver driver) {
        super.driver = driver;
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, 60);
        PageFactory.initElements(driver, this);
    }
    
  //Method for Page Stability
    public void waitForPageToBeStable() {
        logger.info("Waiting for the page to stabilize.");
        try {
            new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString().equals("complete")
            );
            
            // Ensure frame exists post stability
            WebElement frameElement = driver.findElement(By.xpath("//iframe[@id='zk_comp_87']"));
            Assert.assertTrue(frameElement.isDisplayed(), "Billing frame is not visible after stabilization!");

            logger.info("Page has stabilized, and frame is visible.");
        } catch (Exception e) {
            logger.error("Page did not stabilize or frame is unavailable: {}", e.getMessage());
            throw e;
        }
    }
    
    public void switchToBillingFrameWithRetries(int retries) {
        logger.info("Attempting to switch to the billing frame with retries.");
        for (int attempt = 1; attempt <= retries; attempt++) {
            try {
                logger.info("Attempt {} to switch to the billing frame.", attempt);
                waitForPageToBeStable(); // Ensure page stability

                // Check if the driver is in the default content before switching
                driver.switchTo().defaultContent();

                // Re-query the frame
                WebElement frameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='zk_comp_87']")));
                
                // Ensure the frame is displayed
                if (!frameElement.isDisplayed()) {
                    logger.warn("Frame not visible on attempt {}", attempt);
                    continue;
                }

                // Switch to frame
                driver.switchTo().frame(frameElement);
                logger.info("Switched to the billing frame on attempt {}.", attempt);
                return; // Exit if successful
            } catch (Exception e) {
                logger.warn("Failed to switch to the billing frame on attempt {}: {}", attempt, e.getMessage());
                if (attempt == retries) {
                    logger.error("Exceeded maximum retry attempts to switch to the billing frame.");
                    throw e; // Throw exception after maximum retries
                }
            }
        }
    }
    
    
    private void waitForElementVisibility(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)); // Wait until the element is visible
            logger.info("Element is visible: " + element);
        } catch (Exception e) {
            logger.error("Element is not visible within the expected time: {}", e.getMessage());
            throw e;
        }
    }
    
    
    
    
    //Page Action Methods

    // Method to hover over the Billing menu
    public void hoverOverBillingMenu() {
        logger.info("Hovering over the Billing menu.");
        try {
            // Wait for the billing menu to be visible and interactable
            wait.until(ExpectedConditions.visibilityOf(billingMenu));
            wait.until(ExpectedConditions.elementToBeClickable(billingMenu));
            
            // Perform the hover action
            actions.moveToElement(billingMenu).perform();
            logger.info("Hovered over the Billing menu.");
        } catch (Exception e) {
            logger.error("Failed to hover over the Billing menu: {}", e.getMessage());
            throw e; // Throw exception to fail the test if hover fails
        }
    }

    // Method to click on Search Bill
    public void clickSearchBillOption() {
        logger.info("Clicking on 'Search Bill' option.");
        clickOnElement("xpath", "//a[@id='searchbill-a']");
        logger.info("Clicked on 'Search Bill' option.");
    }

    // Method to switch to the billing frame
    public void switchToBillingFrame() {
        logger.info("Switching to the billing frame.");
        try {
            waitForPageToBeStable(); // Ensure page stability
            WebElement frameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='zk_comp_87']"))); // Re-query the frame
            driver.switchTo().frame(frameElement); // Switch to the frame
            logger.info("Switched to the billing frame.");
        } catch (Exception e) {
            logger.error("Failed to switch to the billing frame: {}", e.getMessage());
            throw e;
        }
    }

    // Method to assert the presence of the 'Search Bills' text
    public void assertSearchBillsTextPresence() {
        logger.info("Asserting the presence of the 'Search Bills' text.");
        try {
            // Reset to main content before switching
            driver.switchTo().defaultContent();
            switchToBillingFrameWithRetries(3); // Attempt to switch to the frame

            // Explicitly re-query the search button inside the frame
            WebElement searchButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnsearch']")));

            Assert.assertTrue(searchButtonElement.isDisplayed(), "The 'Search Bills' text is not displayed!");
            logger.info("The 'Search Bills' text is present.");
        } catch (Exception e) {
            logger.error("Failed to assert the presence of the 'Search Bills' text: {}", e.getMessage());
            throw e;
        }
    }
    
    
 // Method to validate the billing cycle field
    public void validateBillingCycleField() {
        logger.info("Validating the presence of the billing cycle field.");
        try {
            waitForElementVisibility(billingCycleField); // Wait for the field
            Assert.assertTrue(billingCycleField.isDisplayed(), "The billing cycle field is not displayed!");
            logger.info("The billing cycle field is present.");
        } catch (Exception e) {
            logger.error("Failed to validate the billing cycle field: {}", e.getMessage());
            throw e;
        }
    }
    
 // Method to validate the bill number field
    public void validateBillNumberField() {
        logger.info("Validating the presence of the bill number field.");
        try {
            waitForElementVisibility(billNumberField); // Wait for the field
            Assert.assertTrue(billNumberField.isDisplayed(), "The bill number field is not displayed!");
            logger.info("The bill number field is present.");
        } catch (Exception e) {
            logger.error("Failed to validate the bill number field: {}", e.getMessage());
            throw e;
        }
    }
    
 // Method to validate the account number field
    public void validateAccountNumberField() {
        logger.info("Validating the presence of the account number field.");
        try {
            waitForElementVisibility(accountNumberField); // Wait for the field
            Assert.assertTrue(accountNumberField.isDisplayed(), "The account number field is not displayed!");
            logger.info("The account number field is present in the DOM");
        } catch (Exception e) {
            logger.error("Failed to validate the account number field: {}", e.getMessage());
            throw e;
        }
    }
    
 // Method to validate multiple elements
    public void validateElementsPresence() {
        logger.info("Validating the presence of multiple fields.");
        try {
            // List of elements to validate
            List<WebElement> elementsToValidate = List.of(
                accountTypeValueBa,
                accountTypeValueBaa,
                accountNameField,
                documentTypeField,
                appliedFromDateField,
                appliedToDateField
            );

            for (WebElement element : elementsToValidate) {
            	waitForElementVisibility(element); 
                Assert.assertTrue(element.isDisplayed(), "Element is not displayed: " + element);
                logger.info("All elements are present " + element);
            }
        } catch (Exception e) {
            logger.error("Failed to validate the presence of one or more fields: {}", e.getMessage());
            throw e;
        }
    }
    
    
}
