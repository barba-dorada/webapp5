package ru.javawebinar.webapp.model;

import java.util.*;

/**
 * gkislin
 * 12.12.2014.
 */
public final class Resume implements Comparable<Resume> {
    private final String uuid;
    private String fullName;
    private String location;
    //private String homePage;
    private Map<ContactType,String> contacts = new HashMap<>();
    private Map<SectionType,List<Section>> sections = new HashMap<>();

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
        sectionsInit();
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }
private void sectionsInit(){
    for(SectionType sectionType:SectionType.values()){
        sections.put(sectionType,new LinkedList<Section>());
    }

}
    public void addSection(SectionType type,Section section) {
        sections.get(type).add(section);
    }

//    public void addSection(Section section) {
//        sections.get(section.type).add(section);
//    }


    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
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

//    public String getHomePage() {
//        return homePage;
//    }

    public Map<ContactType,String> getContacts() {
        return contacts;
    }

    public  Map<SectionType,List<Section>> getSections() {
        return sections;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public void setHomePage(String homePage) {
//        this.homePage = homePage;
//    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Resume other = (Resume) obj;
        return other.uuid.equals(uuid);
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
            System.out.println("__"+sectionType.getTitle()+"__");
            List<Section> list = sections.get(sectionType);
            if(list.size()>0){
                for(Section s:list){
                    System.out.println(s);
                }

            }
        }
    }

    private void printContacts() {
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
