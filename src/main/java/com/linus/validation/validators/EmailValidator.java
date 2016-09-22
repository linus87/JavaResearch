package com.linus.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.linus.validation.constraints.Email;

/**
 * 
 * @author lyan2
 */
public class EmailValidator implements ConstraintValidator<Email, String> {
	java.util.regex.Pattern pattern; 
	
	@Override
	public void initialize(Email constraintAnnotation) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		pattern = java.util.regex.Pattern.compile(ePattern);
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		java.util.regex.Matcher m = pattern.matcher(value);
        return m.matches();
	}

}
