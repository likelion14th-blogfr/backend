package likelion14th.blogfr.controller;

import likelion14th.blogfr.config.JwtTokenProvider;
import likelion14th.blogfr.dto.response.ApiResponse;
import likelion14th.blogfr.dto.response.ArticleResponse;
import likelion14th.blogfr.service.ArticleService;
import likelion14th.blogfr.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final JwtTokenProvider jwtTokenProvider;
    private final LikeService likeService;

    /* 좋아요 생성 */
    @PostMapping("/{articleId}")
    public ResponseEntity<ApiResponse<ArticleResponse>> addLike(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long articleId
            ){
        jwtTokenProvider.validateAuthorizationHeader(authorization);
        likeService.addLike(articleId, authorization);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, HttpStatus.CREATED.value(), "좋아요 생성 성공", null));

    }

    /* 좋아요 취소 */
    @DeleteMapping("{articleId}")
    public ResponseEntity<ApiResponse<ArticleResponse>> deleteLike(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long articleId
            ){
        jwtTokenProvider.validateAuthorizationHeader(authorization);
        likeService.deleteLike(articleId, authorization);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, HttpStatus.OK.value(), "좋아요 취소 성공", null));
    }
}
