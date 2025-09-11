package io.demo.pages;

import io.demo.core.BasePage;
import io.demo.core.Config;
import io.demo.core.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("usernameField");
    private final By passwordField = By.id("passwordField");
    private final By loginBtn = By.xpath("//button[text()='Login']");
    public Config cfg;

    public LoginPage(WebDriver driver) {

        super(driver);
    }

    public LoginPage open() {
        //String url=cfg.app.baseUrl+cfg.app.loginPath;
        String url = ConfigLoader.load().app.baseUrl + ConfigLoader.load().app.loginPath;
        driver.get(url);
        return this;
    }

    public LoginPage typeUsername(String val) {
        waitVisible(usernameField).sendKeys(val);
        return this;
    }

    public LoginPage typePassword(String val) {
        waitVisible(passwordField).sendKeys(val);
        return this;
    }

    public void submit() {
        waitClickable(loginBtn).click();
    }

    @Override
    public boolean isAt() {

        return driver.findElement(usernameField).isDisplayed();

    }
}
