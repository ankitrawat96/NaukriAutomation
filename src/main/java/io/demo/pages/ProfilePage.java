package io.demo.pages;

import io.demo.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {

    private final By editIcon = By.xpath("//em[contains(@class,'icon edit')]");
    private final String chipXpath = "//span[normalize-space()='%s']";
    private final By saveButton = By.xpath("(//button[contains(text(),'Save')])[2]");
    private final By clickOnProfileButton=By.xpath("//a[normalize-space()='View profile']");
    private final By resumeUpload=By.xpath("//input[@type='file' and @id='attachCV']");
    private final By toastMessage=By.xpath("//p[@class='msg']");
    public ProfilePage(WebDriver driver) { super(driver); }


    public void viewProfile(){
        waitClickable(clickOnProfileButton).click();
    }
    public void clickEdit() {
        waitClickable(editIcon).click();
    }
 public void updateFile(String path) throws InterruptedException {
     waitTillPresenceOfElement(resumeUpload).sendKeys(path);
     try{
       WebElement toast=waitVisible(toastMessage);
         System.out.println("✅ Upload result: " + toast.getText());
     }
     catch (Exception e){

         System.out.println("❌ Upload failed: Toast message not found");
     }
 }
 public void updateDocFile(String path){
        waitTillPresenceOfElement(resumeUpload).sendKeys(path);
     try{
         WebElement toast=waitVisible(toastMessage);
         System.out.println("✅ Upload result: " + toast.getText());
     }
     catch (Exception e){

         System.out.println("❌ Upload failed: Toast message not found");
     }
 }


    public void chooseAvailability(String label) {
        By chip = By.xpath(String.format(chipXpath, label));
        WebElement el = waitClickable(chip);
        el.click();
    }
    public  void selectAvailabilityChip(String label) throws InterruptedException {
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


    public void save() {
        waitClickable(saveButton).click();
        System.out.println("Test Successful");
    }
    // Method to click Save
    public void clickSave () throws InterruptedException {
        waitClickable(saveButton);
        //WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Save')])[2]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", waitClickable(saveButton));
        Thread.sleep(500);
        try {
            waitClickable(saveButton).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", waitClickable(saveButton));
        }
        System.out.println("Clicked Save.");
    }

    @Override
    public boolean isAt() {
        System.out.println("Page test of Profile page:"+ driver.findElement(clickOnProfileButton).isEnabled());
        return driver.findElement(clickOnProfileButton).isEnabled();
    }





}
