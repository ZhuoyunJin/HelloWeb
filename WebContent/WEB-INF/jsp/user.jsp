<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
	<%-- <jsp:include page="header.jsp" /> --%>
	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="body">
		<h3>${Name}</h3>
	</tiles:putAttribute>
	</tiles:insertDefinition>
	<!--  <h3><%=(String) session.getAttribute("xxx-token")%></h3>  -->
	<!--  <a href="admin/${id}/addTech">Add a technician</a> -->
</body>
</html>