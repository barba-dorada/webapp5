<%@ page import="ru.javawebinar.webapp.model.Section" %>
<%@ page import="ru.javawebinar.webapp.model.SectionType" %>
<%@ page import="ru.javawebinar.webapp.web.Format" %>
<%@ page import="ru.javawebinar.webapp.web.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <STYLE type="text/css">

    </STYLE>
    <jsp:useBean id="resume" type="ru.javawebinar.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<section>
    <div class="resume">
        <h2>${resume.fullName}</h2>
        <%--    <c:if test="${not empty  resume.homePage}">
            Домашняя старница: ${resume.homePage}<br>
            </c:if>--%>
        <c:if test="${not empty  resume.location}">
            Проживание: ${resume.location}
        </c:if>

        <div class="contacts">
            <c:forEach var="type" items="${resume.contacts.keySet()}">
                <jsp:useBean id="type" type="ru.javawebinar.webapp.model.ContactType"/>
                <div class="contact"><%=HtmlUtil.getContact(resume, type)%>
                </div>
            </c:forEach>
        </div>

        <div class="sections">
            <c:forEach var="entry" items="${resume.sections.entrySet()}">
                <jsp:useBean id="entry" type="java.util.Map.Entry"/>
                <%
                    SectionType sectionType = ((SectionType) entry.getKey());
                    Section section = ((Section) entry.getValue());
                %>
                <%=Format.view(sectionType, section)%>

            </c:forEach>
        </div>
    </div>
    <button onclick="window.history.back()">ОК</button>
</section>
</body>
</html>
