package com.gamestore.gamestore.domain.utils;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public <T> boolean isValid(T entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> getViolations(T entity) {
        return this.validator.validate(entity);
    }

    @Override
    public <T> String getViolationsMessages(T entity) {
        return String.format(this.getViolations(entity)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("%n")));
    }
}
