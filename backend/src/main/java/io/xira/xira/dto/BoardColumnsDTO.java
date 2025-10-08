package io.xira.xira.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BoardColumnsDTO {

    @NotBlank(message = "Project ID is required")
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @NotBlank(message = "Task ID is required")
    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    @NotBlank(message = "Position is required")
    @Column(name = "position", nullable = false)
    private Integer position;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }



}
