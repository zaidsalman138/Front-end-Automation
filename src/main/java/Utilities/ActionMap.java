package Utilities;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionMap {
//    public WebDriver driver;
    private HashMap<String, Runnable> actionMap;

    public ActionMap() {
        actionMap = new HashMap<>();
        initializeActions();
    }

    private void initializeActions() {
        actionMap.put("open browser", () -> openBrowser());
        actionMap.put("click on button", () -> clickOnButton());
        // Add more actions as needed
    }

    public void performAction(WebDriver driver ,String action, String text) {
        //Runnable runnable = actionMap.get(action.toLowerCase());
        if (text != null) {
            //runnable.run();
            switch (action) {
                case "click":
                    System.out.println("Performing click action");
                    WebElement button = driver.findElement(By.xpath("//button[text()="+text+"]"));
                    if (button.isDisplayed() && button.isEnabled()) {
                     button.click();
                    }
                    break;
                case "enter text":
                    System.out.println("Performing enter text action");
                    // Add your enter text logic here
                    break;
                default:
                    System.out.println("Unsupported action: " + action);
            }
        } else {
            System.out.println("Action not found: " + action);
        }
    }
     
    private void openBrowser() {
        System.out.println("Opening the browser...");
        System.setProperty("webdriver.chrome.driver", new PropertyReader().getProperty("chromeDriverPath"));
        //driver = new ChromeDriver();
    }

    private void clickOnButton() {
        System.out.println("Clicking on the button...");
    // Assuming you have a button element with id "buttonId"
    //WebElement button = driver.findElement(By.id("button"));
    //button.click();
    }

    // Add more action methods as needed
}