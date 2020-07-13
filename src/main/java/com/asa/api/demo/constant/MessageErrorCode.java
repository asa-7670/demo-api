package com.asa.api.demo.constant;

public enum MessageErrorCode {

    REQUIRED("error.required"),
    EMPTY("error.empty"),
    MIN("error.min"),
    MAX("error.max"),
    INVALID("error.invalid"),
    NOT_FOUND("error.not.found"),
    EXIST("error.exist");

    private final String code;
    MessageErrorCode(String code) {
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
}
