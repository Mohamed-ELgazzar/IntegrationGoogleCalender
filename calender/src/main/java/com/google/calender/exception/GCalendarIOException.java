package com.google.calender.exception;

import java.io.IOException;

public class GCalendarIOException extends IOException {

    public GCalendarIOException(String message) {
        super(message);
    }

    public GCalendarIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
