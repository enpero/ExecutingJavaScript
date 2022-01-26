package homeWork;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseClass {
    WebDriver driver;

//    String login = System.getProperty("login");

    public final Logger logger = LogManager.getLogger(FillingInDataInTheGUI.class);

    String name = CssSelectors.NAME.getValue();
    String nameLatin = CssSelectors.NAME_LATIN.getValue();
    String surname = CssSelectors.SURNAME.getValue();
    String surnameLatin = CssSelectors.SURNAME_LATIN.getValue();
    String blogName = CssSelectors.BLOGNAME.getValue();
    String dateOfBirth = CssSelectors.DATE_OF_BIRTH.getValue();
    String company = CssSelectors.COMPANY.getValue();
    String post = CssSelectors.POST.getValue();
    String countryLabelRussia = CssSelectors.COUNTRY_LABEL_RUSSIA.getValue();
    String countryLabelMoscow = CssSelectors.COUNTRY_LABEL_MOSCOW.getValue();
    String englishLevel = CssSelectors.ENGLISH_LEVEL.getValue();
    String saveAndContinue = CssSelectors.SAVE_AND_CONTINUE.getValue();

    String getRussia = CssSelectors.GET_RUSSIA.getValue();
    String getMoscow = CssSelectors.GET_MOSCOW.getValue();
    String getEnglishLevel = CssSelectors.GET_ENGLISH_LEVEL.getValue();

    String setName = "Ekaterina";
    String setSurname = "Perova";
    String setDateOfBirth = "26.05.1996";

    String setEnglishLevel = "Начальный уровень (Beginner)";
    String setCity = "Москва";
    String setCountry = "Россия";

    String setCompany = "LLC 'INTER RAO-IT'";
    String setPost = "Quality Assurance engineer";

    @Before
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, SECONDS);
        logger.info("Драйвер поднят");
    }

    @After
    public void end() {
        if (driver != null)
            driver.quit();
    }
}
