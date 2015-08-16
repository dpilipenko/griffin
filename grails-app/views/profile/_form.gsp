<%@ page import="griffin.domain.Profile" %>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'displayName', 'error')} required">
	<label for="displayName">
		<g:message code="profile.displayName.label" default="Display Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="displayName" value="${profileInstance?.displayName?.encodeAsHTML()}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'tagline', 'error')} ">
	<label for="tagline">
		<g:message code="profile.tagline.label" default="Tagline" />
	</label>
	<g:textField name="tagline" value="${profileInstance.tagline}" />
</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="profile.user.label" default="User" />
		<%--<span class="required-indicator">*</span>--%>
	</label>
	${profileInstance?.user }
	<%--<g:select id="user" name="user.id" from="${griffin.domain.User.list()}" optionKey="id" required="" value="${profileInstance?.user?.id}" class="many-to-one"/>--%>

</div>

<div class="fieldcontain ${hasErrors(bean: profileInstance, field: 'army', 'error')} required">
	<label for="army">
		<g:message code="profile.army.label" default="Army" />
		<%--<span class="required-indicator">*</span>--%>
	</label>
	${profileInstance?.army }
	<%--<g:select id="army" name="army.id" from="${griffin.domain.Army.list()}" optionKey="id" required="" value="${profileInstance?.army?.id}" class="many-to-one"/>--%>

</div>
