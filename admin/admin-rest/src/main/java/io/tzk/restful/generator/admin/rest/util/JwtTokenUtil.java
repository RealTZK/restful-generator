package io.tzk.restful.generator.admin.rest.util;

import io.tzk.restful.generator.admin.api.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    public String generateAccessToken(User user) {
        return user.toString();
    }

    public boolean validate(String token) {
        return true;
    }

    public String getUsername(String token) {
        return "tzk";
    }
}
