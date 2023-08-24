package com.ceyentra.last.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BcryptExample {
    public static void main(String[] args) {
        String bcryptHash = "$2a$04$UlL06D4lO34s56VzfnXC7OFEKUzh1YsVFIvn3.0XTp23O07FdV0bm";

        Pattern bcryptPattern = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

        Matcher matcher = bcryptPattern.matcher(bcryptHash);
        System.out.println(matcher.matches());
        if (matcher.matches()) {
            System.out.println("Input matches the bcrypt pattern.");
        } else {
            System.out.println("Input does not match the bcrypt pattern.");
        }
    }
}
