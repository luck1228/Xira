package io.xira.xira.model;

import jakarta.persistence.*;

@Entity
@Table(name = "board_columns")
public class BoardColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String name;

    @Column(name = "position", nullable = false)
    private Integer position;

    // Constructors
    public BoardColumns() {
    }

    public BoardColumns(Integer id, String name, Integer position, Project project) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.project = project;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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