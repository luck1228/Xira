package io.xira.xira.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BoardColumnsDTO {

    @NotBlank(message = "Project ID is required")
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be at most 255 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Position is required")
    @Column(name = "position", nullable = false)
    private Integer position;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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



}
