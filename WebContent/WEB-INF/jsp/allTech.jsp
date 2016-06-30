<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All techs</title>
</head>
<body>
<jsp:include page="header.jsp" />
We have 
${list.size()}
technitians.
<br />
<c:forEach var="tech" items="${list}">
    <a href="getTech/${tech.UUID}"><c:out value="${tech.firstName}" /></a><br/>
</c:forEach>
</body>
</html>