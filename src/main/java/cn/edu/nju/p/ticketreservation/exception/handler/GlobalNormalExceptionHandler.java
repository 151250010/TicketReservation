package cn.edu.nju.p.ticketreservation.exception.handler;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.exception.*;
import io.lettuce.core.RedisConnectionException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.format.DateTimeParseException;
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

    @ExceptionHandler(NumberFormatException.class)
    public BaseResult handleNumberFormatException(NumberFormatException e) {
        return new BaseResult<>("Number Format Exception : " + e.getMessage(), ErrorCode.INTEGER_FORMAT_EXCEPTION);
    }

    @ExceptionHandler(RedisConnectionException.class)
    public BaseResult handleRedisConnectionException(RedisConnectionException e) {
        return new BaseResult<>("Redis Connection Exception : " + e.getMessage(), ErrorCode.REDIS_CONNECT_REFUSED);
    }

    @ExceptionHandler(DateNotAvailableException.class)
    public BaseResult handleRedisConnectionException(DateNotAvailableException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.DATE_NOT_AVAILABLE);
    }

    @ExceptionHandler(SiteNullException.class)
    public BaseResult handleSiteNullException(SiteNullException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.SITE_NOT_FOUND);
    }

    @ExceptionHandler(PlanNotExistException.class)
    public BaseResult handlePlanNullException(PlanNotExistException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.PLAN_NOT_FOUND);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public BaseResult handleDateTimeParseException(DateTimeParseException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.DATE_NOT_AVAILABLE);
    }

    @ExceptionHandler(UserNotRegisterException.class)
    public BaseResult handleUserNotRegister(UserNotRegisterException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.USER_NOT_REGISTER);
    }

    @ExceptionHandler(MoneyNotEnoughException.class)
    public BaseResult moneyNotEnough(MoneyNotEnoughException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.MONEY_NOT_ENOUGH);
    }

    @ExceptionHandler(OrderCancelledException.class)
    public BaseResult orderHasCancelled(OrderCancelledException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.ORDER_HAS_CANCELLED);
    }

    @ExceptionHandler({SeatNotEnoughException.class})
    public BaseResult seatNotEnoughException(SeatNotEnoughException e) {
        return new BaseResult<>(e.getMessage(), ErrorCode.SEAT_NOT_ENOUGH);
    }
}
