package com.example.lab10.Constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordSizeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordSizeDomain {
    String message() default "{com.example.lab10.PasswordSizeDomain.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
