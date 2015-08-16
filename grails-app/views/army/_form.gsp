<%@ page import="griffin.domain.Army" %>



<div class="fieldcontain ${hasErrors(bean: armyInstance, field: 'soldiers', 'error')} ">
	<label for="soldiers">
		<g:message code="army.soldiers.label" default="Soldiers" />
		
	</label>
	<g:select name="soldiers" from="${griffin.domain.Animal.list()}" multiple="multiple" optionKey="id" size="5" value="${armyInstance?.soldiers*.id}" class="many-to-many"/>

</div>

