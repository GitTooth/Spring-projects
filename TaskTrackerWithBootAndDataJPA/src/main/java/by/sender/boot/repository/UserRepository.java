package by.sender.boot.repository;

import by.sender.boot.entities.Project;
import by.sender.boot.entities.Task;
import by.sender.boot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsername(String firstName);
    List<User> findById(int id);

    @Query("select u from User u where ?1 not member u.projects and u.isDev = 1")
    List<User> findNotAssignedOnProject(Project project);

    @Query("select u from User u where ?1 member u.projects and u.isDev = 1")
    List<User> findAssignedOnProject(Project project);

    @Query("select u from User u where ?1 not member u.tasks and u.isDev = 1")
    List<User> findNotAssignedOnTask(Task task);

    @Query("select u from User u where ?1 member u.tasks and u.isDev = 1")
    List<User> findAssignedOnTask(Task task);
}
