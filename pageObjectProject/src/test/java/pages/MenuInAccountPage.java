package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuInAccountPage {
    public WebDriver driver;


    public MenuInAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "div.nav.nav_mobile-fix.no-print.js-overflow-scroll a[title=\"О себе\"]")
    private WebElement aboutMeBtn;

    public void openAboutInformation() {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.visibilityOf(aboutMeBtn)).click();
    }
}
