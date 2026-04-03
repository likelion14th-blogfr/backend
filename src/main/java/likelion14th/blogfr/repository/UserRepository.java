package likelion14th.blogfr.repository;

import likelion14th.blogfr.domain.Comment;
import likelion14th.blogfr.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
}
