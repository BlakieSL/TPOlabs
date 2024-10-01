package com.example.lab10.Constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordSizeValidator implements ConstraintValidator<PasswordSizeDomain, String> {
    @Override
    public void initialize(PasswordSizeDomain constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if(password == null || password.isEmpty()){
            return true;
        }
        return password.length()>5;
    }
}
