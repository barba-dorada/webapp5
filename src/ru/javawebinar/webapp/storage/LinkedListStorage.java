package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.*;

/**
 * Created by vad on 10.01.2015.
 */
public class LinkedListStorage extends AbstractStorage<Integer> {

    List<Resume> list = new LinkedList<>();

    public LinkedListStorage() {
    }

    @Override
    protected void doSave(Resume r, Integer ctx) {
        list.add(r);
    }

    @Override
    protected void doClear() {
        list.clear();
    }

    @Override
    protected void doUpdate(Resume r, Integer ctx) {
        list.set(ctx, r);
    }

    @Override
    public Resume doLoad(String uuid, Integer ctx) {
        return list.get(ctx);
    }

    @Override
    protected void doDelete(String uuid, Integer ctx) {
        list.remove(ctx.intValue());
    }

    @Override
    public Collection<Resume> getAllSorted() {
        List<Resume> sList = new LinkedList<>(list);
        Collections.sort(sList);
        return sList;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    Integer getContext(String uuid) {
        for (int i = 0; i <size() ; i++) {
            if(list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean exist(Integer ctx) {
        return !ctx.equals(-1);
    }
}
