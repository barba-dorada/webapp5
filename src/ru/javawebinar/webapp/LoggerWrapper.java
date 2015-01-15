package ru.javawebinar.webapp;

import ru.javawebinar.webapp.model.Resume;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vad on 15.01.2015.
 * идея взята тут: https://github.com/gkislin/soa-ws/blob/master/common/src/main/java/com/github/gkislin/common/LoggerWrapper.java
 */


public class LoggerWrapper {

    private Logger logger;

    public LoggerWrapper(Logger logger) {
        this.logger = logger;
    }

    public static LoggerWrapper get(Class aClass) {
        return new LoggerWrapper(Logger.getLogger(aClass.getSimpleName()));
    }

    public void info(String msg) {
        logger.info( msg);
    }

    public void info(String msg, String uuid) {
        if (uuid != null) msg += " (uuid:" + uuid + ")";
        logger.info( msg);
    }

    public void warn(String msg) {
        logger.log(Level.WARNING, msg);
    }

    public void warn(String msg, Throwable t) {
        logger.log(Level.WARNING, msg, t);
    }

    public void error(Exception e) {
        logger.log(Level.SEVERE, "", e);
    }

    public void error(String msg) {
        logger.log(Level.SEVERE,  msg);
    }

    public void error(String msg, Throwable t) {
        logger.log(Level.SEVERE, msg, t);
    }

    public WebAppException getWebAppException(String msg) {
        error(msg);
        return new WebAppException(msg);
    }

    public WebAppException getWebAppException(String msg, Throwable e) {
        error(msg, e);
        return new WebAppException(msg, e);
    }

    public WebAppException getWebAppException(String msg, Resume resume) {
        String message = msg;
        //if(uuid!=null) message+=" (uuid:"+uuid+")";
        if (resume != null) message += " (uuid:" + resume.getUuid() + ")";
        error(message);
        return new WebAppException(message, resume);
    }

    public WebAppException getWebAppException(String msg, String uuid) {
        String message = msg;
        if (uuid != null) message += " (uuid:" + uuid + ")";
        //if(resume!=null) message+=" (uuid:"+r.getUuid()+")";
        error(message);
        return new WebAppException(message, uuid);
    }
}
