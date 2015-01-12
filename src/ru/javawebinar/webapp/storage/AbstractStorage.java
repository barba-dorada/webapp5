package ru.javawebinar.webapp.storage;

import ru.javawebinar.webapp.WebAppException;
import ru.javawebinar.webapp.model.Resume;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GKislin
 * 09.01.2015.
 */
abstract public class AbstractStorage implements IStorage {
    protected Logger logger = Logger.getLogger("---");

    @Override
    public void save(Resume r) {
        logger.info("Save resume with uuid=" + r.getUuid());
        // TODO try to move here exception treatment
        try {
            doSave(r);
        } catch (WebAppException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    protected abstract void doSave(Resume r);

    @Override
    public void clear() {
        logger.info("Delete all resumes.");
        doClear();

    }

    protected abstract void doClear();

    @Override
    public void update(Resume r) {
        logger.info("Update resume with " + r.getUuid());
        try {
            doUpdate(r);
        } catch (WebAppException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    protected abstract void doUpdate(Resume r);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume with uuid=" + uuid);

        try {
            return doLoad(uuid);
        } catch (WebAppException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    protected abstract Resume doLoad(String uuid);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid=" + uuid);



        try {
            doDelete(uuid);
        } catch (WebAppException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw e;
        }


    }

    protected abstract void doDelete(String uuid);

}
