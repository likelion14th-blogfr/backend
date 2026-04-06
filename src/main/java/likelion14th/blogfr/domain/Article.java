package likelion14th.blogfr.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdAt;

    private int commentCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int likeCount;

    @Builder
    public Article(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.commentCount = 0;
        this.user = user;
        this.likeCount = 0;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseCommentCount() {
        this.commentCount += 1;
    }

    public void decreaseCommentCount() {
        this.commentCount -= 1;
    }

    public void increaseLikeCount() {
        this.likeCount += 1;
    }

    public void decreaseLikeCount() {
        this.likeCount -= 1;
    }
}
