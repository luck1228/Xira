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

    @Query(value = """
                SELECT
                    p2.id AS id,
                    p2.name AS name,
                    p2.description AS description,
                    p2.status AS status,
                    p1.position AS position,
                    p1.project_id AS projectId
                FROM board_columns p1
                JOIN tasks p2 ON p2.id = p1.task_id
                WHERE p1.project_id = :projectId
                ORDER BY p2.status DESC, p1.position ASC
            """, nativeQuery = true)
    List<Object[]> findColumnsWithTasks(@Param("projectId") Integer projectId);

    List<BoardColumns> findAllByOrderByProjectIdDesc();
    List<BoardColumns> findByProjectIdOrderByPositionAsc(Integer projectId);


}
