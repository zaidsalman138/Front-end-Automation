package PageObjects;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import Utilities.BaseClass;

public class SearchFulfillmentSubOrder extends BaseClass {
	
	private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(SearchFulfillmentOrder.class);

    // Locators using @FindBy
    @FindBy(xpath = "//div[@id='z_h4-cave']")
    private WebElement fulfillmentOrderMenu;
    
    @FindBy(xpath = "//div[@id='z_t0-cave']")
    private WebElement searchSubOrderOption;
    
    @FindBy(xpath = "//button[text()='Search']")
    private WebElement searchButton;
    
    @FindBy(xpath="//button[normalize-space()='Reset']")
    private WebElement resetButton;
    
    @FindBy(xpath = "//input[@id='z_i7']")
    private WebElement externalReferenceID;

    @FindBy(xpath = "//input[@id='z_v7-real']")
    private WebElement orderType;

    @FindBy(xpath = "//input[@id='z__8-real']")
    private WebElement workOrderSpecification;

    @FindBy(xpath = "//input[@id='z_38-real']")
    private WebElement workSpecification;

    @FindBy(xpath = "//input[@id='z_88-real']")
    private WebElement status;

    @FindBy(xpath = "//input[@id='z_c8-real']")
    private WebElement slaStatus;

    @FindBy(xpath = "//td[@id='z_89']")
    private WebElement createDate;
    
    @FindBy(xpath = "(//i[@class='z-combobox-btn'])[2]")
    private WebElement workSpecificationDropdown;

    @FindBy(xpath = "((//tr[@class='z-comboitem'])[96])//td[@class='z-comboitem-text']")
    private WebElement addonOption;
    
    @FindBy(xpath = "//div[@class='z-vlayout-inner'][10]")
    private WebElement searchTable;
    
 // Constructor
    public SearchFulfillmentSubOrder(WebDriver driver) {
        super.driver = driver;
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 50);
        PageFactory.initElements(driver, this);
    }
    
    
 // Method to click on Fulfillment Order menu
    public void clickFulfillmentOrderMenu() {
        logger.info("Clicking on the Fulfillment Order menu.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(fulfillmentOrderMenu));
            fulfillmentOrderMenu.click();
            logger.info("Clicked on the Fulfillment Order menu.");
        } catch (Exception e) {
            logger.error("Failed to click on the Fulfillment Order menu: {}", e.getMessage());
            throw e;
        }
    }
    
 // Method to click on the Search Sub Order option with retry logic
    public void clickSearchSubOrderOption() {
        logger.info("Clicking on the Search Sub Order option.");
        int retries = 3; // Number of retries
        for (int attempt = 1; attempt <= retries; attempt++) {
            try {
                logger.info("Attempt {} to click on the Search Sub Order option.", attempt);
                wait.until(ExpectedConditions.elementToBeClickable(searchSubOrderOption));
                searchSubOrderOption.click();
                logger.info("Clicked on the Search Sub Order option on attempt {}.", attempt);
                return; // Exit the loop if successful
            } catch (org.openqa.selenium.StaleElementReferenceException staleException) {
                logger.warn("StaleElementReferenceException on attempt {}: {}", attempt, staleException.getMessage());
                if (attempt == retries) {
                    logger.error("Exceeded maximum retry attempts for clicking the Search Sub Order option.");
                    throw staleException;
                }
            } catch (Exception e) {
                logger.error("Failed to click on the Search Sub Order option: {}", e.getMessage());
                throw e;
            }
        }
    }
    
 // Method to validate Search Button presence with retries
    public void validateSearchButtonPresence() throws Exception {
        logger.info("Validating the presence of the Search button with retries.");
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.visibilityOf(searchButton));
                logger.info("Search button is present.");
                return; // Exit if successful
            } catch (StaleElementReferenceException e) {
                attempts++;
                logger.warn("Attempt {} - Search button is stale. Retrying... Exception: {}", attempts, e.getMessage());
                if (attempts == 3) {
                    logger.error("Failed to validate the Search button after retries.");
                    throw e; // Rethrow the exception after max retries
                }
            } catch (Exception e) {
                logger.error("Search button is not present due to an unexpected issue: {}", e.getMessage());
                throw e;
            }
        }
    }

    // Method to validate Reset Button presence with retries
    public void validateResetButtonPresence() throws Exception {
        logger.info("Validating the presence of the Reset button with retries.");
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.visibilityOf(resetButton));
                logger.info("Reset button is present.");
                return; // Exit if successful
            } catch (StaleElementReferenceException e) {
                attempts++;
                logger.warn("Attempt {} - Reset button is stale. Retrying... Exception: {}", attempts, e.getMessage());
                if (attempts == 3) {
                    logger.error("Failed to validate the Reset button after retries.");
                    throw e; // Rethrow the exception after max retries
                }
            } catch (Exception e) {
                logger.error("Reset button is not present due to an unexpected issue: {}", e.getMessage());
                throw e;
            }
        }
    }
    
 // Method to validate the presence of multiple elements
    public void validateSubOrderElementsPresence() {
        logger.info("Validating the presence of elements on the Search Fulfillment Sub Order page.");
        try {
            // List of elements to validate
            List<WebElement> elementsToValidate = List.of(
                externalReferenceID,
                orderType,
                workOrderSpecification,
                workSpecification,
                status,
                slaStatus,
                createDate
            );

            // Wait for visibility of one or two key elements
            wait.until(ExpectedConditions.visibilityOf(externalReferenceID));
            wait.until(ExpectedConditions.visibilityOf(orderType));

            for (WebElement element : elementsToValidate) {
                wait.until(ExpectedConditions.visibilityOf(element)); // Ensure visibility
                Assert.assertTrue(element.isDisplayed(), "Element is not displayed: " + element);
                logger.info("Element is present: " + element);
            }
            logger.info("All elements are present on the Search Fulfillment Sub Order page.");
        } catch (Exception e) {
            logger.error("Failed to validate elements on the Search Fulfillment Sub Order page: {}", e.getMessage());
            throw e;
        }
    }
    
 // Method to click on Work Specification dropdown
    public void clickWorkSpecificationDropdown() throws Exception {
        logger.info("Clicking on the Work Specification dropdown.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(workSpecificationDropdown));
            Thread.sleep(3000);
            workSpecificationDropdown.click();
            logger.info("Clicked on the Work Specification dropdown.");
        } catch (Exception e) {
            logger.error("Failed to click on the Work Specification dropdown: {}", e.getMessage());
            throw e;
        }
    }

    // Method to select Addon option
    public void selectAddonOption() throws Exception {
        logger.info("Selecting the Addon option from the Work Specification dropdown.");
        try {
        	
            wait.until(ExpectedConditions.elementToBeClickable(addonOption));
            addonOption.click();
            logger.info("Selected the Addon option.");
        } catch (Exception e) {
            logger.error("Failed to select the Addon option: {}", e.getMessage());
            throw e;
        }
    }

    // Method to click the Search button
    public void clickSearchButton() {
        logger.info("Clicking on the Search button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            searchButton.click();
            logger.info("Clicked on the Search button.");
        } catch (Exception e) {
            logger.error("Failed to click on the Search button: {}", e.getMessage());
            throw e;
        }
    }
    
 // Method to validate the presence of the Search Table
    public void validateSearchTablePresence() {
        logger.info("Validating the presence of the Search Table.");
        try {
            wait.until(ExpectedConditions.visibilityOf(searchTable)); // Wait for visibility of the table
            Assert.assertTrue(searchTable.isDisplayed(), "Search Table is not displayed!");
            logger.info("Search Table is present.");
        } catch (Exception e) {
            logger.error("Failed to validate the presence of the Search Table: {}", e.getMessage());
            throw e;
        }
    }
    

}
