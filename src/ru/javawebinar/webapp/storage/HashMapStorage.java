package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.*;

/**
 * GKislin
 * 09.01.2015.
 */
public class HashMapStorage extends AbstractStorage {
    public HashMapStorage() {
        //logger = Logger.getLogger(HashMapStorage.class.getName());
    }

    Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume r) {
        if (map.get(r.getUuid()) != null) throw logger.getWebAppException("Resume already exist", r);
        map.put(r.getUuid(), r);
    }

    @Override
    public void doClear() {
        map.clear();
    }

    @Override
    protected void doUpdate(Resume r) {
        Resume rr = map.get(r.getUuid());
        if (rr == null) throw logger.getWebAppException("unable update, uuid not found!", r);
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume doLoad(String uuid) {
        Resume resume = map.get(uuid);
        if (resume == null) throw logger.getWebAppException("uuid not found!",uuid);
        return resume;
    }

    @Override
    protected void doDelete(String uuid) {
        Resume resume = map.remove(uuid);
        if (resume == null) throw logger.getWebAppException("uuid not found!",uuid);
    }

    @Override
    public Collection<Resume> getAllSorted() {
        List<Resume> list=new LinkedList<Resume>(map.values());
        Collections.sort(list);
        return list;
    }

    @Override
    public int size() {
        return map.size();
    }
}
