package com.urlshorterner.urlshorterner.Util;

import org.springframework.stereotype.Component;

@Component
public class Base62Encoder {

    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String encode(Long number) {
        StringBuilder sb = new StringBuilder();

        while (number > 0) {
            int remainder = (int) (number % 62);
            sb.append(CHAR_SET.charAt(remainder));
            number = number / 62;
        }

        return sb.reverse().toString();
    }



}
