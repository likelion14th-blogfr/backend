package likelion14th.blogfr.repository;

import likelion14th.blogfr.domain.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
    boolean existsByArticleIdAndUserId(Long articleId, Long userId);
    Optional<ArticleLike> findByArticleIdAndUserId(Long articleId, Long userId);
    void deleteAllByArticleId(Long articleId);
}
