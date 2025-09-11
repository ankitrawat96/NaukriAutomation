package demo.tests;

import io.demo.core.BaseTest;
import io.demo.core.Config;
import io.demo.pages.LoginPage;
import io.demo.pages.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginFlowTest extends BaseTest {

    @Test
    public void loginAndEditAvailability() throws InterruptedException {
        Config cfg = this.cfg;

        LoginPage login = new LoginPage(driver).open();
        Assert.assertTrue(login.isAt(), "Login page should be visible");

        //Login Successful
        login.typeUsername(cfg.credentials.username)
             .typePassword(cfg.credentials.password)
             .submit();

        // Navigate to profile
        ProfilePage profile = new ProfilePage(driver);
        driver.get(cfg.app.baseUrl + "/mnjuser/profile");
        Assert.assertTrue(profile.isAt(), "Login Successfully");

        // Edit availability
        profile.viewProfile();
        System.out.println("view profile");
        profile.clickEdit();
        System.out.println("clicked edit button");
        profile.chooseAvailability("1 Month");
        profile.clickSave();
        Thread.sleep(10000);
        profile.clickEdit();
        profile.selectAvailabilityChip("15 Days or less");
        profile.chooseAvailability("15 Days or less");
        profile.save();




    }

    @Test
    public void uploadResume() throws InterruptedException {

        Config cfg = this.cfg;

        LoginPage login = new LoginPage(driver).open();
        Assert.assertTrue(login.isAt(), "Login page should be visible");

        //Login Successful
        login.typeUsername(cfg.credentials.username)
                .typePassword(cfg.credentials.password)
                .submit();

        // Navigate to profile
        ProfilePage profile = new ProfilePage(driver);
        driver.get(cfg.app.baseUrl + "/mnjuser/profile");
        Assert.assertTrue(profile.isAt(), "Login Successfully");

        // Edit availability
        profile.viewProfile();


        profile.updateFile(cfg.app.filePath);
        Thread.sleep(5000);
        System.out.println("Resume uploaded successfully with PDF file");
        Thread.sleep(3000);
        System.out.println("Resume update again with docx file");
        profile.updateDocFile(cfg.app.DocFilePath);
        System.out.println("Docx resume uploaded successfully");
    }

}
