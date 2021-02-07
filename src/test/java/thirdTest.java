import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class thirdTest {
    protected static WebDriver driver;
    private final Logger logger = LogManager.getLogger(thirdTest.class);
    private final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final String ITEM_FIRST = "ЗУБР";
    private final String ITEM_SECOND = "MAKITA";

    @Before
    public void setUp() {
        driver = WebDriverFactory.create(driverType.chrome, "start-maximized");
        logger.info("Драйвер поднят");
    }

    @After
    public void setDown() {
        if (driver != null) {
            //         driver.quit();
            logger.info("Драйвер успешно закрыт");
        }
    }

    @Test
    public void marketTest() {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        driver.get(cfg.url_volt());
        driver.findElement(By.cssSelector("a[title=\"Электроинструменты\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//h1[contains(text(),\"Электроинструменты\")]"))));
        WebElement perf = driver.findElement(By.cssSelector("a.box-block.divider.radius-3.mvspace-5.pspace-10.text-center[title=\"Перфораторы\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(perf)).click();
        deleteFrame();
        WebElement selector = driver.findElement(By.id("filterForm"));
        wait.until(ExpectedConditions.visibilityOf(selector));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selector);
        driver.findElement(By.cssSelector("input[title=" + ITEM_FIRST + "]")).click();
        driver.findElement(By.cssSelector("input[title=" + ITEM_SECOND + "]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.notifyQuantity.rounded5.hide>a"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.double-filter.mhbspace-10.pspace-10")));
        driver.findElement(By.cssSelector("span[role=\"combobox\"]")).click();
        WebElement selectElem = driver.findElement(By.cssSelector("select.listing-select.listing-select-sort.select2-hidden-accessible"));
        Select select = new Select(selectElem);
        select.selectByValue("1");
        List<WebElement> itemList = driver.findElements(By.cssSelector("div.new-item-list-name > a"));
        WebElement firstItem = findFirst(itemList, ITEM_FIRST);
        WebElement secondItem = findFirst(itemList, ITEM_SECOND);
        String firstItemTitle = firstItem.getAttribute("title");
        String secondItemTitle = secondItem.getAttribute("title");
        addItemToCompare(firstItem);
        deleteFrame();
        addItemToCompare(secondItem);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[id=\"cCountCompare\"]"))).click();
        deleteFrame();
        ArrayList<String> titleCompareItem = collectTitle();
        Assert.assertTrue(titleCompareItem.contains(firstItemTitle));
        Assert.assertTrue(titleCompareItem.contains(secondItemTitle));

    }

    private void addItemToCompare(WebElement addElement) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        StringBuffer compareSelector = new StringBuffer();
        compareSelector.append("//input[@id=\"compare-");
        compareSelector.append(getItemId(addElement));
        compareSelector.append("\"]/parent::span");
        driver.findElement(By.xpath(compareSelector.toString())).click();
        deleteFrame();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[role=\"dialog\"]"))));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("div.button.line.toCompare >a")))).click();
    }

    private WebElement findFirst(List<WebElement> itemList, String name) {
        List<String> itemNameList = new ArrayList();
        for (WebElement elem : itemList
        ) {
            itemNameList.add(elem.getAttribute("title"));
        }
        for (String nameItem : itemNameList) {
            if (nameItem.contains(name)) {
                return itemList.get(itemNameList.indexOf(nameItem));
            }
        }
        logger.error("Элементы не найдены");
        return null;
    }

    private String getItemId(WebElement element) {
        return element.getAttribute("data-code");
    }

    private ArrayList<String> collectTitle() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleIs("Сравнение товаров"));
        List<WebElement> itemCompareList = driver.findElements(By.cssSelector("div.title > a"));
        ArrayList<String> itemNameList = new ArrayList();
        for (WebElement elem : itemCompareList
        ) {
            itemNameList.add(elem.getAttribute("data-product-title"));
        }
        return itemNameList;
    }

    private void deleteFrame() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var iframes = document.querySelectorAll('iframe');" +
                "for (var i = 0; i < iframes.length; i++) { " +
                "iframes[i].parentNode.removeChild(iframes[i]);}");
    }
}
