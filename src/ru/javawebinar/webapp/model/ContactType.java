package ru.javawebinar.webapp.model;

import java.io.Serializable;

/**
 * GKislin
 * 26.12.2014.
 */
public enum ContactType implements Serializable{

    PHONE("Тел."),
    MOBILE("Мобильный"),
    HOME_PHONE("Домашний тел."),
    SKYPE("Skype"),
    MAIL("Почта"),
    ICQ("ICQ"),
    HOME_PAGE("домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
