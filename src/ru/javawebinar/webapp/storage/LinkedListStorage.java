package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vad on 10.01.2015.
 */
public class LinkedListStorage extends AbstractStorage<Integer> {

    List<Resume> list = new LinkedList<>();

    public LinkedListStorage() {
        NOT_FOUND_CTX = -1;
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
        Resume resume = new Resume(uuid, "", "");
        return list.indexOf(resume);
    }
}
