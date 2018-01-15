package zoo.plus.task.currencyconverter.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormattedDateValidator implements ConstraintValidator<FormattedDate, String> {

    private SimpleDateFormat simpleDateFormat;

    @Override
    public void initialize(FormattedDate constraintAnnotation) {
        simpleDateFormat = new SimpleDateFormat(constraintAnnotation.pattern());
        simpleDateFormat.setLenient(false);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean isValid = true;

        if (!StringUtils.isEmpty(value)) {
            try {
                simpleDateFormat.parse(value);
            } catch (ParseException e) {
                isValid = false;
            }
        }

        return isValid;
    }
}
