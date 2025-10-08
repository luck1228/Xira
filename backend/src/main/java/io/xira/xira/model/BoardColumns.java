package io.xira.xira.model;

import jakarta.persistence.*;

@Entity
@Table(name = "board_columns")
public class BoardColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    @Column(name = "position", nullable = false)
    private Integer position;

    // Constructors
    public BoardColumns() {
    }

    public BoardColumns(Integer id, Integer projectId, Integer taskId, Integer position) {
        this.id = id;
        this.projectId = projectId;
        this.taskId = taskId;
        this.position = position;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}