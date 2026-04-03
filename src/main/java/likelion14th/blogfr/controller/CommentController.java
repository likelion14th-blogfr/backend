package likelion14th.blogfr.controller;

import likelion14th.blogfr.config.JwtTokenProvider;
import likelion14th.blogfr.dto.request.AddCommentRequest;
import likelion14th.blogfr.dto.response.ApiResponse;
import likelion14th.blogfr.dto.response.CommentResponse;
import likelion14th.blogfr.repository.CommentRepository;
import likelion14th.blogfr.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final JwtTokenProvider jwtTokenProvider;

    /* 댓글 생성 */
    @PostMapping("/{articleId}")
    public ResponseEntity<ApiResponse<CommentResponse>> addComment(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @PathVariable Long articleId, @RequestBody AddCommentRequest request
    ){
        jwtTokenProvider.validateAuthorizationHeader(authorization);

        CommentResponse response = commentService.addComment(articleId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, HttpStatus.CREATED.value(), "댓글 등록 성공", response));
    }

    /* 댓글 삭제 */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<String>> deleteComment(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @PathVariable Long commentId
    ){
        jwtTokenProvider.validateAuthorizationHeader(authorization);
        commentService.deleteComment(commentId);

        return ResponseEntity.ok(
                new ApiResponse<>(true, HttpStatus.OK.value(), "댓글 삭제 성공", null)
        );
    }
}
