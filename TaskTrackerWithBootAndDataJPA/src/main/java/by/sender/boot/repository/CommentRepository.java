package by.sender.boot.repository;

import by.sender.boot.entities.Comment;
import by.sender.boot.entities.Project;
import by.sender.boot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByUser(User user);
}
