package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.LoggerWrapper;
import ru.javawebinar.webapp.model.Resume;

/**
 * GKislin
 * 09.01.2015.
 */
abstract public class AbstractStorage<T> implements IStorage {
    protected LoggerWrapper logger = LoggerWrapper.get(getClass());

    @Override
    public void save(Resume r) {
        logger.info("Save resume", r.getUuid());
        T ctx = getContext(r.getUuid());
        if (exist(ctx)) throw logger.getWebAppException("Resume already exist", r);
        doSave(r, ctx);
    }

    abstract protected boolean exist(T ctx);

    protected abstract void doSave(Resume r, T ctx);

    @Override
    public void clear() {
        logger.info("Delete all resumes.");
        doClear();
    }

    protected abstract void doClear();

    @Override
    public void update(Resume r) {
        logger.info("Update resume", r.getUuid());
        T ctx = getContext(r.getUuid());
        if (!exist(ctx)) throw logger.getWebAppException("Resume not exist", r);
        doUpdate(r, ctx);
    }

    protected abstract void doUpdate(Resume r, T ctx);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume", uuid);
        T ctx = getContext(uuid);
        if (!exist(ctx)) throw logger.getWebAppException("Resume not exist", uuid);
        return doLoad(uuid, ctx);
    }

    protected abstract Resume doLoad(String uuid, T ctx);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume", uuid);
        T ctx = getContext(uuid);
        if (!exist(ctx)) throw logger.getWebAppException("Resume not exist", uuid);
        doDelete(uuid, ctx);
    }

    protected abstract void doDelete(String uuid, T ctx);

    abstract T getContext(String uuid);

    @Override
    public boolean isSectionSupported() {
        return true;
    }

    @Override
    public boolean isContactSupported() {
        return true;
    }
}
