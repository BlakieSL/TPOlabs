package com.example.lab10.Constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordSpecialValidator implements ConstraintValidator<PasswordSpecialDomain, String> {
    @Override
    public void initialize(PasswordSpecialDomain constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isEmpty()) {
            return true;
        }
        return password.matches(".*[^a-zA-Z0-9].*[^a-zA-Z0-9].*[^a-zA-Z0-9].*[^a-zA-Z0-9].*");
    }
}
