package io.xira.xira.service;

import io.xira.xira.dto.TaskDTO;
import io.xira.xira.model.Task;
import io.xira.xira.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task createTask(TaskDTO dto) {
        Task task = new Task();
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setProjectId(dto.getProjectId());
        if (dto.getAssigneeId() != null) {
            task.setAssigneeId(dto.getAssigneeId());
        }

        // Defaults for optional fields
        task.setStatus(dto.getStatus() != null ? dto.getStatus() : "TO_DO");
        task.setPriority(dto.getPriority() != null ? dto.getPriority() : "MEDIUM");

        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    public Task updateTask(Integer id, Task taskDetails) {
        System.out.println("Updating task with ID: " + id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        
        task.setName(taskDetails.getName());
        task.setDescription(taskDetails.getDescription());
        if (taskDetails.getAssigneeId() != null) {
            task.setAssigneeId(taskDetails.getAssigneeId());
        }
        System.out.println("New status: " + taskDetails.getStatus());
        if( taskDetails.getStatus() != null) {
            task.setStatus(taskDetails.getStatus());
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

}
