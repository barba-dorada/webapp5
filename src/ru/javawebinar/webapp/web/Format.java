package ru.javawebinar.webapp.web;

import ru.javawebinar.webapp.model.*;

import java.time.format.DateTimeFormatter;

/**
 * Created by vad on 19.02.2015.
 */
public class Format {
    public static String edit(SectionType type, Section section) {
        switch (type) {
            case OBJECTIVE:
                return "<dl><dt>" + type.getTitle() + "</dt>" +
                        "<dd><input type='text' name='" + type.name() + "' size='100' " +
                        "value='" + section.getContent() + "'></dd></dl>";
            case QUALIFICATIONS:
            case ACHIEVEMENT:
                return "<dl><dt>" + type.getTitle() + "</dt>" +
                        "<dd><textarea name='" + type.name() + "' cols='100' rows='5'>" +
                        section.getContent() + "</textarea></dd></dl>";
            case EDUCATION:
            case EXPERIENCE:
                return "";
        }
        return "";
    }

    public static String view(SectionType type, Section section) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy LLLL");
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='section'>" +
                "<div class='sectionname'><a name='" + type.name() + "'>" + type.getTitle() + "</a></div>" +
                "<div class='sectioncontent'>");

        switch (type) {
            case OBJECTIVE:
                sb.append(section.getContent());
                break;
            case QUALIFICATIONS:
            case ACHIEVEMENT:
                MultiTextSection multiTextSection=(MultiTextSection)section;
                sb.append("<div class='mtext'>");
                for(String s:multiTextSection.getValues()){
                    sb.append("<div class='mtrow'>").append(s).append("</div>");
                }
                sb.append("</div>");
                break;

            case EDUCATION:
            case EXPERIENCE:
                OrganizationSection os = (OrganizationSection) section;
                for (Organization org : os.getValues()) {
                    sb.append("<div class='organization'>");
                    sb.append("<div class='orgname'><a href='" + org.getLink().getUrl() + "'>" + org.getLink().getName() + "</a></div>");

                    for (Organization.Period period : org.getPeriods()) {
                        sb.append("<div class='period'><div>");
                        sb.append(period.getStartDate().format(dtf)).append("-");
                        sb.append(period.getEndDate().equals(Organization.Period.NOW) ? "сейчас" : period.getEndDate().format(dtf)).append("</div>");
                        sb.append("<div>").append(period.getPosition()).append(":").append(period.getContent()).append("</div>");
                        sb.append("</div>");
                    }
                    sb.append("</div>");
                }
                break;
        }

        sb.append("</div></div>");
        return sb.toString();
    }
  /*      String div(String text,String clazz){
            return text;
        }
        StringBuilder div(StringBuilder sb, String clazz){
            return sb.insert(0,"<div class='"+clazz+"'>").append("</div>");
        }*/
}
