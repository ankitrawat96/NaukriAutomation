package io.demo.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return TL_DRIVER.get();
    }

    public static void createDriver(Config cfg) {
        String browser = System.getenv().getOrDefault("BROWSER", cfg.app.browser).toLowerCase();

        switch (browser) {
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                if (cfg.app.headless) options.addArguments("--headless=new");
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
                TL_DRIVER.set(new EdgeDriver(options));
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (cfg.app.headless) options.addArguments("-headless");
                TL_DRIVER.set(new FirefoxDriver(options));
            }
            default -> {
                ChromeOptions options = new ChromeOptions();
                if (cfg.app.headless)
                   // options.addArguments("--headless=new");
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
                options.addArguments("--remote-allow-origins=*");
                // isolate user data dir in CI
                //options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/chrome-profile-" + Thread.currentThread().getId());
                TL_DRIVER.set(new ChromeDriver(options));
            }
        }

        WebDriver driver = TL_DRIVER.get();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(cfg.app.implicitWaitSec));
        if (cfg.app.windowMaximize) driver.manage().window().maximize();
    }

    public static void quitDriver() {
        WebDriver driver = TL_DRIVER.get();
        if (driver != null) {
            driver.quit();
            TL_DRIVER.remove();
        }
    }
}
