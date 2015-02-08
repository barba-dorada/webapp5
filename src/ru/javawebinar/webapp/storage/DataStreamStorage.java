package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by vad on 24.01.2015.
 */
public class DataStreamStorage extends FileStorage {

    public DataStreamStorage(String path) {
        super(path);
    }


    @Override
    public Resume read(FileInputStream is) throws IOException {
        try (DataInputStream ois = new DataInputStream(is)) {
            String uuid = readStr(ois);
            String fullName = readStr(ois);
            String location = readStr(ois);
            Resume r = new Resume(uuid, fullName, location);


            String delimiter = readStr(ois);
            if (!delimiter.equals("contacts")) throw new WebAppException("Storage, FormatError");

            readList(ois, () -> {
                        r.addContact(ContactType.valueOf(readStr(ois)), readStr(ois));
                        return null; // кривовато.
                    }
            );

            delimiter = readStr(ois);
            if (!"sections".equals(delimiter)) throw new WebAppException("Storage, FormatError");
            int count = ois.readInt();

            for (int i = 0; i < count; i++) {
                SectionType secType = SectionType.valueOf(readStr(ois));
                switch (secType) {

                    case OBJECTIVE:
                        String title = readStr(ois);
                        TextSection textSection = new TextSection(title);
                        r.addSection(secType, textSection);
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        MultiTextSection multiTextSection = new MultiTextSection();
                        multiTextSection.setValues(readList(ois, ois::readUTF));
                        r.addSection(secType, multiTextSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> orgList = readList(ois, () -> {
                                    String orgTitle = readStr(ois);
                                    String orgUrl = readStr(ois);
                                    List<Organization.Period> periods = readList(ois, () -> {
                                        Organization.Period p = new Organization.Period(
                                                readDate(ois),
                                                readDate(ois),
                                                readStr(ois),
                                                readStr(ois));
                                        return p;
                                    });
                                    return new Organization(new Link(orgTitle, orgUrl), periods);
                                }
                        );
                        OrganizationSection organizationSection = new OrganizationSection(orgList);
                        r.addSection(secType, organizationSection);
                        break;
                }
            }

            delimiter = readStr(ois);
            if (!"end".equals(delimiter)) throw new WebAppException("Storage, FormatError");

            return r;
        }
    }

    @Override
    public void write(Resume r, FileOutputStream out) throws IOException {

        try (DataOutputStream oout = new DataOutputStream(out)) {
            writeStr(oout, r.getUuid());
            writeStr(oout, r.getFullName());
            writeStr(oout, r.getLocation());
            writeStr(oout, "contacts");
            Map<ContactType, String> contacts = r.getContacts();

            writeCollection(oout, contacts.entrySet(), contact -> {
                writeStr(oout, contact.getKey().name());
                writeStr(oout, contact.getValue());
            });

            writeStr(oout, "sections");
            Map<SectionType, Section> sections = r.getSections();
            oout.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> es : sections.entrySet()) {
                SectionType key = es.getKey();
                Section section = es.getValue();
                writeStr(oout, key.name());
                switch (key) {
                    case OBJECTIVE:
                        TextSection textSection = (TextSection) section;
                        writeStr(oout, textSection.getValue());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(oout, ((MultiTextSection) section).getValues(), s -> writeStr(oout, s));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(oout, ((OrganizationSection) section).getValues(), org -> {
                            writeStr(oout, org.getLink().getName());
                            writeStr(oout, org.getLink().getUrl());
                            writeCollection(oout, org.getPeriods(), period -> {
                                writeDate(oout, period.getStartDate());
                                writeDate(oout, period.getEndDate());
                                writeStr(oout, period.getPosition());
                                writeStr(oout, period.getContent());
                            });
                        });
                        break;
                }
            }
            writeStr(oout, "end");
        }
    }


    private void writeDate(DataOutputStream os, LocalDate d) throws IOException {
        os.writeInt(d.getYear());
        writeStr(os, d.getMonth().name());
    }

    private LocalDate readDate(DataInputStream is) throws IOException {
        return LocalDate.of(is.readInt(), Month.valueOf(readStr(is)), 1);
    }


    private <T> List<T> readList(DataInputStream is, ElementReader<T> reader) throws IOException {
        int size = is.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private <T> void writeCollection(DataOutputStream os, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        os.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    void writeStr(DataOutputStream os, String str) throws IOException {
        if (str == null) str = "NULL";
        os.writeUTF(str);
    }

    String readStr(DataInputStream is) throws IOException {
        String s = is.readUTF();
        return "NULL".equals(s) ? null : s;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

}


