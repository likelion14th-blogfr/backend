package likelion14th.blogfr.article;

import likelion14th.blogfr.article.dto.ArticleCreateRequest;
import likelion14th.blogfr.auth.dto.LoginRequest;
import likelion14th.blogfr.auth.dto.LoginResponse;
import likelion14th.blogfr.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    @PostMapping()
    public ApiResponse<String> createArticle(@RequestBody ArticleCreateRequest request){
        return ApiResponse.of(true, HttpStatus.OK.value(), "게시글 등록 성공", null);
    }

}
