package likelion14th.blogfr.controller;

import likelion14th.blogfr.service.ArticleService;
import likelion14th.blogfr.dto.request.ArticleCreateRequest;
import likelion14th.blogfr.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    /* 게시글 생성 */
    @PostMapping()
    public ApiResponse<String> createArticle(@RequestBody ArticleCreateRequest request){
        return new ApiResponse<>(true, HttpStatus.OK.value(), "게시글 등록 성공");
    }

}
