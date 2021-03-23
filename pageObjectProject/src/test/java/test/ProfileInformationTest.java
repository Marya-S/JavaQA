package test;

import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AboutMe;
import pages.HomePage;
import pages.LoginPage;
import pages.MenuInAccountPage;
import utils.WebDriverFactory;
import utils.driverType;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ProfileInformationTest {
    protected static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(ProfileInformationTest.class);
    private static final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final String loginValue;
    private final String passwordValue;


    @BeforeClass
    public static void setUp() {
        driver = WebDriverFactory.create(driverType.chrome, "start-maximized");
        logger.info("Драйвер поднят");
        driver.get(cfg.url());
    }

    @After
    public void setDown() {
        if (driver != null) {
            //         driver.quit();
            logger.info("Драйвер успешно закрыт");
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"test_ivanov@internet.ru", "test_1234567"}
        });
    }


    public ProfileInformationTest(String loginValue, String passwordValue) {
        this.loginValue = loginValue;
        this.passwordValue = passwordValue;
    }


    @Test
    public void changeInformationAbout() {
        HomePage home = new HomePage(driver);
        home.pushEnterBtn();
        LoginPage login = new LoginPage(driver);
        login.login(loginValue, passwordValue);
        home.unrollAccountMenu();
        home.enterInAccount();
        MenuInAccountPage menuInAccount = new MenuInAccountPage(driver);
        menuInAccount.openAboutInformation();
        AboutMe aboutMe = new AboutMe(driver);
        aboutMe.addNewContact();
        aboutMe.chooseSkypeMethod();
        aboutMe.saveAndContinue();
    }
}
