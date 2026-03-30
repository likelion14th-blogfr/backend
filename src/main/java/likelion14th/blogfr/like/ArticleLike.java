package likelion14th.blogfr.like;

import jakarta.persistence.*;
import likelion14th.blogfr.article.Article;
import likelion14th.blogfr.user.User;

@Entity
public class ArticleLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
