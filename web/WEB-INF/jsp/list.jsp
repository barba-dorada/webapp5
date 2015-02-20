<%@ page import="ru.javawebinar.webapp.model.ContactType" %>
<%@ page import="ru.javawebinar.webapp.model.Resume" %>
<%@ page import="ru.javawebinar.webapp.storage.XmlFileStorage" %>
<%@ page import="ru.javawebinar.webapp.web.HtmlUtil" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ru.javawebinar.webapp.WebAppConfig" %>
<%@ page import="ru.javawebinar.webapp.storage.IStorage" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: adm
  Date: 11.02.2015
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>


<table>
    <tr>

        <td colspan="5" style="text-align: right"><a href="resume?action=create"><img src="img/add.png"> Добавить
            Резюме</a></td>
    </tr>
    <tr>
        <td>

            <table border="1" cellpadding="8" cellspacing="0">
                <tr>
                    <th>Имя</th>
                    <th>Проживание</th>
                    <th>Email</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>

                <%
                    IStorage storage = WebAppConfig.get().getStorage();
                    Collection<Resume> resumes = storage.getAllSorted();
                    request.setAttribute("resumeList", resumes);
                %>
                <c:forEach items="${resumeList}" var="resume">
                    <jsp:useBean id="resume" type="ru.javawebinar.webapp.model.Resume"/>
                    <tr>
                        <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                        <td><%=HtmlUtil.mask(resume.getLocation())%>
                        </td>
                        <td><%=HtmlUtil.getContact(resume, ContactType.MAIL)%>
                        </td>
                        <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a></td>
                        <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></td>
                    </tr>
                </c:forEach>




            </table>
        </td>
    </tr>
</table>


</body>
</html>
