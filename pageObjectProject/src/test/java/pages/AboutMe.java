package pages;

import org.openqa.selenium.By;
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
    private WebElement addFirstContactBtn;
    @FindBy(xpath = "//input[@name=\"contact-0-service\"]/../div[@class=\"input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation\"]")
    private WebElement communicateMethodBtn;
    @FindBy(css = "button[name=\"continue\"]")
    private WebElement saveAndContinueBtn;
    @FindBy(xpath = "//input[@name=\"contact-0-value\"]")
    private WebElement inputFirstContactField;
    @FindBy(xpath = "//input[@name=\"contact-1-value\"]")
    private WebElement inputSecondContactField;

    public void addNewContact() {
        addFirstContactBtn.click();
    }

    public void chooseSkypeMethod() {
        communicateMethodBtn.click();
        communicateMethodBtn.findElement(By.xpath("//button[@title=\"Skype\"]")).click();
        inputFirstContactField.sendKeys("@skypeName");
    }

    public void chooseTelegramMethod(){
        communicateMethodBtn.click();
        communicateMethodBtn.findElement(By.xpath("//button[@title=\"Тelegram\"]")).click();
        inputSecondContactField.sendKeys("@testTelegramName");


    }

    public void saveAndContinue() {
        saveAndContinueBtn.click();
    }

}
