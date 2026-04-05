package likelion14th.blogfr.service;

import jakarta.transaction.Transactional;
import likelion14th.blogfr.config.JwtTokenProvider;
import likelion14th.blogfr.domain.Article;
import likelion14th.blogfr.domain.User;
import likelion14th.blogfr.dto.request.AddArticleRequest;
import likelion14th.blogfr.dto.request.UpdateArticleRequest;
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
    private final JwtTokenProvider jwtTokenProvider;

    /* 게시글 생성 */
    @Transactional
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

    /* 게시글 수정 */
    @Transactional
    public ArticleResponse updateArticle(Long articleId, UpdateArticleRequest request, String authorization){
        Article article=articleRepository.findById(articleId)
                .orElseThrow(()->new CustomException(404,"해당 ID의 게시글을 찾을 수 없습니다."));

        Long userId = jwtTokenProvider.getUserIdFromAuthorization(authorization);

        if (!article.getUser().getId().equals(userId)) {
            throw new CustomException(403, "해당 글에 대한 수정 권한이 없습니다.");
        }

        article.update(request.getTitle(),request.getContent());
        return ArticleResponse.of(article);
    }

    /* 게시글 삭제 */
    @Transactional
    public void deleteArticle(Long articleId, String authorization){
        Article article=articleRepository.findById(articleId)
                .orElseThrow(()->new CustomException(404,"해당 ID의 게시글을 찾을 수 없습니다."));

        Long userId = jwtTokenProvider.getUserIdFromAuthorization(authorization);

        if (!article.getUser().getId().equals(userId)) {
            throw new CustomException(403, "해당 글에 대한 삭제 권한이 없습니다.");
        }

        articleRepository.delete(article);
    }
}
