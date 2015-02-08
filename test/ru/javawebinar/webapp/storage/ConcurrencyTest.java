package ru.javawebinar.webapp.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.LogManager;

/**
 * GKislin
 * 30.01.2015.
 */
public class ConcurrencyTest {
//    static {
//        try (FileInputStream logProps = new FileInputStream(new File("logging.properties"))) {
//            LogManager.getLogManager().readConfiguration(logProps);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testArrayStorage() throws Exception {
//        run(new ArrayStorage());
//    }
//
    @Test
    public void testSerializedFileStorage() throws Exception {
        run(new SerializedFileStorage("./datastore/os/"));
    }

//    @Test
//    public void testMapStorage() throws InterruptedException {//throws Exception {
//        run(new HashMapStorage());
//
//    }

    private void run(IStorage storage) throws InterruptedException {//throws Exception {
        for (int i = 1; i < 5000; i++) {
            new Thread(() -> {
                Resume r = new Resume("name", "location");
                storage.save(r);
                Assert.assertEquals(r,storage.load(r.getUuid()));
                storage.delete(r.getUuid());
                try {
                    storage.delete(r.getUuid());
                    Assert.fail("delete error!");
                }catch (WebAppException wae){
                    //all ok!
                }
                storage.getAllSorted();
            }).start();
        }
        Thread.sleep(5000);
        storage.clear();
    }
}
