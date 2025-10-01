package io.xira.xira.controller;

import io.xira.xira.dto.BoardColumnsDTO;
import io.xira.xira.model.BoardColumns;
import io.xira.xira.service.BoardColumnsService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board-columns")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardColumnsController {

    private final BoardColumnsService boardColumnsService;

    public BoardColumnsController(BoardColumnsService boardColumnsService) {
        this.boardColumnsService = boardColumnsService;
    }

    @GetMapping
    public List<BoardColumns> getAllBoardColumns() {
        return boardColumnsService.getAllBoardColumns();
    }

    @GetMapping("/project/{id}")
    public List<BoardColumns> getBoardColumns(@PathVariable Integer id) {
        return boardColumnsService.getBoardColumns(id);
    }

    @PostMapping
    public BoardColumns createBoardColumn(@Valid @RequestBody BoardColumnsDTO boardColumnsDTO) {
        return boardColumnsService.createBoardColumn(boardColumnsDTO);
    }

    @PutMapping("/{id}")
    public BoardColumns updateBoardColumn(@PathVariable Integer id, @RequestBody BoardColumns boardColumnDetails) {
        return boardColumnsService.updateBoardColumn(id, boardColumnDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBoardColumn(@PathVariable Integer id) {
        boardColumnsService.deleteBoardColumn(id);
    }

}
