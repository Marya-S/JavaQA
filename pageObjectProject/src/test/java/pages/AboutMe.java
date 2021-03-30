package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutMe {
    public WebDriver driver;

    public AboutMe(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add.js-lk-cv-custom-select-add")
    private WebElement addContactBtn;
    @FindBy(xpath = "//div[@data-num=\"0\"]//div[@class=\"select lk-cv-block__input lk-cv-block__input_3 lk-cv-block__input_md-4 js-lk-cv-custom-select\"]")
    private WebElement communicateFirstMethodBtn;
    @FindBy(xpath = "//div[@data-num=\"1\"]//div[@class=\"select lk-cv-block__input lk-cv-block__input_3 lk-cv-block__input_md-4 js-lk-cv-custom-select\"]")
    private WebElement communicateSecondMethodBtn;
    @FindBy(xpath = "//div[@data-num=\"0\"]//div[@class=\"select lk-cv-block__input lk-cv-block__input_3 lk-cv-block__input_md-4 js-lk-cv-custom-select\"]//button[@title=\"Skype\"]")
    private WebElement skype;
    @FindBy(xpath = "//div[@data-num=\"1\"]//div[@class=\"select lk-cv-block__input lk-cv-block__input_3 lk-cv-block__input_md-4 js-lk-cv-custom-select\"]//button[@title=\"Тelegram\"]")
    private WebElement telegram;
    @FindBy(css = "button[name=\"continue\"]")
    private WebElement saveAndContinueBtn;
    @FindBy(xpath = "//input[@name=\"contact-0-value\"]")
    private WebElement inputFirstContactField;
    @FindBy(xpath = "//input[@name=\"contact-1-value\"]")
    private WebElement inputSecondContactField;
    @FindBy(xpath = "//div[@data-num=\"0\"]//div[@class=\"container__col container__col_12 container__col_md-0\"]//button[@class=\"lk-cv-block__action lk-cv-block__action_md-no-spacing js-formset-delete\"]")
    private WebElement deleteFirst;
    @FindBy(xpath = "//div[@data-num=\"1\"]//div[@class=\"container__col container__col_12 container__col_md-0\"]//button[@class=\"lk-cv-block__action lk-cv-block__action_md-no-spacing js-formset-delete\"]")
    private WebElement deleteSecond;

    public void addNewContact() {
        addContactBtn.click();
    }

    public void chooseSkypeMethod(String skypeName) {
        communicateFirstMethodBtn.click();
        skype.click();
        inputFirstContactField.sendKeys(skypeName);
    }

    public void chooseTelegramMethod(String testTelegramName) {
        communicateSecondMethodBtn.click();
        telegram.click();
        inputSecondContactField.sendKeys(testTelegramName);


    }

    public void saveAndContinue() {
        saveAndContinueBtn.click();
    }

    public String getFirstContact() {
        return inputFirstContactField.getAttribute("value");
    }

    public String getSecondContact() {
        return inputSecondContactField.getAttribute("value");
    }

    public void deleteFirst() {
        deleteFirst.click();
    }

    public void deleteSecond() {
        deleteSecond.click();
    }

}
