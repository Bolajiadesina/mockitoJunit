package com.unittesting.mockitoJunit.unitTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.unittesting.mockitoJunit.utils.StringUtils;

 class StringUtilsTest {

    private StringUtils stringUtils;

    @BeforeEach
    void setup() {
        stringUtils = new StringUtils();
    }

    @ParameterizedTest()
    @CsvSource({ ",FALSE",
    "null, TRUE",
    "uyuyuyi,TRUE" })
    public void checkIfStringIsEmptyOrNull(String word) {

        // when

        Boolean isValid = StringUtils.isEmptyBlank(word);

        // then
        assertFalse(isValid.equals("FALSE"));

    }

}
