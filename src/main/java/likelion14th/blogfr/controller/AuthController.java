package likelion14th.blogfr.controller;

import likelion14th.blogfr.service.AuthService;
import likelion14th.blogfr.dto.response.ApiResponse;
import likelion14th.blogfr.dto.request.LoginRequest;
import likelion14th.blogfr.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);
        return new ApiResponse<>(true, HttpStatus.OK.value(), "로그인 성공", response);    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(){
        return new ApiResponse<>(true, HttpStatus.OK.value(), "로그아웃 되었습니다.", "logout success");
    }
}
