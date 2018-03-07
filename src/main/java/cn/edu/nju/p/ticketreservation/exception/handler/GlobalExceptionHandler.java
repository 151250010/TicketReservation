package cn.edu.nju.p.ticketreservation.exception.handler;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.exception.VerifyCodeHasExistedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@Component
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(VerifyCodeHasExistedException.class)
    public BaseResult handleVerifyCodeExisted(VerifyCodeHasExistedException e) {
        String message = e.getMessage();
        return new BaseResult<>(message, ErrorCode.VERIFY_CODE_EXISTED);
    }

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

    @ExceptionHandler(DuplicateKeyException.class)
    public BaseResult handleDuplicateKeyException(DuplicateKeyException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.DAO_DUPLICATE_KEY);
    }

    @ExceptionHandler(MailSendException.class)
    public BaseResult handleAddressFailedException(MailSendException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.MAIL_ADDRESS_NOT_EXIST);
    }

}
