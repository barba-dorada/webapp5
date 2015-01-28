package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.model.Section;
import ru.javawebinar.webapp.model.SectionType;

import java.io.*;
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
        try( DataInputStream ois = new DataInputStream(is)) {
            String uuid = ois.readUTF();
            String fullName = ois.readUTF();
            String location = ois.readUTF();
            String delimiter = ois.readUTF();
            if (!delimiter.equals("contacts")) throw new WebAppException("Storage, FormatError");
            Resume r = new Resume(uuid, fullName, location);
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                r.addContact(ContactType.valueOf(ois.readUTF()), ois.readUTF());
            }
            delimiter = ois.readUTF();
            if (!"sections".equals(delimiter)) throw new WebAppException("Storage, FormatError");
            count = ois.readInt();

            for (int i = 0; i < count; i++) {
         //       r.addSection(SectionType.valueOf(ois.readUTF()), (Section) ois.readObject());
            }

            delimiter = ois.readUTF();
            if (!"end".equals(delimiter)) throw new WebAppException("Storage, FormatError");

            return r;
        }
    }

    @Override
    public void write(Resume r, FileOutputStream out) throws IOException {

        try(DataOutputStream oout = new DataOutputStream(out)) {

            oout.writeUTF(r.getUuid());
            oout.writeUTF(r.getFullName());
            oout.writeUTF(r.getLocation());
            oout.writeUTF("contacts");
            Map<ContactType, String> contacts = r.getContacts();
            oout.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> es : contacts.entrySet()) {
                oout.writeUTF(es.getKey().name());
                oout.writeUTF(es.getValue());
            }

            oout.writeUTF("sections");
            Map<SectionType, Section> sections = r.getSections();
            oout.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> es : sections.entrySet()) {
                oout.writeUTF(es.getKey().name());
                //oout.writeObject(es.getValue());
            }
            oout.writeUTF("end");
        }
    }
}


