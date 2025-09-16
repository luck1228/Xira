package io.xira.xira.service;

//import io.xira.xira.dto.TaskDTO;
import io.xira.xira.model.Task;
//import io.xira.xira.model.Project;
//import io.xira.xira.model.User;
import io.xira.xira.repository.TaskRepository;
import io.xira.xira.repository.ProjectRepository;
import io.xira.xira.repository.UserRepository;
import org.springframework.stereotype.Service;

//import java.time.LocalDateTime;
import java.util.List;
//import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
            ProjectRepository projectRepository,
            UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /*public Task createTask(TaskDTO dto) {
        Task task = new Task();
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());

        // Set project reference
        Optional<Project> projectOpt = projectRepository.findById(dto.getProjectId());
        projectOpt.ifPresent(task::setProject);

        // Set assignee reference if provided
        if (dto.getAssigneeId() != null) {
            userRepository.findById(dto.getAssigneeId())
                    .ifPresent(task::setAssignee);
        }

        // Defaults for optional fields
        task.setStatus(dto.getStatus() != null ? dto.getStatus() : "TO_DO");
        task.setPriority(dto.getPriority() != null ? dto.getPriority() : "MEDIUM");

        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }*/

    public Task updateTask(Integer id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        
        task.setName(taskDetails.getName());
        task.setDescription(taskDetails.getDescription());
        
        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

}
