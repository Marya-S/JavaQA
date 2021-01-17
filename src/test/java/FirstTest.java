import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(FirstTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @After
    public void setDown(){
        if(driver != null){
            driver.quit();
            logger.info("Драйвер успешно закрыт");
        }
    }

    @Test
    public void openPage(){
        driver.get(cfg.url());
        logger.info("Страница открыта");
        Assert.assertTrue(existsElement());
        logger.info("Тест пройден");
    }

    private boolean existsElement() {
        try {
            driver.findElement(By.xpath("//title[contains(text(),'Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям')]"));
        } catch (NoSuchElementException e) {
            logger.error("Элемент не найден");
            return false;
        }
        return true;
    }


}
