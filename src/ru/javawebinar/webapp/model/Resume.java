package ru.javawebinar.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * gkislin
 * 12.12.2014.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Resume implements Comparable<Resume>,Serializable {

    private static final long serialVersionUID = 1L;

    private final String uuid;
    private String fullName;
    private String location;
    private Map<ContactType,String> contacts =new EnumMap<>(ContactType.class);
    private Map<SectionType,Section> sections = new EnumMap<>(SectionType.class);

    public Resume(){
        this("","");
    }

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }
    public void addSection(SectionType type,Section section) {
        sections.put(type, section);
    }
    public Section getSection(SectionType type){
        return sections.get(type);
    }

    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
    }
    public String getContact(ContactType type){
        return contacts.get(type);
    }


    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public Map<ContactType,String> getContacts() {
        return contacts;
    }

    public  Map<SectionType,Section> getSections() {
        return sections;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        if (!fullName.equals(resume.fullName)) return false;

        if (!contacts.equals(resume.contacts)) return false;
        if (!location.equals(resume.location)) return false;
        if (sections != null ? !sections.equals(resume.sections) : resume.sections != null) return false;

        return true;
    }

    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName);
    }
    public void print(){
        printHeader();
        printContacts();
        printSections();
    }

    private void printSections() {
        for(SectionType sectionType:SectionType.values()){
            System.out.println("=="+sectionType.getTitle()+"===================");
            Section section = sections.get(sectionType);
                    System.out.print(section);
            }
    }

    private void printContacts() {
        System.out.println("==контакты===================");
        for (ContactType contactType:contacts.keySet()) {
            String val=contacts.get(contactType);
            if (val != null) {
                System.out.println(contactType.getTitle()+":"+val);
            }
        }
    }

    private void printHeader() {
        System.out.println( "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' );
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }
}
