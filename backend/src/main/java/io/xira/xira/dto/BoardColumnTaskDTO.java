package io.xira.xira.dto;

public class BoardColumnTaskDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer position;

    public BoardColumnTaskDTO(Integer id, String name, String description, Integer position) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.position = position;
    }

    // getters & setters
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}