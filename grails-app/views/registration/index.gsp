<html>
<head>
	<title>Register</title>
</head>
<body>
	<div>
		<g:form name="formRegister" url="[controller:'registration', action:'register']">
			<g:textField name="username" />
			<g:passwordField name="password" />
			<g:passwordField name="passwordConfirm" />
			<g:actionSubmit value="Register" action="register"/>
		</g:form>
		<g:if test="${flash.formInvalid }">
			<p>${flash.message }</p>
		</g:if>
	</div>
</body>
</html>