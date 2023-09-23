package com.unittesting.mockitoJunit.service;

import com.unittesting.mockitoJunit.entity.GeneralResponseEnum;
import com.unittesting.mockitoJunit.utils.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceNotFoundException extends RuntimeException{

    private GeneralResponseEnum exceptionType;
    private String message;
    private String code;

    public ResourceNotFoundException (GeneralResponseEnum exceptionType, String message) {
        super((!StringUtils.isEmptyBlank(message)) ? message : exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.message = message;
    }
}
