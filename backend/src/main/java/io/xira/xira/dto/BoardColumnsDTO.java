package io.xira.xira.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BoardColumnsDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be at most 255 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Position is required")
    @Column(name = "position", nullable = false)
    private Integer position;

    @NotBlank(message = "Project ID is required")
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

}
