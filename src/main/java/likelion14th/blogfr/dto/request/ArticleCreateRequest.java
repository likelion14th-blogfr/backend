package likelion14th.blogfr.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleCreateRequest {
    private String title;
    private String content;
}