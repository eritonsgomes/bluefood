package br.com.softblue.bluefood.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    public static String encrypt(String str) {
        if(StringUtil.isEmpty(str)) {
            return null;
        }

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder.encode(str);
    }

}
