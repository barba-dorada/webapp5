package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.*;

/**
 * GKislin
 * 09.01.2015.
 */
public class HashMapStorage extends AbstractStorage<Resume> {
    public HashMapStorage() {
        NOT_FOUND_CTX=null;
    }

    Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume r,Resume ctx) {
        map.put(r.getUuid(), r);
    }

    @Override
    public void doClear() {
        map.clear();
    }

    @Override
    protected void doUpdate(Resume r,Resume ctx) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume doLoad(String uuid,Resume ctx) {
        return ctx;
    }

    @Override
    protected void doDelete(String uuid,Resume ctx) {
        map.remove(uuid);
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

    @Override
    Resume getContext(String uuid) {
        return map.get(uuid);
    }
}
