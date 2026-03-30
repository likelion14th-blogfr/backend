package likelion14th.blogfr.user;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String createToken(Long userId) {
        return "token-" + userId;
    }
}