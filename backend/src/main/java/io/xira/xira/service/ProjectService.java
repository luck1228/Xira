package io.xira.xira.service;

import io.xira.xira.dto.ProjectDTO;
import io.xira.xira.model.Project;
import io.xira.xira.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAllByOrderByCreatedAtDesc();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project createProject(ProjectDTO dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setCreatedAt(LocalDateTime.now());

        return projectRepository.save(project);
    }

    public Project updateProject(Integer id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());

        return projectRepository.save(project);
    }

    public void deleteProject(Integer id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        projectRepository.delete(project);
    }

}
