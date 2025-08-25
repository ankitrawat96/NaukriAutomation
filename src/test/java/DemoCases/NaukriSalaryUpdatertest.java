package DemoCases;

import ConfigFiles.ConfigFile;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Properties;

public class NaukriSalaryUpdatertest {
///b2a781683fd04b1a93436767aced57b3
 public static EncapuslationTest credential;
 public static   Properties prop;
    public static WebDriver driver;
    public static WebDriverWait wait;
    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }
    @Test
     public void noticeUpdateTest() throws Exception {

        // driver = new ChromeDriver();
         prop= ConfigFile.getProperties();
         credential=new EncapuslationTest(prop.getProperty("username"),prop.getProperty("password"));

        try {
            // Step 1: Login to Naukri
            driver.get(prop.getProperty("loginUrl"));

            Thread.sleep(2000);
            driver.findElement(By.id(prop.getProperty("Xpath_userName"))).sendKeys(credential.getUserName());
            driver.findElement(By.id(prop.getProperty("Xpath_password"))).sendKeys(credential.getPassword());
            driver.findElement(By.xpath(prop.getProperty("Xpath_submitButton"))).click();

            // Step 2: Navigate to profile page

            try {
                WebElement clickOnProfileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("clickOnProfileButton"))));
                clickOnProfileButton.click();
            } catch (Exception e) {
                driver.get(prop.getProperty("profileURL"));
            }


            // Step 3: Click the Edit button

            WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("editIcon"))));
            editIcon.click();

            // Step 4: Select “1 Month” (30 Days) availability
            selectAvailabilityChipByText(driver, wait, "1 Month");

            // Step 5: Save
            clickSave(driver, wait);
            Thread.sleep(5000);

            // Step 6: Reopen the Edit section
            editIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("editIcon"))));
            editIcon.click();

            // Step 7: Select “15 Days or less”
            selectAvailabilityChip(driver, wait, "15 Days or less");

            // Step 8: Save again
            clickSave(driver, wait);

            System.out.println("Availability updated to '1 Month' and then back to '15 Days or less'.");

            //} //catch (Exception e) {
            // e.printStackTrace();
            //} finally {
            //  driver.quit();
            // }
        } finally {

        }
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Method to select chip by visible text (like "1 Month", "2 Months", etc.)
    public static void selectAvailabilityChipByText(WebDriver driver, WebDriverWait wait, String visibleText) {
        String xpath = "//span[normalize-space()='" + visibleText + "']";
        WebElement chip = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", chip);
        chip.click();
        System.out.println("Selected availability: " + visibleText);
    }

    // Method to select the current active chip again (for "15 Days or less")
    public static void selectAvailabilityChip(WebDriver driver, WebDriverWait wait, String label) throws InterruptedException {
        String xpath = "//span[contains(@class,'chip-item') and normalize-space(text())='" + label + "']";
        WebElement chip = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        // Only click if not already active
        String classAttr = chip.getAttribute("class");
        if (!classAttr.contains("active")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", chip);
            Thread.sleep(300); // give time for UI to settle

            try {
                wait.until(ExpectedConditions.elementToBeClickable(chip)).click();
            } catch (ElementClickInterceptedException e) {
                System.out.println("Click intercepted — using JavaScript click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chip);
            }

            System.out.println("Selected availability: " + label);
        } else {
            System.out.println("Availability already set to: " + label);
        }
    }



    // Method to click Save
    public static void clickSave(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Save')])[2]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
        Thread.sleep(500);
        try {
            saveButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
        }
        System.out.println("Clicked Save.");
    }
}
