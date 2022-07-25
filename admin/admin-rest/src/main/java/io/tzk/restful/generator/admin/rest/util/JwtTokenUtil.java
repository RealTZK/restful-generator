package io.tzk.restful.generator.admin.rest.util;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    public boolean validate(String token) {
        return true;
    }

    public String getUsername(String token) {
        return "tzk";
    }
}
