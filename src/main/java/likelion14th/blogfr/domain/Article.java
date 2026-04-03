package likelion14th.blogfr.domain;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.text.html.parser.DTD;
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
    public Article(String title, String content, LocalDateTime createdAt, int likeCount,int commentCount, User user) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.commentCount = commentCount;
        this.user = user;
        this.likeCount = likeCount;
    }

    public void increaseCommentCount() {
        this.commentCount += 1;
    }

    public void decreaseCommentCount() {
        this.commentCount -= 1;
    }
}
