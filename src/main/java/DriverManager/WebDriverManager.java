package DriverManager;

import Utilities.PropertyReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        // Read the browser type from the configuration
        PropertyReader propertyReader = new PropertyReader();
        String browserType = propertyReader.getProperty("browser").toLowerCase();
        boolean headless = Boolean.parseBoolean(propertyReader.getProperty("headless"));

        switch (browserType) {
            case "chrome":
                // Setup ChromeDriver
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(headless);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                // Setup FirefoxDriver
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(headless);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new IllegalStateException("Unsupported browser type: " + browserType);
        }

        // Apply implicit wait configuration
        int implicitWait = Integer.parseInt(propertyReader.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

        return driver;
    }
}
