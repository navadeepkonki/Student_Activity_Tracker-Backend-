package com.student.tracker.controller;

import com.student.tracker.model.Task;
import com.student.tracker.service.TaskService;

import jakarta.validation.Valid;

import com.student.tracker.repository.TaskRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task with id " + id + " deleted successfully.";
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable Long id,
            @Valid @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @PutMapping("/complete/{id}")
    public Task completeTask(@PathVariable Long id) {
        return taskService.completeTask(id);
    }

    @GetMapping("user/{id}/tasks")
    public List<Task> getTasksByUserId(@PathVariable Long id) {
        return taskService.getTasksByUserId(id);
    }

}
