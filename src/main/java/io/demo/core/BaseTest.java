package io.demo.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected WebDriver driver;
    protected Config cfg;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        cfg = ConfigLoader.load();
        DriverFactory.createDriver(cfg);
        driver = DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
