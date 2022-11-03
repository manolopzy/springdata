package com.worldexplorer.springbootdatamysql.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class WebValidationErrorBuilder {
	public static WebValidationError fromBindingErrors(Errors errors) {
		WebValidationError error = new WebValidationError("Validationfailed. " + errors.getErrorCount() + " error(s)");
		for (ObjectError objectError : errors.getAllErrors()) {
			error.addValidationError(objectError.getDefaultMessage());
		}
		return error;
	}
}
