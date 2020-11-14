package com.gamestore.gamestore.domain.utils;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {

    <T> boolean isValid(T entity);

    <T> Set<ConstraintViolation<T>> getViolations(T entity);

    <T> String getViolationsMessages(T entity);
}
