package fr.formation.jwtauthserver.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.formation.jwtauthserver.services.CustomUserDetailsService;

public class UniqueUsernameValidator
	implements ConstraintValidator<UniqueUsername, String> {

    private final CustomUserDetailsService service;

    protected UniqueUsernameValidator(CustomUserDetailsService userService) {
	service = userService;
    }

    @Override
    public boolean isValid(String username,
	    ConstraintValidatorContext constraintValidatorContext) {
	if (null == username || username.isEmpty()) {
	    return true;
	}
	return service.usernameIsUnique(username);
    }
}
