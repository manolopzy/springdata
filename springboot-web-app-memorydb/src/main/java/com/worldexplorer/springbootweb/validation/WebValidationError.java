package com.worldexplorer.springbootweb.validation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class holds any errors that arise during any requests. 
 * @JsonInclude annotation says that 
 * even if the errors field is empty, it must be included.
 */
public class WebValidationError {
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final List<String> errors = new ArrayList<>();
	
	private final String errorMessage;

	public WebValidationError(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void addValidationError(String error) {
		errors.add(error);
	}

	public List<String> getErrors() {
		return errors;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
