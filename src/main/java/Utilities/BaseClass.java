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
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.healenium.SelfHealingDriver;



public class BaseClass {
    protected WebDriver driver;
    protected static PropertyReader propertyReader;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    ActionMap actionMap = new ActionMap();
    private static final Logger logger = LoggerFactory.getLogger(BaseClass.class);
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
        driver.manage().window().maximize();
    }

       /**
     * Scrolls the page horizontally by the specified number of pixels.
     * @param pixels Number of pixels to scroll. Positive for right, negative for left.
     */
    
    public void scrollHorizontally(WebElement targetElement) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
            logger.info("Scrolled horizontally by {} pixels");
        } catch (Exception e) {
            logger.error("Failed to scroll horizontally: ", e);
        }
    }    

    @AfterClass
    public void cleanUp() {
        if (driver != null) {
            driver.quit(); // Use quit() instead of close() to ensure all associated windows are closed
            driver = null;
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
    public void clickButton(WebDriver driver, String buttontext){
        actionMap.performAction(driver, "click" ,buttontext );

    }
    public void openBrowser(){
        actionMap.performAction(driver ,"open browser","");

    }
    public void selectFromDropdown(WebElement dropdownElement, String value) {
        Select dropdown = new Select(dropdownElement);   //*[@id="select2-drop"]
        dropdown.selectByVisibleText(value);
    }
    public void selectFromCustomDropdown(WebElement dropdownElement, String value) {
        dropdownElement.click();
        WebElement option = dropdownElement.findElement(By.xpath("//option[contains(text(), '"+value+"')]"));
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
    public void waitForPageLoad() {
        try {
            @SuppressWarnings("deprecation")
            WebDriverWait wait = new WebDriverWait(driver, 30); // Set initial wait time to 30 seconds
            
            // Wait for document ready state
            boolean pageLoaded = wait.until((ExpectedCondition<Boolean>) wd -> {
                try {
                    return ((JavascriptExecutor) wd)
                        .executeScript("return document.readyState").equals("complete");
                } catch (Exception e) {
                    return false;
                }
            });

            // If the page is not loaded within 30 seconds, refresh the page
            if (!pageLoaded) {
                logger.warn("Page did not load within 30 seconds, refreshing...");
                driver.navigate().refresh();
                
                // Wait again for the page to load after refresh
                wait.until((ExpectedCondition<Boolean>) wd -> {
                    try {
                        return ((JavascriptExecutor) wd)
                            .executeScript("return document.readyState").equals("complete");
                    } catch (Exception e) {
                        return false;
                    }
                });
            }

            // Wait for jQuery if present
            wait.until((ExpectedCondition<Boolean>) wd -> {
                try {
                    return ((Long) ((JavascriptExecutor) wd)
                        .executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            });

            // Wait for Angular if present
            wait.until((ExpectedCondition<Boolean>) wd -> {
                try {
                    return ((JavascriptExecutor) wd)
                        .executeScript("return (window.angular !== undefined) ? 0 === angular.element(document).injector().get('$http').pendingRequests.length : true").equals(true);
                } catch (Exception e) {
                    return true;
                }
            });

            Thread.sleep(3000); // Additional wait time for rendering
        } catch (Exception e) {
            logger.error("Page load wait error: " + e.getMessage());
        }
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
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       
       // Click the child element
       actions.moveToElement(childElement).click().build().perform();
    }

    public void switchToFrame(String locatorType, String locatorValue) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // 10 seconds wait time
        WebElement frameElement = findElementByType(locatorType, locatorValue);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    protected void initializeDriver() {
        try {
            WebDriverManager.chromedriver().setup();
        } catch (Exception e) {
            // Fallback to local ChromeDriver if WebDriverManager fails
            System.setProperty("webdriver.chrome.driver", propertyReader.getProperty("chromeDriverPath"));
        }
        WebDriver originalDriver = new ChromeDriver();
        driver = SelfHealingDriver.create(originalDriver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
    @AfterClass
    public void tearDown() {
        // Close the browser after test completion
        if (driver != null) {
            driver.quit(); // Quit the driver
            driver = null; // Set driver to null to avoid using it after quitting
        }
    }

    public String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destination = "screenshots/" + screenshotName + "_" + timestamp + ".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);
            return destination;
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }

    public WebElement findElementByHrefTextAndClick(String hrefText) {
        try {
            // Find element where href attribute contains the specified text
            String xpathExpression = "//a[contains(@href, '" + hrefText + "')]";
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
            element.click();
            logger.debug("Found element with href containing: '{}'", hrefText);
            return element;
        } catch (Exception e) {
            logger.error("Failed to find element with href containing '{}': {}", hrefText, e.getMessage());
            throw new RuntimeException("Element with href text '" + hrefText + "' not found", e);
        }
    }

    // Overloaded method to find exact href match
    public WebElement findElementByExactHref(String exactHref) {
        try {
            String xpathExpression = "//a[@href='" + exactHref + "']";
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
            
            logger.debug("Found element with exact href: '{}'", exactHref);
            return element;
        } catch (Exception e) {
            logger.error("Failed to find element with exact href '{}': {}", exactHref, e.getMessage());
            throw new RuntimeException("Element with exact href '" + exactHref + "' not found", e);
        }
    }

    public void clickButtonByText(String buttonText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            String xpathExpression = "//button[text()='"+ buttonText +"']";
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
            
            button.click();
            waitForPageLoad();
            logger.debug("Clicked on button with text: '{}'", buttonText);
        } catch (Exception e) {
            logger.error("Failed to click on button with text '{}': {}", buttonText, e.getMessage());
            throw new RuntimeException("Failed to click on button with text '" + buttonText + "'", e);
        }
    }

    public void selectInputByText(String inputLocator, String textToSelect) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            List<WebElement> inputs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(inputLocator)));

            for (WebElement input : inputs) {
                if (input.getAttribute("value").equals(textToSelect)) {
                    input.click();
                    logger.debug("Selected input with text: '{}'", textToSelect);
                    return;
                }
            }
            logger.warn("No input found with text: '{}'", textToSelect);
        } catch (Exception e) {
            logger.error("Failed to select input with text '{}': {}", textToSelect, e.getMessage());
            throw new RuntimeException("Failed to select input with text '" + textToSelect + "'", e);
        }
    }

    public void selectTextFromNestedList(String listLocator, String textToSelect) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement listElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(listLocator)));
            
            // Find all ul elements within the list element
            List<WebElement> ulElements = listElement.findElements(By.tagName("li"));
            
            for (WebElement ulElement : ulElements) {
                // Find all li elements within each ul
                //List<WebElement> listItems = ulElement.findElements(By.tagName("div"));
                
                //for (WebElement listItem : listItems) {
                    // Find the span within each list item
                    WebElement span = ulElement.findElement(By.tagName("div"));
                    System.out.println("list itemes" + span.getText());
                    if (span.getText().equals(textToSelect)) {
                        span.click();
                        logger.debug("Selected text: '{}'", textToSelect);
                        return;
                    }
                //}
            }
            logger.warn("No span found with text: '{}'", textToSelect);
        } catch (Exception e) {
            logger.error("Failed to select text '{}': {}", textToSelect, e.getMessage());
            throw new RuntimeException("Failed to select text '" + textToSelect + "'", e);
        }
    }

    public void clickTextFromNestedList(String listLocator, String textToClcik) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement listElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(listLocator)));
            
            // Find all ul elements within the list element
            List<WebElement> ulElements = listElement.findElements(By.tagName("span"));
            
            for (WebElement ulElement : ulElements) {
                  
                    System.out.println("list itemes : " + ulElement.getText());
                    if (ulElement.getText().equals(textToClcik)) {
                        ulElement.click();
                        logger.debug("Clicked text: '{}'", textToClcik);
                        return;
                    }
                
            }
            logger.warn("No span found with text: '{}'", textToClcik );
        } catch (Exception e) {
            logger.error("Failed to clicked text '{}': {}", textToClcik, e.getMessage());
            throw new RuntimeException("Failed to clicked text '" + textToClcik + "'", e);
        }
    }

    public void clickTextFromNestedList(String locatorType, String listLocator, String textToClcik) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            By locator = getByLocator(locatorType, listLocator);
            WebElement listElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            
            // Find all ul elements within the list element
            List<WebElement> ulElements = listElement.findElements(By.tagName("a"));
            
            for (WebElement ulElement : ulElements) {
                System.out.println("list itemes : " + ulElement.getText());
                if (ulElement.getText().equals(textToClcik)) {
                    ulElement.click();
                    logger.debug("Clicked text: '{}'", textToClcik);
                    return;
                }
            }
            logger.warn("No span found with text: '{}'", textToClcik);
        } catch (Exception e) {
            logger.error("Failed to clicked text '{}': {}", textToClcik, e.getMessage());
            throw new RuntimeException("Failed to clicked text '" + textToClcik + "'", e);
        }
    }

    private By getByLocator(String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            case "css":
            case "cssselector":
                return By.cssSelector(locatorValue);
            case "class":
            case "classname":
                return By.className(locatorValue);
            case "linktext":
                return By.linkText(locatorValue);
            case "partiallinktext":
                return By.partialLinkText(locatorValue);
            case "tagname":
                return By.tagName(locatorValue);
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }

    public void clickOnBlankPage(int x, int y) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script = "document.elementFromPoint(arguments[0], arguments[1]).click();";
            js.executeScript(script, x, y);
            logger.debug("Clicked on blank page at coordinates: ({}, {})", x, y);
        } catch (Exception e) {
            logger.error("Failed to click on blank page: {}", e.getMessage());
            throw new RuntimeException("Failed to click on blank page at coordinates (" + x + ", " + y + ")", e);
        }
    }

    public void clickOnElementByXPath(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            element.click();
            logger.debug("Clicked on element with XPath: '{}'", xpath);
        } catch (Exception e) {
            logger.error("Failed to click on element with XPath '{}': {}", xpath, e.getMessage());
            throw new RuntimeException("Failed to click on element with XPath '" + xpath + "'", e);
        }
    }

    public void clickElementByText(String tagName, String text) {
        try {
            String xpath = String.format("//%s[text()='%s']", tagName, text);
            WebElement element = driver.findElement(By.xpath(xpath));
            element.click();
            logger.debug("Clicked on element with text: '{}'", text);
        } catch (Exception e) {
            logger.error("Failed to click on element with text '{}': {}", text, e.getMessage());
            throw new RuntimeException("Failed to click on element with text '" + text + "'", e);
        }
    }

    public void navigateBack() {
        try {
            driver.navigate().back();
            logger.debug("Navigated back to the previous page.");
            waitForPageLoad();
        } catch (Exception e) {
            logger.error("Failed to navigate back: {}", e.getMessage());
            throw new RuntimeException("Failed to navigate back to the previous page", e);
        }
    }

    public void clickLinkByText(String linkText) {
        try {
            WebElement link = driver.findElement(By.linkText(linkText));
            link.click();
            logger.debug("Clicked on link with text: '{}'", linkText);
        } catch (Exception e) {
            logger.error("Failed to click on link with text '{}': {}", linkText, e.getMessage());
            throw new RuntimeException("Failed to click on link with text '" + linkText + "'", e);
        }
    }
}
