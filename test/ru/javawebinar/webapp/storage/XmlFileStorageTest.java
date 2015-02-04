package ru.javawebinar.webapp.storage;

public class XmlFileStorageTest extends AbstractStorageTest {
    {
        storage = new XmlFileStorage("./datastore/xml/");
    }

}