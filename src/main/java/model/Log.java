package model;

import java.time.LocalDateTime;

public class Log {
    private String message;
    private Long dateTime;
    private String service;

    public Log() {
    }

    public Log(String message, Long dateTime, String service) {
        this.message = message;
        this.dateTime = dateTime;
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
