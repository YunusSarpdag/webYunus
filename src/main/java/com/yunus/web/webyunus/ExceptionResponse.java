package com.yunus.web.webyunus;

import java.util.Date;

public class ExceptionResponse {
    private Date date;
    private int returnCode;
    private String message;

    public ExceptionResponse(Date date, int returnCode, String message) {
        this.date = date;
        this.returnCode = returnCode;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getMessage() {
        return message;
    }
}
