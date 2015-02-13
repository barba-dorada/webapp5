package ru.javawebinar.webapp.web;

import ru.javawebinar.webapp.WebAppConfig;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.storage.IStorage;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by vad on 10.02.2015.
 */
public class ResumeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
          String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Resume r;
        IStorage storage = WebAppConfig.get().getStorage();

        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("list");
                return;
            case "create":
                r = new Resume();//Resume.EMPTY;
                break;
            case "view":
            case "edit":
                r = storage.load(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}
