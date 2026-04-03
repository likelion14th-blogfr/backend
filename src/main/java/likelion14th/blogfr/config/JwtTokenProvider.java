package likelion14th.blogfr.config;

import likelion14th.blogfr.exception.CustomException;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String createToken(Long userId) {
        return "token-" + userId;
    }

    public void validateAuthorizationHeader(String authorization) {
        if (authorization == null || authorization.isBlank()) {
            throw new CustomException(401, "토큰이 없습니다.");
        }

        if (!authorization.startsWith("Bearer ")) {
            throw new CustomException(401, "토큰 형식이 올바르지 않습니다.");
        }

        String token = authorization.substring(7);

        if (!isValidToken(token)) {
            throw new CustomException(401, "유효하지 않은 토큰입니다.");
        }
    }

    private boolean isValidToken(String token) {
        if (token == null || !token.startsWith("token-")) {
            return false;
        }

        String userIdPart = token.substring(6);

        try {
            Long.parseLong(userIdPart);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    }
