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
			<br>Now, we put some custom content, to override the original one.....
	</tiles:putAttribute>
	</tiles:insertDefinition>
	<!--  <h2>${id}</h2>  -->
	<h3>${Name}</h3>
	<!--  <h3><%=(String) session.getAttribute("xxx-token")%></h3>  -->
	<!--  <a href="balance">balance</a>  -->
	<a href="admin/${id}/addTech">Add a technician</a>
	<a href="allTech">Manage</a>

</body>
</html>