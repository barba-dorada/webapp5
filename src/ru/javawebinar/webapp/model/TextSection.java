package ru.javawebinar.webapp.model;

/**
 * GKislin
 * 26.12.2014.
 */
public class TextSection extends Section{
    private static final long serialVersionUID = 1L;
    private String title;
    //private String comment;


//    public TextSection(SectionType type) {
//        //super(type);
//    }


    public TextSection( String title) {
        //super(type);
        this.title = title;
      //  this.comment=comment;

    }

    public String getValue() {
        return title;
    }

    @Override
    public String toString() {
        return title + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextSection)) return false;

        TextSection that = (TextSection) o;


        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;

        return result;
    }
}
