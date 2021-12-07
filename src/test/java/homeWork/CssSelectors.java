package homeWork;

public enum CssSelectors {

    /*АТРИБУТЫ*/

    //Имя
    NAME("fname"),
    //Имя (латиницей)
    NAME_LATIN("fname_latin"),
    //Фамилия
    SURNAME("lname"),
    //Фамилия (латиницей)
    SURNAME_LATIN("lname_latin"),
    //Имя в блоге
    BLOGNAME("blog_name"),
    //Дата рождения
    DATE_OF_BIRTH("date_of_birth"),
    //Компания
    COMPANY("id_company"),
    //Должность
    POST("id_work"),


    /*ЛОКАТОРЫ*/
    AUTHORIZATION("button.header2__auth.js-open-modal"),
    EMAIL("div.new-input-line_slim:nth-child(3) > input:nth-child(1)"),
    PASSWORD(".js-psw-input"),
    ENTER("div.new-input-line_last:nth-child(5) > button:nth-child(1)"),
    COUNTRY_LABEL_RUSSIA(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)"),
    COUNTRY_LABEL_MOSCOW(".js-lk-cv-dependent-slave-city.js-lk-cv-custom-select"),
    GET_RUSSIA("//*[contains(text(), 'Россия')]"),
    ENGLISH_LEVEL("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)"),
    GET_MOSCOW("//*[contains(text(), 'Москва')]"),
    GET_ENGLISH_LEVEL("//*[contains(text(), 'Начальный уровень (Beginner)')]"),
    ADD_COMMUNICATION_METHOD("button[class='lk-cv-block__action lk-cv-block__action_md-no-spacing js-formset-add js-lk-cv-custom-select-add']"),
    DELETE_CONTACT("button[class='lk-cv-block__action lk-cv-block__action_md-no-spacing js-formset-delete']"),
    CHOOSE_COMMUNICATION_METHOD("//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container']//button[last()]"),
    SAVE_AND_CONTINUE("//*[contains(text(), 'Сохранить и продолжить')]");

    private String selector;


    CssSelectors(String selector) {
        this.selector = selector;
    }

    public  String getValue(){
        return selector;
    }
}
