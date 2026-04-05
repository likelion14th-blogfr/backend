package likelion14th.blogfr.service;

import jakarta.transaction.Transactional;
import likelion14th.blogfr.config.JwtTokenProvider;
import likelion14th.blogfr.domain.Article;
import likelion14th.blogfr.domain.ArticleLike;
import likelion14th.blogfr.domain.Comment;
import likelion14th.blogfr.domain.User;
import likelion14th.blogfr.dto.response.CommentResponse;
import likelion14th.blogfr.exception.CustomException;
import likelion14th.blogfr.repository.ArticleLikeRepository;
import likelion14th.blogfr.repository.ArticleRepository;
import likelion14th.blogfr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleLikeRepository articleLikeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /* 좋아요 생성 */
    @Transactional
    public void addLike(Long articleId, String authorization) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomException(404, "게시글이 존재하지 않습니다."));

        Long userId = jwtTokenProvider.getUserIdFromAuthorization(authorization);

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new CustomException(404, "사용자가 존재하지 않습니다."));

        if (articleLikeRepository.existsByArticleIdAndUserId(articleId, user.getId())) {
            throw new CustomException(400, "이미 좋아요를 누른 게시글입니다.");
        }

        ArticleLike articleLike = new ArticleLike(article, user);
        articleLikeRepository.save(articleLike);

        article.increaseLikeCount();
    }

    /* 좋아요 삭제 */
    @Transactional
    public void deleteLike(Long articleId, String authorization) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomException(404, "게시글이 존재하지 않습니다."));

        Long userId = jwtTokenProvider.getUserIdFromAuthorization(authorization);

        ArticleLike articleLike = articleLikeRepository.findByArticleIdAndUserId(articleId, userId)
                .orElseThrow(() -> new CustomException(404, "좋아요가 존재하지 않습니다."));

        articleLikeRepository.delete(articleLike);
        article.decreaseLikeCount();
    }
}
