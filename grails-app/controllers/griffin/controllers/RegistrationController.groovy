package griffin.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory;

class RegistrationController {
	def userService
	
	def index () { }
	
	def register () {
		RegisterForm form = new RegisterForm(); bindData form, params
		if (form.isValid()) {
			doRegister(form.username, form.password)
		} else {
			flash.formInvalid = true
			flash.message = "Double Check Your Inputs"
			redirect (action: 'index') /* go back to registration screen */
			return
		}
		redirect(action: 'success') /* go to success screen */
		return
	}
	
	def success () {
		
	}
	
	private def doRegister (String username, String password) {
		return userService.registerUser(username, password)
	}
	
	private static final Log log = LogFactory.getLog(this)
}

protected class RegisterForm {
	String username, password, passwordConfirm
	def isValid() {
		return (checkUsername() && checkPassword())
	}
	def checkUsername() {
		if (username == null) return false;
		if (username.isEmpty()) return false;
		return true;
	}
	def checkPassword() {
		if ( password == null || passwordConfirm == null ) return false;
		if ( ! password.equals(passwordConfirm) ) return false;
		return true; 
	}
}
