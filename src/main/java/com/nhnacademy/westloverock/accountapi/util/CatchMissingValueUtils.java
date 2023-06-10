package com.nhnacademy.westloverock.accountapi.util;

import com.nhnacademy.westloverock.accountapi.exception.MissingValueException;
import org.springframework.validation.BindingResult;

public class CatchMissingValueUtils {
    private CatchMissingValueUtils() {
        throw new IllegalStateException();
    }

    public static void throwMissingValue(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MissingValueException("요청에 대한 필드값이 부족합니다.");
        }
    }
}
