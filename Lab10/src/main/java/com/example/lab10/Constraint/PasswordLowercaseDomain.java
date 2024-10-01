package com.example.lab10.Constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordLowercaseValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordLowercaseDomain {
    String message() default "{com.example.lab10.PasswordLowercaseDomain.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
