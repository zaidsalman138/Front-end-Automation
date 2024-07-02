package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.BaseClass;


public class PrepaidOrder extends BaseClass {

    private WebDriver driver;
    public PrepaidOrder(WebDriver driver) {
        this.driver = driver;
    }

    public  void prepaidOrder() {
    CreateCustomer createCustomer = new CreateCustomer(driver);
    String personalId = createCustomer.personalid;        
    WebDriverWait wait = new WebDriverWait(driver, 30);  

    waitAndClick(wait, By.xpath("//*[@id='appnavigator']"));
   
    mouseOverAndClickChild("xpath", "//*[contains(text(), 'SALES')]", "xpath", "//*[@id='mCSB_2_container']/li[4]/a");
   
    
    String customerTitle = driver.getTitle();
    Assert.assertEquals(customerTitle, "Order", "The user is not redirected to the Order Page after login.");
    waitAndClick(wait, By.id("Invoice_listView_basicAction_LBL_ADD_RECORD"));
    clickByXpath(driver,"//*[@class='select2-container inputElement select2']/a/span[text()='Provide Order']");
    sendTextOnElement("id", "s2id_autogen2_search","Provide Order");
    clickOnElement("class", "select2-match");
    clickByXpath(driver, "//*[@id='select2-chosen-6']");
    sendTextOnElement("id", "s2id_autogen6_search", "Prepaid");
    clickOnElement("class", "select2-match");
    clickOnElement("id", "Invoice_editView_fieldName_personal_id_select");
    clickandSendTextOnElement("name", "vat_tax_number", "2974962252");
    clickByXpath(driver, "//button[text()='Search']");
    clickByXpath(driver, "//*[@title='2974962252']");
    // add Products detail
    scrollDown(0, 400);
    clickByXpath(driver,"//div[2]/span/i[@class='vicon-products']");
    clickandSendTextOnElement("name", "productname","Telekom Karta Offer");
    clickByXpath(driver, "//button[text()='Search']");
    clickOnElement("xpath", "//a[text()='Telekom Karta Offer']");
    clickOnElement("id", "addProduct");
    scrollDown(0, 400);
    clickOnElement("id", "valid");
    clickOnElement("xpath", "//button[text()='Save & Proceed']");
    // click on save for price details page
    clickOnElement("xpath", "//button[text()='Save & Proceed']");


    
    }
}