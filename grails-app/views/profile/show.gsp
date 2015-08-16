
<%@ page import="griffin.domain.Profile" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-profile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-profile" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list profile">
			
				<li class="fieldcontain">
					<span id="displayName-label" class="property-label"><g:message code="profile.displayName.label" default="Display Name" /></span>
					<span class="property-value" aria-labelledby="displayName-label">${profileInstance?.displayName?.encodeAsHTML()}</span>
				</li>
				
				
				<li class="fieldcontain">
					<span id="tagline-label" class="property-label"><g:message code="profile.tagline.label" default="Tagline" /></span>
					<span class="property-value" aria-labelledby="tagline-label">${profileInstance?.tagline?.encodeAsHTML()}</span>
				</li>
				
			
				<g:if test="${profileInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="profile.user.label" default="User" /></span>
					<span class="property-value" aria-labelledby="user-label">${profileInstance?.user?.encodeAsHTML()}</span>
				</li>
				</g:if>
			
				<g:if test="${profileInstance?.army}">
				<li class="fieldcontain">
					<span id="army-label" class="property-label"><g:message code="profile.army.label" default="Army" /></span>
					<span class="property-value" aria-labelledby="army-label"><g:link controller="army" action="show" id="${profileInstance?.army?.id}">${profileInstance?.army?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${profileInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
