package ru.javawebinar.webapp.storage;

/**
 * GKislin
 * 09.01.2015.
 */
public class SerializedFileStorageTest extends AbstractStorageTest {
    public SerializedFileStorageTest() {
        storage=  new SerializedFileStorage("./datastore/os/");
    }

}
