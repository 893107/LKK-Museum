package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
