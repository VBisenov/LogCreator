package model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

public class Log {
    private String message;
    private Time dateTime = new Time(new Date().getTime());
    private String service;

    public Log() {
    }

    public Log(String message, Time dateTime, String service) {
        this.message = message;
        this.dateTime = dateTime;
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = dateTime.toString() + " [" + service + "] " + message;
    }

    public Time getDateTime() {
        return dateTime;
    }

    public void setDateTime(Time dateTime) {
        this.dateTime = dateTime;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
