package ru.javawebinar.webapp.storage;

/**
 * GKislin
 * 09.01.2015.
 */
public class DataStreamStorageTest extends AbstractStorageTest {
    public DataStreamStorageTest() {
        storage=  new DataStreamStorage("./datastore");
    }

}
