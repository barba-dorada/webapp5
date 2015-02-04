package ru.javawebinar.webapp.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.lang.Exception;import java.lang.String;import java.time.LocalDate;

/**
 * Created by vad on 04.02.2015.
 */

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }

}