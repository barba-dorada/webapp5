package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
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
    public Resume read(File file) {

        try(FileInputStream fis = new FileInputStream(file);
        ObjectInputStream oin = new ObjectInputStream(fis)){
            Resume r = (Resume) oin.readObject();
            return r;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new WebAppException("IO");
    }

    @Override
    public void write(Resume r, File file) {

        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(r);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
