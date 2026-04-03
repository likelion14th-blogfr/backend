package likelion14th.blogfr.service;

import likelion14th.blogfr.config.JwtTokenProvider;
import likelion14th.blogfr.exception.CustomException;
import likelion14th.blogfr.domain.User;
import likelion14th.blogfr.repository.UserRepository;
import likelion14th.blogfr.dto.request.LoginRequest;
import likelion14th.blogfr.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByLoginId(request.loginId())
                .orElseThrow(() -> new CustomException(404, "존재하지 않는 사용자입니다."));

        if (!user.getPassword().equals(request.password())) {
            throw new CustomException(401, "비밀번호가 틀렸습니다.");
        }

        String accessToken = jwtTokenProvider.createToken(user.getId());

        return new LoginResponse(
                accessToken,
                user.getId(),
                user.getLoginId()
        );
    }
}
