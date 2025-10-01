package io.xira.xira.repository;

import io.xira.xira.model.BoardColumns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardColumnsRepository extends JpaRepository<BoardColumns, Integer>{

    List<BoardColumns> findAllByOrderByProjectIdDesc();
    List<BoardColumns> findByProjectId(Integer projectId);

}
