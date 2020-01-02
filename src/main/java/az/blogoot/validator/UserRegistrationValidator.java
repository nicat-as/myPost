package az.blogoot.validator;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import az.blogoot.constants.VallidatorConstants;
import az.blogoot.domain.RegistrationForm;
import az.blogoot.service.ValidationService;

/**
 * UserRegistrationValidator
 */
@Component
public class UserRegistrationValidator implements Validator {

    @Autowired
    private ValidationService validationService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RegistrationForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RegistrationForm form = (RegistrationForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "user.regForm.name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "user.regForm.surName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.regForm.email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.regForm.password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "user.regForm.passwordConfirmation.required");

        if (!errors.hasErrors()) {

            if (!form.getName().matches("[a-zA-Z]*")) {
                errors.rejectValue("name", "user.regForm.name.alpha");
            }

            if (!GenericValidator.isInRange(form.getName().length(), VallidatorConstants.FIRST_NAME_MIN_LEN,
                    VallidatorConstants.FIRST_NAME_MAX_LEN)) {
                errors.rejectValue("name", "user.regForm.name.length",
                        new Object[] { VallidatorConstants.FIRST_NAME_MIN_LEN, VallidatorConstants.FIRST_NAME_MAX_LEN },
                        "name length min max");
            }

            if (!form.getLastname().matches("[a-zA-Z]*")) {
                errors.rejectValue("lastname", "user.regForm.surName.alpha");
            }

            if (!GenericValidator.isInRange(form.getLastname().length(), VallidatorConstants.LAST_NAME_MIN_LEN,
                    VallidatorConstants.LAST_NAME_MAX_LEN)) {
                errors.rejectValue("lastname", "user.regForm.surName.length",
                        new Object[] { VallidatorConstants.LAST_NAME_MIN_LEN, VallidatorConstants.LAST_NAME_MAX_LEN },
                        "lastname length min max");
            }

            if (!GenericValidator.isEmail(form.getEmail())) {
                errors.rejectValue("email", "user.regForm.email.true");
            }

            if (validationService.isDuplicate(form.getEmail())) {
                errors.rejectValue("email", "user.regForm.email.duplicate");
            }

            if (!checkPassword(form.getPassword())) {
                errors.rejectValue("password", "user.regForm.password.valid");
            }

            if (!GenericValidator.isInRange(form.getPassword().length(), VallidatorConstants.PASSWORD_MIN_LEN,
                    VallidatorConstants.PASSWORD_MAX_LEN)) {

                errors.rejectValue("password", "user.regForm.password.length",
                        new Object[] { VallidatorConstants.PASSWORD_MIN_LEN, VallidatorConstants.PASSWORD_MAX_LEN },
                        "password length min max");
            }

            if (!form.getPassword().equals(form.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "user.regForm.passwordConfirmation.required");
            }

        }

    }

    private static boolean checkPassword(String password) {
        boolean hasNum = false;
        boolean hasUpper = false;
        boolean hasLower = false;
        char c;

        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasNum = true;
            }
        }

        if (hasLower && hasUpper && hasNum) {
            return true;
        }

        return false;
    }

}