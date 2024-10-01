package com.example.lab10.Constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UrlHttpsValidator implements ConstraintValidator<UrlHttpsDomain,String> {

    @Override
    public void initialize(UrlHttpsDomain constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        return url.startsWith("https://");
    }
}
