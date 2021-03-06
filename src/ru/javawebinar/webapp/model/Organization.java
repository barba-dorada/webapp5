package ru.javawebinar.webapp.model;

import ru.javawebinar.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 19.12.2014.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link link;
    private List<Period> periods;

    public Organization() {
        periods = new ArrayList<>();
    }

    public Organization(Link link, List<Period> periods) {
        this.link = link;
        this.periods = periods;
    }

    public Organization(Link link, Period... periods) {
        this.link = link;
        this.periods = Arrays.asList(periods);
    }

    public Organization(String s1, String s2, Period... periods) {
        this.link = new Link(s1, s2);
        this.periods = Arrays.asList(periods);
    }

/*    public String toHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='organization'>");
        sb.append("<div class='orgname'><a href='" + link.getUrl() + "'>" + link.getName() + "</a></div>");
        for (Period period : periods) {
            sb.append(period.toHtml());
        }
        sb.append("</div>");
        return sb.toString();
    }*/

    public Link getLink() {
        return link;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        Organization that = (Organization) o;

        if (!link.equals(that.link)) return false;
        if (!periods.equals(that.periods)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = link.hashCode();
        result = 31 * result + periods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link=" + link +
                ", periods=" + periods +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        public static final LocalDate NOW = LocalDate.of(3000, 1, 1);
        static final long serialVersionUID = 1L;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String position;
        private String content;

        public Period() {
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String position, String content) {
            this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(endYear, endMonth, 1), position, content);
        }

        public Period(LocalDate startDate, LocalDate endDate, String position, String content) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.content = content;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getPosition() {
            return position;
        }

        public String getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Period)) return false;

            Period period = (Period) o;

            if (content != null ? !content.equals(period.content) : period.content != null) return false;
            if (!endDate.equals(period.endDate)) return false;
            if (position != null ? !position.equals(period.position) : period.position != null) return false;
            if (!startDate.equals(period.startDate)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + (position != null ? position.hashCode() : 0);
            result = 31 * result + (content != null ? content.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Period{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", position='" + position + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
