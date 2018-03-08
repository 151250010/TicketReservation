package cn.edu.nju.p.ticketreservation.exception.handler;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.exception.VerifyCodeHasExistedException;
import cn.edu.nju.p.ticketreservation.exception.VipScoreNotRecognizedException;
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
public class GlobalNormalExceptionHandler {

    @ExceptionHandler(VerifyCodeHasExistedException.class)
    public BaseResult handleVerifyCodeExisted(VerifyCodeHasExistedException e) {
        String message = e.getMessage();
        return new BaseResult<>(message, ErrorCode.VERIFY_CODE_EXISTED);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public BaseResult handleDuplicateKeyException(DuplicateKeyException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.DAO_DUPLICATE_KEY);
    }

    @ExceptionHandler(MailSendException.class)
    public BaseResult handleAddressFailedException(MailSendException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.MAIL_ADDRESS_NOT_EXIST);
    }

    @ExceptionHandler(VipScoreNotRecognizedException.class)
    public BaseResult handleVipScoreException(VipScoreNotRecognizedException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.VIP_SCORE_NOT_AVAILABLE);
    }
}
