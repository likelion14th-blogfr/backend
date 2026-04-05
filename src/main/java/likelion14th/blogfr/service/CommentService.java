package likelion14th.blogfr.service;

import jakarta.transaction.Transactional;
import likelion14th.blogfr.config.JwtTokenProvider;
import likelion14th.blogfr.domain.Article;
import likelion14th.blogfr.domain.Comment;
import likelion14th.blogfr.domain.User;
import likelion14th.blogfr.dto.request.AddCommentRequest;
import likelion14th.blogfr.dto.response.CommentResponse;
import likelion14th.blogfr.exception.CustomException;
import likelion14th.blogfr.repository.ArticleRepository;
import likelion14th.blogfr.repository.CommentRepository;
import likelion14th.blogfr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /* 댓글 작성 */
    @Transactional
    public CommentResponse addComment(Long articleId, AddCommentRequest request){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomException(404, "게시글이 존재하지 않습니다."));

        User user = userRepository.findById(1L)
                .orElseThrow(()-> new CustomException(404, "사용자가 존재하지 않습니다."));

        Comment comment = new Comment(article, request.getContent(), user);

        commentRepository.save(comment);

        article.increaseCommentCount();

        return CommentResponse.of(comment);
    }

    /* 댓글 삭제 */
    @Transactional
    public void deleteComment(Long commentId, String authorization){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new CustomException(404, "댓글이 존재하지 않습니다."));

        Long userId = jwtTokenProvider.getUserIdFromAuthorization(authorization);

        if (!comment.getUser().getId().equals(userId)) {
            throw new CustomException(403, "해당 댓글에 대한 삭제 권한이 없습니다.");
        }

        Article article = comment.getArticle();
        commentRepository.delete(comment);
        article.decreaseCommentCount();
    }
}
