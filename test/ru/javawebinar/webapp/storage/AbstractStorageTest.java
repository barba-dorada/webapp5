package ru.javawebinar.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.*;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * GKislin
 * 09.01.2015.
 */
public abstract class AbstractStorageTest {

    protected Resume R4;
    protected Resume R1;
    protected Resume R2;
    protected Resume R3;
    protected IStorage storage;

    @Before
    public void before() {
        R1 = new Resume("Полное Имя1", "location1");
        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        R2 = new Resume("Полное Имя2", "location2");
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        R3 = new Resume("Полное Имя3", "location3");
        R3.addSection(SectionType.OBJECTIVE,new TextSection("позиция 3","комент"));
        R3.addSection(SectionType.EXPERIENCE,new MultiTextSection("qqqq","wwwww"));
        R4 = new Resume("Полное Имя4", "location4");
        storage.clear();
        storage.save(R3);
        storage.save(R1);
        storage.save(R2);
    }

    @Test
    public void testClear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void testSave() {
        storage.save(R4);
        assertEquals(4, storage.size());
        assertEquals(R4, storage.load(R4.getUuid()));
    }

    @Test(expected = WebAppException.class)
    public void testSaveAlreadyExist() throws Exception {
        storage.save(R1);
    }

    @Test
    public void testUpdate() throws Exception {
        R2.setFullName("Updated N2");
        storage.update(R2);
        assertEquals(R2, storage.load(R2.getUuid()));
    }

    @Test
    public void testUpdateSection() throws Exception {
        R3.addSection(SectionType.OBJECTIVE,new TextSection("позиция 4","комент5"));
        R3.addSection(SectionType.EXPERIENCE,new MultiTextSection("new Exp","new Exp2"));
        storage.update(R3);
        assertEquals(R3, storage.load(R3.getUuid()));
    }

    @Test(expected = WebAppException.class)
    public void testUpdateNotFound() {
        storage.update(R4);
    }

    @Test
    public void testLoad() throws Exception {
        assertEquals(R1, storage.load(R1.getUuid()));
        assertEquals(R2, storage.load(R2.getUuid()));
        assertEquals(R3, storage.load(R3.getUuid()));
    }

    @Test(expected = WebAppException.class)
    public void testLoadNotFound() {
        storage.load("dummy");
    }

    @Test(expected = WebAppException.class)
    public void testDeleteNotFound() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void testGetAllSorted() throws Exception {
        Resume[] src = new Resume[]{R1, R2, R3};
        Arrays.sort(src);
        assertArrayEquals(src, storage.getAllSorted().toArray());
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }
}
