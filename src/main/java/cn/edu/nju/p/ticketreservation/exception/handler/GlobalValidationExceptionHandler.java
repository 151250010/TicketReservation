package cn.edu.nju.p.ticketreservation.exception.handler;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
@Component
@ResponseBody
public class GlobalValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        int fieldErrorCount = bindingResult.getFieldErrorCount();
        List<FieldError> errorList = bindingResult.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError error : errorList) {
            stringBuilder.append(error.getField())
                    .append(":")
                    .append(error.getRejectedValue().toString())
                    .append(" ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        String result = "ErrorCount : " + fieldErrorCount + " , ErrorMessage: " + stringBuilder.toString();
        return new BaseResult<>(result, ErrorCode.PARAMETER_VALIDATED_FAILED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResult handleConstraintViolationException(ConstraintViolationException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.PARAMETER_VALIDATED_FAILED);
    }
}
