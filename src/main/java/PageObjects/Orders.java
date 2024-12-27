package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Orders {

	
	 WebDriver driver;
	    WebDriverWait wait;
	    Actions actions;

	    // Locators
	    private By appNavigator = By.xpath("//div[@id='appnavigator']//div[@class='row app-navigator']");
	    private By salesMenu = By.xpath("//div[@id='SALES_modules_dropdownMenu']//div[@class='menu-items-wrapper app-menu-items-wrapper']");
	    private By orderOption = By.xpath("//span[@class='module-name textOverflowEllipsis'][normalize-space()='Order']");
	    private By addRecordButton = By.xpath("//button[@id='Invoice_listView_basicAction_LBL_ADD_RECORD']");
	    private By searchButton = By.xpath("//button[normalize-space()='Search']");
	    private By orderRows = By.xpath("//tbody[@class='overflow-y']/tr"); // XPath for rows in the table

	    // Constructor
	    public Orders(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, 50); // Wait for up to 10 seconds
	        this.actions = new Actions(driver); // Initialize Actions
	    }

	 // Click on the app navigator
	    public void clickAppNavigator() {
	        wait.until(ExpectedConditions.elementToBeClickable(appNavigator)).click();
	    }

	    // Hover over the Sales menu
	    public void hoverOnSalesMenu() {
	        WebElement salesMenuElement = wait.until(ExpectedConditions.visibilityOfElementLocated(salesMenu));
	        actions.moveToElement(salesMenuElement).perform();
	    }

	    // Click on the Order option
	    public void clickOrderOption() {
	        wait.until(ExpectedConditions.elementToBeClickable(orderOption)).click();
	    }

	    public void verifyPageTitle() {
	        // Verify that the page title is "Order"
	        String expectedTitle = "Order";
	        wait.until(ExpectedConditions.titleIs(expectedTitle));
	        String actualTitle = driver.getTitle();
	        if (!actualTitle.equals(expectedTitle)) {
	            throw new AssertionError("Page title mismatch! Expected: " + expectedTitle + ", but found: " + actualTitle);
	        }
	    }
	    
	    public void verifyAddRecordButton() {
	        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addRecordButton));
	        Assert.assertTrue(addButton.isDisplayed(), "Add Record button is not displayed!");
	    }

	    // Wait and verify "Search" button
	    public void verifySearchButton() {
	        WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
	        Assert.assertTrue(searchBtn.isDisplayed(), "Search button is not displayed!");
	    }
	    
	    // Verify that only one row exists initially (indicating no data is loaded)
	    public void verifyNoDataInitially() {
	        List<WebElement> rows = driver.findElements(orderRows);
	        Assert.assertEquals(rows.size(), 1, "Expected only 1 row initially (no data), but found more!");
	    }
	    
	 // Click the Search button and verify more than one row is displayed
	    public void clickSearchButtonAndVerifyDataLoads() {
	        // Click the Search button
	        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
	        searchBtn.click();

	        // Wait for the rows to update
	        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(orderRows, 1));

	        // Verify that more than one row exists
	        List<WebElement> rows = driver.findElements(orderRows);
	        Assert.assertTrue(rows.size() > 1, "Expected more than 1 row after clicking Search, but found: " + rows.size());
	        System.out.println("Orders fetched...!");
	    }
	    
}
