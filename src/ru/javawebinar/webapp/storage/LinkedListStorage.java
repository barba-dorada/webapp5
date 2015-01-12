package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.*;

/**
 * Created by vad on 10.01.2015.
 */
public class LinkedListStorage extends AbstractStorage {

    List<Resume> list = new LinkedList<>();

    @Override
    protected void doSave(Resume r) {
        if (list.contains(r)) throw new WebAppException("uuid already exist!");
        list.add(r);
    }

    @Override
    protected void doClear() {
        list.clear();
    }

    @Override
    protected void doUpdate(Resume r) {
        int idx = list.indexOf(r);
        if (idx == -1) throw new WebAppException("uuid not found!", r.getUuid());
        list.set(idx, r);
    }

    @Override
    public Resume doLoad(String uuid) {
        Resume resume = new Resume(uuid, "", "");
        int idx = list.indexOf(resume);
        if (idx == -1) throw new WebAppException("uuid not found!", uuid);
        return list.get(idx);
    }

    @Override
    protected void doDelete(String uuid) {
        Resume r = load(uuid);
        list.remove(r);
    }

    @Override
    public Collection<Resume> getAllSorted() {
        List<Resume> sList=new LinkedList<>(list);
        Collections.sort(sList);
        return sList;
    }

    @Override
    public int size() {
        return list.size();
    }
}
