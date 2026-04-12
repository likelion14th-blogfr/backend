package likelion14th.blogfr.controller;

import likelion14th.blogfr.config.JwtTokenProvider;
import likelion14th.blogfr.dto.request.UpdateArticleRequest;
import likelion14th.blogfr.dto.response.ArticleDetailResponse;
import likelion14th.blogfr.dto.response.ArticleResponse;
import likelion14th.blogfr.service.ArticleService;
import likelion14th.blogfr.dto.request.AddArticleRequest;
import likelion14th.blogfr.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final JwtTokenProvider jwtTokenProvider;

    /* 게시글 생성 */
    @PostMapping()
    public ResponseEntity<ApiResponse<ArticleResponse>> addArticle(
            @RequestHeader("Authorization") String authorization,
            @RequestBody AddArticleRequest request){
        jwtTokenProvider.validateAuthorizationHeader(authorization);

        ArticleResponse response = articleService.addArticle(request, authorization);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, HttpStatus.CREATED.value(), "게시글 등록 성공",response)
        );
    }

    /* 게시글 수정 */
    @PutMapping("/{articleId}")
    public ResponseEntity<ApiResponse<ArticleResponse>> updateArticle(
            @RequestHeader("Authorization") String authorization,
            @RequestBody UpdateArticleRequest request,
            @PathVariable Long articleId
            ){
        jwtTokenProvider.validateAuthorizationHeader(authorization);
        articleService.updateArticle(articleId, request, authorization);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, HttpStatus.OK.value(), "게시글 수정 성공", null));
    }

    /* 게시글 삭제 */
    @DeleteMapping("/{articleId}")
    public ResponseEntity<ApiResponse<String>> deleteArticle(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long articleId
            ){
        jwtTokenProvider.validateAuthorizationHeader(authorization);
        articleService.deleteArticle(articleId, authorization);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, HttpStatus.OK.value(), "게시글 삭제 성공", null));
    }
    /* 게시글 전체 조회 */
    @GetMapping()
    public ResponseEntity<ApiResponse<List<ArticleResponse>>> getAllArticles(){
        List<ArticleResponse> response = articleService.getAllArticles();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, HttpStatus.OK.value(), "전체 게시글 조회 성공", response)
        );
    }

    /* 게시글 개별 조회 */
    @GetMapping("/{articleId}")
    public ResponseEntity<ApiResponse<ArticleDetailResponse>> getArticle(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long articleId
            ){
        jwtTokenProvider.validateAuthorizationHeader(authorization);
        ArticleDetailResponse response =articleService.getArticle(articleId, authorization);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, HttpStatus.OK.value(), "게시글 조회 성공", response));
    }

}
