package PageObjects;

import Utilities.BaseClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;




public class CustomerView extends BaseClass {
        private static final Logger logger = LogManager.getLogger(CustomerView.class);
        private WebDriver driver;
        public CustomerView(WebDriver driver) {
            this.driver = driver;
        }
        public  void customer360view() throws InterruptedException{

            
            WebDriverWait wait = new WebDriverWait(driver, 30);
            driver.get(propertyReader.getProperty("crmSiaUrl"));
            
            String siaTitle = driver.getTitle();
            System.out.println("Actual title of SIA Page " +siaTitle);
            Assert.assertEquals(siaTitle, "Service Instance Account", "SIA Title does not Matched");
            waitForPageLoad();
            WebElement siaStatus = driver.findElement(By.xpath("//*[@id='s2id_autogen3']"));
            siaStatus.click();
            selectTextFromNestedList("//*[@id='select2-drop']/ul", "Active");
            clickOnBlankPage(100, 100);
            clickButtonByText("Search");
            waitForPageLoad();

           WebElement siaNo = driver.findElement(By.xpath("//*[@id='ServiceInstanceAccount_listView_row_2']/td[2]/span/span"));
           String SIANo = siaNo.getText();
           logger.info("Service Instance Account No.: ", siaNo.getText());
           siaNo.click();
           waitForPageLoad();
           captureScreenshot(driver, SIANo);
           clickOnElement("css", ".tab-item:nth-child(3) .custom-module");
           logger.info("Clicked on SIA Tab ");
           waitForPageLoad();
           clickById(driver,"from_date");
           waitForPageLoad();
           clickOnElement("xpath", "//*[@class=\"datepicker-days\"]/table/tbody/tr[1]/td[1]");
           clickById(driver,"to_date");
           waitForPageLoad();
           clickOnElement("xpath", "//*[@class=\"datepicker-days\"]/table/tbody/tr[6]/td[7]");
           clickById(driver, "search_history");
           logger.info("Clicked on Search History Button");
           waitForPageLoad();
           clickOnElement("css", ".tab-item:nth-child(4) .custom-module");
           logger.info("Clicked on In Tab");
           waitForPageLoad();
           clickByXpath(driver, "//*[@class='tab-icon']/i[@title='Tickets']");
           logger.info("Clicked on Tickets Tab");
           waitForPageLoad();
           clickOnElement("css", ".tab-item:nth-child(6) .custom-module");
           logger.info("Clicked on Recharge History Tab");
           waitForPageLoad();
           clickOnElement("css", ".tab-item:nth-child(7) .custom-module");
           logger.info("Clicked on View Contract by SI Tab");
           
           waitForPageLoad();
           clickById(driver,"view_contract_by_si");
           logger.info("Clicked on View Contract Button");
           waitForPageLoad();
           clickOnElement("css", ".tab-item:nth-child(8) .custom-module");
           logger.info("Clicked on BTH Tab");
           waitForPageLoad();

           clickOnElement("css", ".tab-item:nth-child(9) .custom-module");
           logger.info("Clicked on DD Tab");           
           waitForPageLoad();
           clickById(driver, "search_deposit_details");
           logger.info("Clicked on Search Deposit Details Button");
           
           

           clickOnElement("css", ".tab-item:nth-child(10) .custom-module");
           logger.info("Clicked on Inventory Tab");
           
           waitForPageLoad();
           clickOnElement("css", ".tab-item:nth-child(11) .custom-module");
           logger.info("Clicked on Compaign Tab");
           
           waitForPageLoad();
           clickOnElement("css", ".tab-item:nth-child(12) .custom-module");
           logger.info("Clicked on DM Tab");
           

           waitForPageLoad();
           clickOnElement("css", "button#search_discountdetail");

           captureScreenshot(driver, "end_execution");
        }

    public void customerAccount() throws InterruptedException{
        String crmCaUrl = propertyReader.getProperty("crmUrl") + "index.php?module=Accounts&view=List&app=SALES";
        driver.get(crmCaUrl);
        waitForPageLoad();
        
        WebElement caStatus = driver.findElement(By.xpath("//*[@id=\"s2id_autogen5\"]/ul"));
        scrollHorizontally(caStatus);
        caStatus.click();
        selectTextFromNestedList("//*[@id=\"select2-drop\"]/ul", "Active");
        clickOnBlankPage(100, 100);
        clickButtonByText("Search");
        waitForPageLoad();
        WebElement caNo = driver.findElement(By.xpath("//*[@id='Accounts_listView_row_1']//*[@data-name='account_no']/span/span/a"));
        caNo.click();
        logger.info("Clicked on Customer Account");
        String caTitle = driver.getTitle();
        captureScreenshot(driver, caTitle);
        waitForPageLoad();
        clickElementByText("strong", "Order");
        logger.info("Clicked on Order Tab");
        //clickElementByText("span", "Order");
        clickByXpath(driver, "//span[@class='content' and contains(text(), 'Order')]");
        waitForPageLoad();
        //navigateBack();
        clickElementByText("strong", "Order");
        clickLinkByText("Opportunities");
        waitForPageLoad();
        clickButtonByText("Search");
        
        
        clickElementByText("strong", "Order");
        clickLinkByText("Service Contracts");
    
        waitForPageLoad();
        clickButtonByText("Search");
        
        clickElementByText("strong", "Order");
        clickLinkByText("CUG VPN Details");
        captureScreenshot(driver, driver.getTitle());
        
        clickElementByText("strong", "Bill Details");
        logger.info("Clicked on Bill Details Tab");
        waitForPageLoad();
        clickLinkByText("Bill Detail");
        waitForPageLoad();
        clickById(driver,"Accounts_editView_fieldName_billing_account_no_select");
        waitForPageLoad();
        switchToNewWindow(driver);
        clickByXpath(driver, "//*[@id='BillingAccount_popUpListView_row_1']/td[2]");
        selectDateOneYearBack("from_date");
        waitForPageLoad();
        clickById(driver,"to_date");
        clickOnElement("css", "td.today.day");
        waitForPageLoad();
        clickById(driver,"search_billdetail");
        waitForPageLoad();
        captureScreenshot(driver, "bill_details");   
        //navigateBack(); 
        clickElementByText("strong", "Accounts");
        logger.info("Clicked on Accounts Tab");
        clickLinkByText("Contacts");
        logger.info("Clicked on Contacts Tab");
        waitForPageLoad();
        clickElementByText("strong", "Accounts");
        clickOnElement("xpath", "//*[@id=\"mCSB_13_container\"]/li[2]/a/span[1]/span[2]");
        waitForPageLoad();
        clickElementByText("strong", "Accounts");
        clickOnElement("xpath", "//*[@id=\"mCSB_13_container\"]/li[3]/a/span[1]/span[2]");
        waitForPageLoad();
        
        clickElementByText("strong", "Accounts");
        clickOnElement("xpath", "//*[@id=\"mCSB_13_container\"]/li[4]/a/span[1]/span[2]");
        waitForPageLoad();
        
        clickElementByText("strong", "Transaction Details");
        logger.info("Clicked on Transaction Details Tab");
        clickLinkByText("Notification History");
        captureScreenshot(driver, "notification_history");
        logger.info("Clicked on Notification History Tab");

        clickById(driver,"searchNotificationHistory");
        captureScreenshot(driver, "notification_history_search");
        clickElementByText("strong", "Transaction Details");
        clickLinkByText("Reward History");
        logger.info("Clicked on Reward History Tab");
        waitForPageLoad();
        captureScreenshot(driver, "reward_history_search");
        clickElementByText("strong", "Transaction Details");
        clickLinkByText("Usage History");
        logger.info("Clicked on Usage History Tab");
        waitForPageLoad();
        captureScreenshot(driver, "usage_history_search");
        clickElementByText("strong", "Transaction Details");
        clickLinkByText("Balance Details");
        logger.info("Clicked on Balance Details Tab");
        captureScreenshot(driver, "balance_details_search");
        clickElementByText("strong", "Transaction Details");
        clickLinkByText("Invoice History");
        logger.info("Clicked on Invoice History Tab");
        captureScreenshot(driver, "invoice_history_search");
        clickElementByText("strong", "Transaction Details");
        clickLinkByText("IVR Call History");
        logger.info("Clicked on IVR Call History Tab");
        captureScreenshot(driver, "ivr_call_history_search");
        clickElementByText("strong", "Transaction Details");
        clickLinkByText("Recharge History");
        logger.info("Clicked on Recharge History Tab");
        captureScreenshot(driver, "recharge_history_search");
        clickElementByText("strong", "Transaction Details");
        clickLinkByText("Adjustment History");
        logger.info("Clicked on Adjustment History Tab");
        waitForPageLoad();
        captureScreenshot(driver, "adjustment_history_search");
        clickElementByText("strong", "Transaction Details");
        clickLinkByText("Payment History");
        logger.info("Clicked on Payment History Tab");
        captureScreenshot(driver, "payment_history_search");
        clickElementByText("strong", "Transaction Details");
        clickLinkByText("Credit Limit History");
        logger.info("Clicked on Credit Limit History Tab");
        waitForPageLoad();
        captureScreenshot(driver, "credit_limit_history_search");
        clickElementByText("strong", "Transaction Details");
        clickLinkByText("Recharge History");
        logger.info("Clicked on Recharge History Tab");
        waitForPageLoad();      
        clickElementByText("strong", "Others");
        logger.info("Clicked on Others Tab");
        waitForPageLoad();
        clickLinkByText("Comments");
        logger.info("Clicked on Comments Tab");
        waitForPageLoad();
        clickElementByText("strong", "Others");
        clickLinkByText("Documents");
        logger.info("Clicked on Documents Tab");
        waitForPageLoad();
        clickElementByText("strong", "Others");
        clickLinkByText("Tickets");
        logger.info("Clicked on Tickets Tab");
        waitForPageLoad();

    }

    
    
}