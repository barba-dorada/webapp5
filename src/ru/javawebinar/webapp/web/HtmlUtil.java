package ru.javawebinar.webapp.web;

import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

/**
 * User: gkislin
 * Date: 06.03.14
 */
public class HtmlUtil {

    public static String mask(String str) {
        return (str == null || str.isEmpty()) ? "&nbsp;" : str;
    }

    public static String getContact(Resume r, ContactType type) {
        String contact = r.getContacts().get(type);
        return contact == null ? "&nbsp;" : type.toHtml(contact);
    }
}
