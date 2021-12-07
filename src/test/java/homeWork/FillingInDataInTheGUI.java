package homeWork;

import io.github.bonigarcia.wdm.WebDriverManager;

import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class FillingInDataInTheGUI {
    WebDriver driver;
    private final Logger logger = LogManager.getLogger(FillingInDataInTheGUI.class);

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
    public void StartUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        sleep(10000);
        logger.info("Драйвер поднят");
    }

    /* Алгоритм действий
        1. Открыть сайт OTUS
        2. Авторизация
        3. Войти в личный кабинет
        4. Очистить данные личного кабинета
        5. В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        6. Нажать "Сохранить"
        7. Открыть https://otus.ru в “чистом браузере”
        8. Авторизоваться на сайте
        9. Войти в личный кабинет
        10. Проверка, что на вкладке 'О себе' отображаются указанные ранее данные
        */

    @Description("Авторизация, заполнение данных, проверка сохраненных данных")
    @Test
    public void fillingInDataTheGUIAndCheckingTheSaveData() throws InterruptedException {
        openOTUS();
        authorizationInOTUS();
        enterLK();
        clearingFields();
        aboutMe();
        saveChanges();
        openOTUSInCleanBrowser();
        authorizationInOTUS();
        enterLK();
        checkingTheSaveData();
    }

    @Description("Открытие сайта ОТУС")
    private void openOTUS() throws InterruptedException {
        driver.get("http://otus.ru");
        sleep(1000);
        logger.info("Сайт otus.ru открыт");
    }

    @Description("Авторизация на сайте ОТУС")
    private void authorizationInOTUS() {
        var setLoginAndPassword = "spbbrow@ya.ru";
        var authorization = CssSelectors.AUTHORIZATION.getValue();
        var email = CssSelectors.EMAIL.getValue();
        var password = CssSelectors.PASSWORD.getValue();
        var enter = CssSelectors.ENTER.getValue();

        driver.findElement(By.cssSelector(authorization)).click();
        driver.findElement(By.cssSelector(email)).sendKeys(setLoginAndPassword);
        driver.findElement(By.cssSelector(password)).sendKeys(setLoginAndPassword);
        driver.findElement(By.cssSelector(enter)).submit();
        logger.info("Успешная авторизация");
    }

    @Description("Переход на вкладку 'О себе', в личный кабинет")
    private void enterLK() throws InterruptedException {
        sleep(1000);
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Вкладка 'О себе' открыта");
    }

    @Description("Очистка полей на вкладке 'О себе'")
    private void clearingFields() throws InterruptedException {

        //Персональные данные
        driver.findElement(By.name(name)).clear();
        driver.findElement(By.name(nameLatin)).clear();
        driver.findElement(By.name(surname)).clear();
        driver.findElement(By.name(surnameLatin)).clear();
        driver.findElement(By.name(blogName)).clear();
        driver.findElement(By.name(dateOfBirth)).clear();
        sleep(1000);

        /* Основная информация*/
        if (driver.findElement(By.cssSelector(countryLabelRussia)).getText().contains(setCountry)) {
            driver.findElement(By.cssSelector(countryLabelRussia)).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Не указано')]")).click();
        }
        sleep(4000);

        //Другое
        driver.findElement(By.id(company)).clear();
        driver.findElement(By.id(post)).clear();
        sleep(4000);
        logger.info("Заполненные поля очищены");
    }

    @Description("Заполнение полей на вкладке 'О себе'")
    private void aboutMe() throws InterruptedException {
        setFieldsInPersonalData();
        setMainInformation();
        setContactInformation();
        setOther();
        logger.info("Все поле на вкладке 'О себе' заполнены");
    }

    @Description("Заполнение персональный данных")
    private void setFieldsInPersonalData() {

        driver.findElement(By.name(name)).sendKeys(setName);
        driver.findElement(By.name(nameLatin)).sendKeys(setName);
        driver.findElement(By.name(surname)).sendKeys(setSurname);
        driver.findElement(By.name(surnameLatin)).sendKeys(setSurname);
        driver.findElement(By.name(blogName)).sendKeys(setName + " " + setSurname);
        driver.findElement(By.name(dateOfBirth)).sendKeys(setDateOfBirth);
    }

    @Description("Заполнение блока Основной информации")
    private void setMainInformation() throws InterruptedException {
        chooseCountry();
        chooseCity();
        chooseEnglishLevel();
    }

    @Description("Выбор страны")
    private void chooseCountry() throws InterruptedException {
        if (!driver.findElement(By.cssSelector(countryLabelRussia)).getText().contains(setCountry)) {
            driver.findElement(By.cssSelector(countryLabelRussia)).click();
            driver.findElement(By.xpath(getRussia)).click();
        }
        logger.info("Страна выбрана");
        sleep(4000);
    }

    @Description("Выбор города")
    private void chooseCity() throws InterruptedException {
        driver.findElement(By.cssSelector(countryLabelMoscow)).click();
        driver.findElement(By.xpath(getMoscow)).click();
        sleep(4000);

        logger.info("Город выбран");
    }

    @Description("Выбор уровня английского языка")
    private void chooseEnglishLevel() {
        if (!driver.findElement(By.cssSelector(englishLevel)).getText().contains(setEnglishLevel)) {
            driver.findElement(By.cssSelector(englishLevel)).click();
            driver.findElement(By.xpath(getEnglishLevel)).click();
        }
        logger.info("Уровень английского языка выбран");
    }

    @Description("Заполнение блока 'Контактная информация'")
    private void setContactInformation() {
        if (driver.findElement(By.id("id_contact-0-value")).getAttribute("value").equals(blogName) & driver.findElement(By.id("id_contact-1-value")).getAttribute("value").equals(blogName)) {
            driver.findElement(By.xpath("//div[@class='container__col container__col_12 container__col_md-0']//button")).click();
            driver.findElement(By.xpath("//div[@data-num='1']//div[@class='container__col container__col_12 container__col_md-0']//button[last()]")).click();

            driver.findElement(By.xpath("//span[@class='placeholder']")).click();
            driver.findElement(By.cssSelector("button[title='Тelegram']")).click();
            driver.findElement(By.id("id_contact-0-value")).sendKeys(blogName);
            logger.info("Добавлен 1 контакт");

            driver.findElement(By.cssSelector("button[class='lk-cv-block__action lk-cv-block__action_md-no-spacing js-formset-add js-lk-cv-custom-select-add']")).click();
            driver.findElement(By.xpath("//span[@class='placeholder']")).click();
            driver.findElement(By.xpath("//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container']//button[last()]")).click();
            driver.findElement(By.id("id_contact-1-value")).sendKeys(blogName);
        }
        logger.info("Добавлено 2 контакта");
    }

    @Description("Заполнение блока 'Другое'")
    private void setOther() throws InterruptedException {

        driver.findElement(By.id("id_gender")).click();
        sleep(4000);
        driver.findElement(By.cssSelector("option[value='f']")).click();
        driver.findElement(By.id(company)).sendKeys(setCompany);
        driver.findElement(By.id(post)).sendKeys(setPost);
    }

    @Description("Нажать 'Сохранить'")
    private void saveChanges() throws InterruptedException {
        driver.findElement(By.xpath(saveAndContinue)).click();
        sleep(2000);
        logger.info("Заполненные данные - сохранены");
    }

    @Description("Открытие сайта ОТУС в 'чистом' браузере")
    private void openOTUSInCleanBrowser() throws InterruptedException {
        driver.quit();
        driver = new ChromeDriver();
        sleep(5000);
        openOTUS();
        logger.info("Открыт сайт ОТУС в чистом браузере");
    }

    @Description("Проверка, что на вкладке 'О себе' отображаются указанные ранее данные")
    private void checkingTheSaveData() {
        Assert.assertEquals(setName, driver.findElement(By.name(name)).getAttribute("value"));
        Assert.assertEquals(setName, driver.findElement(By.name(nameLatin)).getAttribute("value"));
        Assert.assertEquals(setSurname, driver.findElement(By.name(surname)).getAttribute("value"));
        Assert.assertEquals(setSurname, driver.findElement(By.name(surnameLatin)).getAttribute("value"));
        Assert.assertEquals(setName + " " + setSurname, driver.findElement(By.name(blogName)).getAttribute("value"));
        Assert.assertEquals(setDateOfBirth, driver.findElement(By.name(dateOfBirth)).getAttribute("value"));
        Assert.assertEquals(setCountry, driver.findElement(By.cssSelector(countryLabelRussia)).getText());
        Assert.assertEquals(setCity, driver.findElement(By.cssSelector(countryLabelMoscow)).getText());
        Assert.assertEquals(setEnglishLevel, driver.findElement(By.cssSelector(englishLevel)).getText());
        Assert.assertEquals(setCompany, driver.findElement(By.id(company)).getAttribute("value"));
        Assert.assertEquals(setPost, driver.findElement(By.id(post)).getAttribute("value"));
        logger.info("Все раннее заполненные данные успешно отображаются");
    }

    @After
    public void End() {
        if (driver != null)
            driver.quit();
    }
}
