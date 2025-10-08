package io.xira.xira.service;

import io.xira.xira.dto.BoardColumnsDTO;
import io.xira.xira.model.BoardColumns;
import io.xira.xira.model.Project;
import io.xira.xira.model.Task;
import io.xira.xira.repository.BoardColumnsRepository;
import io.xira.xira.repository.ProjectRepository;
import io.xira.xira.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardColumnsService {

    private final BoardColumnsRepository boardColumnsRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    public BoardColumnsService(BoardColumnsRepository boardColumnsRepository) {
        this.boardColumnsRepository = boardColumnsRepository;
    }

    public List<BoardColumns> getAllBoardColumns() {
        return boardColumnsRepository.findAllByOrderByProjectIdDesc();
    }

    public List<BoardColumns> getBoardColumns(Integer id) {
        return boardColumnsRepository.findByProjectIdOrderByPositionAsc(id);
    }

    public BoardColumns getBoardColumnById(Integer id) {
        return boardColumnsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board Column not found with id: " + id));
    }

    public BoardColumns createBoardColumn(BoardColumnsDTO boardColumnsDTO) {
        BoardColumns boardColumn = new BoardColumns();

        Project project = projectRepository.findById(boardColumnsDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + boardColumnsDTO.getProjectId()));
        Task task = taskRepository.findById(boardColumnsDTO.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + boardColumnsDTO.getTaskId()));

        boardColumn.setPosition(boardColumnsDTO.getPosition());
        boardColumn.setProject(project);
        boardColumn.setTask(task);
        return boardColumnsRepository.save(boardColumn);
    }

    public BoardColumns updateBoardColumn(Integer id, BoardColumns boardColumnDetails) {
        BoardColumns boardColumn = boardColumnsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board Column not found with id: " + id));

        boardColumn.setTask(boardColumnDetails.getTask());
        boardColumn.setPosition(boardColumnDetails.getPosition());

        return boardColumnsRepository.save(boardColumn);
    }

    public void deleteBoardColumn(Integer id) {
        BoardColumns boardColumn = boardColumnsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board Column not found with id: " + id));
        boardColumnsRepository.delete(boardColumn);
    }

    public BoardColumnsRepository getBoardColumnsRepository() {
        return boardColumnsRepository;
    }

}
