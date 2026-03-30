package likelion14th.blogfr.user.dto;

public record LoginResponse(
        String accessToken,
        Long userId,
        String username
) {
}
