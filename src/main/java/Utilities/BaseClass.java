package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.Random;
import java.util.concurrent.TimeUnit;





public class BaseClass {
    protected static WebDriver driver;
    protected static PropertyReader propertyReader;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    ActionMap actionMap = new ActionMap();
    @BeforeClass
    public void setup() {
        // Initialize the PropertyReader
        propertyReader = new PropertyReader();
        // Load the configuration properties
        propertyReader.loadProperties();
        // Get browser type from configuration (e.g., "chrome" or "firefox")
        String browserType = propertyReader.getProperty("browserType");

        // Initialize the WebDriver based on browser type
        if (browserType.equalsIgnoreCase("chrome")) {
            // Set ChromeDriver path
            System.setProperty("webdriver.chrome.driver", propertyReader.getProperty("chromeDriverPath"));
            // Initialize ChromeDriver
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            // Set GeckoDriver path
            System.setProperty("webdriver.gecko.driver", propertyReader.getProperty("geckoDriverPath"));
            // Initialize FirefoxDriver
            driver = new FirefoxDriver();
        } else {
            // Handle unsupported browser types
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        // Maximize the browser window
        driver.manage().window().maximize();
        // Set implicit wait time
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to the base URL from the configuration
        driver.get(propertyReader.getProperty("baseUrl"));
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after test completion
        if (driver != null) {
           // driver.quit();
        }
    }

    public WebElement clickOnElement(String locatorType, String locatorValue) {
        WebElement element = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                element = driver.findElement(By.id(locatorValue));
                element.isDisplayed();
                element.click();
                break;
            case "name":
                element = driver.findElement(By.name(locatorValue));
                element.isDisplayed();
                element.click();
                break;
            case "xpath":
                element = driver.findElement(By.xpath(locatorValue));
                element.isDisplayed();
                element.click();
                break;
            case "css":
            case "cssselector":
                element = driver.findElement(By.cssSelector(locatorValue));
                element.isDisplayed();
                element.click();
                break;
            case "class":
            case "classname":
                element = driver.findElement(By.className(locatorValue));
                element.isDisplayed();
                element.click();
                break;
            case "linktext":
                element = driver.findElement(By.linkText(locatorValue));
                element.isDisplayed();
                element.click();
                break;
            case "partiallinktext":
                element = driver.findElement(By.partialLinkText(locatorValue));
                element.isDisplayed();
                element.click();
                break;
            case "tagname":
                element = driver.findElement(By.tagName(locatorValue));
                element.isDisplayed();
                element.click();
                
                break;
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
        return element;
    }
    public WebElement clickandSendTextOnElement(String locatorType, String locatorValue, String FieldValue) {
        WebElement element = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                element = driver.findElement(By.id(locatorValue));
                element.isDisplayed();
                element.click();
                element.sendKeys(FieldValue);
                break;
            case "name":
                element = driver.findElement(By.name(locatorValue));
                element.isDisplayed();
                element.click();
                element.sendKeys(FieldValue);
                break;
            case "xpath":
                element = driver.findElement(By.xpath(locatorValue));
                element.isDisplayed();
                element.click();
                element.sendKeys(FieldValue);
                break;
            case "css":
            case "cssselector":
                element = driver.findElement(By.cssSelector(locatorValue));
                element.isDisplayed();
                element.click();
                element.sendKeys(FieldValue);
                break;
            case "class":
            case "classname":
                element = driver.findElement(By.className(locatorValue));
                element.isDisplayed();
                element.click();
                element.sendKeys(FieldValue);
                break;
            case "linktext":
                element = driver.findElement(By.linkText(locatorValue));
                element.isDisplayed();
                element.click();
                element.sendKeys(FieldValue);
                break;
            case "partiallinktext":
                element = driver.findElement(By.partialLinkText(locatorValue));
                element.isDisplayed();
                element.click();
                element.sendKeys(FieldValue);
                break;
            case "tagname":
                element = driver.findElement(By.tagName(locatorValue));
                element.isDisplayed();
                element.click();
                element.sendKeys(FieldValue);
                
                break;
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
        return element;
    }
    public void scrollDowntoElement( String elementType,String elementData) { 
        
      try{      
          WebElement element = findElementByType(elementType, elementData);
          js.executeScript("arguments[0].scrollIntoView(true);", element);}
        catch(Exception e){
          System.err.println("Exception occurred while scrolling down: " + e.getMessage());
          e.printStackTrace();
        }   
      // js.executeScript("window.scrollBy(0,1000)");
    }
    public WebElement findElementByType(String elementType, String elementData) {
        switch (elementType.toLowerCase()) {
            case "id":
                return driver.findElement(By.id(elementData));
            case "name":
                return driver.findElement(By.name(elementData));
            case "xpath":
                return driver.findElement(By.xpath(elementData));
            case "css":
            case "cssselector":
                return driver.findElement(By.cssSelector(elementData));
            case "class":
            case "classname":
                return driver.findElement(By.className(elementData));
            case "linktext":
                return driver.findElement(By.linkText(elementData));
            case "partiallinktext":
                return driver.findElement(By.partialLinkText(elementData));
            case "tagname":
                return driver.findElement(By.tagName(elementData));
            default:
                throw new IllegalArgumentException("Invalid locator type: " + elementType);
        }
    
    }
    public WebElement sendTextOnElement(String locatorType, String locatorValue, String Value) {
        WebElement element = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                element = driver.findElement(By.id(locatorValue));
                element.sendKeys(Value);
                break;
            case "name":
                element = driver.findElement(By.name(locatorValue));
                element.sendKeys(Value);
                break;
            case "xpath":
                element = driver.findElement(By.xpath(locatorValue));
                element.sendKeys(Value);
                break;
            case "css":
            case "cssselector":
                element = driver.findElement(By.cssSelector(locatorValue));
                element.sendKeys(Value);
                break;
            case "class":
            case "classname":
                element = driver.findElement(By.className(locatorValue));
                element.sendKeys(Value);
                break;
            case "linktext":
                element = driver.findElement(By.linkText(locatorValue));
                element.sendKeys(Value);
                break;
            case "partiallinktext":
                element = driver.findElement(By.partialLinkText(locatorValue));
                element.sendKeys(Value);
                break;
            case "tagname":
                element = driver.findElement(By.tagName(locatorValue));
                element.sendKeys(Value);
                
                break;
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
        return element;
    }


    public WebElement findElementByXPath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void scrollDown(int scr_wd, int scr_lt) {
        try {
            js.executeScript("window.scrollBy(" + scr_wd + "," + scr_lt + ")");
        } catch (Exception e) {
            System.err.println("Exception occurred while scrolling down: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void clickButton(){
        actionMap.performAction("click on button");

    }
    public void openBrowser(){
        actionMap.performAction("open browser");

    }
    public void selectFromDropdown(WebElement dropdownElement, String value) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(value);
    }
    public void selectFromCustomDropdown(WebElement dropdownElement, String value) {
        dropdownElement.click();
        WebElement option = dropdownElement.findElement(By.xpath("//div[contains(text(), '" + value + "')]"));
        option.click();
    }
    public void switchToNewWindow(WebDriver driver) {
        // Store the current window handle for the original window
        String originalWindowHandle = driver.getWindowHandle();
    
        // Loop through all available window handles
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                // Switch to the new window
                driver.switchTo().window(windowHandle);
                System.out.println("Switched to new window: " + driver.getTitle());
    
                // Perform your actions on the new window here
                // Example: driver.findElement(By.id("someElement")).click();
    
                // Close the new window if needed
              //  driver.close();
    
                // Break out of the loop once the required window is found and actions are performed
                break;
            }
        }
    
        // Switch back to the original window
       // driver.switchTo().window(originalWindowHandle);
        //System.out.println("Switched back to original window: " + driver.getTitle());
    }

    public void switchToOriginWindow(WebDriver driver) {
        // Store the current window handle for the original window
        String originalWindowHandle = driver.getWindowHandle();
    
        // Loop through all available window handles
        for (String windowHandle : driver.getWindowHandles()) {
            if (windowHandle.equals(originalWindowHandle)) {
                // Switch to the new window
                driver.switchTo().window(windowHandle);
                System.out.println("Switched to new window: " + driver.getTitle());
    
                // Perform your actions on the new window here
                // Example: driver.findElement(By.id("someElement")).click();
    
                // Close the new window if needed
              //  driver.close();
    
                // Break out of the loop once the required window is found and actions are performed
                break;
            }
        }
    
        // Switch back to the original window
        //driver.switchTo().window(originalWindowHandle);
        //System.out.println("Switched back to original window: " + driver.getTitle());
    }


    public String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder randomNumber = new StringBuilder();
    
        // Ensure the first digit is not zero
        randomNumber.append(random.nextInt(9) + 1);
    
        // Generate the rest of the digits
        for (int i = 1; i < length; i++) {
            randomNumber.append(random.nextInt(10));
        }
    
        return randomNumber.toString();
    }
    public String intToString(int num) {
        return Integer.toString(num);
    }
    public void waitForPageLoad(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, 60); // Wait up to 30 seconds

    // Wait for JavaScript document.readyState to be 'complete'
    wait.until((ExpectedCondition<Boolean>) wd ->
        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
    public void clickElementMultipleTimes(WebDriver driver, String locator, int times) {
    // Find the element using CSS selector
      WebElement element = driver.findElement(By.xpath(locator));

    // Click the element the specified number of times
     for (int i = 0; i < times; i++) {
        element.click();
     }
    }
    public void clickById(WebDriver driver, String id) {
        driver.findElement(By.id(id)).click();
    }
    public void clickByXpath(WebDriver driver, String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }
    public void waitAndClick(WebDriverWait wait, By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.isDisplayed();
        element.click();
    }
    public void mouseOverAndClick(String locatorType, String locatorValue) {
        Actions actions = new Actions(driver);
        WebElement element = findElementByType(locatorType, locatorValue);
    
        actions.moveToElement(element).click().build().perform();
    }
    public void mouseOverAndClickChild(String parentLocatorType, String parentLocatorValue, String childLocatorType, String childLocatorValue) {
      Actions actions = new Actions(driver);
    
      // Find the parent element and perform mouse over
       WebElement parentElement = findElementByType(parentLocatorType, parentLocatorValue);
       actions.moveToElement(parentElement).perform();
    
       // Wait for the child element to be visible (you might need to add explicit wait here)
       WebElement childElement = findElementByType(childLocatorType, childLocatorValue);
    
       // Click the child element
       actions.moveToElement(childElement).click().build().perform();
    }

    public void switchToFrame(String locatorType, String locatorValue) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // 10 seconds wait time
        WebElement frameElement = findElementByType(locatorType, locatorValue);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }
}
