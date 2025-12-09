package io.xira.xira.repository;

import io.xira.xira.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
      
    List<Task> findAllByOrderByCreatedAtDesc();

    List<Task> findByProjectId(Integer projectId); // Find tasks by project
    List<Task> findByAssigneeId(Integer assigneeId); // Find tasks assigned to a user
}
