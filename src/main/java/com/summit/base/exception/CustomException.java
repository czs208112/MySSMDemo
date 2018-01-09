package com.summit.base.exception;

//系统自定义异常处理类,针对预期的异常，需要在程序中抛出此类的异常
public class CustomException extends Exception {

    //异常信息
    private String message;

    //异常代码
    private long exceptionCode;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(String message, long exceptionCode) {
        this.message = message;
        this.exceptionCode = exceptionCode;
    }

    public long getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(long exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
