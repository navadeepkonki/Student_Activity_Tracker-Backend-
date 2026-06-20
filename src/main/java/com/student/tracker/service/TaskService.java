package com.student.tracker.service;

import com.student.tracker.model.Task;
import com.student.tracker.model.User;
import com.student.tracker.repository.TaskRepository;
import com.student.tracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task assignTaskToUser(Long userId, Task task) {
        User user = userRepository.findById(userId).orElse(null);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task completeTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(true);
            return taskRepository.save(task);
        }
        return null;
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task updateTask(Long id, Task updatedTask) {
    Task task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));

    task.setTitle(updatedTask.getTitle());
    task.setDescription(updatedTask.getDescription());
    task.setDeadline(updatedTask.getDeadline());
    task.setCompleted(updatedTask.isCompleted());

    return taskRepository.save(task);
}




}

