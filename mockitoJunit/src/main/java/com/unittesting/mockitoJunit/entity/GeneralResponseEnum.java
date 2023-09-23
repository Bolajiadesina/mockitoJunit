package com.unittesting.mockitoJunit.entity;

import org.springframework.stereotype.Service;


public enum GeneralResponseEnum {

    NOT_FOUND("55", "Resource not found");

    String code, message;

    GeneralResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getValue(GeneralResponseEnum generalResponseEnum) {
        return generalResponseEnum.message;
    }

    public String getCode() {
        return this.code;
    }

    public static String getCode(GeneralResponseEnum generalResponseEnum) {
        return generalResponseEnum.code;
    }

    public String getMessage() {
        return this.message;
    }

    public static String getMessage(GeneralResponseEnum generalResponseEnum) {
        return generalResponseEnum.message;
    }
}
