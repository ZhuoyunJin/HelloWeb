<!-- <ul>
    <li>Title 1</li>
    <li>Title 2</li>
    <li>Title 3</li>
</ul> -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:importAttribute name="menu"/>
<div>
<ul>
    <c:forEach var="i" items="${menu}">
		<li><a href="${i.url}">${i.caption }</a></li>
 </c:forEach>
</div>