package likelion14th.blogfr.config;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String createToken(Long userId) {
        return "token-" + userId;
    }
}