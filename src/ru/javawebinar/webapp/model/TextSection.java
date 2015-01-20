package ru.javawebinar.webapp.model;

/**
 * GKislin
 * 26.12.2014.
 */
public class TextSection extends Section{
    private String title;
    private String comment;

    public TextSection(SectionType type) {
        super(type);
    }

    public TextSection(SectionType type, String title, String comment) {
        super(type);
        this.title = title;
        this.comment=comment;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
