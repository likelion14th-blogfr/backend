package likelion14th.blogfr.dto.response;

import likelion14th.blogfr.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private Long articleId;
    private String author;
    private LocalDateTime createdAt;

    public static CommentResponse of(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .articleId(comment.getArticle().getId())
                .author(comment.getUser().getNickname())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
