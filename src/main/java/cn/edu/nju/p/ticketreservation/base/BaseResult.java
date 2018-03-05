package cn.edu.nju.p.ticketreservation.base;

import java.io.Serializable;

public class BaseResult<T> implements Serializable {

    private T data;
    private int errorCode;

    public BaseResult(T data, int errorCode) {
        this.data = data;
        this.errorCode = errorCode;
    }

    public BaseResult(T data) {
        this.data = data;
        this.errorCode = ErrorCode.SUCCESS;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
