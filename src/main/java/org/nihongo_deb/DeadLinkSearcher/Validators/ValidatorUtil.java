package org.nihongo_deb.DeadLinkSearcher.Validators;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidatorUtil {
    private final Validator validator;

    public ValidatorUtil(Validator validator) {
        this.validator = validator;
    }

    public <T> boolean isValid(T req){
        if (req != null){
            Set<ConstraintViolation<T>> result = validator.validate(req);
            return result.isEmpty();
        } else return false;
    }
}
