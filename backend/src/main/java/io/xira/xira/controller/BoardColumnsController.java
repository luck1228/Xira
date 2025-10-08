package io.xira.xira.controller;

import io.xira.xira.dto.BoardColumnTaskDTO;
import io.xira.xira.dto.BoardColumnsDTO;
import io.xira.xira.model.BoardColumns;
import io.xira.xira.repository.BoardColumnsRepository;
import io.xira.xira.service.BoardColumnsService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board-columns")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardColumnsController {

    private final BoardColumnsService boardColumnsService;
    private final BoardColumnsRepository boardColumnsRepository;

    public BoardColumnsController(BoardColumnsService boardColumnsService) {
        this.boardColumnsService = boardColumnsService;
        this.boardColumnsRepository = boardColumnsService.getBoardColumnsRepository();
    }

    @GetMapping
    public List<BoardColumns> getAllBoardColumns() {
        return boardColumnsService.getAllBoardColumns();
    }

    @GetMapping("/project/{projectId}")
    public List<BoardColumnTaskDTO> getColumnsWithTasks(@PathVariable Integer projectId) {
        return boardColumnsRepository.findColumnsWithTasksByProjectId(projectId);
    }

    @PostMapping
    public BoardColumns createBoardColumn(@Valid @RequestBody BoardColumnsDTO boardColumnsDTO) {
        return boardColumnsService.createBoardColumn(boardColumnsDTO);
    }

    @PutMapping("/{projectId}")
    public BoardColumns updateBoardColumn(@PathVariable Integer projectId, @RequestBody BoardColumns boardColumnDetails) {
        return boardColumnsService.updateBoardColumn(projectId, boardColumnDetails);
    }

    @DeleteMapping("/{projectId}")
    public void deleteBoardColumn(@PathVariable Integer projectId) {
        boardColumnsService.deleteBoardColumn(projectId);
    }

}
