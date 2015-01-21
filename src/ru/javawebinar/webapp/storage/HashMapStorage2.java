package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.*;

/**
 * GKislin
 * 09.01.2015.
 */
public class HashMapStorage2 extends AbstractStorage<String> {
    public HashMapStorage2() {
    }

    Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume r,String ctx) {
        map.put(ctx, r);
    }

    @Override
    public void doClear() {
        map.clear();
    }

    @Override
    protected void doUpdate(Resume r,String ctx) {
        map.put(ctx, r);
    }

    @Override
    protected Resume doLoad(String uuid,String ctx) {
        return map.get(ctx);
    }

    @Override
    protected void doDelete(String uuid,String ctx) {
        map.remove(ctx);
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
    String getContext(String uuid) {
        return uuid;
    }

    @Override
    protected boolean exist(String ctx) {
        return map.containsKey(ctx);
    }
}
