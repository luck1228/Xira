package io.xira.xira.service;

import io.xira.xira.dto.BoardColumnsDTO;
import io.xira.xira.model.BoardColumns;
import io.xira.xira.repository.BoardColumnsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardColumnsService {

    private final BoardColumnsRepository boardColumnsRepository;

    public BoardColumnsService(BoardColumnsRepository boardColumnsRepository) {
        this.boardColumnsRepository = boardColumnsRepository;
    }

    public List<BoardColumns> getAllBoardColumns() {
        return boardColumnsRepository.findAllByOrderByProjectIdDesc();
    }

    public BoardColumns createBoardColumn(BoardColumnsDTO boardColumnsDTO) {
        BoardColumns boardColumn = new BoardColumns();
        boardColumn.setName(boardColumnsDTO.getName());
        return boardColumnsRepository.save(boardColumn);
    }

    public BoardColumns updateBoardColumn(Integer id, BoardColumns boardColumnDetails) {
        BoardColumns boardColumn = boardColumnsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board Column not found with id: " + id));

        boardColumn.setName(boardColumnDetails.getName());

        return boardColumnsRepository.save(boardColumn);
    }

    public void deleteBoardColumn(Integer id) {
        BoardColumns boardColumn = boardColumnsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board Column not found with id: " + id));
        boardColumnsRepository.delete(boardColumn);
    }

}
