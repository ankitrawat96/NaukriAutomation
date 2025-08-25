package DemoCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver createDriver() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // 🔑 Important: Create a unique Chrome profile each run
        try {
            Path tempProfile = Files.createTempDirectory("chrome-profile-");
            options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }



        return new ChromeDriver(options);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
