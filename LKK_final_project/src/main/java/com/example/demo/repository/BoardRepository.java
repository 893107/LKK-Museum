package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Board;
import com.example.demo.model.User;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
	List<Board> findAllByUserOrderByIdDesc(User user);

	Page<Board> findByCategory(Pageable pageable, String category);
}
