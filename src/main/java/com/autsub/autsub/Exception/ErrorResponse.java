package com.autsub.autsub.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class ErrorResponse {

    private List<String> message;

    @JsonFormat(pattern = "dd-mm-yyyy")
    LocalTime localTime;

    public ErrorResponse(List<String> messages) {
        this.message = messages;
        this.localTime = LocalTime.now();
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
