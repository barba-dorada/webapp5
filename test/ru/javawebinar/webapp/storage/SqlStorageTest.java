package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppConfig;

public class SqlStorageTest extends AbstractStorageTest {
    {
        storage = new SqlStorage("jdbc:postgresql://localhost:5432/resumeDB","user1","user1");
    }

}