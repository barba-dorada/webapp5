package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by vad on 26.01.2015.
 */
abstract public class FileStorage extends AbstractStorage<File> {
    File dir;

    public FileStorage(String path) {
        dir=new File(path);
    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        write(r, file);
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
        return read(file);
    }

    abstract public Resume read(File file);

    abstract public void write(Resume r, File file);


    @Override
    protected void doDelete(String uuid, File file) {
        file.delete();
    }

    @Override
    File getContext(String uuid) {
        return Paths.get(dir.getAbsolutePath(), uuid).toFile();
    }

    @Override
    public Collection<Resume> getAllSorted() {

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
