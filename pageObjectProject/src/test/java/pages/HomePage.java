package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    public WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@data-modal-id=\"new-log-reg\"]")
    private WebElement enterButton;

    @FindBy(css = "div.header2-menu__item-wrapper.header2-menu__item-wrapper__username")
    private WebElement userNameRef;

    @FindBy(css = "a[title=\"Личный кабинет\"]")
    private WebElement enterInAccount;

    public void pushEnterBtn(){
        enterButton.click();
    }

    public void unrollAccountMenu(){
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.visibilityOf(userNameRef)).click();
    }

    public void enterInAccount(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(enterInAccount)).click();
    }

}
