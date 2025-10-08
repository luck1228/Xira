package io.xira.xira.repository;

import io.xira.xira.dto.BoardColumnTaskDTO;
import io.xira.xira.model.BoardColumns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardColumnsRepository extends JpaRepository<BoardColumns, Integer>{

    @Query("""
                SELECT new io.xira.xira.dto.BoardColumnTaskDTO(
                    b.id,
                    b.task.name,
                    b.task.description,
                    b.position
                )
                FROM BoardColumns b
                WHERE b.project.id = :projectId
                ORDER BY b.position
            """)
    List<BoardColumnTaskDTO> findColumnsWithTasksByProjectId(@Param("projectId") Integer projectId);

    List<BoardColumns> findAllByOrderByProjectIdDesc();
    List<BoardColumns> findByProjectIdOrderByPositionAsc(Integer projectId);


}
