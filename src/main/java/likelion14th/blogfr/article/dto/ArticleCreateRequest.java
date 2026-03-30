package likelion14th.blogfr.article.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleCreateRequest {
    private String title;
    private String content;
}