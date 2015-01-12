package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * GKislin
 * 09.01.2015.
 */
public class HashMapStorage extends AbstractStorage {
    public HashMapStorage() {
        logger = Logger.getLogger(HashMapStorage.class.getName());
    }

    Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume r) {
        if (map.get(r.getUuid()) != null) throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
        map.put(r.getUuid(), r);
    }

    @Override
    public void doClear() {
        map.clear();
    }

    @Override
    protected void doUpdate(Resume r) {
        Resume rr = map.get(r.getUuid());
        if (rr == null) throw new WebAppException("unable update, uuid not found!", r);
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume doLoad(String uuid) {
        Resume resume = map.get(uuid);
        if (resume == null) throw new WebAppException("uuid not found!");
        return resume;
    }

    @Override
    protected void doDelete(String uuid) {
        Resume resume = map.remove(uuid);
        if (resume == null) throw new WebAppException("uuid not found!");
    }

    @Override
    public Collection<Resume> getAllSorted() {
        Resume[] a=new Resume[size()];
        map.values().toArray(a);
        Arrays.sort(a);
        return Arrays.asList(a);
    }

    @Override
    public int size() {
        return map.size();
    }
}
