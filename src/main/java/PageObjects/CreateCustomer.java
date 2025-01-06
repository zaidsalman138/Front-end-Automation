package PageObjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import Utilities.BaseClass;
import Utilities.RandomValue;



public class CreateCustomer extends BaseClass {
    private WebDriver driver;
    public CreateCustomer(WebDriver driver) {
        this.driver = driver;
    }
    String passport = generateRandomNumber(10);
    public String personalid=passport;
    RandomValue randomValue = new RandomValue();
   // Actions actions = new Actions(driver);
    public  void createcustomer() {
    
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Navigate to the Customer section
        waitAndClick(driver,wait,By.xpath("//*[@id='appnavigator']"));

        // Click on the option to create a new customer
        clickByXpath(driver, "//*[contains(text(), 'SALES')]");
        

       // actions.moveToElement(salesNavigatioElement).perform();
       // WebElement customerNavigatioElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menubar_quickCreate_Accounts")));
        //WebElement customerNavigatioElement = driver.findElement(By.xpath("//*[contains(text(), 'Customers')]"));
        //actions.moveToElement(customerNavigatioElement).perform();
        //customerNavigatioElement.click();
        // Fill in the required information for the new customer
        String customerTitle = driver.getTitle();
        Assert.assertEquals(customerTitle, "Customers", "The user is not redirected to the Customer page after login.");
        //clickById(driver, "Accounts_listView_basicAction_LBL_ADD_RECORD");
        clickOnElement(driver,"id","Accounts_listView_basicAction_LBL_ADD_RECORD");
        String customername = randomValue.generateRandomName(10);
        clickOnElement(driver,"id", "select2-chosen-2");
        sendTextOnElement(driver,"id","s2id_autogen2_search","Residential");
        clickOnElement(driver,"xpath","//*[@class='select2-match']");
        sendTextOnElement(driver,"id", "Accounts_editView_fieldName_accountname","FN"+customername);
        sendTextOnElement(driver,"id", "Accounts_editView_fieldName_lastname" , "LN"+customername);
        clickOnElement(driver,"id", "select2-chosen-6");
        sendTextOnElement(driver,"id", "s2id_autogen6_search", "Foreigner");
        clickOnElement(driver,"classname","select2-match");
        clickOnElement(driver,"id", "select2-chosen-8");
        sendTextOnElement(driver,"id", "s2id_autogen8_search", "Male");
        clickOnElement(driver,"classname", "select2-match");
        clickOnElement(driver,"name", "dob");  
        waitAndClick(driver,wait, By.xpath("//*[@class='datepicker-days']/table/thead/tr[1]/th[1]"));
        clickElementMultipleTimes(driver,"//*[@class='datepicker-days']/table/thead/tr[1]/th[1]",8);
        clickOnElement(driver,"xpath", "//*[@class='datepicker-days']/table/tbody/tr[2]/td[1]");
        clickOnElement(driver,"id", "select2-chosen-14");
        sendTextOnElement(driver,"id", "s2id_autogen14_search", "Both");
        clickOnElement(driver,"classname", "select2-match");
        clickOnElement(driver,"id", "select2-chosen-16");
        sendTextOnElement(driver,"id", "s2id_autogen16_search", "English");
        clickOnElement(driver,"classname", "select2-match");
        scrollDown(0,100);
        waitAndClick(driver,wait, By.id("s2id_autogen23"));

        clickOnElement(driver,"xpath","//*[@id='select2-drop']/ul/li/div[text()='SMS']");
        clickOnElement(driver,"xpath","//*[@id='select2-drop']/ul/li/div[text()='E-Mail']");
        clickOnElement(driver,"xpath","//*[@id='select2-drop']/ul/li/div[text()='Phone Calls']");
        scrollDown(0, 400);
        clickOnElement(driver,"id","Accounts_editView_fieldName_hq_addressreference_select");
        switchToNewWindow(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        waitAndClick(driver,wait, By.xpath("//*[@title='ADDR100002433']"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Create Contact 
        scrollDown(0, 400);
        waitAndClick(driver,wait,By.id("Accounts_editView_fieldName_customer_pic_create"));
        switchToNewWindow(driver);
        clickOnElement(driver,"xpath","//*[@class='quickCreateContent']//span[text()='None']");
        clickOnElement(driver,"xpath","//*[@id='select2-drop']/ul/li[2]/div[text()='Mr.']");
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        sendTextOnElement(driver,"id","Contacts_editView_fieldName_firstname","Test");
        
        sendTextOnElement(driver,"id","Contacts_editView_fieldName_customerid_info",passport);
        clickOnElement(driver,"name","saveButton");
       // scrollDown(0, 400);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Save the new customer
        switchToOriginWindow(driver);
        waitForElementToBeClickable(driver,By.xpath("//button[contains(text(), 'Save')]"), 60);
        waitAndClick(driver,wait,By.xpath("//button[contains(text(), 'Save')]"));
        scrollDown(0, 1200);
        
        waitForElementToBeClickable(driver,By.name("getCDA"), 30);
        clickOnElement(driver,"name", "getCDA");
        waitAndClick(driver,wait,By.xpath("//button[contains(text(), 'Save')]"));
        // Verify that the new customer was created successfully
        String confirmationMessage = driver.getTitle();
        System.out.println("Created Customer name is :- "+confirmationMessage);
        assert confirmationMessage.equalsIgnoreCase("Customers - FN"+customername+ "LN"+customername);
        String CANo = driver.findElement(By.xpath("//*[@class='summaryViewEntries']//span[contains(text(), 'CA')]")).getText();
        System.out.println("Customer Account No. :"+CANo);


        
    //}
            // verification CA in BRM
    //public void verificationcanobrm() {
      //  String CANo = "CA0000002061";
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        String brmurl = propertyReader.getProperty("brmUrl");
        driver.get(brmurl);
        mouseOverAndClickChild(driver,"xpath", "//*[@id='account-a']/span[text()='Account']", "xpath", "//*[text()='Search Customer Account']");
        waitForPageLoad(driver);
        switchToFrame(driver,"id", "zk_comp_87");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        waitAndClick(driver,wait, By.id("txtentireaccnumber"));
        sendTextOnElement(driver,"id", "txtentireaccnumber", CANo);
        clickOnElement(driver,"id", "btnsearch");
        String brmCANo = driver.findElement(By.xpath("//*[@id='searchaccountlistbox-rows']//div[contains(text(),'CA')]")).getText();
        assert CANo.equalsIgnoreCase(brmCANo);
        
    }
}
