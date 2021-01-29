import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SecondTest {
    protected static WebDriver driver;
    private final Logger logger = LogManager.getLogger(FirstTest.class);
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setUp() {
        // WebDriverManager.chromedriver().setup();
        //   ChromeOptions options = new ChromeOptions();
        //  options.addArguments("start-maximized");
        driver = WebDriverFactory.create(driverType.chrome, "start-maximized");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер успешно закрыт");
        }
    }

    @Test
    public void openYandex() {
        driver.get(cfg.url_yandex());
        logger.info("Страница открыта");
        Assert.assertEquals("Яндекс", driver.getTitle());
        logger.info("Тест пройден");
    }

    @Test
    public void searchInTele2() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get(cfg.url_tele2());
        logger.info("Страница Tele2 открыта");
        driver.findElement(By.id("searchNumber")).sendKeys("97");
        WebElement preloader_icon = driver.findElement(By.xpath("//div[@class=\"preloader-icon\"]"));
        wait.until(ExpectedConditions.stalenessOf(preloader_icon));
    }

}

