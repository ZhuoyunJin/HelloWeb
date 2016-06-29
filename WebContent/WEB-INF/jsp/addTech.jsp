<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="command"  class="common.Technician" scope="request" ></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Tech</title>
<script type="text/javascript">
	function addTech() {
		var form = document.forms[0];
		form.action = "${pageContext.request.contextPath}/admin/submitTech";
		alert(form.action);
		//form.action = "${pageContext.request.contextPath}/user/addUser2";  
		//form.action = "${pageContext.request.contextPath}/user/addUser3";  
		form.method = "post";
		form.submit();
	}
</script>
</head>
<body>
	<form:form method="POST" commandName="xxx" action="submitTech">
	First Name
	<input type="text" name="firstName">
		<br /> Last Name
	<input type="text" name="lastName">
		<br />
		<%-- <form:select path="lov">
			<form:option value="NONE" label="--- Select ---" />
			<form:options items="${lov}" />
		</form:select> --%>
		<form:select path="title" items="${lov}">  
        </form:select>
		<input type="submit" Value="Submit" />
		<br />
	</form:form>
</body>
</html>