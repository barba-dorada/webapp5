package ru.javawebinar.webapp;


import ru.javawebinar.webapp.model.Resume;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: gkislin
 * Date: 18.04.2014
 */
public class WebAppException extends RuntimeException {
    private Resume resume = null;
    private String uuid = null;

    static Logger logger = Logger.getLogger("WebAppException");

    private void log(){
        String message=getMessage();
        if(uuid!=null) message+="(uuid:"+uuid+")";
        if(resume!=null) message+="(uuid:"+resume.getUuid()+")";
        logger.log(Level.SEVERE,message);
    }

    public WebAppException(String message) {
        super(message);
        log();
    }

    public WebAppException(String message, Throwable e) {
        super(message, e);
        log();
    }

    public WebAppException(String message, Resume resume) {
        super(message);
        this.resume = resume;
        log();
    }

    public WebAppException(String message, Resume resume, Throwable e) {
        super(message, e);
        this.resume = resume;
        log();
    }

    public WebAppException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
        log();
    }

    public Resume getResume() {
        return resume;
    }

    public String getUuid() {
        return uuid;
    }
}
