package web.nuuunikita.project.firstrestproject.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


import java.util.List;

public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult){
        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> fieldsErrors = bindingResult.getFieldErrors();
        fieldsErrors.forEach(fieldError ->  errorMsg.append(fieldError.getField())
                .append(" - ")
                .append(fieldError.getDefaultMessage() == null ? fieldError.getCode() : fieldError.getDefaultMessage())//вставка конкретной ошибки у поля
                .append(";"));

        throw new MeasurementErrors(errorMsg.toString());
    }
}
