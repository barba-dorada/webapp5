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
        //dir.isDirectory()
    }

    @Override
    public Resume read(File file) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(file))) {
            String uuid0 = ois.readUTF();
            String fullName = ois.readUTF();
            String location = ois.readUTF();
            String delimiter = ois.readUTF();
            if (!delimiter.equals("contacts")) throw new WebAppException("Storage, FormatError");
            Resume r = new Resume(uuid0, fullName, location);
            int count = ois.readInt();
            for (int i = 0; i < count; i++) {
                r.addContact(ContactType.valueOf(ois.readUTF()), ois.readUTF());
            }
            delimiter = ois.readUTF();
            if (!"sections".equals(delimiter)) throw new WebAppException("Storage, FormatError");
            count=ois.readInt();

            for (int i = 0; i < count; i++) {
                r.addSection(SectionType.valueOf(ois.readUTF()),(Section) ois.readObject());
            }




            delimiter = ois.readUTF();
            if (!"end".equals(delimiter)) throw new WebAppException("Storage, FormatError");

            return r;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO add throw?
        return null;
    }

    @Override
    public void write(Resume r, File file) {
        try (
                FileOutputStream out = new FileOutputStream(file);
                ObjectOutputStream oout = new ObjectOutputStream(out)) {

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
            for(Map.Entry<SectionType,Section> es:sections.entrySet()){
                oout.writeUTF(es.getKey().name());
                oout.writeObject(es.getValue());
            }
            oout.writeUTF("end");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
