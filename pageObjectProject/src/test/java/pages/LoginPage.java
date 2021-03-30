package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    public WebDriver driver;
    @FindBy(css = "form[action=\"/login/\"] input[name=\"email\"]")
    private WebElement email;
    @FindBy(css = "form[action=\"/login/\"] input[name=\"password\"]")
    private WebElement password;
    @FindBy(css = "form[action=\"/login/\"] button[type=\"submit\"]")
    private WebElement submitBtn;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void login(String emailValue, String passwordValue) {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(emailValue);
        password.sendKeys(passwordValue);
        submitBtn.click();
    }
}
