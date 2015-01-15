package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;


/**
 * GKislin
 * 26.12.2014.
 */
public class ArrayStorage extends AbstractStorage {
    private static final int LIMIT = 100;

    private Resume[] array = new Resume[LIMIT];
    private int size = 0;

    public ArrayStorage() {
        super();
    }



    protected void doClear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    protected void doSave(Resume r) {
        int idx = getIndex(r.getUuid());
        if (idx != -1) throw logger.getWebAppException("Resume already exist", r);
        array[size++] = r;
    }

    protected void doUpdate(Resume r) {
        int idx = getIndex(r.getUuid());
        if (idx == -1) throw logger.getWebAppException("Resume not exist", r);
        array[idx] = r;
    }

    protected Resume doLoad(String uuid) {
        int idx = getIndex(uuid);
        if (idx == -1) throw logger.getWebAppException("Resume not exist",uuid);
        return array[idx];
    }

    protected void doDelete(String uuid) {
        int idx = getIndex(uuid);
        if (idx == -1) throw logger.getWebAppException("Resume not exist",uuid);
        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(array, idx + 1, array, idx,
                    numMoved);
        array[--size] = null; // clear to let GC do its work
    }

    @Override
    public Collection<Resume> getAllSorted() {
        // TODO via comparator
        Arrays.sort(array, 0, size);
        return Arrays.asList(Arrays.copyOf(array, size));
    }

    @Override
    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                if (array[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
