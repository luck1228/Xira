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

    @Column(nullable = false)
    private String name;

    @Column(name = "position", nullable = false)
    private Integer position;

    // Constructors
    public BoardColumns() {
    }

    public BoardColumns(Integer id, Integer projectId, String name, Integer position) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.position = position;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}