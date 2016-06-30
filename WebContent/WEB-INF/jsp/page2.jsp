<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">

	<tiles:putAttribute name="body">
		<br>Now, we put some custom content, to override the original one.....
	</tiles:putAttribute>
	
</tiles:insertDefinition>