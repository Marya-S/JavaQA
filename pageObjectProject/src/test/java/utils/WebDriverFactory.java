package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class WebDriverFactory {
    public static WebDriver create(driverType webDriverName) {
        WebDriver driver = null;
        switch (webDriverName) {
            case firefox:
                driver = new FirefoxDriver();
                break;
            case chrome:
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }

    public static WebDriver create(driverType webDriverName, String option) {
        WebDriver driver = null;
        switch (webDriverName) {
            case firefox:
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments(option);
                driver = new FirefoxDriver(options);
                break;
            case chrome:
                ChromeOptions options1 = new ChromeOptions();
                options1.addArguments(option);
                driver = new ChromeDriver(options1);
                break;
        }
        return driver;

    }

}
