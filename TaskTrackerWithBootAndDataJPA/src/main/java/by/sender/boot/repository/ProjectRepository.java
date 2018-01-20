package by.sender.boot.repository;

import by.sender.boot.entities.Project;
import by.sender.boot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByName(String projectName);
    List<Project> findByUser(User user);
}
