package com.example.lab10.Constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordLowercaseValidator implements ConstraintValidator<PasswordLowercaseDomain, String> {
    @Override
    public void initialize(PasswordLowercaseDomain constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isEmpty()) {
            return true;
        }
        return password.matches(".*[a-z].*");
    }
}
