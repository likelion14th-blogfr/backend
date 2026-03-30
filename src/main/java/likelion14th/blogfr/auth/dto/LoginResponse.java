package likelion14th.blogfr.auth.dto;

public record LoginResponse(
        String accessToken,
        Long userId,
        String username
) {
}
