package likelion14th.blogfr.service;

import likelion14th.blogfr.domain.Article;
import likelion14th.blogfr.domain.User;
import likelion14th.blogfr.dto.request.AddArticleRequest;
import likelion14th.blogfr.dto.response.ArticleResponse;
import likelion14th.blogfr.exception.CustomException;
import likelion14th.blogfr.repository.ArticleRepository;
import likelion14th.blogfr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    /* 게시글 생성 */
    public ArticleResponse addArticle(AddArticleRequest request){

        User user = userRepository.findById(1L)
                .orElseThrow(()-> new CustomException(404, "사용자를 찾을 수 없습니다."));

        Article article = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .likeCount(0)
                .commentCount(0)
                .build();

        Article saved =  articleRepository.save(article);
        return ArticleResponse.of(saved);
    }
}
