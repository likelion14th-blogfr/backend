package likelion14th.blogfr.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ArticleDetailResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private Integer totalLikes;
    private Integer totalComments;
    private List<CommentResponse> comments;
    private boolean isLiked;
}
