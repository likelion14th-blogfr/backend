package likelion14th.blogfr.repository;

import likelion14th.blogfr.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}