package io.xira.xira.model;

import jakarta.persistence.*;

@Entity
@Table(name = "board_columns")
public class BoardColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Project project;

    @Column(name = "position", nullable = false)
    private Integer position;

    @OneToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id", nullable = false)
    private Task task;

    // Constructors
    public BoardColumns() {
    }

    public BoardColumns(Integer id, Project project, Task task, Integer position) {
        this.id = id;
        this.project = project;
        this.task = task;
        this.position = position;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}