package com.virtyx.validation;

import java.util.List;

import com.virtyx.exception.ValidationError;

public class ValidationProperty {
	
	private Validation<?> parent;
	
	private String property;
	
	private ValidationAny<?, ?> type;
	
	public ValidationProperty(Validation<?> parent, String property) {
		this.parent = parent; 
		this.property = property;
	}
	
	public ValidationString string() {
		this.type = new ValidationString(this.parent);
		return (ValidationString) this.type;
	}
	
	public ValidationNumber number() {
		this.type = new ValidationNumber(this.parent);
		return (ValidationNumber) this.type;
	}
	
	public ValidationEnum enumm(Class<?> enumm) {
		this.type = new ValidationEnum(this.parent, enumm);
		return (ValidationEnum) this.type;
	}
	
	public List<ValidationError> validate(String key, Object value) {
		return this.type.validateValue(key, value);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}