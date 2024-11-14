package com.lkh.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewRepository, Integer> {
    
}
