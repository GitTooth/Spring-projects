package by.sender.boot.repository;

import by.sender.boot.entities.Project;
import by.sender.boot.entities.Task;
import by.sender.boot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByName(String taskName);
}
