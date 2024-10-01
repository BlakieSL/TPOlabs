package com.example.lab10.Constraint;

import com.example.lab10.Injection;
import com.example.lab10.Repository.UrlRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UrlUniqueValidator implements ConstraintValidator<UrlUniqueDomain, String> {
    private UrlRepository urlRepository;
    private EntityManager entityManager;
    @Override
    public void initialize(UrlUniqueDomain constraintAnnotation) {
        entityManager = Injection.getBean(EntityManager.class);
        this.urlRepository = (UrlRepository) Injection.getBean(UrlRepository.class);
        ConstraintValidator.super.initialize(constraintAnnotation);

    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        try{
            entityManager.setFlushMode(FlushModeType.COMMIT);

           return !urlRepository.existsUrlByTargetUrl(url);
        }finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
        }
    }
}