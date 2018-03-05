package cn.edu.nju.p.ticketreservation.exception_handler;

import cn.edu.nju.p.ticketreservation.base.BaseResult;
import cn.edu.nju.p.ticketreservation.base.ErrorCode;
import cn.edu.nju.p.ticketreservation.exception.VerifyCodeHasExistedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Component
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(VerifyCodeHasExistedException.class)
    public BaseResult handleVerifyCodeExisted(VerifyCodeHasExistedException e) {
        String message = e.getMessage();
        return new BaseResult<>(message, ErrorCode.VERIFY_CODE_EXISTED);
    }
}
