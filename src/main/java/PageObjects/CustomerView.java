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
        //private WebDriver driver;
        public CustomerView(WebDriver driver) {
            this.driver = driver;
        }
        public  void customer360view() throws InterruptedException{

            @SuppressWarnings("deprecation")
            WebDriverWait wait = new WebDriverWait(driver, 30);
            driver.get(propertyReader.getProperty("crmSiaUrl"));
            // Navigate to the Customer section
            //waitAndClick(wait, By.xpath("//*[@id='appnavigator']/div/span"));

            // Click on the option to create a new customer
           // clickByXpath(driver, "//*[contains(text(), 'SALES')]");
            
            //mouseOverAndClickChild("xpath", "//*[contains(text(), 'SALES')]" ,"xpath","//a[contains(@href, 'ServiceInstanceAccount')]/span[2]");
            
            String siaTitle = driver.getTitle();
            System.out.println("Actual title of SIA Page " +siaTitle);
            Assert.assertEquals(siaTitle, "Service Instance Account", "SIA Title does not Matched");
            waitForPageLoad();
            WebElement siaStatus = driver.findElement(By.xpath("//*[@id='s2id_autogen3']"));
            siaStatus.click();
            selectTextFromNestedList("//*[@id='select2-drop']/ul", "Active");
        // Assuming you have a BaseClass instance
        // Click on a blank area of the page at coordinates (100, 100)
            clickOnBlankPage(100, 100);
            //Thread.sleep(10);
            clickButtonByText("Search");
            waitForPageLoad();
            
           // logger.info("Created Customer name is: {}", confirmationMessage);
           // assert confirmationMessage.equalsIgnoreCase("Customers - FN"+customername+ "LN"+customername);
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
           //[title='View Contract by SI']
           clickOnElement("css", ".tab-item:nth-child(6) .custom-module");
           logger.info("Clicked on Recharge History Tab");
           
           waitForPageLoad();
           clickOnElement("css", ".tab-item:nth-child(7) .custom-module");
           
           
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



            /*String brmurl = propertyReader.getProperty("brmUrl");
            driver.get(brmurl);
            mouseOverAndClickChild("xpath", "//*[@id='account-a']/span[text()='Account']", "xpath", "//*[text()='Search Customer Account']");
            waitForPageLoad();
            switchToFrame("id", "zk_comp_87");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            waitAndClick(wait, By.id("txtentireaccnumber"));
            sendTextOnElement("id", "txtentireaccnumber", CANo);
            clickOnElement("id", "btnsearch");
            //String brmCANo = driver.findElement(By.xpath("//*[@id='searchaccountlistbox-rows']//div[contains(text(),'CA')]")).getText();
            //assert CANo.equalsIgnoreCase(brmCANo); */
            captureScreenshot(driver, "end_execution");
        }

    public void customerAccount() throws InterruptedException{
        driver.get(propertyReader.getProperty("crmCaUrl"));
        waitForPageLoad();
        //clickOnBlankPage(100, 100);
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
        clickElementByText("span", "Order");
        waitForPageLoad();
        navigateBack();
        clickElementByText("strong", "Order");
        clickLinkByText("Opportunities");
        waitForPageLoad();
        clickButtonByText("Search");
        //String oppTitle = driver.getTitle();
        
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
        clickById(driver,"from_date");
        clickOnElement("xpath", "//*[@class=\"datepicker-days\"]/table/tbody/tr[1]/td[1]");
        clickById(driver,"to_date");
        clickOnElement("xpath", "//*[@class=\"datepicker-days\"]/table/tbody/tr[6]/td[7]");
        clickById(driver,"search_billdetail");
        waitForPageLoad();
        captureScreenshot(driver, "bill_details");
        navigateBack();
        clickElementByText("strong", "Accounts");
        logger.info("Clicked on Accounts Tab");
        clickLinkByText("Contracts");
        waitForPageLoad();
        navigateBack();
        clickElementByText("strong", "Accounts");
        clickLinkByText("ServiceAccount");
        waitForPageLoad();
        clickElementByText("strong", "Accounts");
        clickLinkByText("BillingAccount");
        waitForPageLoad();
        
        clickElementByText("strong", "Accounts");
        clickLinkByText("SIA");
        waitForPageLoad();
        clickElementByText("strong", "Transaction Details");
        logger.info("Clicked on Transaction Details Tab");
        waitForPageLoad();      



        
       




    }
}
