package ru.javawebinar.webapp.model;

/**
 * GKislin
 * 26.12.2014.
 */
public class TextSection extends Section{
    private static final long serialVersionUID = 1L;
    private String title;
    private String comment;


//    public TextSection(SectionType type) {
//        //super(type);
//    }

    public TextSection( String title, String comment) {
        //super(type);
        this.title = title;
        this.comment=comment;
    }

    @Override
    public String toString() {
        return title + ':'+ comment + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextSection)) return false;

        TextSection that = (TextSection) o;

        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
