package com.example.qtpq.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseObject {

    private int status = 0;
    private String message = "OK";
    private Object data = null;

    public ResponseObject() {
    }

    public ResponseObject(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
