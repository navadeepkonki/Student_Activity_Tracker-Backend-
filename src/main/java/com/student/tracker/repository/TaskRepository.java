package com.student.tracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.student.tracker.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
// interface because , we dont write full class code , JPA repository generates at runtime 
//so we write just the blueprint
    List<Task> findByUserId(Long userId);
}
 
