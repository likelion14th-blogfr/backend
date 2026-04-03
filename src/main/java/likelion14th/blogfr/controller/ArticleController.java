package likelion14th.blogfr.controller;

import likelion14th.blogfr.config.JwtTokenProvider;
import likelion14th.blogfr.service.ArticleService;
import likelion14th.blogfr.dto.request.ArticleCreateRequest;
import likelion14th.blogfr.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final JwtTokenProvider jwtTokenProvider;

    /* 게시글 생성 */
    @PostMapping()
    public ResponseEntity<ApiResponse<String>> createArticle(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestBody ArticleCreateRequest request){
        jwtTokenProvider.validateAuthorizationHeader(authorization);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, HttpStatus.CREATED.value(), "게시글 등록 성공")
        );
    }

    /* 게시글 전체 조회 */
//    @GetMapping()
//    public ResponseEntity<ApiResponse>

}
