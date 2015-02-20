package ru.javawebinar.webapp.web;

import ru.javawebinar.webapp.model.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static ru.javawebinar.webapp.model.SectionType.*;

/**
 * Created by vad on 19.02.2015.
 */
public enum Format {

    TEXT {
        String editTemplate(SectionType type, Section section) {
            return format("<dl><dt>%s</dt><dd><input type='text' name='%s' size='100' value='%s'></dd></dl>",
                    type.getTitle(), type.name(), section.getContent());
        }

        void view2(StringBuffer sb, Section section) {
            sb.append(section.getContent());
        }
    },
    MULTI_TEXT {
        String editTemplate(SectionType type, Section section) {
            return format("<dl><dt>%s</dt><dd><textarea name='%s' cols='100' rows='5'>%s</textarea></dd></dl>",
                    type.getTitle(), type.name(), section.getContent());
        }

        @Override
        void view2(StringBuffer sb, Section section) {
            MultiTextSection multiTextSection = (MultiTextSection) section;
            sb.append("<div class='mtext'>");
            for (String s : multiTextSection.getValues()) {
                sb.append("<div class='mtrow'>").append(s).append("</div>");
            }
            sb.append("</div>");
        }
    },
    ORGANIZATION {
        @Override
        void view2(StringBuffer sb, Section section) {
            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy LLLL");
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
        }
    };


    private static Map<SectionType, Format> map = new HashMap<>();
    static {
        map.put(OBJECTIVE, TEXT);
        map.put(QUALIFICATIONS, MULTI_TEXT);
        map.put(ACHIEVEMENT, MULTI_TEXT);
        map.put(EDUCATION, ORGANIZATION);
        map.put(EXPERIENCE, ORGANIZATION);
    }

    public static String edit(SectionType type, Section section) {
        return map.get(type).editTemplate(type, section);
    }

    public static String view(SectionType type, Section section) {
        return map.get(type).viewTemplate(type, section);
    }

    String editTemplate(SectionType type, Section section) {
        return "";
    }

    String viewTemplate(SectionType type, Section section) {

        StringBuffer sb = new StringBuffer();

        sb.append(format("<div class='section'><div class='sectionname'><a name='%s'>%s</a></div><div class='sectioncontent'>",
                type.name(), type.getTitle()));

        view2(sb, section);

        sb.append("</div></div>");
        return sb.toString();
    }

    void view2(StringBuffer sb, Section section) {
    }


}
