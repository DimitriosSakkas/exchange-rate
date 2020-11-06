package com.bk.exchangerate.util;

import java.util.Date;

public class ErrorMessage {

    private int statusCode;
    private Date timestamp;
    private String title;
    private String description;

    public ErrorMessage(int statusCode, Date timestamp, String title, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.title = title;
        this.description = description;
    }
}
