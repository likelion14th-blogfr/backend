package likelion14th.blogfr.repository;

import likelion14th.blogfr.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
