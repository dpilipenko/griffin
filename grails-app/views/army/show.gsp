
<%@ page import="griffin.domain.Army" %>
<%@ page import="griffin.domain.AnimalTemplate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'army.label', default: 'Army')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-army" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-army" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list army">
				<g:if test="${armyInstance?.soldiers}">
				<li class="fieldcontain">
					<span id="soldiers-label" class="property-label"><g:message code="army.soldiers.label" default="Soldiers" /></span>
					<g:each in="${soldierMap}" var="soldier">
						<g:set var="grouping" value="${soldier.key.commonName }" />
						<g:set var="groupingCount" value="${soldier.value.size() }" />
						<span class="property-value" aria-labbledby="soldiers-label"><p>${grouping }: ${groupingCount }</p></span>
					</g:each>
				</li>
				</g:if>
			</ol>
			<g:form url="[resource:armyInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${armyInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
