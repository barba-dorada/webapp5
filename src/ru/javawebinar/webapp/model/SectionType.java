package ru.javawebinar.webapp.model;

import java.io.Serializable;

/**
 * GKislin
 * 26.12.2014.
 */
public enum SectionType implements Serializable{


    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование") ;



    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
