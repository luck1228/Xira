package io.xira.xira.dto;


public class BoardColumnTaskDTO {
    private Integer id;
    private String name;
    private String description;
    private String status;
    private Integer position;
    private Integer projectId;

    public BoardColumnTaskDTO(Integer id, String name, String description, String status,
                              Integer position, Integer projectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.position = position;
        this.projectId = projectId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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