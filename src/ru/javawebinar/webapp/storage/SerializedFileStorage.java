package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.io.*;

/**
 * Created by vad on 26.01.2015.
 */
public class SerializedFileStorage extends FileStorage {

    public SerializedFileStorage(String path) {
        super(path);
    }

    @Override
    public Resume read(FileInputStream fis) throws IOException {
        try (ObjectInputStream oin = new ObjectInputStream(fis)) {
            return (Resume) oin.readObject();
        } catch (ClassNotFoundException e) {
            throw logger.getWebAppException("",e);
        }
    }

    @Override
    public void write(Resume r, FileOutputStream out) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(r);
        }
    }
}
