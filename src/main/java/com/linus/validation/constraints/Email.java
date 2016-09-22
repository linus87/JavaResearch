package com.linus.validation.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.linus.validation.validators.EmailValidator;

/**
 * The annotated element must be an email string.
 * Accepts any type.
 *
 * @author lyan2
 */
@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { EmailValidator.class })
public @interface Email {
	// must exist
	String message() default "{com.linus.validation.constraints.email}";
	
	// must exist
	Class<?>[] groups() default {};
	
	// must exist
	Class<? extends Payload>[] payload() default {};
}
