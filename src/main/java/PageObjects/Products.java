package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Products {

	
	private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // Locators
    private By appNavigator = By.xpath("//div[@id='appnavigator']//div[@class='row app-navigator']");
    private By salesDropdown = By.xpath("//div[@id='SALES_modules_dropdownMenu']//div[@class='menu-items-wrapper app-menu-items-wrapper']");
    private By productsOption = By.xpath("//a[@href='index.php?module=Products&view=List&app=SALES']//span[@class='module-icon']//i[@title='Products']");
    private By dropdownButton = By.xpath("//button[@class='btn btn-default btn-sm dropdown-toggle']"); // Locator for dropdown button
    private By searchButton = By.xpath("//button[normalize-space()='Search']"); // Locator for Search button

    // Constructor
    public Products(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 80);
        this.actions = new Actions(driver);
    }

    // Step 1: Click on dropdown
    public void clickAppNavigator() {
        WebElement navigatorElement = wait.until(ExpectedConditions.elementToBeClickable(appNavigator));
        navigatorElement.click();
        
    }

    // Step 2: Wait and hover over Sales dropdown
    public void hoverOverSalesDropdown() {
        WebElement salesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(salesDropdown));
        actions.moveToElement(salesElement).perform();
        
    }

    // Step 3: Wait and click Products
    public void clickProductsOption() {
        WebElement productsElement = wait.until(ExpectedConditions.elementToBeClickable(productsOption));
        productsElement.click();
        System.out.println("Clicked on Products option.");
    }
    
 // Method to verify page title
    public void verifyPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title is incorrect!");
        System.out.println("Verified: Page title is '" + expectedTitle + "'.");
    }
    
    public void verifyDropdownButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownButton));
        Assert.assertTrue(button.isDisplayed(), "Dropdown button is not visible.");
        System.out.println("Verified: Dropdown button is visible.");
    }

    // **Validate Search Button**
    public void verifySearchButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        Assert.assertTrue(button.isDisplayed(), "Search button is not visible.");
        System.out.println("Verified: Search button is visible.");
    }
}
