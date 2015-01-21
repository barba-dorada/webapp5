package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;


/**
 * GKislin
 * 26.12.2014.
 */
public class ArrayStorage extends AbstractStorage<Integer> {
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
    protected void doSave(Resume r,Integer ctx) {
        array[size++] = r;
    }

    protected void doUpdate(Resume r,Integer ctx) {
        array[ctx] = r;
    }

    protected Resume doLoad(String uuid,Integer ctx) {
        return array[ctx];
    }

    protected void doDelete(String uuid,Integer ctx) {
        int numMoved = size - ctx - 1;
        if (numMoved > 0)
            System.arraycopy(array, ctx + 1, array, ctx,
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

    @Override
    Integer getContext(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                if (array[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    protected boolean exist(Integer ctx) {
        return !ctx.equals(-1);
    }
}
