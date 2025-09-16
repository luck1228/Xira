package io.xira.xira.repository;

import io.xira.xira.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
      
    List<Task> findAllByOrderByCreatedAtDesc();

    List<Task> findByProjectId(Long projectId); // Find tasks by project
    List<Task> findByAssigneeId(Long assigneeId); // Find tasks assigned to a user
}
