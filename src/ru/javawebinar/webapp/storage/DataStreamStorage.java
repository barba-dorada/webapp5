package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.model.Section;
import ru.javawebinar.webapp.model.SectionType;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by vad on 24.01.2015.
 */
public class DataStreamStorage extends AbstractStorage<File>{
    
    File dir;
    
    public DataStreamStorage(String path){
        dir=new File(path);
        //dir.isDirectory() 
    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        write(r, file);
    }

    private void write(Resume r, File file) {
        try(
        FileOutputStream out = new FileOutputStream(file);
        ObjectOutputStream oout = new ObjectOutputStream(out)){

            oout.writeUTF(r.getUuid());
            oout.writeUTF(r.getFullName());
            oout.writeUTF(r.getLocation());
            oout.writeUTF("contacts");
            Map<ContactType, String> contacts = r.getContacts();
            oout.writeInt(contacts.size());
            for(Map.Entry<ContactType,String> es:contacts.entrySet()){
                oout.writeUTF(es.getKey().name());
                oout.writeUTF(es.getValue());
            }

//            oout.writeChars("sections");
//            Map<SectionType, List<Section>> sections = r.getSections();
//            oout.writeInt(sections.size());
//            for(Map.Entry<SectionType,List<Section>> es:sections.entrySet()){
//                oout.writeChars(es.getKey().name());
//                oout.writeChars(es.getValue());
//            }
            oout.writeUTF("end");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doClear() {
        for(File f:dir.listFiles()){
            f.delete();
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        write(r,file);
    }

    @Override
    protected Resume doLoad(String uuid, File file) {
        // create an ObjectInputStream for the file we created before
        try(ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(file))){
            String uuid0=ois.readUTF();
            String fullName=ois.readUTF();
            String  location=ois.readUTF();
            String delimiter=ois.readUTF();
            if(!delimiter.equals("contacts"))  throw new WebAppException("Storage, FormatError");
            Resume r=new Resume(uuid0,fullName,location);
            int count=ois.readInt();
            for (int i = 0; i <count ; i++) {
                r.addContact(ContactType.valueOf(ois.readUTF()),ois.readUTF());
            }
            delimiter=ois.readUTF();
            if(!"end".equals(delimiter))  throw new WebAppException("Storage, FormatError");

            return r;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO add throw?
        return null;

    }

    @Override
    protected void doDelete(String uuid, File file) {
        file.delete();
    }

    @Override
    File getContext(String uuid) {
        return Paths.get(dir.getAbsolutePath(),uuid).toFile();
    }

    @Override
    public Collection<Resume> getAllSorted() {
//        Collection<Resume> resumes=new ArrayList<Resume>();

        List<Resume> resumes = new LinkedList<>();

        for(String fileName:dir.list()){
            Resume r=load(fileName);
            resumes.add(r);
        }
        Collections.sort(resumes);
        return resumes;

    }

    @Override
    public int size() {
        return dir.listFiles().length;
    }
}
